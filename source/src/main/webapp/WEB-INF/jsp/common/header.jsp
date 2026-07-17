<%@ page contentType="text/html; charset=UTF-8" %>

<header class="header">
  <a href="${pageContext.request.contextPath}/Controller?page_id=home"
     class="system-title">TaskManager</a>

  <button id="menuButton" class="menu-button" type="button">☰</button>
</header>

<nav id="menu" class="menu">
  <button id="closeButton" class="close-button" type="button">×</button>

  <a href="${pageContext.request.contextPath}/Controller?page_id=toHome">ホーム</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toProjectList">案件</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toTaskList">タスク</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toMonth">月次集計</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toMypage">マイページ</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toMemberList">メンバー</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=toLogout">ログアウト</a>
</nav>
    