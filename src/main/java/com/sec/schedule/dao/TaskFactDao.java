package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sec.schedule.entity.TaskFact;
import com.sec.schedule.model.CompositeIdTaskFact;
import java.util.List;

/**
 * TaskFactDao
 */
public interface TaskFactDao extends JpaRepository<TaskFact,CompositeIdTaskFact>,JpaSpecificationExecutor<TaskFact>{
    
    
    List<TaskFact> findByStatus(String status);

    

    @Query("select count(1) from task_fact where stat_dt = ?1")
    Long findCountByStatDt(String statDt);

    @Query("select t from task_fact t where t.id.statDt = ?1  order by t.id.statDt desc")
    List<TaskFact> findTaskFactListByStatDt(String statDt);

    @Query("select t from task_fact t where t.id.statDt in (?1)")
    List<TaskFact> findTaskFactListByStatDts(List<String> statDtList);
}