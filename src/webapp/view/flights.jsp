<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<title>Airline flights</title>
<link href="view/styles.css" rel="stylesheet">
</head>
<body>
	<h1 class="info">Вывести список всех рейсов</h1>	
	<table class="table">
		<thead>
			<tr>
				<th>Id</th><th>Passengers number</th><th>Distance</th>
				<th>Brigade id</th><th>Plane id</th><th>Departure airport id</th>
				<th>Arrival airport id</th><th>Is delayed</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${flights}" var="flight">
        		<tr>
            		<td>${flight.id}</td>
            		<td>${flight.passengersNum}</td>
            		<td>${flight.distance}</td>
            		<td>
            			<c:choose>
       						<c:when test="${empty flight.brigade}">-</c:when>
    						 <c:otherwise>${flight.brigade.id}</c:otherwise>
 						</c:choose>	
            		</td>
            		<td>
            			<c:choose>
       						<c:when test="${empty flight.plane}">-</c:when>
    						 <c:otherwise>${flight.plane.id}</c:otherwise>
 						</c:choose>	
            		</td>
            		<td>${flight.fromAirportId}</td>
            		<td>${flight.toAirportId}</td>
            		<td>
            			<c:choose>
       						<c:when test="${flight.delayed}">
             					<input type="checkbox" onClick="return false;" checked>
      						 </c:when>
      						 <c:otherwise>
      						 	<input type="checkbox" onClick="return false;">
       						</c:otherwise>
 						</c:choose>	
           			</td>
        		</tr>
    		</c:forEach>
    	</tbody>
	</table>
	<a href="Airline"> На главную</a>
</body>
</html>