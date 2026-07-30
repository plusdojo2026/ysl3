<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<div class="mypage-page">
			<div class="title-area">
				<h1 class="page-title">マイページ</h1>
			</div>
			<div class="mypage-message-area">
				<c:if test="${not empty errMsg}">
					<c:out value="${errMsg}" />
				</c:if>
			</div>
			<section class="mypage-profile-card">
				<div class="mypage-profile-main">
					<p class="mypage-profile-name">
						<c:out value="${user.userName}" />
					</p>
					<p class="mypage-profile-id">
						ID：
						<c:out value="${user.loginId}" />
					</p>
				</div>
				<div class="mypage-role">
					<c:choose>
						<c:when test="${user.role == 1}">管理者</c:when>
						<c:when test="${user.role == 0}">一般</c:when>
						<c:otherwise>メンバー</c:otherwise>
					</c:choose>
				</div>
			</section>
			<div class="mypage-content">
				<section class="mypage-card">
					<div class="mypage-section-title-area">
						<h2 class="mypage-section-title">プロフィール</h2>
					</div>
					<div class="mypage-profile-row">
						<div class="mypage-profile-label">ログインID</div>
						<div class="mypage-profile-value">
							<c:out value="${user.loginId}" />
						</div>
					</div>
					<div class="mypage-profile-row">
						<div class="mypage-profile-label">名前</div>
						<div class="mypage-profile-value">
							<c:out value="${user.userName}" />
						</div>
					</div>
					<div class="mypage-profile-row">
						<div class="mypage-profile-label">メール</div>
						<div class="mypage-profile-value">
							<c:out value="${user.mail}" />
						</div>
					</div>
				</section>
				<section class="mypage-card">
					<div class="mypage-section-title-area">
						<h2 class="mypage-section-title">パスワード変更</h2>
					</div>
					<form class="mypage-password-form" method="POST" action="<c:url value='/Controller'/>">
						<input type="hidden" name="page-id" value="MY01">
						<div class="mypage-field">
							<label class="mypage-label">現在のパスワード</label>
							<input class="mypage-input" type="password" name="password" placeholder="現在のパスワード">
						</div>
						<div class="mypage-field">
							<label class="mypage-label">新しいパスワード</label>
							<input class="mypage-input" type="password" name="new-password" placeholder="新しいパスワード">
						</div>
						<div class="mypage-field">
							<label class="mypage-label">新しいパスワード（確認）</label>
							<input class="mypage-input" type="password" name="new-password-confirm" placeholder="もう一度入力してください">
						</div>
						<div class="mypage-button-area">
							<button class="pw-change-btn" type="submit" name="btn-id" value="password-change">変更</button>
						</div>
					</form>
				</section>
			</div>
		</div>
	</main>

	<!---------- メインここまで ---------->


	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
