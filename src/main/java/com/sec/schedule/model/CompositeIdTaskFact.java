package com.sec.schedule.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdTaskFact implements Serializable{
    private String statDt;
    private String taskId;


    //-- getter and setter --//
    /**
     * @return the statDt
     */
    public String getStatDt() {
        return statDt;
    }
    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }
    /**
     * @param statDt the statDt to set
     */
    public void setStatDt(String statDt) {
        this.statDt = statDt;
    }
    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}