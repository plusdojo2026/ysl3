<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タスク詳細 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/task.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/task.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main">
		<div class="title-area"><h1 class="page-title">タスク詳細</h1></div>
		<!-- メッセージ表示領域 -->
		<div class="msg-area">
			<c:if test="${not empty msg}">
				<p class="success-message">
					<c:out value="${msg}" />
				</p>
			</c:if>
			<c:if test="${not empty errMsg}">
				<p class="error-message">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<!-- タスク詳細情報 -->
		<section class="task-detail-area">
			<h2>
				<c:out value="${taskDetail.name}" />
			</h2>
			<div class="task-detail-info">
				<p>
					案件名：
					<c:out value="${taskDetail.projectName}" />
				</p>
				<p>
					タスク名：
					<c:out value="${taskDetail.name}" />
				</p>
				<p>
					ステータス：
					<c:choose>
						<c:when test="${taskDetail.status == 0}">
                            未着手
                        </c:when>
						<c:when test="${taskDetail.status == 1}">
                            進行中
                        </c:when>
						<c:when test="${taskDetail.status == 2}">
                            完了
                        </c:when>
						<c:when test="${taskDetail.status == 3}">
                            保留
                        </c:when>
						<c:otherwise>
                            不明
                        </c:otherwise>
					</c:choose>
				</p>
				<p>
					見積工数：
					<c:out value="${taskDetail.estimatedWorks}" />
					時間
				</p>
				<p>
					進捗度：
					<progress value="${taskDetail.progress}" max="100"></progress>
					<c:out value="${taskDetail.progress}" />
					%
				</p>
				<p>
					開始日：
					<c:out value="${taskDetail.startDate}" />
				</p>
				<p>
					期限：
					<c:out value="${taskDetail.limitDate}" />
				</p>
				<p>
					担当者：
					<c:out value="${taskDetail.userName}" />
				</p>
			</div>
			<!-- タスク編集画面への遷移 -->
			<c:url var="taskEditUrl" value="/Controller">
				<c:param name="page-id" value="TA03" />
				<c:param name="task-id" value="${param['task-id']}" />
			</c:url>
			<a href="${taskEditUrl}" class="btn btn-edit"> 編集 </a>
		</section>
		<!-- タスク概要 -->
		<section class="task-explanation-area">
			<h2>タスク概要</h2>
			<p>
				<c:out value="${taskDetail.explainText}" />
			</p>
		</section>
		<!-- ステータス変更 -->
		<section class="task-status-area">
			<h2>ステータス変更</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/Controller">
				<input type="hidden" name="page-id" value="TA04"> <input
					type="hidden" name="btn-id" value="task-status-change"> <input
					type="hidden" name="task-id" value="${param['task-id']}">
				<div class="task-status-buttons">
					<button type="submit" name="task-status" value="0"
						class="btn status-not-started">未着手に戻す</button>
					<button type="submit" name="task-status" value="1"
						class="btn status-in-progress">進行中にする</button>
					<button type="submit" name="task-status" value="2"
						class="btn status-completed">完了にする</button>
					<button type="submit" name="task-status" value="3"
						class="btn status-on-hold">保留にする</button>
				</div>
			</form>
		</section>
		<!-- 工数ログ一覧 -->
		<section class="task-work-area">
			<h2>工数ログ一覧</h2>
			<c:choose>
				<c:when test="${empty taskWorkList}">
					<p>登録されている工数ログはありません。</p>
				</c:when>
				<c:otherwise>
					<div class="work-log-list">
						<c:forEach var="workLog" items="${taskWorkList}">
							<div class="work-log-card">
								<p>
									作業日：
									<c:out value="${workLog.workDate}" />
								</p>
								<p>
									作業内容：
									<c:out value="${workLog.explainText}" />
								</p>
								<p>
									工数：
									<c:out value="${workLog.work}" />
									時間
								</p>
								<!-- 工数削除 -->
								<form method="post"
									action="${pageContext.request.contextPath}/Controller">
									<input type="hidden" name="page-id" value="TA04"> <input
										type="hidden" name="task-id" value="${param['task-id']}">
									<input type="hidden" name="work-id" value="${workLog.id}">
									<button type="submit" name="btn-id" value="work-delete"
										class="btn btn-delete"
										onclick="return confirm('この工数ログを削除しますか？');">削除</button>
								</form>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</section>
		<!-- 工数登録画面への遷移 -->
		<section class="work-regist-button-area">
			<c:url var="workRegistUrl" value="/Controller">
				<c:param name="page-id" value="WO01" />
				<c:param name="task-id" value="${param['task-id']}" />
			</c:url>
			<a href="${workRegistUrl}" class="btn btn-work"> 工数を登録する </a>
		</section>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
