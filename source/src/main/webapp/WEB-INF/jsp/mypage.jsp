<%@ page contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <title>マイページ | TaskManager</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css"/>
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/mypage.js" defer></script>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp"%>
    <!---------- メインここから ---------->
    <main class="main">
      <h1>マイページ</h1>
      <input type="text" name="user-name" />

      <h2>プロフィール</h2>
      <input type="text" name="login-id" /><br />
      <input type="text" name="user-name" /><br />
      <input type="text" name="mail" /><br />

      <h2>パスワード変更</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page-id" value="MY01" />
        <input type="text" name="password" /><br />
        <input type="text" name="new-password" /><br />
        <input type="text" name="new-password-confirm" /><br />
        <input type="submit" name="btn-id" value="変更" />
      </form>
    </main>
    <!---------- メインここまで ---------->
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
  </body>
</html>
