<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メンバー管理 | TaskManager</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member.css">
<script src="${pageContext.request.contextPath}/js/common.js" defer></script>
<script src="${pageContext.request.contextPath}/js/member.js" defer></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

	<main class="main">
		<div class="title-area">
			<h1 class="page-title">メンバー一覧</h1>
		</div>

		<c:choose>
			<c:when test="${sessionScope.user.role != 1}">
				<p>この画面は管理者のみ利用できます。</p>
			</c:when>

			<c:otherwise>
				<c:if test="${not empty msg}">
					<p>
						<c:out value="${msg}" />
					</p>
				</c:if>

				<c:if test="${not empty errMsg}">
					<p>
						<c:out value="${errMsg}" />
					</p>
				</c:if>
				<section class="member-top">
					<div class="search-area">
						<div class="list-content">
							<label id="key-word"> <input type="search"
								class="search-bar" id="search-bar" placeholder="キーワード検索" /> <input
								type="button" class="search-button" id="search-button"
								value="検索" />
							</label>
						</div>
					</div>
					<a href="${pageContext.request.contextPath}/Controller?page-id=AD02" class="regist-btn">新規登録</a>
				</section>

				<section>
					<h2>メンバー一覧</h2>
					<c:choose>
						<c:when test="${empty allUserList}">
							<p>表示できるメンバーはいません。</p>
						</c:when>
						<c:otherwise>
							<table>
								<thead>
									<tr>
										<th>ID</th>
										<th>ログインID</th>
										<th>氏名</th>
										<th>権限</th>
										<th>メールアドレス</th>
										<th>状態</th>
										<th>操作</th>
									</tr>
								</thead>
									<tbody>
										<c:forEach var="member" items="${allUserList}">
											<!-- 修正：divを削除し、trにクラスを付与 -->
											<tr class="member-row">
												<td><c:out value="${member.id}" /></td>
												<td><c:out value="${member.loginId}" /></td>
												<td><c:out value="${member.userName}" /></td>
												<td><c:choose>
														<c:when test="${member.role == 1}">管理者</c:when>
														<c:otherwise>一般</c:otherwise>
													</c:choose></td>
												<td><c:out value="${member.mail}" /></td>
												<td><c:choose>
														<c:when test="${member.sol == 1}">有効</c:when>
														<c:otherwise>無効</c:otherwise>
													</c:choose></td>
												<td><c:url var="memberEditUrl" value="/Controller">
														<c:param name="page-id" value="AD03" />
														<c:param name="user-id" value="${member.id}" />
													</c:url> <a href="${memberEditUrl}">編集</a></td>
											</tr>
										</c:forEach>
									</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</section>
			</c:otherwise>
		</c:choose>
	</main>

	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
