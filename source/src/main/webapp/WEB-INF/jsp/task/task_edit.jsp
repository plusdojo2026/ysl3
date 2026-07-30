<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タスク編集 | TaskManager</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/task.css" />
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<!---------- メインここから ---------->
	<main class="main">
		<div class="management-page">
			<div class="title-area">
				<h1 class="page-title">タスク編集</h1>
			</div>
			<div class="management-message-area">
				<c:if test="${not empty msg}">
					<c:out value="${msg}" />
				</c:if>
				<c:if test="${not empty errMsg}">
					<c:out value="${errMsg}" />
				</c:if>
			</div>
			<form class="management-form-card" method="POST"
				action="<c:url value='/Controller'/>">
				<input type="hidden" name="task-id" value="${editTask.taskId}">
				<input type="hidden" name="project-id" value="${editTask.projectId}">
				<input type="hidden" name="page-id" value="TA03">
				<div class="management-form-grid">
					<div class="management-field">
						<label class="management-label"> 案件名 <span
							class="management-required">(必須)</span>
						</label> <input class="management-input" type="text" name="project-name"
							value="${editTask.projectName}"readonly">
					</div>
					<div class="management-field">
						<label class="management-label"> タスク名 <span
							class="management-required">(必須)</span>
						</label> <input class="management-input" type="text" name="task-name"
							value="${editTask.name}" maxlength="20">
					</div>
					<div class="management-field">
						<label class="management-label"> ステータス <span
							class="management-required">(必須)</span>
						</label> <select class="management-select" name="task-status">
							<option value="0" ${editTask.status == 0 ? 'selected' : ''}>
								未着手</option>
							<option value="1" ${editTask.status == 1 ? 'selected' : ''}>
								進行中</option>
							<option value="2" ${editTask.status == 2 ? 'selected' : ''}>
								完了</option>
							<option value="3" ${editTask.status == 3 ? 'selected' : ''}>
								保留</option>
						</select>
					</div>
					<div class="management-field">
						<label class="management-label"> 優先度 <span
							class="management-required">(必須)</span>
						</label> <select class="management-select" name="task-priority">
							<option value="0" ${editTask.priority == 0 ? 'selected' : ''}>
								低</option>
							<option value="1" ${editTask.priority == 1 ? 'selected' : ''}>
								中</option>
							<option value="2" ${editTask.priority == 2 ? 'selected' : ''}>
								高</option>
						</select>
					</div>
					<div class="management-field">
						<label class="management-label" for="user-id"> 担当者 <span
							class="management-required">（必須）</span>
						</label> <select class="management-select" id="user-id" name="user-id"
							required>
							<c:forEach var="member" items="${memberList}">
								<option value="${member.id}"
									${editTask.userId == member.id ? 'selected' : ''}>
									<c:out value="${member.userName}" />
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="management-field">
						<label class="management-label">予算工数</label> <input
							class="management-input" type="number"
							name="task-estimated-works" min="0.5" step="0.5"
							value="${editTask.estimatedWorks}" required>
					</div>
					<div class="management-field management-field-wide">
						<label class="management-label">進捗率</label> <input
							class="management-range" type="range" name="progress" min="0"
							max="100" value="${editTask.progress}" step="5" list="marks">
						<datalist id="marks">
							<option value="0" label="0%"></option>
							<option value="5" label="5%"></option>
							<option value="10" label="10%"></option>
							<option value="15" label="15%"></option>
							<option value="20" label="20%"></option>
							<option value="25" label="25%"></option>
							<option value="30" label="30%"></option>
							<option value="35" label="35%"></option>
							<option value="40" label="40%"></option>
							<option value="45" label="45%"></option>
							<option value="50" label="50%"></option>
							<option value="55" label="55%"></option>
							<option value="60" label="60%"></option>
							<option value="65" label="65%"></option>
							<option value="70" label="70%"></option>
							<option value="75" label="75%"></option>
							<option value="80" label="80%"></option>
							<option value="85" label="85%"></option>
							<option value="90" label="90%"></option>
							<option value="95" label="95%"></option>
							<option value="100" label="100%"></option>
						</datalist>
					</div>
					<div class="management-field management-field-wide">
						<label class="management-label">開始日/期限</label>
						<div class="management-date-period">
							<input class="management-input" type="date"
								name="task-start-date" value="${editTask.startDate}"> <span
								class="management-date-separator">ー</span> <input
								class="management-input" type="text" name="task-limit"
								value="${editTask.limitDate}">
						</div>
					</div>
					<div class="management-field management-field-wide">
						<label class="management-label">説明</label> <input
							class="management-input" type="date" name="task-explanation"
							value="${editTask.explainText}" maxlength="1000">
					</div>
				</div>
				<div class="management-btn-area">
					<button class="management-submit-btn" type="submit" name="btn-id"
						value="task-update">保存</button>
				</div>
			</form>
		</div>
	</main>
	<!---------- メインここまで ---------->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>