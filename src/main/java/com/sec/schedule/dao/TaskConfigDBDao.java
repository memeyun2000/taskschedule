package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskConfigDB;

public interface TaskConfigDBDao extends JpaRepository<TaskConfigDB,String> {
    
}