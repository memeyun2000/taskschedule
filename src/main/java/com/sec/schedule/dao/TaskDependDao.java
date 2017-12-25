package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskDepend;
import com.sec.schedule.model.CompositeIdTaskDepend;

/**
 * TaskDependDao
 */
public interface TaskDependDao extends JpaRepository<TaskDepend,CompositeIdTaskDepend>{

} 