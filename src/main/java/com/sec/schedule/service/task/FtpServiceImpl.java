package com.sec.schedule.service.task;

import org.springframework.stereotype.Service;

import com.sec.schedule.dict.TaskStatus;
import com.sec.schedule.entity.TaskFact;

@Service("Ftp")
public class FtpServiceImpl implements TaskService {

	@Override
	public String executeTask(TaskFact taskFact) {
		return TaskStatus.SUCCESS;
	}

}