<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>タスク一覧 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <h2>タスク一覧</h2>
  
  
  <!--検索-->
  <select name="project_name" >
  	<option value="">案件名</option>
  	<c:forEach var="e" items="${project_name}">
  	<option>${e.project_name}</option></c:forEach>
  </select>
  
  <select name="task_status">
  	<option value="">ステータス</option>
  	<c:forEach var="e" items="${task_status}">
  	<option>${e.task_status}</option></c:forEach>
  </select>
  
  <select name="user_name">
  	<option value="">担当者</option>
  	<c:forEach var="e" items="${user_id}">
  	<option>${e.user_id}</option></c:forEach>
  </select>
  
  <form method="post">
  	<input type="text" name="task_name" value="${task_name}" placeholder="タスク名">
  	<input type="submit" value="検索">
  </form>


  <!--一覧項目-->
  
  <c:forEach var="e" items="${task_table}">
  
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskDetail">
  <div class="task_list" >
  	<p>１．${e.task_id}</p>
  	<p>案件名${e.project_id}</p>
  	<p>タスク名${e.task_name}</p>
  	<p>ステータス${e.task_status}</p>
  	<p>期限${e.task_limit}</p>
  	<p>担当者${e.user_id}</p>	
  	<p>進捗<progress value="${e.progress}" max="100" style="width: 200px; height: 20px;"></progress></p>
  	<input type="submit" value="編集">
   </div>
   </c:forEach></a>
   
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>