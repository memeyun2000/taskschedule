package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.sec.schedule.entity.TaskDepend;
import com.sec.schedule.model.CompositeIdTaskDepend;

/**
 * TaskDependDao
 */
public interface TaskDependDao extends JpaRepository<TaskDepend,CompositeIdTaskDepend>{

    /**
     * 查找 前置依赖
     */
    @Query("select t from task_depend t where id.taskId = ?1")
    List<TaskDepend> findDependTaskListByTaskId(String taskId);

    /**
     * 查找 后置依赖
     */
    @Query("select t from task_depend t where id.dependTaskId = ?1")
    List<TaskDepend> findDependTaskListByDependTaskId(String dependTaskId);
}
