package com.sec.schedule.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdTaskDepend implements Serializable{
    private String taskId;
    private String dependTaskId;

    //-- getter and setter --//
    /**
     * @return the dependTaskId
     */
    public String getDependTaskId() {
        return dependTaskId;
    }
    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }
    /**
     * @param dependTaskId the dependTaskId to set
     */
    public void setDependTaskId(String dependTaskId) {
        this.dependTaskId = dependTaskId;
    }
    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}