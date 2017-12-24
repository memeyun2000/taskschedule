package com.sec.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sec.schedule.cron.JobExecutorImpl;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.entity.TaskDepend;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.entity.TaskInfo;
import com.sec.schedule.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@Component
public class TaskExecuteService{

    @Autowired
    private JobExecutorImpl jobExecutor;

    @Autowired
    private TaskFactDao taskFactDao;
    
    public void execute(BlockingQueue<TaskFact> taskQueue) throws Exception{
        //计算队列不为空，放入计算任务
        if (taskQueue.size() > 0) {
            int taskQueueSize = taskQueue.size();
            System.out.println("计算队列水位线：" + taskQueue.size());
            for (int i = 0; i < taskQueueSize; i++) {
                jobExecutor.executeTask(taskQueue.poll());
            }
        } else {
            //TODO：查找需要计算的任务
            calculateReadyTask(taskQueue);
            System.out.println("找到需要计算的任务");
        }
    }


    //计算满足依赖可以执行的任务
    private void calculateReadyTask(BlockingQueue<TaskFact> taskQueue) {
        //未执行的任务列表
        List<TaskFact> status2TaskFactList;
        //任务列表 含时间依赖信息
        List<TaskInfo> taskInfoList;
        //任务依赖信息
        List<TaskDepend> taskDependList;
        //涉及到的任务依赖 时间依赖的 已计算完成的任务
        List<TaskFact> dependTaskFactList;

        status2TaskFactList = taskFactDao.findByStatus("2");
        List<String> tmpDtList = getDependStatDtList(status2TaskFactList);
        dependTaskFactList = taskFactDao.findTaskFactListByStatDts(tmpDtList);
    }

    public List<String> getDependStatDtList(List<TaskFact> taskFactList) {
        // List l = taskFactList.stream().map(task -> task.getStatus());
        List<String> statDtList = taskFactList
            .stream()
            // .map(task ->  task.getId().getStatDt() + "" + task.getGranularity())
            .map(task -> {
                List<String> tmp = new ArrayList<String>();
                tmp.add(task.getId().getStatDt());
                tmp.add(task.getGranularity());
                return tmp;
            })
            .distinct()
            .map(task -> {
                String statDt = task.get(0);
                String granularity = task.get(1);
                if(granularity.equalsIgnoreCase("D")) {
                    statDt = DateUtils.dateStrAddDate(statDt, "d", 1);
                } else if(granularity.equalsIgnoreCase("M")) {
                    statDt = DateUtils.dateStrAddDate(statDt, "m", 1);
                } else if(granularity.equalsIgnoreCase("S")) {
                    statDt = DateUtils.dateStrAddDate(statDt, "m", 3);
                } else if(granularity.equalsIgnoreCase("HY")) {
                    statDt = DateUtils.dateStrAddDate(statDt, "m", 6);
                } else if(granularity.equalsIgnoreCase("Y")) {
                    statDt = DateUtils.dateStrAddDate(statDt, "y", 1);
                } else {
                    statDt = "";
                }
                return statDt;
            })
            .distinct()
            .collect(Collectors.toList());
            
            // .map(granularity -> );

        return statDtList;
    }
}