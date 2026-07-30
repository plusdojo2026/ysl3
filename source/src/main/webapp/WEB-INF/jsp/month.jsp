<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>月次集計 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/month.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/month.js" defer></script>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main">
		<!---------- メインここから ---------->
		<div class="title-area">
			<h1 class="page-title">月次集計</h1>
		</div>
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
		<c:url var="lastMonthUrl" value="/Controller">
			<c:param name="page-id" value="MO01" />
			<c:param name="month" value="${lastMonth}" />
		</c:url>
		<c:url var="nextMonthUrl" value="/Controller">
			<c:param name="page-id" value="MO01" />
			<c:param name="month" value="${nextMonth}" />
		</c:url>
		<section class="month-control-area">
			<div class="month-control">
				<a href="${lastMonthUrl}" class="month-change-btn"> ＜ </a> <span
					class="target-month"> <c:out value="${targetMonth}" />
				</span> <a href="${nextMonthUrl}" class="month-change-btn"> ＞ </a>
			</div>
		</section>
		<section class="month-summary-area">
			<h2 class="sub-page-title">月次サマリー</h2>
			<table class="month-table">
				<thead>
					<tr>
						<th>稼働メンバー</th>
						<th>総工数</th>
						<th>予定工数</th>
						<th>残工数</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="summary" items="${summaryList}">
						<tr>
							<td><c:out value="${summary.memberCount}" /> 人</td>
							<td><fmt:formatNumber value="${summary.totalWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${summary.estimatedWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${summary.remainWork}"
									maxFractionDigits="1" /> h</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</section>
		<section class="project-summary-area">
			<h2 class="sub-page-title">案件別実績</h2>
			<table class="month-table">
				<thead>
					<tr>
						<th>案件名</th>
						<th>実績工数</th>
						<th>予定工数</th>
						<th>残り工数</th>
						<th>進捗率</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="project" items="${projectSummaryList}">
						<tr>
							<td><c:out value="${project.name}" /></td>
							<td><fmt:formatNumber value="${project.totalWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${project.plannedWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${project.remainWork}"
									maxFractionDigits="1" /> h</td>
							<td>
								<div class="progress">
									<progress value="${project.progressRate}" max="100">
									</progress>
									<span> <fmt:formatNumber value="${project.progressRate}"
											maxFractionDigits="1" /> %
									</span>
								</div>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</section>
		<section class="user-summary-area">
			<h2 class="sub-page-title">ユーザー別実績</h2>
			<table class="month-table">
				<thead>
					<tr>
						<th>担当者名</th>
						<th>実績工数</th>
						<th>予定工数</th>
						<th>残り工数</th>
						<th>進捗率</th>
					</tr>
				</thead>
				<tbody>


					<c:forEach var="user" items="${userSummaryList}">
						<tr>
							<td><c:out value="${user.userName}" /></td>
							<td><fmt:formatNumber value="${user.totalWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${user.plannedWork}"
									maxFractionDigits="1" /> h</td>
							<td><fmt:formatNumber value="${user.remainWork}"
									maxFractionDigits="1" /> h</td>
							<td>
								<div class="progress">
									<progress value="${user.progressRate}" max="100"> </progress>
									<span> <fmt:formatNumber value="${user.progressRate}"
											maxFractionDigits="1" /> %
									</span>
								</div>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</section>
		<!---------- メインここまで ---------->
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>