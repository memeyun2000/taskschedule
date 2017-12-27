package com.sec.schedule.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdTaskFact implements Serializable{
    private static final long serialVersionUID = 123456789L;

    private String statDt;
    private String taskId;

    @Override
    public boolean equals(Object obj) {
        // return obj instanceof CompositeIdTaskFact;
        return obj instanceof CompositeIdTaskFact 
                && taskId.equals(((CompositeIdTaskFact)obj).taskId)
                && statDt.equals(((CompositeIdTaskFact)obj).statDt); 
    }

    /**
     * 中给出了一个能最大程度上避免哈希冲突的写法，但我个人认为对于一般的应用来说没有必要搞的这么麻烦．如果你的应用中HashSet中需要存放上万上百万个对象时，那你应该严格遵循书中给定的方法．如果是写一个中小型的应用，那么下面的原则就已经足够使用了：
     * 要保证Coder对象中所有的成员都能在hashCode中得到体现．
     * 对于本例，我们可以这么写：
     * 
     * String 类中的 hashCode()
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 17 * 31 + taskId.hashCode();
        result = 17 * 31^2 + statDt.hashCode();
        return result;
    }


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