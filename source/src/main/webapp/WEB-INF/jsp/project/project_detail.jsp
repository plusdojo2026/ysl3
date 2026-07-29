<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>案件詳細 | TaskManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css"/>
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/project.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
    <!---------- メインここから ---------->
    <main class="main">
      <button class="edit-btn" name="btn-id">編集</button>

      <!-- 案件詳細ここから -->
      <section class="pr-detail-view">
        <div class="project-list-card">
    <input type="text" id="project-id" name="project-id" value="${project.code}" readonly>
    <input type="text" id="project-name" name="project" value="${project.name}" readonly>
    <input type="text" id="customer" name="customer" value="${project.customer}" readonly>
    <input type="text" id="project-start_date" name="project-start-date" value="${project.startDate}" readonly>
    <input type="text" id="project-limit" name="project-limit" value="${project.limitDate}" readonly>
    <input type="text" id="project-status" name="project-status" value="${project.status}" readonly>
    <input type="text" id="project-priority" name="project-priority" value="${project.priority}" readonly>
    <input type="text" id="pm-id" name="pm-id" value="${project.pmId}" readonly>
    <input type="text" id="project-end_date" name="project-end-date" value="${project.endDate}" readonly>
    <input type="text" id="explain-text" name="explain-text" value="${project.explainText}" readonly>
    <input type="text" id="estimated-work" name="estimated-work" value="${project.estimatedWork}" readonly>
    <input type="text" id="pm-name" name="pm-name" value="${project.pmName}" readonly>
    <input type="text" id="total-work" name="total-work" value="${project.totalWork}" readonly>
    <input type="text" id="member-count" name="member-count" value="${project.memberCount}" readonly>
    <input type="text" id="remain-work" name="remain-work" value="${project.remainWork}" readonly>
          <div>案件コード</div>
          <div>案件名</div>
          <div>顧客名</div>
          <div>工数</div>
          <div>ステータス</div>
          <div>進捗度</div>
          <div>開始日</div>
          <div>期限日</div>
          
          <div>優先度</div>
          <div>PM名</div>
          
          <div>現在までの工数</div>
        </div>
      </section>
      <!-- 案件詳細ここまで -->

      <!-- タスク一覧ここから -->
      <section class="task-view">
        <h3 class="view-title">タスク一覧</h3>
        <div class="task-log-card">
        
        <c:forEach var="task" items="${taskList}">
            <tr>
                <td><c:out value="${task.id}" /></td>
                <td><c:out value="${task.taskName}" /></td>
            </tr>
        </c:forEach>
        
          <div>タスク名</div>
          <div>担当者</div>
          <div>期限：</div>
          <div>ステータス</div>
          <div>%</div>
          <button>工数入力</button>
          <button>削除</button>
        </div>
      </section>
      <!-- タスク一覧ここまで -->

      <!-- 工数ログ表示ここから -->
      <section class="log-view">
        <h3 class="view-title">工数ログ</h3>
        <div class="work-log-card">
        <c:forEach var="e" items="${projectWorkList}" >
 	  <p>工数ログ名：${e.explainText}</p>
 	  <p>時間：${e.work}</p>
 	 <input type="submit" name="work-delete-btn" value="削除" onclick="return delete()">
   </c:forEach><br>
        </div>
      </section>
      <!-- 工数ログ表示ここまで -->
    </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>