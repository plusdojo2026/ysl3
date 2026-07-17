<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>メンバー一覧 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
<h1>メンバー管理</h1>
<input type="button" value="検索">
<input type="button" value="新規登録">

<c:forEach var="e" item="">
<form method="post" action="">
<input type="text" name="user_id" value="{$e.user_id}">
<input type="text" name="login_id" value="{$e.login_id}">
<input type="text" name="user_name" value="{$e.user_name}">
<div class="role-btn">
	<input type="radio" name="role" value="一般" >
	<input type="radio" name="role" value="管理者" >
</div>
<input type="text" name="mail" value="{$e.mail}">
<input type="text" name="date" value="{$e.date}">
<div class="sol-btn">
	<input type="radio" name="sol" value="有効">
	<input type="radio" name="sol" value="無効">
</div>
<input type="submit" name="submit" value="編集">
</form>
</c:forEach>
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>