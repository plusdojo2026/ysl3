<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ホーム | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<main class="main">
		<div class="title-area"><h1 class="home-title">ホーム</h1></div>
		<div class="msg-area">
		<c:if test="${not empty errMsg}"><div><c:out value="${errMsg}" /></div><c:remove var="errMsg" scope="request" /></c:if>
		</div>
		<div class="home-layout">
			<div class="home-main">
				<!-- ナビゲーションここから -->
				<section class="nav-area">
					<div class="nav-card"><a href="${pageContext.request.contextPath}/Controller?page-id=PR01">案件一覧</a></div>
					<div class="nav-card"><a href="${pageContext.request.contextPath}/Controller?page-id=TA01">タスク一覧</a></div>
					<div class="nav-card"><a href="${pageContext.request.contextPath}/Controller?page-id=MO01">月次集計</a></div>
					<div class="nav-card"><a href="${pageContext.request.contextPath}/Controller?page-id=MY01">マイページ</a></div>
				</section>
				<!-- ナビゲーションここまで -->
				<!-- 自分のタスク一覧ここから -->
				<section class="my-task-area">
					<div class="sub-title-area"><h1 class="sub-home-title">担当タスク</h1></div>
						<div class="card-area">
							<c:forEach var="task" items="${homeTaskList}">
								<div class="task-card">
								
									<!-- タスクメイン情報 -->
									<c:url var="taskDetailUrl" value="/Controller">
										<c:param name="page-id" value="HO01" />
										<c:param name="task-id" value="${task.taskId}" />
									</c:url>
									<h3 class="task-title">
										<a href="${taskDetailUrl}" class="task-link"><c:out value="${task.name}" /></a>
									</h3>
									<!-- タスクメイン情報 -->
									<div class="task-card-info">
										<span class="meta-item limit"> 期限: <c:out value="${task.limitDate}" /></span>
										<span class="meta-item status"> <c:choose><c:when test="${task.status == 0}">
										<span class="status">開始前</span></c:when><c:when test="${task.status == 1}">
										<span class="status">進行中</span></c:when><c:when test="${task.status == 2}">
										<span class="status">完　了</span></c:when><c:when test="${task.status == 3}">
										<span class="status">保　留</span></c:when><c:otherwise>
										<span class="status">不　明</span></c:otherwise></c:choose></span>
										<span class="meta-item priority"> 優先度: <c:choose>
												<c:when test="${task.priority == 0}">低</c:when>
												<c:when test="${task.priority == 1}">中</c:when>
												<c:when test="${task.priority == 2}">高</c:when>
												<c:otherwise>不明</c:otherwise></c:choose></span>
										<span class="meta-item time"> 見積: <c:out value="${task.estimatedWorks}" />H</span>
									</div>
									<!-- 進捗バー -->
									<div class="task-progress">
										<progress value="${task.progress}" max="100" class="progress-bar"></progress>
										<span class="progress-text"><c:out value="${task.progress}" />%</span>
									</div>
									<div class="task-actions">
										<!-- 工数登録ボタン -->
										<form method="post"action="${pageContext.request.contextPath}/Controller" style="display: inline;">
											<input type="hidden" name="page-id" value="HO01">
											<input type="hidden" name="task-id" value="${task.taskId}">
											<button type="submit" name="btn-id" value="work-regist"class="btn btn-work">工数登録</button>
										</form>
										<!-- 削除ボタン -->
										<form method="post" action="${pageContext.request.contextPath}/Controller" style="display: inline;">
											<input type="hidden" name="page-id" value="HO01">
											<input type="hidden" name="task-id" value="${task.taskId}">
											<button type="submit" name="btn-id" value="task-delete"class="btn btn-delete">削除</button>
										</form>
									</div>
								</div>
							</c:forEach>
						</div>
				</section>
	
				<!-- 自分のタスク一覧ここまで -->
			
			</div>
				<!-- 自分の工数ログ一覧ここから -->
				<section class="home-side">
					<div class="sub-title-area"><h1 class="sub-home-title">工数ログ履歴</h1></div>
					<c:forEach var="workLog" items="${homeWorkList}">
						<div class="home-work-log">
							<div class="home-work-log-time"><c:out value="${workLog.work}" />時間</div>
							<div class="home-work-log-name"><c:out value="${workLog.taskName}" /> /　<c:out value="${workLog.explainText}" /></div>
							<div class="home-work-log-date"><c:out value="${workLog.workDate}" /></div>
						</div>
					</c:forEach>
				</section>
				<!-- 自分の工数ログ一覧ここまで -->



		</div>
	</main>

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
