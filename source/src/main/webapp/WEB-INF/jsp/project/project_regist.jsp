<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>案件登録 | TaskManager</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
  <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
  <!---------- メインここから ---------->
  <main class="main">
    <!-- 案件登録フォームここから -->
	  <form method="POST" class="pr-regist-form" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page-id" value="PR02">
        案件コード(必須)<br>
        <input type="text" class="pr-regist-input" name="project-code" placeholder="20文字以内" required max="20">
        案件名(必須)<br>
        <input type="text" class="pr-regist-input" name="project-name" placeholder="案件名を入力してください" required max="100">
        顧客名<br>
        <input type="text" class="pr-regist-input" name="project-customer" placeholder="顧客名を入力してください" required max="50">
        担当PM<br>
        <select class="pr-regist-input pr-regist-select" name="project-manager">
        ステータス(必須)<br>
        <select class="pr-regist-input pr-regist-select" name="project-status">
        優先度(必須)<br>
        <select class="pr-regist-input pr-regist-select" name="project-priority">
        開始日<br>
        <input type="date" class="pr-regist-input pr-regist-date" name="project-start">
        終了予定日<br>
        <input type="date" class="pr-regist-input pr-regist-date" name="project-finish">
        予算工数(h)<br>
        <input type="number" class="pr-regist-input" name="project-work">
        説明<br>
        <input type="text" class="pr-regist-input" name="project-explain" max="1000">
        <input type="submit" class="regist-btn" name="btn-id"  value="登録">
      </form>
    <!-- 案件登録フォームここまで -->
  </main>
    <!---------- メインここまで ---------->
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>