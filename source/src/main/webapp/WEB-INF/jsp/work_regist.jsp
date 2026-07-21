<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.time.LocalDate" %>

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
 <br><br><br> <main class="main">
	<form method="POST" action="/ysl3/WorkAction" id="form" >
		<div>
		 工数登録
		 <input type="text" id="project-name" name="project" value="${project.id}"  required readonly>/
		 <input type="text" id="task-name" name="task" value="${task.id}"  required  readonly>

		</div>
		<div>
		  日付（必須）
		 <input type="date" name="day" value="<%= LocalDate.now() %>"><br><!-- 日付入力 -->
		</div>
		<div>
		  作業内容
		  <input type="text" name="work_explanation" id="work_explanation" ><br>
		</div>
		<div class="container">
		  	<h2>工数（必須、0.5時間刻み)</h2>
		  	<input type="range" id="work" name="work" min="0.5" max="24" step="0.5" class="slider" value="0" oninput="updateTime(this.value)">
		</div><br><br>
		
		<span id="timeLabel">0.5時間</span><br>
		
		<input type="submit" value="登録">
		
		
		
<script>
	function updateTime(value) {
	    document.getElementById("timeLabel").textContent =
	        value + "時間";
}
</script>
		
	</form>
  	 </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>