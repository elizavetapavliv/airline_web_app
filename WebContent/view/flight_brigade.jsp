<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<p id="flight_id" hidden="true">${brigade.flight.id}</p>
		<table class="vertical_table">
			<tr>
				<th>Id</th>
				<td>${brigade.id}</td>
			</tr>
			<tr>
				<th>Pilot 1</th>
				<td>${brigade.pilot1}</td>
			</tr>
			<tr>
				<th>Pilot 2</th>
				<td>${brigade.pilot2}</td>
			</tr>
			<tr>
				<th>Navigator</th>
				<td>${brigade.navigator}</td>
			</tr>
			<tr>
				<th>Radio operator</th>
				<td>${brigade.radioOperator}</td>
			</tr>
			<tr>
				<th>Stewardesses</th>
				<td>
					<table class="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${brigade.stewardesses}" var="stewardess">
								<tr>
									<td>${stewardess.id}</td>
									<td>${stewardess.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</c:if>
	<a href="Airline">На главную</a>
	<script src="view/script.js"></script>
</body>
</html>