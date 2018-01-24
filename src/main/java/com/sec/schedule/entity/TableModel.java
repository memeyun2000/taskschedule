package com.sec.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sec.schedule.model.CompositeIdTableModel;

@Entity(name="table_model")
public class TableModel {
    @Id
    private CompositeIdTableModel id;
    @Column(length=50)
    private String descr;
    @Column(length=1)
    private String tableType;
    @Column(length=1000)
    private String memo;


    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    /**
     * @return the id
     */
    public CompositeIdTableModel getId() {
        return id;
    }
    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }
    /**
     * @return the tableType
     */
    public String getTableType() {
        return tableType;
    }
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    /**
     * @param id the id to set
     */
    public void setId(CompositeIdTableModel id) {
        this.id = id;
    }
    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    /**
     * @param tableType the tableType to set
     */
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    

}