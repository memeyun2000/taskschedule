package com.sec.schedule.cron;

import org.springframework.core.task.TaskExecutor;

import com.sec.schedule.App;
import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.service.task.TaskService;

public class JobExecutorImpl {  
    public TaskExecutor taskExecutor;

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
            task = (TaskService)App.ctx.getBean(taskFact.getTaskType());
            //TODO:任务执行的逻辑未实现
            String result = task.executeTask(taskFact);
            System.out.println("status:" + result);
			System.out.println("执行任务中:DATE[" + taskFact.getId().getStatDt() + "],TASKID[" + taskFact.getId().getTaskId() + "]");
		}
    }

    public void executeTask(TaskFact taskFact){
        taskExecutor.execute(new JobExecuteTask(taskFact));
    }

}