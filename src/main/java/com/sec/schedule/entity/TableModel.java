package com.sec.schedule.entity;

import javax.persistence.Entity;

import com.sec.schedule.model.CompositeIdTableModel;

@Entity(name="table_model")
public class TableModel {

    private CompositeIdTableModel id;

    private String tableCname;

    private String descr;

}