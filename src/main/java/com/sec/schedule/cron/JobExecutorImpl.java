package com.sec.schedule.cron;

import org.springframework.core.task.TaskExecutor;

import com.sec.schedule.entity.TaskFact;

public class JobExecutorImpl {
    public TaskExecutor taskExecutor;

    public JobExecutorImpl(TaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
    }

    //任务执行线程
    private class JobExecuteTask implements Runnable{
        private TaskFact taskFact;

        public JobExecuteTask(TaskFact taskFact) {
            this.taskFact = taskFact;
        }

		@Override
		public void run() {
            //TODO:任务执行的逻辑未实现
			System.out.println("执行任务中");
		}
    }

    public void executeTask(TaskFact taskFact){
        taskExecutor.execute(new JobExecuteTask(taskFact));
    }

}