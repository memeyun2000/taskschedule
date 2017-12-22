package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;

/**
 * TaskFactDao
 */
public interface TaskFactDao extends JpaRepository<TaskFact,CompositeIdTaskFact>{
    
    @Query("select count(1) from task_fact where stat_dt = ?1")
    Long findCountByStatDt(String statDt);
}