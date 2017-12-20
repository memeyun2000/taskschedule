package com.sec.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskInfo;

public interface TaskInfoDao extends JpaRepository<TaskInfo,String>{
    List<TaskInfo> findById(String taskId);
    List<TaskInfo> findAll();

    TaskInfo findTaskById(String taskId);

}