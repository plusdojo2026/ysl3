<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タスク詳細 | TaskManager</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main">
		<div class="title-area">
			<h1 class="page-title">タスク詳細</h1>
		</div>
		<div class="task-msg-area">
			<c:if test="${not empty msg}">
				<p class="task-success-msg">
					<c:out value="${msg}" />
				</p>
			</c:if>
			<c:if test="${not empty errMsg}">
				<p class="task-error-msg">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<div class="home-layout">
			<div class="home-main">
				<section
					class="task-detail-card <c:choose><c:when test='${taskDetail.priority == 0}'>priority-low</c:when><c:when test='${taskDetail.priority == 1}'>priority-medium</c:when><c:when test='${taskDetail.priority == 2}'>priority-high</c:when><c:otherwise>priority-unknown</c:otherwise></c:choose>">
					<div class="task-detail-heading">
						<div class="task-detail-name-area">
							<p class="task-detail-project-name">
								<c:out value="${taskDetail.projectName}" />
							</p>
							<h2 class="task-detail-name">
								<c:out value="${taskDetail.name}" />
							</h2>
						</div>
						<c:url var="taskEditUrl" value="/Controller">
							<c:param name="page-id" value="TA03" />
							<c:param name="task-id" value="${param['task-id']}" />
						</c:url>
						<a href="${taskEditUrl}" class="task-edit-btn">タスク編集</a>
					</div>
					<div class="task-detail-grid">
						<div class="task-detail-item">
							<span class="task-detail-label">担当者</span> <span
								class="task-detail-value"><c:out
									value="${taskDetail.userName}" /></span>
						</div>
						<div class="task-detail-item">
							<span class="task-detail-label">ステータス</span> <span
								class="task-detail-status <c:choose><c:when test='${taskDetail.status == 0}'>status-not-started</c:when><c:when test='${taskDetail.status == 1}'>status-in-progress</c:when><c:when test='${taskDetail.status == 2}'>status-completed</c:when><c:when test='${taskDetail.status == 3}'>status-on-hold</c:when></c:choose>">
								<c:choose>
									<c:when test="${taskDetail.status == 0}">未着手</c:when>
									<c:when test="${taskDetail.status == 1}">進行中</c:when>
									<c:when test="${taskDetail.status == 2}">完了</c:when>
									<c:when test="${taskDetail.status == 3}">保留</c:when>
									<c:otherwise>不明</c:otherwise>
								</c:choose>
							</span>
						</div>
						<div class="task-detail-item">
							<span class="task-detail-label">見積工数</span> <span
								class="task-detail-value task-detail-work"><c:out
									value="${taskDetail.estimatedWorks}" /> H</span>
						</div>
						<div class="task-detail-item">
							<span class="task-detail-label">開始日</span> <span
								class="task-detail-value"><c:out
									value="${taskDetail.startDate}" /></span>
						</div>
						<div class="task-detail-item task-detail-item-wide">
							<span class="task-detail-label">期限日</span> <span
								class="task-detail-value"><c:out
									value="${taskDetail.limitDate}" /></span>
						</div>
					</div>
					<div class="task-detail-progress">
						<span class="task-detail-label">進捗度</span>
						<div class="task-progress">
							<progress value="${taskDetail.progress}" max="100"
								class="progress-bar"></progress>
							<span class="progress-text"><c:out
									value="${taskDetail.progress}" />%</span>
						</div>
					</div>
					<div class="task-detail-explanation">
						<span class="task-detail-label">タスク概要</span>
						<p class="task-detail-explanation-text">
							<c:out value="${taskDetail.explainText}" />
						</p>
					</div>
				</section>
				<section class="task-status-area">
					<div class="sub-title-area">
						<h1 class="sub-page-title">ステータス変更</h1>
					</div>
					<form method="post"
						action="${pageContext.request.contextPath}/Controller">
						<input type="hidden" name="page-id" value="TA04"> <input
							type="hidden" name="btn-id" value="task-status-change"> <input
							type="hidden" name="task-id" value="${param['task-id']}">
						<div class="task-status-btn-area">
							<c:if test="${taskDetail.status != 0}">
								<button type="submit" name="task-status" value="0"
									class="task-status-btn status-not-started">未着手に戻す</button>
							</c:if>
							<c:if test="${taskDetail.status != 1}">
								<button type="submit" name="task-status" value="1"
									class="task-status-btn status-in-progress">進行中にする</button>
							</c:if>
							<c:if test="${taskDetail.status != 2}">
								<button type="submit" name="task-status" value="2"
									class="task-status-btn status-completed">完了にする</button>
							</c:if>
							<c:if test="${taskDetail.status != 3}">
								<button type="submit" name="task-status" value="3"
									class="task-status-btn status-on-hold">保留にする</button>
							</c:if>
						</div>
					</form>
				</section>
			</div>
			<section class="home-side">
				<div class="sub-title-area task-work-title-area">
					<h1 class="sub-page-title">工数ログ履歴</h1>
					<c:url var="workRegistUrl" value="/Controller">
						<c:param name="page-id" value="WO01" />
						<c:param name="task-id" value="${param['task-id']}" />
					</c:url>
					<a href="${workRegistUrl}" class="task-work-regist-btn">工数登録</a>
				</div>

				<div class="home-work-log">
					<c:forEach var="workLog" items="${taskWorkList}">
						<details class="work-log-card">
							<summary class="work-log-summary">
								<span class="home-work-log-time"><c:out
										value="${workLog.work}" />時間</span> <span class="home-work-log-name"><c:out
										value="${taskDetail.name}" /></span> <span
									class="home-work-log-date"><c:out
										value="${workLog.workDate}" /></span> <span
									class="work-log-open-label"><span class="work-log-arrow"></span></span>
							</summary>
							<div class="work-log-detail">
								<p class="work-log-detail-row">
									<span class="work-log-detail-label">案件名</span> <span
										class="work-log-detail-value"><c:out
											value="${taskDetail.projectName}" /></span>
								</p>
								<p class="work-log-detail-row">
									<span class="work-log-detail-label">作業内容</span> <span
										class="work-log-detail-value"><c:out
											value="${workLog.explainText}" /></span>
								</p>
								<p class="work-log-detail-row">
									<span class="work-log-detail-label">担当者</span> <span
										class="work-log-detail-value"><c:out
											value="${workLog.userName}" /></span>
								</p>
								<form method="post"
									action="${pageContext.request.contextPath}/Controller"
									class="task-work-delete-form">
									<input type="hidden" name="page-id" value="TA04"> <input
										type="hidden" name="task-id" value="${param['task-id']}">
									<input type="hidden" name="work-id" value="${workLog.id}">
									<button type="submit" name="btn-id" value="work-delete"
										class="btn delete-btn task-work-delete-btn"
										onclick="return confirm('この工数ログ削除しますか？');">削除</button>
								</form>
							</div>
						</details>
					</c:forEach>
				</div>
			</section>
		</div>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>