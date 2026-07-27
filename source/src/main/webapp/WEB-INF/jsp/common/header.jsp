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

	<%-- 今だけ --%>
	<div class="now-menu">
		<a href="${pageContext.request.contextPath}/Controller?page-id=PR04&preview=true">案件詳細</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=PR02&preview=true">案件登録</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=PR03&preview=true">案件編集</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=TA04&preview=true">タスク詳細</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=TA02&preview=true">タスク登録</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=TA03&preview=true">タスク編集</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=WO01&preview=true">工数登録</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=AD02&preview=true">メンバー登録</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=AD03&preview=true">メンバー編集</a>
		<a href="${pageContext.request.contextPath}/Controller?page-id=LO01&preview=true">ログイン画面</a>
	</div>
	<%-- 今だけ --%>

</nav>
