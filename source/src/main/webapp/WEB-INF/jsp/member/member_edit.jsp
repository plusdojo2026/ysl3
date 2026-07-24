<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>メンバー編集 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
<h1>メンバー編集</h1>

<input type="text" name="user_name">
<div class="role-btn">
	<input type="radio" name="role" value="一般" >
	<input type="radio" name="role" value="管理者" >
</div>
<input type="text" name="mail" >
<div class="sol-btn">
	<input type="radio" name="sol" value="有効">
	<input type="radio" name="sol" value="無効">
</div>

<input type="submit" name="submit" value="保存">
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>