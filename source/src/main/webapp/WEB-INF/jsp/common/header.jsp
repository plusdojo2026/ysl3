<%@ page contentType="text/html; charset=UTF-8"%>

<header class="header">
	<a href="${pageContext.request.contextPath}/Controller?page-id=HO01" class="system-title">TaskManager</a> 
	<a href="${pageContext.request.contextPath}/Controller?page-id=MY01" class="user-name-view">${user.userName}</a>
	<button id="menuButton" class="menu-button" type="button">☰</button>
</header>

<nav id="menu" class="menu">
	<button id="closeButton" class="close-button" type="button">×</button>
	<a href="${pageContext.request.contextPath}/Controller?page-id=HO01">ホーム</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=PR01">案件一覧</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=TA01">タスク一覧</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=MO01">月次集計</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー一覧</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=MY01">マイページ</a>
	<a href="${pageContext.request.contextPath}/Controller?page-id=logout">ログアウト</a>

</nav>
