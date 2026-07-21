<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>メンバー登録 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
  
  <h1>メンバー新規登録</h1>
<input type="text" name="login-id">
<input type="text" name="user-name" >
<div class="role-btn">
	<input type="radio" name="role" value="一般">
	<input type="radio" name="role" value="管理者">
</div>
<input type="text" name="mail" >
<input type="text" name="password" >
<input type="submit" name="submit" value="登録">
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>