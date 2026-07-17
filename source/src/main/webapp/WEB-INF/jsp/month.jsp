<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>月次集計 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <h1>月次集計</h1>

<!-- 日付表示 -->
<div class="calendar">
</div>

<!-- 月切り替えボタン -->
<div class="buttons">
  <button ><</button>
  <button >></button>

</div>

<!-- CSV出力ボタン -->
<button id="csv_button">CSV出力</button>

<h2>案件別実績</h2>

<h2>ユーザー別実績</h2>

  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>