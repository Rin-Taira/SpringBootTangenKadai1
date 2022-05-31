<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/index.jsp" />
	</c:if>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
		</h1>
		<div class="user">
			<c:if test="${not empty user}">
				<p class="user_name">${user.name}さん、こんにちは</p>
			</c:if>
			<form class="logout_form" action="logout" method="get">
				<button class="logout_btn" type="submit">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="form_body">
			<c:if test="${not empty msg}">
				<p class="error">${msg}</p>
			</c:if>

			<form action="update" method="get">
				<fieldset class="label-130">
					<div>
						<label>商品ID</label>
						<input type="text" name="product_id" value="${product.productId}" class="base-text">
						<span class="error">
							<c:if test="${not empty msg2}">
								<p>${msg2}</p>
							</c:if>
						</span>
					</div>
					<div>
						<label>商品名</label>
						<input type="text" name="product_name" value="${product.name}" class="base-text">
						<span class="error">
							<c:if test="${not empty msg3}">
								<p>${msg3}</p>
							</c:if>
						</span>
					</div>
					<div>
						<label>単価</label>
						<input type="text" name="price" value="${product.price}" class="base-text">
						<span class="error">
							<c:if test="${not empty msg4}">
								<p>${msg4}</p>
							</c:if>
						</span>
					</div>
					<div>
						<label>カテゴリ</label>
						<select name="category" class="base-text">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label>商品説明</label>
						<textarea name="description" class="base-text">
						${product.description}
            			</textarea>
					</div>
					<div>
						<label>画像</label>
						<input type="file" name="file">
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">更新</button>
					<input type="button" onclick="location.href='./menu.jsp'" value="メニューに戻る" class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">更新しますか？</p>
					<div class="btns">
						<button type="submit" class="basic_btn">更新</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>