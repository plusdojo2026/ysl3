<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>案件一覧 | TaskManager</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/project.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/task.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/project.js" defer></script>
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<!---------- メインここから value="selectProjectUserName" style="display: inline;"---------->
	<main class="main">
		<div class="project-page">


			<div class="title-area">
				<h1 class="page-title">案件一覧</h1>
			</div>
			<div class="project-message-area">
				<c:if test="${not empty msg}">
					<c:out value="${msg}" />
				</c:if>
				<c:if test="${not empty errMsg}">
					<c:out value="${errMsg}" />
				</c:if>
			</div>
			<!-- 検索フォームここから -->
			<form method="post"
				action="${pageContext.request.contextPath}/Controller"
				class="project-search-form">
				<input type="hidden" name="page-id" value="PR01">

				<div class="project-search-field">
					<label class="project-search-label" for="project-status">ステータス</label>
					<select name="projectStatus" id="project-status"
						class="project-select" placeholder="ステータス">
						<option value="" disabled selected style="display: none;">ステータス</option>
						<option value="0">開始前</option>
						<option value="1">進行中</option>
						<option value="2">完了</option>
						<option value="3">保留</option>
					</select>
				</div>

				<div class="project-search-field">
					<label class="project-search-label" for="project-priority">優先度</label>
					<select name="projectPriority" id="project-priority"
						class="project-select">
						<option value="" disabled selected style="display: none;">優先度</option>
						<option value="0">低</option>
						<option value="1">中</option>
						<option value="2">高</option>
					</select>
				</div>

				<div class="project-search-field">
					<label class="project-search-label" for="project-name">キーワード</label>
					<input type="text" id="project-name" class="project-search-text"
						placeholder="キーワード検索" name="projectName">
				</div>

				<button type="submit" name="btn-id" value="project-search"
					class="project-search-btn">検索</button>
				<button type="submit" name="btn-id" value="project-regist"
					class="project-regist-btn">登録</button>
			</form>
			<!-- 検索フォームここまで -->


			<!-- 案件一覧ここから -->
			<section class="my-project-area >">
			    <div class="card-area">
			        <c:forEach var="project" items="${projectList}">
			            <div class="project-card">
				            <!-- 案件メイン情報 -->
	            			<c:url var="projectDetailUrl" value="/Controller">
	                            <c:param name="page-id" value="PR04" />
	                            <c:param name="project-id" value="${project.id}" />
	                        </c:url>
	                        <h3 class="project-title">
	                        	<a href="${projectDetailUrl}" class="task-link"><c:out value="${project.name}" /></a>
	                        </h3>
	                        <!-- タスクメイン情報 -->
	                        <div class="project-card-info">
	                        	<span class="meta-item limit"> 開始：<c:out value="${project.startDate}"></c:out></span>
	                        	<span class="meta-item limit"> 期限：<c:out value="${project.endDate}"></c:out></span>
	                        	<span class="meta-item project-pm"> PM：<c:out value="${project.pmName}"></c:out></span>
	                        	<span class="meta-item project-pm"> 顧客：<c:out value="${project.customer}"></c:out></span>
	                        	<span class="meta-item status">
	                        		<c:choose>
										<c:when test="${project.status == 0}"><span class="status">開始前</span></c:when>
										<c:when test="${project.status == 1}"><span class="status">進行中</span></c:when>
										<c:when test="${project.status == 2}"><span class="status">完了</span></c:when>
										<c:when test="${project.status == 3}"><span class="status">保留</span></c:when>
									</c:choose>
								</span>
								<span class="hidden"> 優先度: 
									<c:choose>
										<c:when test="${project.priority == 0}">低</c:when>
										<c:when test="${project.priority == 1}">中</c:when>
										<c:when test="${project.priority == 2}">高</c:when>
									</c:choose>
								</span>
								<span class="meta-item time"> 見積工数<div class="font-big"><c:out value="${project.estimatedWork}" />H</div></span>
								<span class="meta-item time"> 実績工数<div class="font-big"><c:out value="${project.totalWork}" />H</div></span>
	                        </div>
							<div class="project-progress">
								<progress value="${(project.totalWork * 100) / project.estimatedWork }" max="100" class="progress-bar"></progress>
								<span class="progress-text"><fmt:formatNumber value="${(project.totalWork * 100) / project.estimatedWork - 0.05}" pattern="0.0" />%</span>
							</div>
	                       	<div class="project-actions">
								<!-- 編集ボタン -->
								<form method="post" action="${pageContext.request.contextPath}/Controller" style="display: inline;">
									<input type="hidden" name="page-id" value="PR03">
									<input type="hidden" name="project-id" value="${project.id}">
									<button type="submit" name="btn-id" value="project-to-edit" class="btn delete-btn">編集</button>
								</form>
							</div>
			            </div>
			        </c:forEach>
			    </div>
			</section>
			<!-- 案件一覧ここまで -->
		</div>
	</main>
	<!---------- メインここまで ---------->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>