package com.sec.schedule.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="task_info")
public class TaskInfo{
    @Id
    private String taskId;    //任务id
    private String taskname;  //任务名称
    private String descr;     //任务描述
    private String taskType;  //任务类型

    private String granularity; //任务粒度 Y：年 HY：半年 S：季 M：月 D：天 W:周
    private Long execDelay;    //任务执行延迟 单位：秒
    private Boolean isDependTime;//是否时间依赖

    private Date createtime;    //任务创建时间
    private Date updatetime;    //任务更新时间



    //--- getter and setter  ---//
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
     * @param createtime the createtime to set
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    /**
     * @param granularity the granularity to set
     */
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }
    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    /**
     * @param taskname the taskname to set
     */
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
    /**
     * @param updatetime the updatetime to set
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * @return the createtime
     */
    public Date getCreatetime() {
        return createtime;
    }
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    /**
     * @return the granularity
     */
    public String getGranularity() {
        return granularity;
    }
    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }
    /**
     * @return the taskname
     */
    public String getTaskname() {
        return taskname;
    }
    /**
     * @return the updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }
    /**
     * @return the execDelay
     */
    public Long getExecDelay() {
        return execDelay;
    }
    /**
     * @return the taskType
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * @param execDelay the execDelay to set
     */
    public void setExecDelay(Long execDelay) {
        this.execDelay = execDelay;
    }
    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
}