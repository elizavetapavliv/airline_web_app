<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="airports" /></title>
<link href="view/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="language.jsp" />
	<h1 class="info"><fmt:message key="updateCancel" /></h1>
	<form id="flight_id_airports_form" action="Airline"></form>
	<c:if test="${not empty airports}">
		<form id="choice_form" action="Airline" method="POST"></form>
		<p id="flight_id" hidden="true">${flight_id}</p>
		<p id="from_airport_id" hidden="true">${from_airport_id}</p>
		<table class="vertical_table" id="airports_table">
			<tr>
				<th></th>
				<th><fmt:message key="departure" /></th>
				<th><fmt:message key="arrival" /></th>
			</tr>
			<tr>
				<th><fmt:message key="id" /></th>
				<td>${airports[0].id}</td>
				<td>${airports[1].id}</td>
			</tr>
			<tr>
				<th><fmt:message key="name" /></th>
				<td>${airports[0].name}</td>
				<td>${airports[1].name}</td>
			</tr>
			<tr>
				<th><fmt:message key="weather" /></th>
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
				<th><fmt:message key="technique" /></th>
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
	<a href="Airline"><fmt:message key="toHome" /></a>
	<script src="view/script.js">dom("${sessionScope.locale.language}").initPage();</script>
</body>
</html>