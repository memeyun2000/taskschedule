package com.sec.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="task_impl_spark_job")
public class TaskImplSparkJob {
    @Id
    private String taskId;
    
    private String jarName;
    
    private String master;

    private String deployMode;

    private String driverMemory;

    private String exectorMemory;

    private String exectorCores;

    private String queue;

    private String classname;

    private String taskname;

    private String args1;

    private String args2;

    private String args3;

    private String args4;

    private String args5;

    //-- getter and setter --//
    /**
     * @return the exectorCores
     */
    public String getExectorCores() {
        return exectorCores;
    }
    /**
     * @param exectorCores the exectorCores to set
     */
    public void setExectorCores(String exectorCores) {
        this.exectorCores = exectorCores;
    }
    /**
     * @return the jarName
     */
    public String getJarName() {
        return jarName;
    }
    /**
     * @param jarName the jarName to set
     */
    public void setJarName(String jarName) {
        this.jarName = jarName;
    }
    /**
     * @return the args1
     */
    public String getArgs1() {
        return args1;
    }
    /**
     * @return the args2
     */
    public String getArgs2() {
        return args2;
    }
    /**
     * @return the args3
     */
    public String getArgs3() {
        return args3;
    }
    /**
     * @return the args4
     */
    public String getArgs4() {
        return args4;
    }
    /**
     * @return the args5
     */
    public String getArgs5() {
        return args5;
    }
    /**
     * @return the classname
     */
    public String getClassname() {
        return classname;
    }
    /**
     * @return the deployMode
     */
    public String getDeployMode() {
        return deployMode;
    }
    /**
     * @return the driverMemory
     */
    public String getDriverMemory() {
        return driverMemory;
    }
    /**
     * @return the exectorMemory
     */
    public String getExectorMemory() {
        return exectorMemory;
    }
    /**
     * @return the master
     */
    public String getMaster() {
        return master;
    }
    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
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
     * @param args1 the args1 to set
     */
    public void setArgs1(String args1) {
        this.args1 = args1;
    }
    /**
     * @param args2 the args2 to set
     */
    public void setArgs2(String args2) {
        this.args2 = args2;
    }
    /**
     * @param args3 the args3 to set
     */
    public void setArgs3(String args3) {
        this.args3 = args3;
    }
    /**
     * @param args4 the args4 to set
     */
    public void setArgs4(String args4) {
        this.args4 = args4;
    }
    /**
     * @param args5 the args5 to set
     */
    public void setArgs5(String args5) {
        this.args5 = args5;
    }
    /**
     * @param classname the classname to set
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
    /**
     * @param deployMode the deployMode to set
     */
    public void setDeployMode(String deployMode) {
        this.deployMode = deployMode;
    }
    /**
     * @param driverMemory the driverMemory to set
     */
    public void setDriverMemory(String driverMemory) {
        this.driverMemory = driverMemory;
    }
    /**
     * @param exectorMemory the exectorMemory to set
     */
    public void setExectorMemory(String exectorMemory) {
        this.exectorMemory = exectorMemory;
    }
    /**
     * @param master the master to set
     */
    public void setMaster(String master) {
        this.master = master;
    }
    /**
     * @param queue the queue to set
     */
    public void setQueue(String queue) {
        this.queue = queue;
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
}