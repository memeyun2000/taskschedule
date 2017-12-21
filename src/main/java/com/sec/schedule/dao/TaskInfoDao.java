package com.sec.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskInfo;

public interface TaskInfoDao extends JpaRepository<TaskInfo,String>{
    //使用spring-data 在查询方法名称命名上有“强约束”

    // List<TaskInfo> findByTaskId(String taskId);
    // List<TaskInfo> findAll();
    TaskInfo findByTaskId(String taskId);

}