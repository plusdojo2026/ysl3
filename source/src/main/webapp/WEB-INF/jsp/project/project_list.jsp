<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ホーム | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
  <script src="${pageContext.request.contextPath}/js/home.js" defer></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">

  <h2 class="page-title">案件一覧</h2>
  <button class="project-regist-btn">新規登録</button>

  <!-- 検索フォームここから -->
  <form class="project-search-form">
    <input type="hidden" name="page-id" value="PR01">
 
    <select class="project-select">
    <option>ステータス</option>
    <option>進行中</option>
    <option>完了</option>
    <option>保留</option>
    </select>
    <select class="project-select">
    <option>優先度</option>
    <option>低</option>
    <option>中</option>
    <option>高</option>
    </select>
    <input type="text" class="project-search-text" value="キーワード検索" name="keyword">
    <input type="submit" class="search-btn" value="検索">
  </form>
  <!-- 検索フォームここまで -->

  <!-- 案件一覧ここから -->
   <c:forEach var="project" items="${projectList}">
   <div class="project-card">
   	<ul>
   		<li>案件コード :${project.Id}</li>
   		<li>案件名 :${project.Name}<li>
   		<li>顧客名</li>
   		<li>開始日:${project.}</li>
   		<li>期限日:${project.}</li>
   		<li>ステータス:${project.}</li>
   		<li>優先度:${project.}</li>
   		<li>PM名:${project.}</li>
   		<li>進捗度:${project.}</li>
   		<li>総工数:${project.}</li>
   		<li>現在までの工数:${project.}</li>
   	</ul>
   	
   	<!-- 編集ボタン -->
   	ProjectAction<input type="hidden" name="action" value="projectToEdit">
   	
   	<!-- 編集対象案件ID -->
   	
   	<input type="hidden" name="projectId" value="${project.projecrtId}">
   	<button type="submit" class="edit-btn">編集</button>
   	</form>
   </div>
  </c:forEach>
  <!-- 案件一覧ここまで -->
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>