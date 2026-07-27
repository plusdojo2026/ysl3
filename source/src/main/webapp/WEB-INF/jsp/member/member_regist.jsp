<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メンバー新規登録 | TaskManager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <script src="${pageContext.request.contextPath}/js/common.js" defer></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>

    <main class="main">
        <h1>メンバー新規登録</h1>

        <c:choose>
            <c:when test="${sessionScope.user.role != 1}">
                <p>この画面は管理者のみ利用できます。</p>
            </c:when>

            <c:otherwise>
                <c:if test="${not empty errMsg}">
                    <p><c:out value="${errMsg}" /></p>
                </c:if>

                <c:set var="selectedRole" value="${empty param.role ? '0' : param.role}" />

                <form method="post" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="page-id" value="AD02">

                    <div>
                        <label for="login-id">ID（必須）</label>
                        <input type="text" id="login-id" name="login-id" value="${fn:escapeXml(param['login-id'])}" maxlength="10" autocomplete="username" required>
                    </div>

                    <div>
                        <label for="user-name">氏名（必須）</label>
                        <input type="text" id="user-name" name="user-name" value="${fn:escapeXml(param['user-name'])}" maxlength="10" required>
                    </div>

                    <div>
                        <label for="mail">メールアドレス（必須）</label>
                        <input type="email" id="mail" name="mail" value="${fn:escapeXml(param.mail)}" maxlength="255" autocomplete="email" required>
                    </div>

                    <div>
                        <label for="password">初期パスワード（必須・6～20文字）</label>
                        <input type="password" id="password" name="password" minlength="6" maxlength="20" pattern="(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,20}" title="半角英字と数字を含む6文字以上20文字以内で入力してください" autocomplete="new-password" required>
                    </div>

                    <fieldset>
                        <legend>権限</legend>
                        <label>
                            <input type="radio" name="role" value="0" ${selectedRole == '0' ? 'checked' : ''}> 一般
                        </label>
                        <label>
                            <input type="radio" name="role" value="1" ${selectedRole == '1' ? 'checked' : ''}> 管理者
                        </label>
                    </fieldset>

                    <button type="submit" name="btn-id" value="member-regist">登録</button>
                </form>

                <a href="${pageContext.request.contextPath}/Controller?page-id=AD01">メンバー一覧へ戻る</a>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
