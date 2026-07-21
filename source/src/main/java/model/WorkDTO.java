package model;

import java.io.Serializable;

public class WorkDTO  implements Serializable{
private int id;
private int taskId;
private float work;
private String explainText;
private int userId;
private String workDate;
private String userName;
private String taskName;

public WorkDTO(int id, int taskId, float work, String explainText, int userId, String workDate, String userName, String taskName) {
	super ();
	this.id =id;
	this.taskId = taskId;
	this.work = work;
	this.explainText = explainText;
	this.userId = userId;
	this.workDate = workDate;
	this.userName = userName;
	this.taskName = taskName;

}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getTaskId() {
	return taskId;
}

public void setTaskId(int taskId) {
	this.taskId = taskId;
}

public float getWork() {
	return work;
}

public void setWork(float work) {
	this.work = work;
}

public String getExplainText() {
	return explainText;
}

public void setExplainText(String explainText) {
	this.explainText = explainText;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getWorkDate() {
	return workDate;
}

public void setWorkDate(String workDate) {
	this.workDate = workDate;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getTaskName() {
	return taskName;
}

public void setTaskName(String taskName) {
	this.taskName = taskName;
}

public WorkDTO() {
	super ();
	this.id = 0;
	this.taskId = 0;
	this.work = 0;
	this.explainText = "";
	this.userId = 0;
	this.workDate = "";
	this.userName = "";
	this.taskName = "";

}

}


