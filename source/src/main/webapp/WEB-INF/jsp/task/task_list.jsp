<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>タスク一覧 | TaskManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css"/>
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <h2>タスク一覧</h2>
  
  
  <!--検索-->
  <select name="project_name" >
  	<option value="">案件名</option>
  	<c:forEach var="e" items="${projectName}">
  	<option>${e.projectName}</option></c:forEach>
  </select>
  
  <select name="task-status">
  	<option value="">ステータス</option>
  	<c:forEach var="e" items="${taskStatus}">
  	<option>${e.taskStatus}</option></c:forEach>
  </select>
  
  <select name="user-name">
  	<option value="">担当者</option>
  	<c:forEach var="e" items="${userId}">
  	<option>${e.user-id}</option></c:forEach>
  </select>
  
  <form method="post">
  	<input type="text" name="task-name" value="${taskName}" placeholder="タスク名">
  	<input type="submit" value="検索">
  </form>


  <!--一覧項目-->
  
  <c:forEach var="e" items="${taskTable}">
  
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskDetail">
  <div class="task-list" >
  	<p>１．${e.taskId}</p>
  	<p>案件名${e.projectId}</p>
  	<p>タスク名${e.taskName}</p>
  	<p>ステータス${e.taskStatus}</p>
  	<p>期限${e.taskLimit}</p>
  	<p>担当者${e.userId}</p>	
  	<p>進捗<progress value="${e.progress}" max="100" style="width: 200px; height: 20px;"></progress></p>
  	<input type="submit" value="編集">
   </div>
   </c:forEach></a>
   
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>