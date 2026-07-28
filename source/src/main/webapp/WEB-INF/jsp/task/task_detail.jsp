<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>タスク詳細 | TaskManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css"/>
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <!--タスク詳細-->
  
  <h2>タスク詳細/工数登録</h2>
   <p>案件名：${taskDetail.projectName}</p>
   <p>タスク名：${taskDetail.name}</p>
   <p>ステータス：${taskDetail.status}</p>
   <p>見積工数：${taskDetail.estimatedWorks}</p> 
   <p>進捗度：<progress value="${taskDetail.progress}" max="100" style="width: 200px; height: 20px;"></progress></p>
   <p>開始日：${taskDetail.startDate}～</p>
   <p>期限：${taskDetail.limitDate}</p>
   <p>担当者：${taskDetail.userName}</p>
   
   <!--タスク編集ボタン-->
   <input type="submit" name="task-edit-btn" value="編集">
   
   <!--タスク概要-->
    <h3>タスク概要</h3>
     <p>${taskDetail.explainText}</p>
   
   <!--工数ログ一覧-->
 	<h3>工数ログ一覧</h3>
 	<c:forEach var="e" items="${taskWorkList}" >
 	  <p>工数ログ名：${e.explainText}</p>
 	  <p>時間：${e.work}</p>
 	 
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
    <input type="submit" name="work-btn" value="工数の登録へ"></a>
 	
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>