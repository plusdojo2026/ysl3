<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

  <h2 class="page-title">案件一覧</h2>
  
  <form action>
  <input type="hidden" name="action" value="selectProjectUserName">
  <button type="submit" class="project-regist-btn">新規登録</button>
</form>
  <!-- 検索フォームここから -->
  <form class="project-search-form">
    <input type="hidden" name="page-id" value="PR01">
 
    <select class="project-select" placeholder="ステータス">
     <option value="" disabled selected style="display:none;">ステータス</option>
    <option>進行中</option>
    <option>完了</option>
    <option>保留</option>
    </select>
    <select class="project-select" >
   <option value="" disabled selected style="display:none;">優先度</option>
    <option>低</option>
    <option>中</option>
    <option>高</option>
    </select>
    <input type="text" class="project-search-text" placeholder="キーワード検索" name="keyword">
    <input type="submit" class="search-btn" value="検索">
  </form>
  <!-- 検索フォームここまで -->

  <!-- 案件一覧ここから -->
   <c:forEach var="project" items="${projectList}">
   <div class="project-card">
   	<ul>
   		<li>案件コード :${project.Id}</li>
   		
   		<li>案件名 :${project.Name}<li>
   		
   		<li>顧客名:${project.Customer}</li>
   		
   		<li>開始日:${project.StartDate}</li>
   		
   		<li>期限日:${project.EndDate}</li>
   		
   		<li>ステータス:${project.Status}
   			<c:choose>
   				<c:when test="${project.Status==0}">
   				開始前
   				</c:when>
   				<c:when test="${project.Status==1}">
   				進行中
   				</c:when>
   				<c:when test="${project.Status==2}">
   				完了
   				</c:when>
   				<c:otherwise>
   				不明
   				</c:otherwise>
   			</c:choose>
   		</li>
   		
   		<li>優先度
   			<c:choose>
   				<c:when test="${project.Priority ==0}">
   				低
   				</c:when>
   				<c:when test="${project.Priority ==1}">
   				中
   				</c:when>
   				<c:when test="${project.priority ==2}">
   				高
   				</c:when>
   				<c:otherwise>
   				不明
   				</c:otherwise>
   			</c:choose>
   		</li>
   		
   		 <li>PM名:${project.PmName}</li>
   		 
   		 <li>進捗度:
   		 <c:choose>
   		 	<c:when test="${project.TotalWork > 0 }">
   		 		${(project.currentWorkHours * 100) / project.totalWorkHours}%
   		 	</c:when>
   		 	<c:otherwise>
				0%
			</c:otherwise>
		</c:choose>
   		 
   		 </li> 
   		
   		<li>総工数:${project.EstimatedWork}</li>
   		
   		<li>現在までの工数:${project.TotalWork}</li>  
   	</ul>
   	
   	<!-- 編集ボタン -->
   	<form ProjectAction>
   	<input type="hidden" name="action" value="projectToEdit">
   	
   	<!-- 編集対象案件ID -->
   	
   	<input type="hidden" name="projectId" value="${project.projecrtId}">
   	<button type="submit" class="edit-btn">編集</button>
   	</form>
   </div>
  </c:forEach>
  <!-- 案件一覧ここまで -->
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>