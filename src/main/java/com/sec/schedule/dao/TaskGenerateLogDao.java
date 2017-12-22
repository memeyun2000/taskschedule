package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sec.schedule.entity.TaskGenerateLog;

/**
 * TasKGenerateLogDao
 */
public interface TaskGenerateLogDao extends JpaRepository<TaskGenerateLog,String> {
    
    /**
     * 获取未生成任务列表的最大日期
     */
    @Query("select min(statDt) from task_generate_log where status = ?1")
    String findMinStatDtByStatus(Boolean status);

    @Query("select max(statDt) from task_generate_log")
    String findMaxStatDt();
}