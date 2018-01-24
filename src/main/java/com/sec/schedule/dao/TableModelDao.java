package com.sec.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.schedule.entity.TableModel;
import com.sec.schedule.model.CompositeIdTableModel;

public interface TableModelDao extends JpaRepository<TableModel,CompositeIdTableModel>{

}