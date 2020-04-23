<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="flights" /></title>
<link href="view/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="language.jsp" />
	<h1 class="info"><fmt:message key="allFlights" /></h1>	
	<table class="table">
		<thead>
			<tr>
				<th><fmt:message key="id" /></th>
				<th><fmt:message key="passengers" /></th>
				<th><fmt:message key="distance" /></th>
				<th><fmt:message key="brigade" /></th>
				<th><fmt:message key="plane" /></th>
				<th><fmt:message key="departureAirport" /></th>
				<th><fmt:message key="arrivalAirport" /></th>
				<th><fmt:message key="isDelayed" /></th>
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
	<a href="Airline"><fmt:message key="toHome" /></a>
</body>
</html>