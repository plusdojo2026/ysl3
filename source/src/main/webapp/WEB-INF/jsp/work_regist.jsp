<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>工数登録 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
	<form method="POST" action="/ysl3/WorkAction" id="form" >
		<div>
		  日付（必須）
			<input type="date" name="day" required><br><!-- 日付入力 -->
		</div>
		<div>
		  作業内容
		  <input type="text" name="work_explanation" id="work_explanation" ><br>
		</div>
		<div class="container">
		  	<h2>工数</h2><h4>（必須、0.5時間刻み</h4>
		  	<input type="range" id="work" name="work" min="0.5" max="24" step="0.5" class="slider">
		</div><br><br>
		<input type="submit" value="登録">
	</form>
  	 </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>