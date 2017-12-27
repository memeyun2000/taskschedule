package com.sec.schedule.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.sec.schedule.App;
import com.sec.schedule.dao.TaskFactDao;
import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.service.task.TaskService;

@Component
public class JobExecutorImpl { 
    @Autowired
    @Qualifier("taskExecutor") 
    public TaskExecutor taskExecutor;
    @Autowired
    private TaskFactDao taskFactDao;

    public JobExecutorImpl(TaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
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
            System.out.println("开始任务：statdt:" + taskFact.getId().getStatDt() +",taskid:" + taskFact.getId().getTaskId());

            task = (TaskService)App.ctx.getBean(taskFact.getTaskType());
            //TODO:任务执行的逻辑未实现
            String result = task.executeTask(taskFact);

            //任务执行完成 根据任务执行的状态 保存执行结果
            //TODO: 
            // taskFact.setStatus(result);
            // taskFactDao.save(taskFact);

            System.out.println("status:" + result);
			System.out.println("执行任务结束:DATE[" + taskFact.getId().getStatDt() + "],TASKID[" + taskFact.getId().getTaskId() + "]");
		}
    }

    public void executeTask(TaskFact taskFact){
        taskExecutor.execute(new JobExecuteTask(taskFact));
    }

}