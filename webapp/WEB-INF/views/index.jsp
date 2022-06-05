<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">商品管理システム</h1>
	</div>

	<div class="login_form">
		<img src="./images/logo.png" class="login_logo">
		<div class="error">
			<c:if test="${not empty msg1}">
				<p>${msg1}</p>
			</c:if>
		</div>

		<form:form action="login" method="post" modelAttribute="user">
			<fieldset>
				<div class="cp_iptxt">
					<form:input class="base_input" type="text" path="loginId" placeholder="ID"/>
					<form:errors path="loginId" cssStyle="color: red"/>
					<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
				</div>

				<div>
					<form:input  class="base_input" type="password" path="password" placeholder="PASS"/>
					<form:errors path="password" cssStyle="color: red"/>
				</div>
			</fieldset>
			<form:button class="logout_btn" type="submit">ログイン</form:button>
		</form:form>
	</div>
</body>
</html>
