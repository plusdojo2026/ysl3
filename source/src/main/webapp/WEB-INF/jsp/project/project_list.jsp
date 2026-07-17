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
  <h2 class="page-title">工数一覧</h2>

  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>