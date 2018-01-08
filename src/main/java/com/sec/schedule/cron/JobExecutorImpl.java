package com.sec.schedule.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.sec.schedule.App;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.service.task.TaskService;

import java.util.Date;

@Component
public class JobExecutorImpl { 
    private static Logger logger = LoggerFactory.getLogger(JobExecutorImpl.class);

    @Autowired
    @Qualifier("taskExecutor") 
    public ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private TaskFactDao taskFactDao;

    public JobExecutorImpl(ThreadPoolTaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
        this.taskExecutor.setKeepAliveSeconds(Integer.MAX_VALUE);
    }

    //任务执行线程
    private class JobExecuteTask implements Runnable{
        private TaskFact taskFact;
        private TaskService task;


        public JobExecuteTask(TaskFact taskFact) {
            this.taskFact = taskFact;
        }

		@Override
		public void run() {
            
            //TODO:如果需要任务强制停止 在这里写逻辑 让后面的任务不要跑批了
            logger.info("开始任务,statdt:{},,taskid:", taskFact.getId().getStatDt(),taskFact.getId().getTaskId());
            task = (TaskService)App.ctx.getBean(taskFact.getTaskType());
            
            //设置任务正在计算
            taskFact.setStatus(TaskStatus.RUNNING);
            taskFact.setCalBeginTime(new Date());
            taskFactDao.save(taskFact);

            //任务执行
            String result = task.executeTask(taskFact);

            //任务执行完成 根据任务执行的状态 保存执行结果

             taskFact.setStatus(result);
//            taskFact.setStatus(TaskStatus.PENDING);
            taskFact.setCalEndTime(new Date());
            taskFactDao.save(taskFact);

			logger.info("执行任务结束:DATE:[{}],TASKID:[{}],STATUS:[{}]",taskFact.getId().getStatDt(),taskFact.getId().getTaskId(),result);
		}
    }

    public int getThreadPriority(){
        return taskExecutor.getThreadPriority();
    }
    public int getCorePoolSize() {
        
        return taskExecutor.getCorePoolSize();
    }
    public int getActiveCount() {
        
        return taskExecutor.getActiveCount();
    }
    public int getKeepAliveSeconds() {
        return taskExecutor.getKeepAliveSeconds();
    }
    public void executeTask(TaskFact taskFact){
        taskExecutor.execute(new JobExecuteTask(taskFact));
    }

}