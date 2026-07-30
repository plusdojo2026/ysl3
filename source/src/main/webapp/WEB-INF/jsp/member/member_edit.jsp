<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メンバー編集 | TaskManager</title>
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
			<h1 class="page-title">メンバー編集</h1>
		</div>

		<div class="member-msg-area">
			<c:if test="${not empty errMsg}">
				<p class="member-error-msg">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<form method="post"
			action="${pageContext.request.contextPath}/Controller"
			class="member-form-card">
			<input type="hidden" name="page-id" value="AD03"> <input
				type="hidden" name="user-id" value="${member.id}">
			<div class="member-edit-target">
				<span class="member-edit-target-label">編集対象</span> <span
					class="member-edit-target-name"><c:out
						value="${member.userName}" /></span>
			</div>
			<div class="member-form-grid">
				<div class="member-field">
					<label class="member-label" for="user-name">氏名 <span
						class="member-required">必須</span>
					</label> <input type="text" class="member-input member-input-small"
						id="user-name" name="user-name"
						value="${fn:escapeXml(member.userName)}" maxlength="10" required>
					<p class="member-field-note">10文字以内</p>
				</div>
				<div class="member-field">
					<label class="member-label" for="mail">メールアドレス <span
						class="member-required">必須</span>
					</label> <input type="email" class="member-input member-input-mail"
						id="mail" name="mail" value="${fn:escapeXml(member.mail)}"
						maxlength="255" required>
				</div>
				<fieldset class="member-choice-field">
					<legend class="member-label">権限</legend>
					<div class="member-choice-area">
						<label class="member-choice-label"> <input type="radio"
							name="role" value="0" ${member.role == 0 ? 'checked' : ''}>
							一般
						</label> <label class="member-choice-label"> <input type="radio"
							name="role" value="1" ${member.role == 1 ? 'checked' : ''}>
							管理者
						</label>
					</div>
				</fieldset>
				<fieldset class="member-choice-field">
					<legend class="member-label">状態</legend>
					<div class="member-choice-area">
						<label class="member-choice-label"> <input type="radio"
							name="sol" value="1" ${member.sol == 1 ? 'checked' : ''}>
							有効
						</label> <label class="member-choice-label"> <input type="radio"
							name="sol" value="0" ${member.sol == 0 ? 'checked' : ''}>
							無効
						</label>
					</div>
				</fieldset>
			</div>
			<div class="member-form-btn-area">
				<button type="submit" name="btn-id" value="member-update"
					class="member-submit-btn">保存</button>
			</div>
		</form>

	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>