<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header">
	<a href="${pageContext.request.contextPath}/Controller?page-id=HO01" class="system-title">
	<img src="${pageContext.request.contextPath}/images/logo/logo.jpg" alt="TaskManager" class="logo"></a> 
	<a href="${pageContext.request.contextPath}/Controller?page-id=MY01" class="user-name-view">
		<span class="header-user-name"><c:out value="${user.userName}" /></span>
	</a>
	<button id="menuButton" class="menu-btn" type="button"
		aria-label="メニューを開く">☰</button>
</header>
<nav id="menu" class="menu" aria-label="メインメニュー">
	<div class="menu-top">
		<p class="menu-title">メニュー</p>
		<button id="closeButton" class="close-btn" type="button"
			aria-label="メニューを閉じる">×</button>
	</div>
	<div class="menu-list">
		<a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=HO01">ホーム</a>
		<a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=PR01">案件一覧</a>
		<a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=TA01">タスク一覧</a>
		<a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=MO01">月次集計</a>
		<c:if test="${user.role == 1}"> <a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー一覧</a></c:if>
		<a class="menu-link" href="${pageContext.request.contextPath}/Controller?page-id=MY01">マイページ</a>
		<a class="menu-link logout-link" href="${pageContext.request.contextPath}/Controller?page-id=logout">ログアウト</a>
	</div>
</nav>