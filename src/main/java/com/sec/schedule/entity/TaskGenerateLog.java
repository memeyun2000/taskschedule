package com.sec.schedule.entity;

import java.util.Date;

import javax.persistence.Entity;

/**
 * 任务生成日期
 */
@Entity(name="task_generate_log")
public class TaskGenerateLog {
    
    private String statDt;    //数据日期
    private Boolean status;   //任务是否生成
    private Date createTime;
    
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * @return the statDt
     */
    public String getStatDt() {
        return statDt;
    }
    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @param statDt the statDt to set
     */
    public void setStatDt(String statDt) {
        this.statDt = statDt;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}