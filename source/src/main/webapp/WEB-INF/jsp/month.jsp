<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<form method="POST" action="<c:url value='/Controller'/>">
<input type="hidden" name="page-id" value="MO01">

<!-- 月切り替えボタン -->
<div class="month-control">
	<a href="month.action?month=${lastMonth}">
        ＜
    </a>
    <span>
        ${targetMonth}
    </span>
	<a href="month.action?month=${nextMonth}">
        ＞
    </a>
</div>

<!-- サマリーカード -->
<table>
    <tr>
        <th>稼働メンバー</th>
        <th>総工数</th>
        <th>予定</th>
        <th>残工数</th>
    </tr>
		<c:forEach var="summary" items="${summaryList}">
			<tr>
				<td>${summary.member }人</td><!-- 稼働メンバー -->
				<td>${summary.totalwork h}</td><!-- 総工数 -->
				<td>${summary.estimatedWork }h</td><!-- 予定 -->
				<td>${summary.remainWork }h</td><!-- 残工数 -->
			</tr>
		</c:forEach>
</table>
	
<!-- CSV出力ボタン -->
	<form action="${pageContext.request.contextPath}/Controller" method="post">
<!-- 多分EL式 -->	
	<input type="hidden" name="month" >
	<input type="submit" name="csv_btn" value="CSV出力">
	</form>

<h2>案件別実績</h2>
<table>
	<tr>
         <th>案件名</th>
         <th>実績工数</th>
         <th>予定</th>
         <th>残り工数</th>
         <th>進捗率</th>
    </tr>
        <c:forEach var="project" items="${projectSummaryList}">
            <tr>
                <td>${project.name}</td><!-- 案件名 -->
                <td>${project.actualWork} h</td><!-- 実績工数 -->
                <td>${project.plannedWork} h</td><!-- 予定 -->
                <td>${project.remainWork}h</td><!-- 残り工数 -->
                <td>${project.progressRate}%</td><!-- 進捗率 -->
				<td><div class="progress">
    					<div class="progress-bar" role="progressbar" 
    							style="width:${project.progressRate}%;"><!-- 進捗率 -->
    			                  ${project.progressRate}%
			     </div>
			    </div>
			   </td>
			</tr>
	  	</c:forEach>
</table>

<h2>ユーザー別実績</h2>
<table>
    <tr>
        <th>担当者名</th>
        <th>実績工数</th>
        <th>予定</th>
        <th>残り工数</th>
        <th>進捗率</th>
    </tr>
   		<c:forEach var="user" items="${userSummaryList}">
			<tr>
                <td>${user.name}</td><!-- 担当者名 -->
				<td>${user.actualWork} h</td><!-- 実績工数 -->
				<td>${user.plannedWork} h</td><!-- 予定 -->
				<td><div class="progress">
                        <div class="progress-bar" role="progressbar"
                             	style="width:${user.progressRate}%;"><!-- 進捗率 -->
                            	  ${user.progressRate}%
                   </div>
                  </div>
                </td>
             </tr>
         </c:forEach>
</table>
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>