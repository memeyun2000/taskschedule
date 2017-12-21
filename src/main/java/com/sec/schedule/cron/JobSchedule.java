package com.sec.schedule.cron;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sec.schedule.entity.TaskFact;

@Component
public class JobSchedule{
    private BlockingQueue<TaskFact> taskQueue;
    @Autowired
    private JobExecutorImpl jobExecutor;

    public JobSchedule(){
        this.taskQueue = new ArrayBlockingQueue<TaskFact>(100);
    }

    /**
     * 消费者
     * 寻找需要执行的任务 并把需要执行的任务放入任务执行器中执行
     */
    @Scheduled(fixedRate=5000)
    public void jobQueueGenerateAndExecute() throws Exception{
        //计算队列不为空，放入计算任务
        if(taskQueue.size() > 0) {
            int taskQueueSize = taskQueue.size();
            System.out.println("计算队列水位线："+taskQueue.size());
            for (int i=0 ; i<taskQueueSize ;i++) {
                jobExecutor.executeTask(taskQueue.poll());
            }
        } else {
            //TODO：查找需要计算的任务
            taskQueue.put(new TaskFact());
            taskQueue.put(new TaskFact());
            taskQueue.put(new TaskFact());
            System.out.println("找到需要计算的任务");
        }
    }

    /**
     * 生产者
     * 生成每日的任务列表到taskfact表
     */
    @Scheduled(fixedRate=6000)
    public void jobScheduleGenerate() throws Exception{

    }
}