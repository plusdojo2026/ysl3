<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>マイページ | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
<h1>マイページ</h1>
<input type="text" name="user_name" >

<h2>プロフィール</h2>
<input type="text" name="login_id"><br>
<input type="text" name="user_name" ><br>
<input type="text" name="mail" ><br>

<h2>パスワード変更</h2>
<input type="text" name="password" ><br>
<input type="text" name="password" ><br>
<input type="text" name="password" ><br>
<input type="submit" name="submit" value="変更">
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>