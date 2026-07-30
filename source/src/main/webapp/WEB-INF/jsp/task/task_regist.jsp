<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タスク登録 | TaskManager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css">
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/task.js" defer></script>
    <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp"%>
    <main class="main">
        <div class="management-page">
            <div class="title-area">
                <h1 class="page-title">タスク新規登録</h1>
            </div>
            <!-- メッセージ表示領域 -->
            <div class="management-message-area">
                <c:if test="${not empty msg}">
                    <c:out value="${msg}" />
                </c:if>
                <c:if test="${not empty errMsg}">
                    <c:out value="${errMsg}" />
                </c:if>
            </div>
            <!-- タスク登録フォーム -->
            <form class="management-form-card" method="POST" action="<c:url value='/Controller'/>">
                <input type="hidden" name="page-id" value="TA02">
                <input type="hidden" name="project-id" value="${param['project-id']}">
                <div class="management-form-grid">
                    <!-- 案件名 -->
                    <div class="management-field">
                        <label class="management-label">
                            案件名
                            <span class="management-required">（必須）</span>
                        </label>
                        <input class="management-input" type="text" value="${project.name}" required>
                    </div>
                    <!-- タスク名 -->
                    <div class="management-field">
                        <label class="management-label" for="task-name">
                            タスク名
                            <span class="management-required">（必須）</span>
                        </label>
                        <input class="management-input" type="text" id="task-name" name="task-name" maxlength="20" required>
                    </div>
                    <!-- ステータス -->
                    <div class="management-field">
                        <label class="management-label" for="task-status">
                            ステータス
                            <span class="management-required">（必須）</span>
                        </label>
                        <select class="management-select" id="task-status" name="task-status" required>
                            <option value="0" selected>未着手</option>
                            <option value="1">進行中</option>
                            <option value="2">完了</option>
                            <option value="3">保留</option>
                        </select>
                    </div>
                    <!-- 優先度 -->
                    <div class="management-field">
                        <label class="management-label" for="task-priority">
                            優先度
                            <span class="management-required">（必須）</span>
                        </label>
                        <select class="management-select" id="task-priority" name="task-priority" required>
                            <option value="0">低</option>
                            <option value="1" selected>中</option>
                            <option value="2">高</option>
                        </select>
                    </div>
                    <!-- 担当者 -->
                    <div class="management-field">
                        <label class="management-label" for="user-id">
                            担当者
                            <span class="management-required">（必須）</span>
                        </label>
                        <select class="management-select" id="user-id" name="user-id" required>
                            <option class="management-placeholder-option" value="" disabled selected>
                                選択してください
                            </option>
                            <c:forEach var="member" items="${editTask}">
                                <option value="${member.id}">
                                    <c:out value="${member.userName}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- 予算工数 -->
                    <div class="management-field">
                        <label class="management-label" for="task-estimated-works">
                            予算工数
                            <span class="management-required">（必須）</span>
                        </label>
                        <input class="management-input" type="number" id="task-estimated-works" name="task-estimated-works" min="0.5" step="0.5" required>
                    </div>
                    <!-- 進捗率 -->
                    <div class="management-field management-field-wide">
                        <label class="management-label" for="progress">
                            進捗率
                        </label>
                        <input class="management-range" type="range" id="progress" name="progress" min="0" max="100" value="0" step="5" list="marks">
                        <datalist id="marks">
                            <option value="0" label="0%"></option>
                            <option value="5" label="5%"></option>
                            <option value="10" label="10%"></option>
                            <option value="15" label="15%"></option>
                            <option value="20" label="20%"></option>
                            <option value="25" label="25%"></option>
                            <option value="30" label="30%"></option>
                            <option value="35" label="35%"></option>
                            <option value="40" label="40%"></option>
                            <option value="45" label="45%"></option>
                            <option value="50" label="50%"></option>
                            <option value="55" label="55%"></option>
                            <option value="60" label="60%"></option>
                            <option value="65" label="65%"></option>
                            <option value="70" label="70%"></option>
                            <option value="75" label="75%"></option>
                            <option value="80" label="80%"></option>
                            <option value="85" label="85%"></option>
                            <option value="90" label="90%"></option>
                            <option value="95" label="95%"></option>
                            <option value="100" label="100%"></option>
                        </datalist>
                    </div>
                    <!-- 開始日・期限 -->
                    <div class="management-field management-field-wide">
                        <label class="management-label">
                            開始日／期限
                            <span class="management-required">（必須）</span>
                        </label>
                        <div class="management-date-period">
                            <input class="management-input" type="date" name="task-start-date" required>
                            <span class="management-date-separator">ー</span>
                            <input class="management-input" type="date" name="task-limit" required>
                        </div>
                    </div>
                    <!-- 説明 -->
                    <div class="management-field management-field-wide">
                        <label class="management-label" for="task-explanation">
                            説明
                        </label>
                        <input class="management-input" type="text" id="task-explanation" name="task-explanation" maxlength="1000">
                    </div>
                </div>
                <!-- 登録ボタン -->
                <div class="management-btn-area">
                    <button class="management-submit-btn" type="submit" name="btn-id" value="task-regist">
                        登録
                    </button>
                </div>
            </form>
        </div>
    </main>
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
