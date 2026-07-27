<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>工数登録 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/work_regist.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/work_regist.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<!---------- メインここから ---------->
	<main class="main">
        <section class="work-regist-area">
            <h2 class="page-title">工数登録</h2>
            <form method="POST" action="<c:url value='/Controller'/>">
                <input type="hidden" name="task-id" value="${TaskShow.taskId}">
                    <input type="hidden" id="project-name" name="project" value="${ProjectShow.name}" readonly>
                    <input type="hidden" id="task-name" name="task" value="${TaskShow.name}" readonly>
                <div class="form-area pr-ta-area">
                	<div class="info">${ProjectShow.name} / ${TaskShow.name}</div>

                </div>

                <div class="form-area date-area">
                    <div class="form-group">
                        <div class="input-title">日付（必須）</div>
                        <input type="date" id="day"name="work-date" value="<%=LocalDate.now()%>" class="date-input">
                    </div>

                    <div class="form-group">
                        <div class="input-title">作業内容</div>
                        <input type="text" id="work-explanation" name="explain-text" class="date-input">
                    </div>
                </div>

                <div class="form-area">
                    <h2>工数（必須、0.5時間刻み)</h2>
                    <input type="range" id="work" class="progress-bar" name="work" min="0.5" max="24" step="0.5" class="slider" value="0.5" oninput="updateTime(this.value)">
                    <div class="time-display">
                        <span id="time-label">0.5時間</span>
                    </div>
                </div>

                <div class="btn-area">
                    <input type="hidden" name="page-id" value="WO01">
                    <button type="submit" name="btn-id" class="regist-btn" value="work-regist">登録</button>
                </div>
            </form>

            <c:if test="${not empty msg}">
                <p>
                    <c:out value="${msg}" />
                </p>
            </c:if>

            <c:if test="${not empty errMsg}">
                <p>
                    <c:out value="${errMsg}" />
                </p>
            </c:if>
        </section>
	</main>
	<!---------- メインここまで ---------->

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
