<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>タスク編集 | TaskManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css"/>
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
	<h1>タスク編集</h1>

		<form method="POST" action="ysl3/action/TaskAction.java">
			<input type="hidden" name="page-id"  value="TA002"> 
			案件名(必須)<input type="text" name="project-name" value="${editTask.projectName }"><br> 
			タスク名(必須) <input type="text" name="task-name" value="${editTask.name}"><br> 
			ステータス(必須)
				<select name="status">
				<option value="not-started" ${editTask.status == 0 ? 'selected' : ''}>未着手</option>
				<option value="in-progress" ${editTask.status == 1 ? 'selected' : ''}>進行中</option>
				<option value="completed" ${editTask.status == 2 ? 'selected' : ''}>完了</option>
				<option value="pending" ${editTask.status == 3 ? 'selected' : ''}>保留</option>
			</select> 
			
			優先度(必須)<select name="priority">
				<option value="high" ${editTask.priority == 0 ? 'selected' : ''}>高</option>
				<option value="middle" ${editTask.priority == 1 ? 'selected' : ''}>中</option>
				<option value="low" ${editTask.priority == 2 ? 'selected' : ''}>低</option>
			</select> 担当者<input type="text" name="task-manager" value="${editTask.userName}"><br> 
			進捗率<input
				type="range" name="progress" min="0" max="100" value=${editTask.progress} step="5"
				list="marks" ><br>
			<datalist id="marks" >
				<option value="0" label="0%"></option>
    			<option value="5" label="5%"></option>
    			<option value="10" label="10%"></option>
    			<option value="15" label="15%"></option>
   				 <option value="20" label="20%"></option>
  			  <option value="25" label="25%"></option>
    			<option value="30" label="30%"></option>
    			<option value="35" label="35%"></option>
    			<option value="40" label="40%"></option>
    			<option value="45" label="45%"></option>
    			<option value="50" label="50%"></option>
    			<option value="55" label="55%"></option>
    			<option value="60" label="60%"></option>
    			<option value="65" label="65%"></option>
    			<option value="70" label="70%"></option>
    			<option value="75" label="75%"></option>
    			<option value="80" label="80%"></option>
    			<option value="85" label="85%"></option>
    			<option value="90" label="90%"></option>
    			<option value="95" label="95%"></option>
    			<option value="100" label="100%"></option>
			</datalist>
			予算工数 <input type="text" name="budget-works" value="${editTask.estimatedWorks}"><br>
			開始日/期限<input type="text" name="task-start-date" value="${editTask.startDate}">ー
			 <input type="text" name="task-limit" value="${editTask.limitDate}"><br> 
			 
			 
				説明<input type="text"name="task-explanation" value="${editTask.explainText}"><br> 
				<input type="submit" name="btn-id" value="登録"><br>
		</form>
		        <c:if test="${not empty msg}">
            <p><c:out value="${msg}" /></p>
        </c:if>

        <c:if test="${not empty errMsg}">
            <p><c:out value="${errMsg}" /></p>
        </c:if>
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>