<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タスク一覧 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/task.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<main class="main">
		<div class="task-page">
			<div class="title-area">
				<h1 class="page-title">タスク一覧</h1>
			</div>

			<div class="task-message-area">
				<c:if test="${not empty msg}">
					<c:out value="${msg}" />
				</c:if>
				<c:if test="${not empty errMsg}">
					<c:out value="${errMsg}" />
				</c:if>
			</div>

			<form method="post"
				action="${pageContext.request.contextPath}/Controller"
				class="task-search-form">
				<input type="hidden" name="page-id" value="TA01">

				<div class="task-search-field">
					<label class="task-search-label" for="project-name">案件名</label> <select
						class="task-search-select" id="project-name" name="project-id">
						<option value="-1">すべて</option>
						<c:forEach var="project" items="${projectList}">
							<option value="${project}">
								<c:out value="${project}" />
							</option>
						</c:forEach>
					</select>
				</div>

				<div class="task-search-field">
					<label class="task-search-label" for="task-status">ステータス</label> <select
						class="task-search-select" id="task-status" name="task-status">
						<option value="-1">すべて</option>
						<option value="0">未着手</option>
						<option value="1">進行中</option>
						<option value="2">完了</option>
						<option value="3">保留</option>
					</select>
				</div>

				<div class="task-search-field">
					<label class="task-search-label" for="keyword">キーワード</label> <input
						type="search" class="task-search-input" id="keyword"
						name="task-name" value="${fn:escapeXml(param['task-name'])}"
						placeholder="タスク名">
				</div>

				<button type="submit" name="btn-id" value="task-search"
					class="task-search-btn">検索</button>
				<button type="submit" name="btn-id" value="task-regist"
					class="task-regist-btn">登録</button>
			</form>

			<!-- 自分のタスク一覧ここから -->
			<section class="my-task-area">

				<div class="card-area">
					<c:forEach var="task" items="${taskList}">
						<div
							class="task-card <c:choose><c:when test='${task.priority == 0}'>priority-low</c:when><c:when test='${task.priority == 1}'>priority-medium</c:when><c:when test='${task.priority == 2}'>priority-high</c:when><c:otherwise>priority-unknown</c:otherwise></c:choose>">
							<!-- タスクメイン情報 -->
							<c:url var="taskDetailUrl" value="/Controller">
								<c:param name="page-id" value="TA04" />
								<c:param name="task-id" value="${task.taskId}" />
							</c:url>
							<h3 class="task-title">
								<a href="${taskDetailUrl}" class="task-link"><c:out
										value="${task.name}" /></a>
							</h3>
							<!-- タスクメイン情報 -->
							<div class="task-card-info">
								<span class="meta-item limit"> 期限：<c:out
										value="${task.limitDate}" /></span> <span
									class="meta-item task-assignee"> 担当者： <c:choose>
										<c:when test="${not empty task.userName}">
											<c:out value="${task.userName}" />
										</c:when>
										<c:otherwise>
											<c:out value="${user.userName}" />
										</c:otherwise>
									</c:choose>
								</span> <span class="meta-item status"> <c:choose>
										<c:when test="${task.status == 0}">
											<span class="status">開始前</span>
										</c:when>
										<c:when test="${task.status == 1}">
											<span class="status">進行中</span>
										</c:when>
										<c:when test="${task.status == 2}">
											<span class="status">完了</span>
										</c:when>
										<c:when test="${task.status == 3}">
											<span class="status">保留</span>
										</c:when>
										<c:otherwise>
											<span class="status">不明</span>
										</c:otherwise>
									</c:choose>
								</span> <span class="hidden"> 優先度: <c:choose>
										<c:when test="${task.priority == 0}">低</c:when>
										<c:when test="${task.priority == 1}">中</c:when>
										<c:when test="${task.priority == 2}">高</c:when>
										<c:otherwise>不明</c:otherwise>
									</c:choose>
								</span> <span class="meta-item time"> 見積工数
									<div class="font-big">
										<c:out value="${task.estimatedWorks}" />
										H
									</div>
								</span>
							</div>
							<!-- 進捗バー -->
							<div class="task-progress">
								<progress value="${task.progress}" max="100"
									class="progress-bar"></progress>
								<span class="progress-text"><c:out
										value="${task.progress}" />%</span>
							</div>
							<div class="task-actions">
								<!-- 工数登録ボタン -->
								<form method="post"
									action="${pageContext.request.contextPath}/Controller"
									style="display: inline;">
									<input type="hidden" name="page-id" value="HO01"> <input
										type="hidden" name="task-id" value="${task.taskId}">
									<button type="submit" name="btn-id" value="work-regist"
										class="btn work-btn">工数登録</button>
								</form>
								<!-- 削除ボタン -->
								<form method="post"
									action="${pageContext.request.contextPath}/Controller"
									style="display: inline;">
									<input type="hidden" name="page-id" value="HO01"> <input
										type="hidden" name="task-id" value="${task.taskId}">
									<button type="submit" name="btn-id" value="task-delete"
										class="btn delete-btn">削除</button>
								</form>
							</div>
						</div>
					</c:forEach>
				</div>
			</section>
			<!-- 自分のタスク一覧ここまで -->
		</div>
	</main>

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
