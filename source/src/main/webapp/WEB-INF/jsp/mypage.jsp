<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>マイページ | TaskManager</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css" />
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/mypage.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<!---------- メインここから ---------->
	<main class="main">
	  <h1>マイページ</h1>
	
	  <!-- プロフィールカードここから -->
	  <section class="profile-card">
	    <div class="pr-card-name">${user.userName}</div>
		<div class="pr-card-role">${user.role}</div>
	  </section>
	  <!-- プロフィールカードここまで -->
	
	  <!-- プロフィールエリアここから -->
	  <section class="profile-area">
	    <h2>プロフィール</h2>
		<p>ID：${user.loginId}</p>
	    <p>名前：${user.userName}</p>
	    <p>Mail：${user.mail}</p>
	  </section>
	  <!-- プロフィールエリアここまで -->
	
	  <!-- パスワード変更エリアここから -->
	  <section class="password-area">
	    <h2>パスワード変更</h2>
	    <form method="POST" action="<c:url value='/Controller'/>">
	      <input type="hidden" name="page-id" value="MY01" />
	      <input type="text" name="password" placeholder="既存" />
	      <input type="text" name="new-password" />
	      <input type="text" name="new-password-confirm" />
	      <p class="error-message">${errMsg}</p>
	      <button type="submit" name="btn-id" value="password-change">変更</button>
	    </form>
	  </section>
	  <!-- パスワード変更エリアここから -->
	</main>
	<!---------- メインここまで ---------->


	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
