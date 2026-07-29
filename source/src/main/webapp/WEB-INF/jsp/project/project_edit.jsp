<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>案件編集 | TaskManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css"/>
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/project.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
    <!-- 案件編集フォームここから -->
	  <form method="POST" class="pr-edit-form" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page-id" value="PR03">
        案件コード(必須)<br>
        <input type="text" class="pr-regist-input" name="project-code" placeholder="20文字以内" required max="20"><br>
        案件名(必須)<br>
        <input type="text" class="pr-regist-input" name="project-name" placeholder="案件名を入力してください" required max="100"><br>
        顧客名<br>
        <input type="text" class="pr-regist-input" name="customer" placeholder="顧客名を入力してください" required max="50"><br>
        担当PM<br>
        <select class="pr-regist-input pr-regist-select" name="pm-id">
        <option value="" disabled selected style="display:none;">選択してください</option>
        <c:forEach var="user" items="${pmList}">
         <option value="${user.id}">${user.userName}</option>
    	</c:forEach>
       
        </select>
        <br>
        ステータス（必須）<br>
        <select class="pr-regist-input pr-regist-select" name="project-status">
         <option value="" disabled selected style="display:none;">選択してください</option>
         <option value="0">開始前</option>
   		 <option value="1">進行中</option>
   		 <option value="2">完了</option>
    	 <option value="3">保留</option>
   		</select>
   		<br>
        優先度(必須)<br>
        <select class="pr-regist-input pr-regist-select" name="project-priority">
        <option value="" disabled selected style="display:none;">選択してください</option>
   		<option value="0">低</option>
    	<option value="1">中</option>
    	<option value="2">高</option>
        </select>
        <br>開始日<br>
        <input type="date" class="pr-regist-input pr-regist-date" name="project-start-date"><br>
        終了予定日<br>
        <input type="date" class="pr-regist-input pr-regist-date" name="project-end-date"><br>
        予算工数(h)<br>
        <input type="number" class="pr-regist-input" name="project-estimated-works"><br>
        説明<br>
        <input type="text" class="pr-regist-input" name="project-explain" max="1000">
        <br>
        期限<br>
        <input type="date" class="pr-regist-input pr-regist-date" name="project-limit">
        <br>
        
        <input type="submit" class="regist-btn" name="btn-id"  value="更新">
      </form>
    <!-- 案件編集フォームここまで -->
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>