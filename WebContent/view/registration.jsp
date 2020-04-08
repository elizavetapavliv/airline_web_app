<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign up</title>
<link href="view/styles.css" rel="stylesheet" />
</head>
<body>
	<h1 class="info">Регистрация</h1>
	<p class="error">${adminCodeError}</p>
	<form id="registration_form" action="Airline" method="POST"></form>
	<br><br>
	<form id="registration_login_form" action="Airline"></form>
	<script src="view/script.js"></script>
</body>
</html>