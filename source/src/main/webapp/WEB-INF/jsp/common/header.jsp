<%@ page contentType="text/html; charset=UTF-8" %>

<header class="header">
  <a href="${pageContext.request.contextPath}/Controller?page_id=home"
     class="system-title">TaskManager</a>

  <button id="menuButton" class="menu-button" type="button">☰</button>
</header>

<nav id="menu" class="menu">
  <button id="closeButton" class="close-button" type="button">×</button>

  <p class="menu-title">メインメニュー</p>
  <a href="${pageContext.request.contextPath}/Controller?page_id=home">ホーム</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=projectList">案件一覧</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskList">タスク一覧</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=month">月次集計</a>
    <a href="${pageContext.request.contextPath}/Controller?page_id=memberList">
    メンバー一覧
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=mypage">マイページ</a>
  <%-- 今だけ --%>
  <div class="now-menu">  <a href="${pageContext.request.contextPath}/Controller?page_id=projectDetail">
    案件詳細
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=projectRegist">
    案件登録
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=projectEdit">
    案件編集
  </a>

  <a href="${pageContext.request.contextPath}/Controller?page_id=taskDetail">
    タスク詳細
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskRegist">
    タスク登録
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=taskEdit">
    タスク編集
  </a>

  <a href="${pageContext.request.contextPath}/Controller?page_id=workRegist">
    工数登録
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=memberRegist">
    メンバー登録
  </a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=memberEdit">
    メンバー編集
  </a>

  <a href="${pageContext.request.contextPath}/Controller?page_id=login">ログイン画面</a>
  <a href="${pageContext.request.contextPath}/Controller?page_id=logout">ログアウト</a></div>


</nav>
    