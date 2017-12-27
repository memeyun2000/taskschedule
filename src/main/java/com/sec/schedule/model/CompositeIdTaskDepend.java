package com.sec.schedule.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdTaskDepend implements Serializable{
    private static final long serialVersionUID = 29302894312L;

    private String taskId;
    private String dependTaskId;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CompositeIdTaskDepend 
                    && taskId.equals(((CompositeIdTaskDepend)obj).taskId)
                    && dependTaskId.equals(((CompositeIdTaskDepend)obj).dependTaskId);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 17 * 31 + taskId.hashCode();
        result = 17 * 31^2 + dependTaskId.hashCode();
        return result;
    }
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