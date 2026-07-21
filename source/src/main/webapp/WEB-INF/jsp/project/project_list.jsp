<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ホーム | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
  <script src="${pageContext.request.contextPath}/js/home.js" defer></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">

  <h2 class="page-title">案件一覧</h2>
  <button class="project-regist-btn">新規登録</button>

  <!-- 検索フォームここから -->
  <form class="project-search-form">
    <input type="hidden" name="page-id" value="PR01">
    <select class="project-select">ステータス</select>
    <select class="project-select">優先度</select>
    <input type="text" class="project-search-text" value="キーワード検索" name="keyword">
    <input type="submit" class="search-btn" value="検索">
  </form>
  <!-- 検索フォームここまで -->

  <!-- 案件一覧ここから -->
   <section class="project-list-view">
    <div class="project-list-card">
      <div>案件コード</div>
      <div>案件名</div>
      <div>顧客名</div>
      <div>開始日</div>
      <div>期限日</div>
      <div>ステータス</div>
      <div>優先度</div>
      <div>PM名</div>
      <div>工数</div>
      <div>進捗度</div>
      <div>総工数</div>
      <div>現在までの工数</div>
      <button class="edit-btn">編集ボタン</button>
    </div>
   </section>
  <!-- 案件一覧ここまで -->
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>