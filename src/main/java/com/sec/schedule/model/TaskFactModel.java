package com.sec.schedule.model;

import com.sec.schedule.entity.TaskFact;

public class TaskFactModel extends TaskFact{
    private String statDtBegin;
    private String statDtEnd;
 
    /**
     * @return the statDtBegin
     */
    public String getStatDtBegin() {
        return statDtBegin;
    }
    /**
     * @param statDtBegin the statDtBegin to set
     */
    public void setStatDtBegin(String statDtBegin) {
        this.statDtBegin = statDtBegin;
    }
    /**
     * @param statDtEnd the statDtEnd to set
     */
    public void setStatDtEnd(String statDtEnd) {
        this.statDtEnd = statDtEnd;
    }
    /**
     * @return the statDtEnd
     */
    public String getStatDtEnd() {
        return statDtEnd;
    }

}
