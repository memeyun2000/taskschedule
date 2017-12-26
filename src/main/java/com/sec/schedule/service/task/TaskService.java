package com.sec.schedule.service.task;

import com.sec.schedule.entity.TaskFact;

public interface TaskService {
    public String executeTask(TaskFact taskFact);
}