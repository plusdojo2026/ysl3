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
			</form>

			<section class="task-list-card">
				<c:choose>
					<c:when test="${empty taskList}">
						<p class="task-empty">表示できるタスクはありません。</p>
					</c:when>

					<c:otherwise>
						<table class="task-table">
							<thead class="task-table-head">
								<tr>
									<th class="task-table-title">案件名</th>
									<th class="task-table-title">タスク名</th>
									<th class="task-table-title">担当者</th>
									<th class="task-table-title">状態</th>
									<th class="task-table-title">優先度</th>
									<th class="task-table-title">期限</th>
									<th class="task-table-title">見積</th>
									<th class="task-table-title">進捗</th>
									<th class="task-table-title">操作</th>
								</tr>
						</table>
						<tbody>
							<c:forEach var="task" items="${taskList}">
								<tr class="task-row">
									<td class="task-data task-project-name"
										title="${fn:escapeXml(task.projectName)}"><c:out
											value="${task.projectName}" /></td>
									<td class="task-data"><c:url var="taskDetailUrl"
											value="/Controller">
											<c:param name="page-id" value="TA04" />
											<c:param name="task-id" value="${task.taskId}" />
										</c:url> <a href="${taskDetailUrl}" class="task-name-link"
										title="${fn:escapeXml(task.name)}"> <c:out
												value="${task.name}" />
									</a></td>
									<td class="task-data"><c:out value="${task.userName}" />
									</td>
									<td class="task-data"><c:choose>
											<c:when test="${task.status == 0}">未着手</c:when>
											<c:when test="${task.status == 1}">進行中</c:when>
											<c:when test="${task.status == 2}">完了</c:when>
											<c:when test="${task.status == 3}">保留</c:when>
										</c:choose></td>
									<td class="task-data"><c:choose>
											<c:when test="${task.priority == 0}">低</c:when>
											<c:when test="${task.priority == 1}">中</c:when>
											<c:when test="${task.priority == 2}">高</c:when>
										</c:choose></td>
									<td class="task-data"><c:out value="${task.limitDate}" />
									</td>
									<td class="task-data"><c:out
											value="${task.estimatedWorks}" />h</td>
									<td class="task-data"><progress class="task-progress"
											value="${task.progress}" max="100"></progress> <c:out
											value="${task.progress}" />%</td>
									<td class="task-data">
										<form method="post"
											action="${pageContext.request.contextPath}/Controller"
											class="task-edit-form">
											<input type="hidden" name="page-id" value="TA01"> <input
												type="hidden" name="task-id" value="${task.taskId}">
											<button type="submit" name="btn-id" value="task-update"
												class="task-edit-button">編集</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</section>
		</div>
	</main>

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
