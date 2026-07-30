<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="${pageContext.request.contextPath}/js/home.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<!---------- メインここから value="selectProjectUserName" style="display: inline;"---------->
	<main class="main">

		<div class="title-area">
			<h1 class="page-title">案件一覧</h1>
		</div>

		<form method="post"
			action="${pageContext.request.contextPath}/Controller">
			<input type="hidden" name="page-id" value="PR02">
			<button type="submit" name="btn-id" value="project-to-regist"
				class="project-regist-btn">新規登録</button>
		</form>
		<!-- 検索フォームここから -->
		<form class="project-search-form">
			<input type="hidden" name="page-id" value="PR01"> 
			<select name="projectStatus" class="project-select" placeholder="ステータス">
				<option value="" disabled selected style="display: none;">ステータス</option>
				<option value="0">開始前</option>
				<option value="1">進行中</option>
				<option value="2">完了</option>
				<option value="3">保留</option>
			</select> 
			<select name="projectPriority" class="project-select">
				<option value="" disabled selected style="display: none;">優先度</option>
				<option value="0">低</option>
				<option value="1">中</option>
				<option value="2">高</option>
			</select>
			 <input type="text" class="project-search-text" placeholder="キーワード検索"
				name="projectName">
			<input type="submit" name="btn-id" value="検索"
					class="project-search-btn">
		</form>
		<!-- 検索フォームここまで -->

		<!-- 案件一覧ここから -->
		<c:forEach var="project" items="${projectList}">
			<div class="project-card">
				<ul>
					<li>案件コード :${project.code}</li>

					<li><c:url var="projectDetailUrl" value="/Controller">
							<c:param name="page-id" value="PR04" />
							<c:param name="project-id" value="${project.id}" />
						</c:url> <a href="${projectDetailUrl}"> <c:out value="${project.name}" />
					</a></li>

					<li>案件名 :${project.name}</li>

					<li>顧客名:${project.customer}</li>

					<li>開始日:${project.startDate}</li>

					<li>期限日:${project.endDate}</li>

					<li>ステータス: <c:choose>
							<c:when test="${project.status==0}">
   				開始前
   				</c:when>
							<c:when test="${project.status==1}">
   				進行中
   				</c:when>
							<c:when test="${project.status==2}">
   				完了
   				</c:when>
							<c:when test="${project.status==3}">
   				保留
   				</c:when>
							<c:otherwise>
   				不明
   				</c:otherwise>
						</c:choose>
					</li>

					<li>優先度: <c:choose>
							<c:when test="${project.priority ==0}">
   				低
   				</c:when>
							<c:when test="${project.priority ==1}">
   				中
   				</c:when>
							<c:when test="${project.priority ==2}">
   				高
   				</c:when>
							<c:otherwise>
   				不明
   				</c:otherwise>
						</c:choose>
					</li>

					<li>PM名:${project.pmName}</li>

					<li>進捗度: <progress
							value="${(project.totalWork * 100) / project.estimatedWork }"
							max="100"></progress> <fmt:formatNumber
							value="${(project.totalWork * 100) / project.estimatedWork }"
							maxFractionDigits="1" />%
					</li>

					<li>総工数:${project.estimatedWork}</li>

					<li>現在までの工数:${project.totalWork}</li>
				</ul>

				<!-- 編集ボタン -->
				<form method="post"
					action="${pageContext.request.contextPath}/Controller"
					style="display: inline;">
					<input type="hidden" name="page-id" value="PR03"> <input
						type="hidden" name="project-id" value="${project.id}">
					<button type="submit" name="btn-id" value="project-to-edit">編集</button>
				</form>
			</div>
		</c:forEach>
		<!-- 案件一覧ここまで -->
	</main>
	<!---------- メインここまで ---------->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>