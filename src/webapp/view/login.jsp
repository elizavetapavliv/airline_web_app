<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign in</title>
<link href="view/styles.css" rel="stylesheet" />
</head>
<body>
	<h1 class="info">Добро пожаловать!</h1>
	<p class="info">
		<b>Администратор</b> осуществляет назначение <b>Самолета</b> на <b>Рейс</b>
		с учетом вместимости и дальности полета и назначает на <b>Рейс</b>
		одну из летных <b>Бригад</b>. <b>Бригада</b> формируется <b>Командиром</b>
		и состоит из пилотов, штурмана, радиста, стюардесс. Администратор
		может отменить <b>Рейс</b> из-за погодных условий, либо изменить в
		полете <b>Аэропорт</b> назначения из-за технических неисправностей.
	</p>
	<form id="login_form" action="Airline" method="POST"></form>
	<div class="button_container">
		<form id="login_registration_form" action="Airline"></form>
		<form id="login_guest_form" action="Airline" method="POST"></form>
	</div>
	<script src="view/script.js"></script>
</body>
</html>