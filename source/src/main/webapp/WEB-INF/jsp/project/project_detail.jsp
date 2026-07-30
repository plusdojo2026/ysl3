<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>案件詳細 | TaskManager</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/project.js" defer></script>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main">
		<div class="title-area"><h1 class="page-title">案件詳細</h1></div>
		<div class="project-message-area">
			<c:if test="${not empty msg}">
				<p class="project-success-message">
					<c:out value="${msg}" />
				</p>
			</c:if>
			<c:if test="${not empty errMsg}">
				<p class="project-error-message">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<div class="home-layout">
			<div class="home-main">
				<section
					class="project-detail-card <c:choose><c:when test='${projectDetail.priority == 0}'>priority-low</c:when><c:when test='${projectDetail.priority == 1}'>priority-medium</c:when><c:when test='${projectDetail.priority == 2}'>priority-high</c:when><c:otherwise>priority-unknown</c:otherwise></c:choose>">
					<div class="project-detail-heading">
						<div class="project-detail-name-area">
							<p class="project-detail-code">
								<c:out value="${projectDetail.code}" />
							</p>
							<h2 class="project-detail-name">
								<c:out value="${projectDetail.name}" />
							</h2>
						</div>
						<c:url var="projectEditUrl" value="/Controller">
							<c:param name="page-id" value="PR03" />
							<c:param name="project-id" value="${projectDetail.id}" />
						</c:url>
						<a href="${projectEditUrl}" class="project-edit-btn">案件編集</a>
					</div>
					<div class="project-detail-grid">
						<div class="project-detail-item">
							<span class="project-detail-label">顧客名</span> <span
								class="project-detail-value"><c:out
									value="${projectDetail.customer}" /></span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">PM名</span> <span
								class="project-detail-value"><c:out
									value="${projectDetail.pmName}" /></span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">ステータス</span> <span
								class="project-detail-status"> <c:choose>
									<c:when test="${projectDetail.status == 0}">開始前</c:when>
									<c:when test="${projectDetail.status == 1}">進行中</c:when>
									<c:when test="${projectDetail.status == 2}">完了</c:when>
									<c:when test="${projectDetail.status == 3}">保留</c:when>
									<c:otherwise>不明</c:otherwise>
								</c:choose>
							</span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">優先度</span> <span
								class="project-detail-value"> <c:choose>
									<c:when test="${projectDetail.priority == 0}">低</c:when>
									<c:when test="${projectDetail.priority == 1}">中</c:when>
									<c:when test="${projectDetail.priority == 2}">高</c:when>
									<c:otherwise>不明</c:otherwise>
								</c:choose>
							</span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">必要合計工数</span> <span
								class="project-detail-value project-detail-work"><c:out
									value="${projectDetail.estimatedWork}" /> H</span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">現在の合計工数</span> <span
								class="project-detail-value project-detail-work"><c:out
									value="${projectDetail.totalWork}" /> H</span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">開始日</span> <span
								class="project-detail-value"><c:out
									value="${projectDetail.startDate}" /></span>
						</div>
						<div class="project-detail-item">
							<span class="project-detail-label">終了日</span> <span
								class="project-detail-value"><c:out
									value="${projectDetail.endDate}" /></span>
						</div>
						<div class="project-detail-item project-detail-item-wide">
							<span class="project-detail-label">期限日</span> <span
								class="project-detail-value"><c:out
									value="${projectDetail.limitDate}" /></span>
						</div>
					</div>
					<div class="project-detail-progress">
						<span class="project-detail-label">案件進捗</span>
						<div class="project-progress">
							<progress value="${projectDetail.progressRate}" max="100"
								class="progress-bar"></progress>
							<span class="progress-text"><c:out
									value="${projectDetail.progressRate}" />%</span>
						</div>
					</div>
					<div class="project-detail-explanation">
						<span class="project-detail-label">案件概要</span>
						<p class="project-detail-explanation-text">
							<c:out value="${projectDetail.explainText}" />
						</p>
					</div>
				</section>
				<section class="project-task-area">
					<div class="sub-title-area project-task-title-area">
						<h1 class="sub-page-title">タスク一覧</h1>
						<form class="project-task-regist-form" method="get"
							action="<c:url value='/Controller'/>">
							<input type="hidden" name="page-id" value="TA02"> <input
								type="hidden" name="project-id" value="${projectDetail.id}">
							<button type="submit" class="project-task-regist-btn">＋タスク追加</button>
						</form>
					</div>
					<c:choose>
						<c:when test="${empty projectTaskList}">
							<p class="project-empty-message">登録されているタスクはありません。</p>
						</c:when>
						<c:otherwise>
							<div class="card-area">
								<c:forEach var="task" items="${projectTaskList}">
									<div
										class="task-card <c:choose><c:when test='${task.priority == 0}'>priority-low</c:when><c:when test='${task.priority == 1}'>priority-medium</c:when><c:when test='${task.priority == 2}'>priority-high</c:when><c:otherwise>priority-unknown</c:otherwise></c:choose>">
										<c:url var="taskDetailUrl" value="/Controller">
											<c:param name="page-id" value="TA04" />
											<c:param name="task-id" value="${task.taskId}" />
										</c:url>
										<h3 class="task-title">
											<a href="${taskDetailUrl}" class="task-link"><c:out
													value="${task.name}" /></a>
										</h3>
										<div class="task-card-info">
											<span class="meta-item limit">期限：<c:out
													value="${task.limitDate}" /></span> <span
												class="meta-item task-assignee">担当者：<c:out
													value="${task.userName}" /></span> <span class="meta-item status">
												<c:choose>
													<c:when test="${task.status == 0}">開始前</c:when>
													<c:when test="${task.status == 1}">進行中</c:when>
													<c:when test="${task.status == 2}">完了</c:when>
													<c:when test="${task.status == 3}">保留</c:when>
													<c:otherwise>不明</c:otherwise>
												</c:choose>
											</span> <span class="meta-item time">見積工数 <span
												class="font-big"><c:out
														value="${task.estimatedWorks}" />H</span>
											</span>
										</div>
										<div class="task-progress">
											<progress value="${task.progress}" max="100"
												class="progress-bar"></progress>
											<span class="progress-text"><c:out
													value="${task.progress}" />%</span>
										</div>
										<div class="task-actions project-task-actions">
											<c:url var="workRegistUrl" value="/Controller">
												<c:param name="page-id" value="WO01" />
												<c:param name="task-id" value="${task.taskId}" />
											</c:url>
											<a href="${workRegistUrl}" class="btn work-btn">工数登録</a>
											<c:url var="taskEditUrl" value="/Controller">
												<c:param name="page-id" value="TA03" />
												<c:param name="task-id" value="${task.taskId}" />
											</c:url>
											<a href="${taskEditUrl}" class="btn project-task-edit-btn">編集</a>
											<form method="post"
												action="${pageContext.request.contextPath}/Controller"
												class="project-task-delete-form">
												<input type="hidden" name="page-id" value="PR04"> <input
													type="hidden" name="project-id" value="${projectDetail.id}">
												<input type="hidden" name="task-id" value="${task.taskId}">
												<button type="submit" name="btn-id" value="task-delete"
													class="btn delete-btn"
													onclick="return confirm('このタスクを削除しますか？');">削除</button>
											</form>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
				</section>
			</div>
			<section class="home-side">
				<div class="sub-title-area">
					<h1 class="sub-page-title">工数ログ履歴</h1>
				</div>
				<c:choose>
					<c:when test="${empty projectWorkList}">
						<p class="project-empty-message">登録されている工数ログはありません。</p>
					</c:when>
					<c:otherwise>
						<div class="home-work-log">
							<c:forEach var="workLog" items="${projectWorkList}">
								<details class="work-log-card">
									<summary class="work-log-summary">
										<span class="home-work-log-time"><c:out
												value="${workLog.work}" />時間</span> <span
											class="home-work-log-name"><c:out
												value="${workLog.taskName}" /></span> <span
											class="home-work-log-date"><c:out
												value="${workLog.workDate}" /></span> <span
											class="work-log-open-label"><span
											class="work-log-arrow"></span></span>
									</summary>
									<div class="work-log-detail">
										<p class="work-log-detail-row">
											<span class="work-log-detail-label">案件名</span> <span
												class="work-log-detail-value"><c:out
													value="${projectDetail.name}" /></span>
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
											class="work-log-delete-form">
											<input type="hidden" name="page-id" value="TA04"> <input
												type="hidden" name="task-id" value="${workLog.taskId}">
											<input type="hidden" name="work-id" value="${workLog.id}">
											<button type="submit" name="btn-id" value="work-delete"
												class="btn delete-btn work-log-delete-btn"
												onclick="return confirm('この工数ログを削除しますか？');">削除</button>
										</form>
									</div>
								</details>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</section>
		</div>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>