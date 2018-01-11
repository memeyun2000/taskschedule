package com.sec.schedule.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.sec.schedule.model.CompositeIdTaskFact;

@Entity(name="task_fact")
public class TaskFact{
    private CompositeIdTaskFact id = new CompositeIdTaskFact();  //计算日期
    private String taskType;//任务类型
    private String granularity;//粒度

    private Date allowCalTime;  //允许计算的时间开始日期，在此日期之前不能计算
    private Date calBeginTime;  //计算 开始时间
    private Date calEndTime;    //计算 结束时间
    private Date createtime;      //任务 创建时间
    private Boolean isDependTime;

    private String status;   //0：失败 1:计算条件不充分 2:准备计算 3:正在计算 4:计算完成


    //-- getter and setter --//
    /**
     * @return the isDependTime
     */
    public Boolean getIsDependTime() {
        return isDependTime;
    }
    /**
     * @param isDependTime the isDependTime to set
     */
    public void setIsDependTime(Boolean isDependTime) {
        this.isDependTime = isDependTime;
    }
    /**
     * @return the id
     */
    @EmbeddedId
    public CompositeIdTaskFact getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(CompositeIdTaskFact id) {
        this.id = id;
    }
    
    /**
     * @return the createtime
     */
    public Date getCreatetime() {
        return createtime;
    }
    /**
     * @return the granularity
     */
    public String getGranularity() {
        return granularity;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param createtime the createtime to set
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    /**
     * @param granularity the granularity to set
     */
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the allowCalTime
     */
    public Date getAllowCalTime() {
        return allowCalTime;
    }
    /**
     * @return the calBeginTime
     */
    public Date getCalBeginTime() {
        return calBeginTime;
    }
    /**
     * @return the calEndTime
     */
    public Date getCalEndTime() {
        return calEndTime;
    }
    /**
     * @return the taskType
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * @param allowCalTime the allowCalTime to set
     */
    public void setAllowCalTime(Date allowCalTime) {
        this.allowCalTime = allowCalTime;
    }
    /**
     * @param calBeginTime the calBeginTime to set
     */
    public void setCalBeginTime(Date calBeginTime) {
        this.calBeginTime = calBeginTime;
    }
    /**
     * @param calEndTime the calEndTime to set
     */
    public void setCalEndTime(Date calEndTime) {
        this.calEndTime = calEndTime;
    }
    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}