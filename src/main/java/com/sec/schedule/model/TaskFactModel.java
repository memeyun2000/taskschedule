package com.sec.schedule.model;

import com.sec.schedule.entity.TaskFact;

public class TaskFactModel extends TaskFact {
  private String statDtBegin;
  private String statDtEnd;

  private String prevTaskId;
  private String nextTaskId;


	/**
	* Returns value of statDtBegin
	* @return
	*/
	public String getStatDtBegin() {
		return statDtBegin;
	}

	/**
	* Sets new value of statDtBegin
	* @param
	*/
	public void setStatDtBegin(String statDtBegin) {
		this.statDtBegin = statDtBegin;
	}

	/**
	* Returns value of statDtEnd
	* @return
	*/
	public String getStatDtEnd() {
		return statDtEnd;
	}

	/**
	* Sets new value of statDtEnd
	* @param
	*/
	public void setStatDtEnd(String statDtEnd) {
		this.statDtEnd = statDtEnd;
	}

	/**
	* Returns value of prevTaskId
	* @return
	*/
	public String getPrevTaskId() {
		return prevTaskId;
	}

	/**
	* Sets new value of prevTaskId
	* @param
	*/
	public void setPrevTaskId(String prevTaskId) {
		this.prevTaskId = prevTaskId;
	}

	/**
	* Returns value of nextTaskId
	* @return
	*/
	public String getNextTaskId() {
		return nextTaskId;
	}

	/**
	* Sets new value of nextTaskId
	* @param
	*/
	public void setNextTaskId(String nextTaskId) {
		this.nextTaskId = nextTaskId;
	}
}
