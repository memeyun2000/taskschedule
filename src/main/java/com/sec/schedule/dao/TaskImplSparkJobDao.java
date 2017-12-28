package com.sec.schedule.dao;

import com.sec.schedule.entity.TaskImplSparkJob;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskImplSparkJobDao extends JpaRepository<TaskImplSparkJob,String> {
    
}