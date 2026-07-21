<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <title>ホーム | TaskManager</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/home.js" defer></script>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
    <!---------- メインここから ---------->
    <main class="main">
      <h2 class="page-title">ホーム</h2>
      <!-- ホームナビここから -->
      <section class="home-nav">
        <div class="nav-icon">
          <img />
          <a href="">案件</a>
        </div>
        <div class="nav-icon">
          <img />
          <a href="">タスク</a>
        </div>
        <div class="nav-icon">
          <img />
          <a href="">月次</a>
        </div>
        <div class="nav-icon">
          <img />
          <a href="">マイページ</a>
        </div>
      </section>
      <!-- ホームナビここまで -->

      <!-- ホーム工数ログ表示ここから -->

      <section class="home-log-view">
        <h3 class="home-view-title">工数ログ</h3>

        <div class="work-log-card">
          <div>タスク名</div>
          <div>担当者</div>
          <div>実施日</div>
          <div>工数</div>
          <div>▼</div>
        </div>
      </section>
      <!-- ホーム工数ログ表示ここまで -->

      <!-- ホームタスク一覧ここから -->
       <section class="home-task-view">
        <h3 class="home-view-title">タスク一覧</h3>
            <c:forEach var="e" items="${userList}">
	<form method="POST" action="<c:url value='/Controller'/>">
	<input type="hidden" name="page-id" value="HO01">
        <div class="task-log-card">
          <div>タスク名</div>
          <div>担当者</div>
          <div>期限：</div>
          <div>ステータス</div>
          <div>%</div>
          <input type="submit" name="btn-id" value="工数登録">
          <input type="submit" name="btn-id" value="削除">
        </div>
        </form>
       </section>
      <!-- ホームタスク一覧ここまで -->

    </main>
    <!---------- メインここまで ---------->
    <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
  </body>
</html>
