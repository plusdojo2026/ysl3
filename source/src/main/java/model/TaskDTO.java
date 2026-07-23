package model;

import java.io.Serializable;





public class TaskDTO implements Serializable {
	private int taskId;
	private int userId;
	private String name;
	private int status;
	private int priority;
	private String limitDate;
	private String explainText;
	private float estimatedWork;
	private int projectId;
	private String startDate;
	private String endDate;
	private int progress;
	private String userName;
	private float totalWork;
	
	
	public TaskDTO(int taskId, int userId, String name, int status, int priority, String limitDate, String explainText,
			float estimatedWork, int projectId, String startDate, String endDate, int progress, String userName,
			float totalWork) {
		super();
		this.taskId = taskId;
		this.userId = userId;
		this.name = name;
		this.status = status;
		this.priority = priority;
		this.limitDate = limitDate;
		this.explainText = explainText;
		this.estimatedWork = estimatedWork;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progress = progress;
		this.userName = userName;
		this.totalWork = totalWork;
	}


	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getLimitDate() {
		return limitDate;
	}


	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}


	public String getExplainText() {
		return explainText;
	}


	public void setExplainText(String explainText) {
		this.explainText = explainText;
	}


	public float getEstimatedWork() {
		return estimatedWork;
	}


	public void setEstimatedWork(float estimatedWork) {
		this.estimatedWork = estimatedWork;
	}


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getProgress() {
		return progress;
	}


	public void setProgress(int progress) {
		this.progress = progress;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public float getTotalWork() {
		return totalWork;
	}


	public void setTotalWork(float totalWork) {
		this.totalWork = totalWork;
	}


	public TaskDTO() {
		super();
		this.taskId = 0;
		this.userId = 0;
		this.name = "";
		this.status = 0;
		this.priority = 0;
		this.limitDate = "";
		this.explainText = "";
		this.estimatedWork = 0;
		this.projectId = 0;
		this.startDate = "";
		this.endDate = "";
		this.progress = 0;
		this.userName = "";
		this.totalWork = 0;
	}
	
	
	



	
}