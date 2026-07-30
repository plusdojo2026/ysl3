<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>案件登録 | TaskManager</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/project.js" defer></script>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main">
		<div class="management-page">
			<div class="title-area">
				<h1 class="page-title">案件新規登録</h1>
			</div>
			<!-- メッセージ表示領域 -->
			<div class="management-message-area">
				<c:if test="${not empty msg}">
					<c:out value="${msg}" />
				</c:if>
				<c:if test="${not empty errMsg}">
					<c:out value="${errMsg}" />
				</c:if>
			</div>
			<!-- 案件登録フォーム -->
			<form class="management-form-card" method="POST"
				action="<c:url value='/Controller'/>">
				<input type="hidden" name="page-id" value="PR02">
				<div class="management-form-grid">
					<!-- 案件コード -->
					<div class="management-field">
						<label class="management-label" for="project-code"> 案件コード
							<span class="management-required">（必須）</span>
						</label> <input class="management-input" type="text" id="project-code"
							name="project-code" maxlength="20" required>
					</div>
					<!-- 案件名 -->
					<div class="management-field">
						<label class="management-label" for="project-name"> 案件名 <span
							class="management-required">（必須）</span>
						</label> <input class="management-input" type="text" id="project-name"
							name="project-name" maxlength="100" required>
					</div>
					<!-- 顧客名 -->
					<div class="management-field">
						<label class="management-label" for="customer"> 顧客名 <span
							class="management-required">（必須）</span>
						</label> <input class="management-input" type="text" id="customer"
							name="customer" maxlength="50" required>
					</div>
					<!-- PM -->
					<div class="management-field">
						<label class="management-label" for="pm-id"> PM <span
							class="management-required">（必須）</span>
						</label> <select class="management-select" id="pm-id" name="pm-id"
							required>
							<option class="management-placeholder-option" value="" disabled
								selected>選択してください</option>
							<c:forEach var="user" items="${pmList}">
								<option value="${user.id}">
									<c:out value="${user.userName}" />
								</option>
							</c:forEach>
						</select>
					</div>
					<!-- ステータス -->
					<div class="management-field">
						<label class="management-label" for="project-status">
							ステータス <span class="management-required">（必須）</span>
						</label> <select class="management-select" id="project-status"
							name="project-status" required>
							<option value="0" selected>開始前</option>
							<option value="1">進行中</option>
							<option value="2">完了</option>
							<option value="3">保留</option>
						</select>
					</div>
					<!-- 優先度 -->
					<div class="management-field">
						<label class="management-label" for="project-priority">
							優先度 <span class="management-required">（必須）</span>
						</label> <select class="management-select" id="project-priority"
							name="project-priority" required>
							<option value="0">低</option>
							<option value="1" selected>中</option>
							<option value="2">高</option>
						</select>
					</div>
					<!-- 開始日・終了日 -->
					<div class="management-field management-field-wide">
						<label class="management-label"> 開始日／終了日 </label>
						<div class="management-date-period">
							<input class="management-input" type="date"
								name="project-start-date" required> <span
								class="management-date-separator">ー</span> <input
								class="management-input" type="date" name="project-end-date">
						</div>
					</div>
					<!-- 見積工数 -->
					<div class="management-field">
						<label class="management-label" for="project-estimated-works">
							見積工数 <span class="management-required">（必須）</span>
						</label> <input class="management-input" type="number"
							id="project-estimated-works" name="project-estimated-works"
							min="0.5" step="0.5" required>
					</div>
					<!-- 期限 -->
					<div class="management-field">
						<label class="management-label" for="project-limit"> 期限 <span
							class="management-required">（必須）</span>
						</label> <input class="management-input" type="date" id="project-limit"
							name="project-limit" required>
					</div>
					<!-- 案件概要 -->
					<div class="management-field management-field-wide">
						<label class="management-label" for="project-explain">
							案件概要 </label> <input class="management-input" type="text"
							id="project-explain" name="project-explain" maxlength="2000">
					</div>
				</div>
				<!-- 登録ボタン -->
				<div class="management-btn-area">
					<button class="management-submit-btn" type="submit" name="btn-id"
						value="登録">登録</button>
				</div>
			</form>
		</div>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
