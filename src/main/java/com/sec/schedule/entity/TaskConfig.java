package com.sec.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="task_config")
public class TaskConfig {
    @Id
    private String dictKey;

    private String dictValue;



    //-- getter and setter --//
    /**
     * @return the dictKey
     */
    public String getDictKey() {
        return dictKey;
    }
    /**
     * @return the dictValue
     */
    public String getDictValue() {
        return dictValue;
    }
    /**
     * @param dictKey the dictKey to set
     */
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }
    /**
     * @param dictValue the dictValue to set
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

}