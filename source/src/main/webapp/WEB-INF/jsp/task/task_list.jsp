<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タスク一覧 | TaskManager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link class="stylesheet" href="${pageContext.request.contextPath}/css/task.css">
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>

    <main class="main">
        <h1>タスク一覧</h1>

        <c:if test="${not empty msg}">
            <p><c:out value="${msg}" /></p>
        </c:if>

        <c:if test="${not empty errMsg}">
            <p><c:out value="${errMsg}" /></p>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/Controller">
            <input type="hidden" name="page-id" value="TA01">

            <label for="project-name">案件名</label>
            
            <select id="project-name" name="project-id">
            
                <option value="-1">すべて</option>
                <c:forEach var="project" items="${projectList}">
                 <option value="${project}">${project}</option>
                </c:forEach>

            </select>

            <label for="task-status">ステータス</label>
            <select id="task-status" name="task-status">
                <option value="-1">すべて</option>
                <option value="0">未着手</option>
                <option value="1">進行中</option>
                <option value="2">完了</option>
                <option value="3">保留</option>
            </select>


            <label for="keyword">キーワード</label>
            <input type="search" id="keyword" name="task-name" value="${fn:escapeXml(param.keyword)}" placeholder="タスク名">

            <button type="submit" name="btn-id" value="task-search">検索</button>
        </form>

        <c:choose>
            <c:when test="${empty taskList}">
                <p>表示できるタスクはありません。</p>
            </c:when>

            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>案件名</th>
                            <th>タスク名</th>
                            <th>担当者</th>
                            <th>ステータス</th>
                            <th>優先度</th>
                            <th>期限</th>
                            <th>見積工数</th>
                            <th>進捗</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="task" items="${taskList}">
                            <tr>
                                <td>
                                    <c:out value="${task.projectName}" />
                                </td>
                                <td>
                                    <c:url var="taskDetailUrl" value="/Controller">
                                        <c:param name="page-id" value="TA04" />
                                        <c:param name="task-id" value="${task.taskId}" />
                                    </c:url>
                                    <a href="${taskDetailUrl}">
                                        <c:out value="${task.name}" />
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${task.userName}" />
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${task.status == 0}">未着手</c:when>
                                        <c:when test="${task.status == 1}">進行中</c:when>
                                        <c:when test="${task.status == 2}">完了</c:when>
                                        <c:when test="${task.status == 3}">保留</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${task.priority == 0}">低</c:when>
                                        <c:when test="${task.priority == 1}">中</c:when>
                                        <c:when test="${task.priority == 2}">高</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:out value="${task.limitDate}" />
                                </td>
                                <td>
                                    <c:out value="${task.estimatedWorks}" />時間
                                </td>
                                <td>
                                    <progress value="${task.progress}" max="100"></progress>
                                    <c:out value="${task.progress}" />%
                                </td>
                                <td>
                                        <form method="post" action="${pageContext.request.contextPath}/Controller" style="display: inline;">
                                            <input type="hidden" name="page-id" value="TA01">
                                            <input type="hidden" name="task-id" value="${task.taskId}">
                                            <button type="submit" name="btn-id" value="task-update">編集</button>
                                        </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
