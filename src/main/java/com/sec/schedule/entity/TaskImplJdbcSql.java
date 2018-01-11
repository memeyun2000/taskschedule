package com.sec.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="task_impl_jdbc_sql")
public class TaskImplJdbcSql {
    @Id
    private String taskId;
    private String databaseId;
    private String sqlStr;
  
    /**
     * @return the databaseId
     */
    public String getDatabaseId() {
        return databaseId;
    }
    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }
    /**
     * @param databaseId the databaseId to set
     */
    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
	/**
	 * @return the sqlStr
	 */
	public String getSqlStr() {
		return sqlStr;
	}
	/**
	 * @param sqlStr the sqlStr to set
	 */
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
    
}