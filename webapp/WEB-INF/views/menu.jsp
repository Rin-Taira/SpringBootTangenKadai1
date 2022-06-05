<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/index" />
	</c:if>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu.html">商品管理システム</a>
			</h1>
			<div class="user">
				<c:if test="${not empty user}">
					<p class="user_name">${user.name}さん、こんにちは</p>
				</c:if>
				<form class="logout_form" action="logout" method="post">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>

		<c:if test="${user.role == 1}">
			<div class="btn">
				<a class="basic_btn regist" href="insert">新規登録</a>
			</div>
		</c:if>
		<p>
			<c:if test="${not empty msg}">
				<p class="user_name">${msg}</p>
			</c:if>
		</p>
		<form method="get" action="search" class="search_container">
			<input type="text" name="keyword" size="25" placeholder="キーワード検索">
			<input type="submit" value="&#xf002">
		</form>

		<table>
			<div class="caption">
				<p>検索結果: ${fn:length(productList)}件</p>
			</div>
			<div class="order">
				<form method="get" action="sort">
					<select name="sort" class="base-text" onchange="this.form.submit()">
						<option selected disabled>並び替え</option>
						<option value="1" ${s1}>商品ID</option>
						<option value="2" ${s2}>カテゴリ</option>
						<option value="3" ${s3}>単価：安い順</option>
						<option value="4" ${s4}>単価：高い順</option>
						<option value="5" ${s5}>登録日：古い順</option>
						<option value="6" ${s6}>登録日：新しい順</option>
					</select>
				</form>
			</div>
			<thead>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>単価</th>
					<th>カテゴリ</th>
					<th>詳細</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${productList}">
					<form:form action="detail" modelAttribute="product" method="get">
						<form:input type="hidden" path="productId" value="${product.productId}"/>
						<form:input type="hidden" path="categoryId" value="${product.categoryId}"/>
						<form:input type="hidden" path="categoryName" value="${product.categoryName}"/>
						<form:input type="hidden" path="name" value="${product.name}"/>
						<form:input type="hidden" path="price" value="${product.price}"/>
						<form:input type="hidden" path="description" value="${product.description}"/>
						<form:input type="hidden" path="createdDate" value="${product.createdDate}"/>
						<form:input type="hidden" path="updatedDate" value="${product.updatedDate}"/>
						<tr>
							<td>${fn:escapeXml(product.productId)}</td>
							<td>${fn:escapeXml(product.name)}</td>
							<td>${fn:escapeXml(product.price)}</td>
							<td>${fn:escapeXml(product.categoryName)}</td>
							<td><button type="submit" class="detail_btn" name="id" value="${product.productId}">詳細</button></td>
						</tr>
						</form:form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer></footer>

</body>
</html>
