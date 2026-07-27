<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>工数登録 | TaskManager</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/work_regist.css">
	<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
	<script src="${pageContext.request.contextPath}/js/work_regist.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<!---------- メインここから ---------->
	<main class="main">
		<form method="POST" action="<c:url value='/Controller'/>">
			<div class="form-group">
				<label>工数登録</label>
				<input type="text" id="project-name" name="project" value="${ProjectShow.name}" readonly> / 
				<input type="text" id="task-name" name="task" value="${TaskShow.name}" readonly>
			</div>

			<div class="form-group">
				<label for="day">日付（必須）</label>
				<input type="date" id="day" name="day" value="<%=LocalDate.now()%>">
			</div>

			<div class="form-group">
				<label for="work-explanation">作業内容</label>
				<input type="text" id="work-explanation" name="work-explanation">
			</div>

			<div class="form-group container">
				<h2>工数（必須、0.5時間刻み)</h2>
				<input type="range" id="work" name="work" min="0.5" max="24" step="0.5" class="slider" value="0.5" oninput="updateTime(this.value)">
				<div class="time-display">
					<span id="time-label">0.5時間</span>
				</div>
			</div>

			<div class="form-submit">
				<input type="submit" value="登録">
			</div>
		</form>
	</main>
	<!---------- メインここまで ---------->

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
