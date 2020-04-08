<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Flight airports</title>
<link href="view/styles.css" rel="stylesheet">
</head>
<body>
	<h1 class="info">Изменить аэропорт назначения рейса, отменить рейс</h1>
	<form id="flight_id_airports_form" action="Airline"></form>
	<c:if test="${not empty airports}">
		<form id="choice_form" action="Airline" method="POST"></form>
		<p id="flight_id" hidden="true">${flight_id}</p>
		<p id="from_airport_id" hidden="true">${from_airport_id}</p>
		<table class="vertical_table" id="airports_table">
			<tr>
				<th></th>
				<th>Departure</th>
				<th>Arrival</th>
			</tr>
			<tr>
				<th>Id</th>
				<td>${airports[0].id}</td>
				<td>${airports[1].id}</td>
			</tr>
			<tr>
				<th>Name</th>
				<td>${airports[0].name}</td>
				<td>${airports[1].name}</td>
			</tr>
			<tr>
				<th>Weather is good</th>
				<td><c:choose>
						<c:when test="${airports[0].weatherGood}">
							<input type="checkbox" onClick="return false;" checked>
						</c:when>
						<c:otherwise>
							<input type="checkbox" onClick="return false;">
						</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${airports[1].weatherGood}">
							<input type="checkbox" onClick="return false;" checked>
						</c:when>
						<c:otherwise>
							<input type="checkbox" onClick="return false;">
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>Technic is good</th>
				<td><c:choose>
						<c:when test="${airports[0].technicGood}">
							<input type="checkbox" onClick="return false;" checked>
						</c:when>
						<c:otherwise>
							<input type="checkbox" onClick="return false;">
						</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${airports[1].technicGood}">
							<input type="checkbox" onClick="return false;" checked>
						</c:when>
						<c:otherwise>
							<input type="checkbox" onClick="return false;">
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</c:if>
	<a href="Airline">На главную</a>
	<script src="view/script.js"></script>
</body>
</html>