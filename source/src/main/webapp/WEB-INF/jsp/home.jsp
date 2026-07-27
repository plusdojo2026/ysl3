<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ホーム | TaskManager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>

    <main class="main">
        <h1>ホーム</h1>

        <c:if test="${not empty msg}">
            <p><c:out value="${msg}" /></p>
        </c:if>

        <c:if test="${not empty errMsg}">
            <p><c:out value="${errMsg}" /></p>
        </c:if>

        <!-- 各画面へのナビゲーション -->
        <nav aria-label="ホームメニュー">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?page-id=PR01">案件一覧</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?page-id=TA01">タスク一覧</a>
                </li>
                <%-- 月次
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?page-id=MO01">月次集計</a>
                </li>
                --%>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?page-id=MY01">マイページ</a>
                </li>
                <c:if test="${sessionScope.user.role == 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー管理</a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <!-- 自分のタスク一覧 -->
        <section>
            <h2>自分のタスク一覧</h2>
            <c:choose>
                <c:when test="${empty homeTaskList}">
                    <p>表示できるタスクはありません。</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>タスク名</th>
                                <th>期限</th>
                                <th>ステータス</th>
                                <th>優先度</th>
                                <th>進捗</th>
                                <th>見積工数</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="task" items="${homeTaskList}">
                                <tr>
                                    <td>
                                        <c:url var="taskDetailUrl" value="/Controller">
                                            <c:param name="page-id" value="TA04" />
                                            <c:param name="task-id" value="${task.taskId}" />
                                        </c:url>
                                        <a href="${taskDetailUrl}"><c:out value="${task.name}" /></a>
                                    </td>
                                    <td>
                                        <c:out value="${task.limitDate}" />
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${task.status == 0}">開始前</c:when>
                                            <c:when test="${task.status == 1}">進行中</c:when>
                                            <c:when test="${task.status == 2}">完了</c:when>
                                            <c:when test="${task.status == 3}">保留</c:when>
                                            <c:otherwise>不明</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${task.priority == 0}">低</c:when>
                                            <c:when test="${task.priority == 1}">中</c:when>
                                            <c:when test="${task.priority == 2}">高</c:when>
                                            <c:otherwise>不明</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <progress value="${task.progress}" max="100"></progress>
                                        <c:out value="${task.progress}" />%
                                    </td>
                                    <td>
                                        <c:out value="${task.estimatedWork}" />時間
                                    </td>
                                    <td>
                                        <c:url var="taskEditUrl" value="/Controller">
                                            <c:param name="page-id" value="TA03" />
                                            <c:param name="task-id" value="${task.taskId}" />
                                        </c:url>
                                        <a href="${taskEditUrl}">編集</a>

                                        <c:url var="workRegistUrl" value="/Controller">
                                            <c:param name="page-id" value="WO01" />
                                            <c:param name="task-id" value="${task.taskId}" />
                                        </c:url>
                                        <a href="${workRegistUrl}">工数登録</a>

                                        <form method="post" action="${pageContext.request.contextPath}/Controller">
                                            <input type="hidden" name="page-id" value="HO01">
                                            <input type="hidden" name="task-id" value="${task.taskId}">
                                            <button type="submit" name="btn-id" value="task-delete">削除</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>

        <!-- 自分の工数ログ一覧 -->
        <section>
            <h2>自分の工数ログ一覧</h2>
            <c:choose>
                <c:when test="${empty homeWorkList}">
                    <p>表示できる工数ログはありません。</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>タスク名</th>
                                <th>作業日</th>
                                <th>工数</th>
                                <th>内容</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="workLog" items="${homeWorkList}">
                                <tr>
                                    <td>
                                        <c:out value="${workLog.taskName}" />
                                    </td>
                                    <td>
                                        <c:out value="${workLog.workDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${workLog.work}" />時間
                                    </td>
                                    <td>
                                        <c:out value="${workLog.explainText}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>
    </main>

    <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
