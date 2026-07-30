<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>工数登録 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/work_regist.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/work_regist.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<!---------- メインここから ---------->
	<main class="main">
		<section class="work-regist-area">
			<div class="title-area">
				<h1 class="page-title">工数登録</h1>
			</div>

			<form method="POST" action="<c:url value='/Controller'/>">
			
			    <input type="hidden" name="task-id" value="${TaskShow.taskId}">


				<!-- 案件名・タスク名 -->
				<div class="work-target-card">
					<div class="work-target-area">
						<p class="work-target-label">案件名</p>
						<p class="work-project-name" title="${ProjectShow.name }">
						<c:out value="${ProjectShow.name }" /></p>
					</div>
					<div class="work-target-area work-target-task-area">
						<p class="work-target-label">タスク名</p>
						<p class="work-task-name" title="${TaskShow.name }">
						<c:out value="${TaskShow.name }" /></p>
					</div>
				</div>

				<!-- 日付・作業内容 -->
				<div class="work-input-row">
					<div class="work-input-group">
						<div class="work-input-title">日付（必須）</div>
						<input type="date" id="day" name="work-date" value="<%=LocalDate.now()%>" class="work-input">
					</div>
					<div class="work-input-group">
						<div class="work-input-title">作業内容</div>
						<input type="text" id="work-explanation" name="explain-text" class="work-input">
					</div>
				</div>

				<!-- 工数 -->
				<div class="work-slider-card">
					<h2 class="work-slider-title">実施工数（必須、0.5時間刻み）</h2>
					<div class="work-time-display">
						<span id="time-label">0.5時間</span>
					</div>
					<input type="range" id="work" name="work" min="0.5" max="24"
						step="0.5" value="0.5" class="work-slider"
						oninput="updateTime(this.value)">
				</div>

				<!-- 登録ボタン -->
				<div class="work-btn-area">
					<input type="hidden" name="page-id" value="WO01">
					<button type="submit" name="btn-id" value="work-regist"
						class="regist-btn">登録</button>
				</div>
			</form>

			<!-- メッセージ表示場所 -->
			<div class="work-message-area">
				<c:if test="${not empty errMsg}">
					<p class="work-error-message">
						<c:out value="${errMsg}" />
					</p>
				</c:if>
			</div>
		</section>
	</main>

	<!---------- メインここまで ---------->

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
