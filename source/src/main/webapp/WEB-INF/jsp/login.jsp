<%@ page contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>ログイン | TaskManager</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css" />
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/login.js" defer></script>
</head>

<body>
	<header class="header">
		<a href="${pageContext.request.contextPath}/Controller?page-id=HO01"
			class="system-title">TaskManager</a>
	</header>
	<!---------- メインここから ---------->
	<main class="main">
		<section class="login-card">
			<h1 class="login-title">ログイン</h1>
			<h2 class="sub-title">案件・タスク・工数をまとめて管理</h2>
			<h3 class="ad-msg">アカウントは管理者が発行します</h3>
			<form method="POST"
				action="${pageContext.request.contextPath}/Controller">
				<div class="mini-card">
					<input type="hidden" name="page-id" value="LO01" />
					<div class="login-text">ログインID</div>
					<input type="text" name="login-id" class="login-input" required placeholder="ログインIDを入力してください"/>
					<div class="login-text">パスワード</div>
					<input type="password" name="password" class="login-input" required placeholder="パスワードを入力してください" id="password" />
				</div>



				<p class="error-message">
					${errMsg}
				</p>
				<button type="submit" name="btn-id" value="login" class="login-btn" id="login-btn">ログイン</button>


			</form>
			<div id="signup" class="signup">ログインID・パスワードを忘れた方はこちら</div>
		</section>
	</main>
	<!---------- メインここまで ---------->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
