package com.sec.schedule.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.sec.schedule.model.CompositeIdTaskDepend;

@Entity(name="task_depend")
public class TaskDepend{
    private CompositeIdTaskDepend id;

    //-- getter and setter --//
    /**
     * @return the id
     */
    @EmbeddedId
    public CompositeIdTaskDepend getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(CompositeIdTaskDepend id) {
        this.id = id;
    }

}