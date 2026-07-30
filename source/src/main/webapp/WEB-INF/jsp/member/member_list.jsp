<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メンバー管理 | TaskManager</title>
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
			<h1 class="page-title">メンバー一覧</h1>
		</div>

		<div class="member-msg-area">
			<c:if test="${not empty msg}">
				<p class="member-success-msg">
					<c:out value="${msg}" />
				</p>
			</c:if>
			<c:if test="${not empty errMsg}">
				<p class="member-error-msg">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<section class="member-top">
			<div class="member-search-area">
				<label class="member-search-label" for="search-bar">キーワード</label>
				<div class="member-search-control">
					<input type="search" class="member-search-bar" id="search-bar"
						placeholder="ID・氏名・メールアドレスで検索"> <input type="button"
						class="member-search-btn" id="search-button" value="検索">
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/Controller?page-id=AD02"
				class="member-regist-btn">新規登録</a>
		</section>
		<section class="member-list-area">
			<div class="sub-title-area">
				<h1 class="sub-page-title">登録メンバー</h1>
			</div>

			<div class="member-table-area">
				<table class="member-table">
					<thead>
						<tr>
							<th class="member-id-column">ID</th>
							<th class="member-login-column">ログインID</th>
							<th class="member-name-column">氏名</th>
							<th class="member-role-column">権限</th>
							<th class="member-mail-column">メールアドレス</th>
							<th class="member-state-column">状態</th>
							<th class="member-edit-column">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${allUserList}">
							<tr class="member-row">
								<td class="member-id-cell"><c:out value="${member.id}" /></td>
								<td class="member-login-cell"><c:out
										value="${member.loginId}" /></td>
								<td class="member-name-cell"><c:out
										value="${member.userName}" /></td>
								<td class="member-role-cell"><c:choose>
										<c:when test="${member.role == 1}">管理者</c:when>
										<c:otherwise>一般</c:otherwise>
									</c:choose></td>
								<td class="member-mail-cell" title="${member.mail}"><c:out
										value="${member.mail}" /></td>
								<td class="member-state-cell"><span
									class="member-state <c:choose><c:when test='${member.sol == 1}'>member-active</c:when><c:otherwise>member-inactive</c:otherwise></c:choose>">
										<c:choose>
											<c:when test="${member.sol == 1}">有効</c:when>
											<c:otherwise>無効</c:otherwise>
										</c:choose>
								</span></td>
								<td class="member-edit-cell"><c:url var="memberEditUrl"
										value="/Controller">
										<c:param name="page-id" value="AD03" />
										<c:param name="user-id" value="${member.id}" />
									</c:url> <a href="${memberEditUrl}" class="member-edit-btn">編集</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>