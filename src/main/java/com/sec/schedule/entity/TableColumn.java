package com.sec.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sec.schedule.model.CompositeIdTableColumn;

@Entity(name="table_column")
public class TableColumn {
    @Id 
    private CompositeIdTableColumn id;

    private int orderSeq; //字段顺序
    @Column(length=50)
    private String descr;
    @Column(length=15)
    private String columnType; //字段类型
    
    private int columnLength; //字段长度

    private int columnFloatLength; //小数位长度
    @Column(length=30)
    private String dictName; //码表
    @Column(length=1000)
    private String memo;



    /**
     * @return the columnFloatLength
     */
    public int getColumnFloatLength() {
        return columnFloatLength;
    }
    /**
     * @return the columnLength
     */
    public int getColumnLength() {
        return columnLength;
    }
    /**
     * @return the columnType
     */
    public String getColumnType() {
        return columnType;
    }
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    /**
     * @return the dictName
     */
    public String getDictName() {
        return dictName;
    }
    /**
     * @return the id
     */
    public CompositeIdTableColumn getId() {
        return id;
    }
    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }
    /**
     * @return the orderSeq
     */
    public int getOrderSeq() {
        return orderSeq;
    }
    /**
     * @param columnFloatLength the columnFloatLength to set
     */
    public void setColumnFloatLength(int columnFloatLength) {
        this.columnFloatLength = columnFloatLength;
    }
    /**
     * @param columnLength the columnLength to set
     */
    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }
    /**
     * @param columnType the columnType to set
     */
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    /**
     * @param dictName the dictName to set
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    /**
     * @param id the id to set
     */
    public void setId(CompositeIdTableColumn id) {
        this.id = id;
    }
    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    /**
     * @param orderSeq the orderSeq to set
     */
    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }
}