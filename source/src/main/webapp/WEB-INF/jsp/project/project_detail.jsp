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
   
          <p>案件コード${projectDetail. code}</p>
          <p>案件名${projectDetail.name}</p>
          <p>顧客名${projectDetail.customer}</p>
          <p>工数${projectDetail.totalWork}</p>
          <p>ステータス${projectDetail.status}</p>
          <p>進捗度${projectDetail.progressRate}</p>
          <p>開始日${projectDetail.startDate}</p>
          <p>期限日${projectDetail.limitDate}</p>
          
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
        
        <table>
        <c:forEach var="task" items="${projectTaskList}">
            <tr>
                <td><c:out value="${task.taskId}" /></td>
                <td><c:out value="${task.name}" /></td>
                <td><c:out value="${task.userName}" /></td>
                <td><c:out value="${task.limitDate}" /></td>
                <td><c:out value="${task.status}" /></td>
            </tr>
        </c:forEach>
        </table>
        
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