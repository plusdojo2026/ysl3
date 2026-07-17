<%@ page contentType="text/html; charset=UTF-8" %>

<header class="header">
  <a href="${pageContext.request.contextPath}/Controller?page_id=home"
     class="system-title">TaskManager</a>

  <button id="menuButton" class="menu-button" type="button">☰</button>
</header>

<nav id="menu" class="menu">
  <button id="closeButton" class="close-button" type="button">×</button>

  <a href="${pageContext.request.contextPath}/Controller?page_id=home">ホーム</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=projectList">案件一覧</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskList">タスク一覧</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=month">月次集計</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=mypage">マイページ</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=logout">ログアウト</a>
</nav>
    