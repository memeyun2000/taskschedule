package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskConfig;

public interface TaskConfigDao extends JpaRepository <TaskConfig,String>{

}