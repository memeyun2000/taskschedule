package com.sec.schedule.cron;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.service.TaskExecuteService;
import com.sec.schedule.service.TaskGenerateService;

@Component
public class JobSchedule {

    //任务队列 消费者类使用 生产者类不会使用
    //目前生产者 和 消费者 并没有实际的线程交互，两者之间的沟通都是通过数据库交互
    //虽然生产者也可以访问任务队列，并且线程安全
    private BlockingQueue<TaskFact> taskQueue;
    
    @Autowired
    private TaskGenerateService taskGenerateService;
    @Autowired
    private TaskExecuteService taskExecuteService;

    public JobSchedule() {
        this.taskQueue = new ArrayBlockingQueue<TaskFact>(100);
    }

    /**
     * 消费者
     * 寻找需要执行的任务 并把需要执行的任务放入任务执行器中执行
     */
    // @Scheduled(fixedRate=10000)
    public void jobQueueGenerateAndExecute() throws Exception {
        taskExecuteService.execute(taskQueue);
    }

    /**
     * 生产者
     * 生成每日的任务列表到taskfact表
     */
    // @Scheduled(fixedRate = 10000)
    public void jobScheduleGenerate() throws Exception {
        taskGenerateService.execute();
    }
}