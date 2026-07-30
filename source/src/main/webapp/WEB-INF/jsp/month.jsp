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
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<main class="main month-page">
		<div class="title-area">
			<h1 class="page-title">月次集計</h1>
		</div>
		<div class="month-message-area">
			<c:if test="${not empty msg}">
				<p class="month-success-message">
					<c:out value="${msg}" />
				</p>
			</c:if>
			<c:if test="${not empty errMsg}">
				<p class="month-error-message">
					<c:out value="${errMsg}" />
				</p>
			</c:if>
		</div>
		<c:choose>
			<c:when test="${sessionScope.user.role == 1}">
				<c:set var="monthPageId" value="MO01" />
			</c:when>
			<c:otherwise>
				<c:set var="monthPageId" value="MO02" />
			</c:otherwise>
		</c:choose>
		<c:url var="lastMonthUrl" value="/Controller">
			<c:param name="page-id" value="${monthPageId}" />
			<c:param name="month" value="${lastMonth}" />
		</c:url>
		<c:url var="nextMonthUrl" value="/Controller">
			<c:param name="page-id" value="${monthPageId}" />
			<c:param name="month" value="${nextMonth}" />
		</c:url>
		<section class="month-control-area">
			<a href="${lastMonthUrl}" class="month-change-btn" aria-label="前月を表示">＜</a>
			<div class="target-month-area">
				<span class="target-month-label">対象月</span> <span
					class="target-month"><c:out value="${targetMonth}" /></span>
			</div>
			<a href="${nextMonthUrl}" class="month-change-btn" aria-label="翌月を表示">＞</a>
		</section>
		<section class="month-summary-area">
			<div class="sub-title-area">
				<h1 class="sub-page-title">月次サマリー</h1>
			</div>

			<c:forEach var="summary" items="${summaryList}">
				<div class="month-summary-grid">
					<div class="month-summary-card member-summary-card">
						<span class="month-summary-label">稼働メンバー</span> <span
							class="month-summary-value"><c:out
								value="${summary.memberCount}" /><small> 人</small></span>
					</div>
					<div class="month-summary-card total-summary-card">
						<span class="month-summary-label">総工数</span> <span
							class="month-summary-value"><fmt:formatNumber
								value="${summary.totalWork}" maxFractionDigits="1" /><small>
								h</small></span>
					</div>
					<div class="month-summary-card plan-summary-card">
						<span class="month-summary-label">予定工数</span> <span
							class="month-summary-value"><fmt:formatNumber
								value="${summary.estimatedWork}" maxFractionDigits="1" /><small>
								h</small></span>
					</div>
					<div class="month-summary-card remain-summary-card">
						<span class="month-summary-label">残工数</span> <span
							class="month-summary-value"><fmt:formatNumber
								value="${summary.remainWork}" maxFractionDigits="1" /><small>
								h</small></span>
					</div>
				</div>
			</c:forEach>

		</section>
		<section class="project-summary-area">
			<div class="sub-title-area">
				<h1 class="sub-page-title">案件別実績</h1>
			</div>
			<div class="month-table-area">
				<table class="month-table">
					<thead>
						<tr>
							<th class="month-name-column">案件名</th>
							<th>実績工数</th>
							<th>予定工数</th>
							<th>残り工数</th>
							<th class="month-progress-column">進捗率</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="project" items="${projectSummaryList}">
							<tr>
								<td class="month-name-cell" title="${project.name}"><c:out
										value="${project.name}" /></td>
								<td><fmt:formatNumber value="${project.totalWork}"
										maxFractionDigits="1" /> h</td>
								<td><fmt:formatNumber value="${project.plannedWork}"
										maxFractionDigits="1" /> h</td>
								<td><fmt:formatNumber value="${project.remainWork}"
										maxFractionDigits="1" /> h</td>
								<td>
									<div class="month-progress">
										<progress value="${project.progressRate}" max="100"
											class="month-progress-bar"></progress>
										<span class="month-progress-value"><fmt:formatNumber
												value="${project.progressRate}" maxFractionDigits="1" />%</span>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</section>
		<section class="user-summary-area">
			<div class="sub-title-area">
				<h1 class="sub-page-title">
					<c:choose>
						<c:when test="${sessionScope.user.role == 1}">ユーザー別実績</c:when>
						<c:otherwise>個人実績</c:otherwise>
					</c:choose>
				</h1>
			</div>
			<div class="month-table-area">
				<table class="month-table">
					<thead>
						<tr>
							<th class="month-name-column">担当者名</th>
							<th>実績工数</th>
							<th>予定工数</th>
							<th>残り工数</th>
							<th class="month-progress-column">進捗率</th>
						</tr>
					</thead>
					<tbody>

						<c:set var="monthUserFound" value="false" />
						<c:forEach var="userSummary" items="${userSummaryList}">
							<c:if
								test="${sessionScope.user.role == 1 || userSummary.userName == sessionScope.user.userName}">
								<c:set var="monthUserFound" value="true" />
								<tr>
									<td class="month-name-cell"><c:out
											value="${userSummary.userName}" /></td>
									<td><fmt:formatNumber value="${userSummary.totalWork}"
											maxFractionDigits="1" /> h</td>
									<td><fmt:formatNumber value="${userSummary.plannedWork}"
											maxFractionDigits="1" /> h</td>
									<td><fmt:formatNumber value="${userSummary.remainWork}"
											maxFractionDigits="1" /> h</td>
									<td>
										<div class="month-progress">
											<progress value="${userSummary.progressRate}" max="100"
												class="month-progress-bar"></progress>
											<span class="month-progress-value"><fmt:formatNumber
													value="${userSummary.progressRate}" maxFractionDigits="1" />%</span>
										</div>
									</td>
								</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</section>
	</main>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>