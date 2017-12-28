package com.sec.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="task_config_db")
public class TaskConfigDB {
    @Id
    private String databaseId;
    private String driver;
    private String url;
    private String username;
    private String passwd;



    /**
     * @return the databaseId
     */
    public String getDatabaseId() {
        return databaseId;
    }
    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }
    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param databaseId the databaseId to set
     */
    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
    
}