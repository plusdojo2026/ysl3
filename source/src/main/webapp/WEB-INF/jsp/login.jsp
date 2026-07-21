<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン | TaskManager</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<!---------- メインここから ---------->
	<main class="main">
		<h1>ログイン</h1>
		<h2>案件・タスク・工数をまとめて管理</h2>
		<h3>アカウントは管理者が発行します</h3>
		<form method="POST" action="<c:url value='/Controller'/>">
			<input type="hidden" name="page-id" value="L001">
			<div class="main">
				<table>
					<tr>
						<td>ログインID</td>
						<br>
						<td><input type="text" name="login-id" value="${param.id }"
							required></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<br>
						<td><input type="password" name="password" required></td>
					</tr>
				</table>
				<div class="msgArea">${errMsg}</div>
			</div>
		</form>
	</main>
	<!---------- メインここまで ---------->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>