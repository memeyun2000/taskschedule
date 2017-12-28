package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TaskImplJdbcSql;

public interface TaskImplJdbcSqlDao extends JpaRepository<TaskImplJdbcSql,String> {

}