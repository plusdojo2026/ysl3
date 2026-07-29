<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

    <main class="main">
		<div class="title-area">
			<h1 class="page-title">メンバー編集</h1>
		</div>

        <c:choose>
            <c:when test="${sessionScope.user.role != 1}">
                <p>この画面は管理者のみ利用できます。</p>
            </c:when>

            <c:when test="${empty member}">
                <p>メンバー情報を取得できませんでした。</p>
                <a href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー一覧へ戻る</a>
            </c:when>

            <c:otherwise>
                <c:if test="${not empty errMsg}">
                    <p><c:out value="${errMsg}" /></p>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="page-id" value="AD03">
                    <input type="hidden" name="user-id" value="${member.id}">

                    <div>
                        <label for="user-name">氏名（必須）</label>
                        <input type="text" id="user-name" name="user-name" value="${fn:escapeXml(member.userName)}" maxlength="10" required>
                    </div>

                    <div>
                        <label for="mail">メールアドレス（必須）</label>
                        <input type="email" id="mail" name="mail" value="${fn:escapeXml(member.mail)}" maxlength="255" required>
                    </div>

                    <fieldset>
                        <legend>権限</legend>
                        <label>
                            <input type="radio" name="role" value="0" ${member.role == 0 ? 'checked' : ''}> 一般
                        </label>
                        <label>
                            <input type="radio" name="role" value="1" ${member.role == 1 ? 'checked' : ''}> 管理者
                        </label>
                    </fieldset>

                    <fieldset>
                        <legend>状態</legend>
                        <label>
                            <input type="radio" name="sol" value="1" ${member.sol == 1 ? 'checked' : ''}> 有効
                        </label>
                        <label>
                            <input type="radio" name="sol" value="0" ${member.sol == 0 ? 'checked' : ''}> 無効
                        </label>
                    </fieldset>

                    <button type="submit" name="btn-id" value="member-update">保存</button>
                </form>

                <a href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー一覧へ戻る</a>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
