<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tag/brigade_tag.tld" prefix="brigadeTag"%>
<!DOCTYPE html>
<html>
<head>
<title>Flight brigade</title>
<link href="view/styles.css" rel="stylesheet">
</head>
<body>
	<h1 class="info">Вывести информацию о бригаде заданного рейса</h1>
	<form id="flight_id_brigade_form" action="Airline"></form>
	<c:if test="${not empty brigade}">
		<brigadeTag:getBrigade/>
	</c:if>
	<a href="Airline">На главную</a>
	<script src="view/script.js"></script>
</body>
</html>