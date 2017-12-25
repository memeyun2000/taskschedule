package com.sec.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dao.TaskGenerateLogDao;
import com.sec.schedule.dao.TaskInfoDao;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.entity.TaskGenerateLog;
import com.sec.schedule.entity.TaskInfo;
import com.sec.schedule.model.CompositeIdTaskFact;
import com.sec.schedule.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生产者服务类
 */
@Component
public class TaskGenerateService {
    
    //任务生成记录表
    @Autowired
    TaskGenerateLogDao taskGenerateLogDao;
    @Autowired
    TaskInfoDao taskInfoDao;
    @Autowired
    TaskFactDao taskFactDao;

    //生成t+x天任务 t为当前时间
    private static final int GENER_TASK_DATE_NUM = 3;


    public void execute(){

        // 获取最小未生成任务的日期
        String _minStatusFalseStatDt = taskGenerateLogDao.findMinStatDtByStatus(false);
        // 前置检查 检查日期是否满足生成条件
        if(!isStatDtGenerate(_minStatusFalseStatDt)) {
            return;
        }
        
        //-- 生成任务 --//
        generateTask(_minStatusFalseStatDt);
    }

    /**
     * 判断该日期能否满足生成任务的条件
     */
    private boolean isStatDtGenerate(String statDt){
        // 如果没有生成日期，则说明系统是第一次跑批，自动创建一个当前日期
        if(statDt == null) {
            this.createGenerateDtStatusFalse(DateUtils.getCurrentDateStr());
            System.out.println("初始化...任务队列第一次跑批，设置" + DateUtils.getCurrentDateStr() + "为首次数据日期。如需修改任务生成日期，请将：task_generate_log日期提前");
            return false;
        }
        // 最小未生成任务日期 大于 t+3 则 不用生成任务
        // 超过三天 
        if(DateUtils.diffTowDate(DateUtils.stringToDateShort(statDt), 
                                 DateUtils.addDate(new Date(), "d", GENER_TASK_DATE_NUM - 1)) > 0) {
            System.out.println("没有任务需要生成，休息");
            return false;
        }
        createGenerateDtStatusFalse();
        return true;
    }


    /**
     * 提前生成控制表
     */
    private void createGenerateDtStatusFalse() {
        String _maxDt = taskGenerateLogDao.findMaxStatDt();
        _maxDt = DateUtils.dateStrAddDay(_maxDt, 1);
        createGenerateDtStatusFalse(_maxDt);
    }

    /**
     * 创建待生成的日期 待生成日期 为 generateTaskDateNum + 1
     * 从指定日期 到 当前时间 + x
     */
    private void createGenerateDtStatusFalse(String statDt){
        //不能超过的最大生成日期 不能超过今天 + x 
        String _maxStatDt = DateUtils.dateStrAddDay(DateUtils.getCurrentDateStr(), GENER_TASK_DATE_NUM + 1);
        //生成日期
        String _statDt = statDt;
        List<TaskGenerateLog> tasklogList = new ArrayList<TaskGenerateLog>();
        while(DateUtils.diffTowDateStr(_maxStatDt, _statDt) > 0) {
            TaskGenerateLog tasklog = new TaskGenerateLog();
            tasklog.setStatDt(_statDt);
            tasklog.setStatus(false);
            tasklog.setCreateTime(new Date());
            tasklogList.add(tasklog);

            _statDt = DateUtils.dateStrAddDay(_statDt, 1);
        }
        taskGenerateLogDao.save(tasklogList);
    }

    /**
     * 生成任务
     * 逻辑： 把taskinfo 拷贝一份副本到 taskfact
     */
    private void generateTask(String statDt) {
        
        //生成任务 生成当天已未生成的任务，已生成的任务就不生成了
        //只生成日期对应粒度的任务
        List<String> granularityList = checkStatDtGranularity(statDt);
        List<TaskInfo> taskInfoList = taskInfoDao.findOutGenerateTask(statDt,granularityList);
        List<TaskFact> taskFactList = new ArrayList<TaskFact>();
        Date statDateTime = DateUtils.StringToDateTime(statDt);

        for (TaskInfo taskInfo : taskInfoList) {
            String taskId = taskInfo.getTaskId();
            String taskType = taskInfo.getTaskType();
            Long execDelay = taskInfo.getExecDelay();
            String granularity = taskInfo.getGranularity();
            Boolean isDependTime = taskInfo.getIsDependTime();

            TaskFact taskfact = new TaskFact();
            CompositeIdTaskFact taskFactId = new CompositeIdTaskFact();
            taskFactId.setStatDt(statDt);
            taskFactId.setTaskId(taskId);
            taskfact.setId(taskFactId);
            taskfact.setTaskType(taskType);
            taskfact.setCreatetime(new Date());
            taskfact.setIsDependTime(isDependTime);

            Date allowCalTime = DateUtils.addDate(statDateTime, "s", execDelay.intValue());
            taskfact.setAllowCalTime(allowCalTime);

            taskfact.setGranularity(granularity);
            taskfact.setStatus("2");

            taskFactList.add(taskfact);
        }
        taskFactDao.save(taskFactList);

        //更新生成状态
        System.out.println("更新状态");
        TaskGenerateLog taskGenerateLog = taskGenerateLogDao.findOne(statDt);
        taskGenerateLog.setStatus(true);
        taskGenerateLog.setUpdateTime(new Date());
        taskGenerateLogDao.save(taskGenerateLog);
        System.out.println("任务日期："+ statDt +",已生成");
    }

    /**
     *  检查数据日期是什么粒度的 生成任务需要满足粒度要求
     */
    private List<String> checkStatDtGranularity(String statDt) {
        Date _statDate = DateUtils.stringToDateShort(statDt);
        Date monthEnd = DateUtils.getMonthEnd(_statDate);
        Date seasonEnd = DateUtils.getSeasonEnd(_statDate);
        Date halfYearEnd = DateUtils.getHalfYearEnd(_statDate);
        Date yearEnd = DateUtils.getYearEnd(_statDate);
        List<String> granularityList = new ArrayList<String>();
        if(_statDate == monthEnd) {
            granularityList.add("M");
        }
        if(_statDate == seasonEnd) {
            granularityList.add("S");
        }
        if(_statDate == halfYearEnd) {
            granularityList.add("HY");
        }
        if(_statDate == yearEnd) {
            granularityList.add("Y");
        }
        granularityList.add("D");

        return granularityList;
    }
}