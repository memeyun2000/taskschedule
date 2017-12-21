package com.sec.schedule.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.sec.schedule.model.CompositeIdTaskFact;

@Entity(name="task_fact")
public class TaskFact{
    private CompositeIdTaskFact id;  //计算日期
    private String task_type;//任务类型
    private String granularity;//粒度

    private Date allow_cal_time;  //允许计算的时间开始日期，在此日期之前不能计算任务
    private Date cal_begin_time;  //计算 开始时间
    private Date cal_end_time;    //计算 结束时间
    private Date createtime;      //任务 创建时间

    private String status;   //0：失败 1:计算条件不充分 2:准备计算 3:正在计算 4:计算完成


    //-- getter and setter --//
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
     * @return the allow_cal_time
     */
    public Date getAllow_cal_time() {
        return allow_cal_time;
    }
    /**
     * @return the cal_begin_time
     */
    public Date getCal_begin_time() {
        return cal_begin_time;
    }
    /**
     * @return the cal_end_time
     */
    public Date getCal_end_time() {
        return cal_end_time;
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
     * @return the task_type
     */
    public String getTask_type() {
        return task_type;
    }
    /**
     * @param allow_cal_time the allow_cal_time to set
     */
    public void setAllow_cal_time(Date allow_cal_time) {
        this.allow_cal_time = allow_cal_time;
    }
    /**
     * @param cal_begin_time the cal_begin_time to set
     */
    public void setCal_begin_time(Date cal_begin_time) {
        this.cal_begin_time = cal_begin_time;
    }
    /**
     * @param cal_end_time the cal_end_time to set
     */
    public void setCal_end_time(Date cal_end_time) {
        this.cal_end_time = cal_end_time;
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
     * @param task_type the task_type to set
     */
    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }
}