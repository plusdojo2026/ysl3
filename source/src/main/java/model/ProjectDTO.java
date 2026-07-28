package model;

import java.io.Serializable;

public class ProjectDTO implements Serializable{
private int id;
private String code;
private String name;
private String customer;
private int status;
private int priority;
private int pmId;
private String startDate;
private String endDate;
private String explainText;
private String limitDate;
private float estimatedWork;
private String pmName;
private float totalWork;
private int memberCount; //稼働メンバー
private float remainWork; //残り工数
private float plannedWork; //予定工数(案件別実績)
private float progressRate; //進捗率(案件別実績)
private String userName; //ユーザー名(ユーザー別実績)


public ProjectDTO(int id, String code, String name, String customer, int status, int pmId, 
int priority, String startDate, String endDate, String explainText,
String limitDate, float estimatedWork, String pmName, float totalWork,int memberCount,float remainWork,
float plannedWork,float progressRate, String userName) {
	super();
	this.id = id;
	this.code = code;
	this.name = name;
	this.customer = customer;
	this.status = status;
	this.priority = priority;
	this.pmId = pmId;
	this.startDate = startDate;
	this.endDate = endDate;
	this.explainText = explainText;
	this.limitDate = limitDate;
	this.estimatedWork = estimatedWork;
	this.pmName = pmName;
	this.totalWork = totalWork;
	this.memberCount = memberCount; //稼働メンバー
	this.remainWork = remainWork; //残り工数
	this.plannedWork = plannedWork; //予定工数(案件別実績)
	this.progressRate = progressRate; //進捗率(案件別実績)
	this.userName = userName; //ユーザー名(ユーザー別実績)

}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCustomer() {
	return customer;
}

public void setCustomer(String customer) {
	this.customer = customer;
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

public int getPmId() {
	return pmId;
}

public void setPmId(int pmId) {
	this.pmId = pmId;
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

public String getExplainText() {
	return explainText;
}

public void setExplainText(String explainText) {
	this.explainText = explainText;
}

public String getLimitDate() {
	return limitDate;
}

public void setLimitDate(String limitDate) {
	this.limitDate = limitDate;
}

public float getEstimatedWork() {
	return estimatedWork;
}

public void setEstimatedWork(float estimatedWork) {
	this.estimatedWork = estimatedWork;
}

public String getPmName() {
	return pmName;
}

public void setPmName(String pmName) {
	this.pmName = pmName;
}

public float getTotalWork() {
	return totalWork;
}

public void setTotalWork(float totalWork) {
	this.totalWork = totalWork;
}

public int getMemberCount() { //稼働メンバー
	return memberCount;
}

public void setMemberCount(int memberCount) {
	this.memberCount = memberCount;
}

public float getRemainWork() { //残り工数
	return remainWork;
}

public void setRemainWork(float remainWork) {
	this.remainWork = remainWork;
}

public float getPlannedWork() { //予定工数(案件別実績)
	return plannedWork;
}

public void setPlannedWork(float plannedWork) { 
	this.plannedWork = plannedWork;
}

public float getProgressRate() { //進捗率(案件別実績)
	return progressRate;
}

public void setProgressRate(float progressRate) { 
	this.progressRate = progressRate;
}

public String getUserName() { //ユーザー名(ユーザー別実績)
	return userName;
}

public void setUserNmae(String userName) {
	this.userName = userName;
}

public ProjectDTO() {
	super();
	this.id = 0;
	this.code = "";
	this.name = "";
	this.customer = "";
	this.status = 0;
	this.priority = 0;
	this.pmId = 0;
	this.startDate = "";
	this.endDate = "";
	this.explainText = "";
	this.limitDate = "";
	this.estimatedWork = 0;
	this.pmName = "";
	this.totalWork = 0;
	this.memberCount = 0; //稼働メンバー
	this.remainWork = 0; //残り工数
	this.plannedWork = 0;//予定工数(案件別実績)
	this.progressRate = 0;//進捗率(案件別実績)
	this.userName = "";//ユーザー名(ユーザー別実績)
}

}
