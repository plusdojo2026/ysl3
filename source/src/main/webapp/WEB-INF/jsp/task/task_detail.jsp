<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>タスク詳細 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <!--タスク詳細-->
  
  <h2>タスク詳細/工数登録</h2>
   <p>案件名：${project_table.project_name}</p>
   <p>タスク名：${task_table.task_name}</p>
   <p>ステータス：${task_table.task_status}</p>
   <p>見積工数：${task_table.task_estimated_works}</p> 
   <p>進捗度：<progress value="${task_table.progress}" max="100" style="width: 200px; height: 20px;"></progress></p>
   <p>開始日：${task_table.task_start_date}～</p>
   <p>期限：${task_table.task_limit}</p>
   <p>担当者：${user_table.user_name}</p>
   
   <!--タスク編集ボタン-->
   <input type="submit" name="task-edit-btn" value="編集">
   
   <!--タスク概要-->
    <h3>タスク概要</h3>
   
   <!--工数ログ一覧-->
 	<h3>工数ログ一覧</h3>
 	<c:forEach var="e" items="${workList}" >
 	  <p>工数ログ名：${work_name}</p>
 	  <p>時間：${work}</p>
 	 
 	 <input type="submit" name="work-delete-btn" value="削除" onclick="return delete()">
   </c:forEach><br>
 	  
 	<!--ステータス更新ボタン-->
 	<div class=>
 	<input type="submit" name="task-status-btn1" value="未着手に戻す">
    <input type="submit" name="task-status-btn2" value="進行中にする"> 
    <input type="submit" name="task-status-btn3" value="完了にする">
    <input type="submit" name="task-status-btn4" value="保留にする">
    <!--ステータス更新ボタン-->
    </div>
    
    <!--工数登録画面遷移ボタン-->
    <a href="${pageContext.request.contextPath}/Controller?page_id=workRegist">
    <input type="submit" name="work- -btn" value="工数の登録へ"></a>
 	
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>