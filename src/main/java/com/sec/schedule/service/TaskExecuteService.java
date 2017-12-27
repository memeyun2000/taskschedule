package com.sec.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sec.schedule.cron.JobExecutorImpl;
import com.sec.schedule.dao.TaskDependDao;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dao.TaskInfoDao;
import com.sec.schedule.entity.TaskDepend;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.entity.TaskInfo;
import com.sec.schedule.model.CompositeIdTaskFact;
import com.sec.schedule.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@Component
public class TaskExecuteService{

    @Autowired
    private JobExecutorImpl jobExecutor;

    @Autowired
    private TaskFactDao taskFactDao;

    @Autowired
    private TaskDependDao taskDependDao;

    @Autowired
    private TaskInfoDao taskInfoDao;
    
    public void execute(BlockingQueue<TaskFact> taskQueue) throws Exception{
        //计算队列不为空，放入计算任务
        if (taskQueue.size() > 0) {
            int taskQueueSize = taskQueue.size();
            System.out.println("计算队列水位线：" + taskQueue.size());
            for (int i = 0; i < taskQueueSize; i++) {
                TaskFact taskFact = taskQueue.poll();
                System.out.println("---- task:" + taskFact.getId().getTaskId() + ",statDt:" + taskFact.getId().getStatDt());
                jobExecutor.executeTask(taskFact);
            }
        } else {
            calculateReadyTask(taskQueue);
            System.out.println("查找计算的任务");
        }
    }


    //计算满足依赖可以执行的任务
    private void calculateReadyTask(BlockingQueue<TaskFact> taskQueue) throws Exception {
        //未执行的任务列表
        List<TaskFact> status2TaskFactList = taskFactDao.findByStatus("2");
        //任务列表 含时间依赖信息
        List<TaskInfo> taskInfoList = taskInfoDao.findAll();
        //任务依赖信息
        List<TaskDepend> taskDependList = taskDependDao.findAll();
        //涉及到的任务依赖 时间依赖的 任务
        Map<CompositeIdTaskFact,String> dependTaskFactMap;
        List<String> tmpDtList = getDependStatDtList(status2TaskFactList);
        if(tmpDtList.size() <=0 ) {
            //没有任务，不执行操作
            return;
        }
        dependTaskFactMap = taskFactDao.findTaskFactListByStatDts(tmpDtList)
                                        .stream()
                                        .collect(Collectors.toMap(TaskFact::getId, TaskFact::getStatus));

        //任务依赖转换为Map方便查询
        Map<String,List<TaskDepend>> taskDependMap = taskDependList
            .stream()
            .collect(Collectors.groupingBy(depend -> depend.getId().getTaskId() , Collectors.toList()));
        //时间依赖转换为Map方便查询
        Map<String,String> taskTimeDependMap = taskInfoList
            .stream()
            .filter(taskinfo -> taskinfo.getIsDependTime() == true)
            .collect(Collectors.toMap(TaskInfo::getTaskId, TaskInfo::getGranularity));

        // 循环待执行任务列表 找到满足依赖的任务
        List<TaskFact> taskfactReadyCalList = status2TaskFactList.stream()
            .filter(taskfact -> {
                String taskId = taskfact.getId().getTaskId();
                String statDt = taskfact.getId().getStatDt();
                //执行时间不满足条件
                if(DateUtils.diffTowDate(taskfact.getAllowCalTime(), new Date()) > 0 ) {
                    return false;
                }


                //判断任务依赖
                List<TaskDepend> dependTaskList = taskDependMap.getOrDefault(taskId, new ArrayList<TaskDepend>());
                //找到任务依赖的任务是否有未执行完的
                long dependTaskStatus2count = dependTaskList
                    .stream()
                    .filter(dependTask -> {
                        CompositeIdTaskFact taskFactId = new CompositeIdTaskFact();
                        taskFactId.setStatDt(statDt);
                        taskFactId.setTaskId(dependTask.getId().getDependTaskId());
                        return !dependTaskFactMap.getOrDefault(taskFactId, "4").equals("4");
                    }).count();
                if (dependTaskStatus2count > 0) {
                    return false ;
                }

                //判断时间依赖
                String taskGranularity = taskTimeDependMap.getOrDefault(taskId, "");
                String timeDependDateStr = "";
                if(taskGranularity.equalsIgnoreCase("Y")) {
                    timeDependDateStr = DateUtils.dateStrAddDate(statDt, "y", -1);
                } else if(taskGranularity.equalsIgnoreCase("S")) {
                    timeDependDateStr = DateUtils.dateStrAddDate(statDt, "s", -1);
                } else if(taskGranularity.equalsIgnoreCase("M")) {
                    timeDependDateStr = DateUtils.dateStrAddDate(statDt, "m", -1);
                } else if(taskGranularity.equalsIgnoreCase("D")) {
                    timeDependDateStr = DateUtils.dateStrAddDate(statDt, "d", -1);
                }
                CompositeIdTaskFact taskFactId2 = new CompositeIdTaskFact();
                taskFactId2.setStatDt(timeDependDateStr);
                taskFactId2.setTaskId(taskId);
                if( !dependTaskFactMap.getOrDefault(taskFactId2, "4").equals("4")){
                    return false;
                }
                return true;
            }).sorted((task1,task2) -> new Long(DateUtils.diffTowDate(task1.getAllowCalTime(), task2.getAllowCalTime())).intValue() )
            .collect(Collectors.toList());

        //放入任务执行队列中
        for (TaskFact taskFact : taskfactReadyCalList) {
            taskQueue.put(taskFact);
        }
    }

    public List<String> getDependStatDtList(List<TaskFact> taskFactList) {
        List<String> statDtList = taskFactList
            .stream()
            .map(task -> {
                List<String> tmp = new ArrayList<String>();
                tmp.add(task.getId().getStatDt());
                tmp.add(task.getGranularity());
                return tmp;
            })
            .distinct()
            .flatMap(task -> {
                String statDt = task.get(0);
                String _statDt = statDt;
                String granularity = task.get(1);
                List<String> list = new ArrayList<String>();
                if(granularity.equalsIgnoreCase("D")) {
                    _statDt = DateUtils.dateStrAddDate(statDt, "d", -1);
                } else if(granularity.equalsIgnoreCase("M")) {
                    _statDt = DateUtils.dateStrAddDate(statDt, "m", -1);
                } else if(granularity.equalsIgnoreCase("S")) {
                    _statDt = DateUtils.dateStrAddDate(statDt, "m", -3);
                } else if(granularity.equalsIgnoreCase("HY")) {
                    _statDt = DateUtils.dateStrAddDate(statDt, "m", -6);
                } else if(granularity.equalsIgnoreCase("Y")) {
                    _statDt = DateUtils.dateStrAddDate(statDt, "y", -1);
                }
                list.add(statDt);
                list.add(_statDt);
                return list.stream();
            })
            .distinct()
            .collect(Collectors.toList());
        
        return statDtList;
    }
}