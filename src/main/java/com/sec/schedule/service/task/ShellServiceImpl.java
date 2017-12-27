package com.sec.schedule.service.task;

import org.springframework.stereotype.Service;

import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;

@Service("Shell")
public class ShellServiceImpl implements TaskService {

	@Override
	public String executeTask(TaskFact taskFact) {
		try {
			Thread.sleep(20000);	
		} catch(Exception e) {
			
		}
		
		return TaskStatus.SUCCESS;
	}

}