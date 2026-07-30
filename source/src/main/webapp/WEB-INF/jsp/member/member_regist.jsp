<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メンバー新規登録 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/member.js" defer></script>
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main member-page">
		<div class="title-area">
			<h1 class="page-title">メンバー登録</h1>
		</div>

		<div class="member-msg-area">
			<c:if test="${not empty errMsg}">
				<p class="member-error-msg">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<c:set var="selectedRole"
			value="${empty param.role ? '0' : param.role}" />
		<form method="post"
			action="${pageContext.request.contextPath}/Controller"
			class="member-form-card">
			<input type="hidden" name="page-id" value="AD02">
			<div class="member-form-grid">
				<div class="member-field">
					<label class="member-label" for="login-id">ログインID <span
						class="member-required">必須</span>
					</label> <input type="text" class="member-input member-input-small"
						id="login-id" name="login-id"
						value="${fn:escapeXml(param['login-id'])}" maxlength="10"
						autocomplete="username" required>
					<p class="member-field-note">10文字以内</p>
				</div>
				<div class="member-field">
					<label class="member-label" for="user-name">氏名 <span
						class="member-required">必須</span>
					</label> <input type="text" class="member-input member-input-small"
						id="user-name" name="user-name"
						value="${fn:escapeXml(param['user-name'])}" maxlength="10"
						required>
					<p class="member-field-note">10文字以内</p>
				</div>
				<div class="member-field">
					<label class="member-label" for="mail">メールアドレス <span
						class="member-required">必須</span>
					</label> <input type="email" class="member-input member-input-mail"
						id="mail" name="mail" value="${fn:escapeXml(param.mail)}"
						maxlength="255" autocomplete="email" required>
				</div>
				<div class="member-field">
					<label class="member-label" for="password">初期パスワード <span
						class="member-required">必須</span>
					</label> <input type="password" class="member-input member-input-password"
						id="password" name="password" minlength="6" maxlength="20"
						pattern="(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,20}"
						title="半角英字と数字を含む6文字以上20文字以内で入力してください" autocomplete="new-password"
						required>
					<p class="member-field-note">半角英字と数字を含む6～20文字</p>
				</div>
				<fieldset class="member-choice-field">
					<legend class="member-label">権限</legend>
					<div class="member-choice-area">
						<label class="member-choice-label"> <input type="radio"
							name="role" value="0" ${selectedRole == '0' ? 'checked' : ''}>
							一般
						</label> <label class="member-choice-label"> <input type="radio"
							name="role" value="1" ${selectedRole == '1' ? 'checked' : ''}>
							管理者
						</label>
					</div>
				</fieldset>
			</div>
			<div class="member-form-btn-area">
				<button type="submit" name="btn-id" value="member-regist"
					class="member-submit-btn">登録</button>
			</div>
		</form>

	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>