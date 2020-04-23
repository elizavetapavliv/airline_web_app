<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>    
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="signIn" /></title>
<link href="view/styles.css" rel="stylesheet" />
<script src="view/script.js"></script>
</head>
<body>
	<jsp:include page="language.jsp" />
	<h1 class="info"><fmt:message key="welcome" />!</h1>
	<p class="info">
		<b><fmt:message key="administrator" /></b> <fmt:message key="assign" /> <b><fmt:message key="planeAssign" /></b> 
		<fmt:message key="on" /> <b><fmt:message key="flight" /></b> 
		<fmt:message key="takeInto" /> <fmt:message key="assigns" /> <fmt:message key="on" /> <b><fmt:message key="flight" /></b> 
		<fmt:message key="one" /> <b><fmt:message key="brigades" /></b>. <b><fmt:message key="brigadeFormed" /></b> 
		<fmt:message key="formed" /> <b><fmt:message key="commander" /></b>
		<fmt:message key="consists" />. <b><fmt:message key="administrator" /></b> 
		<fmt:message key="cancel" /> <b><fmt:message key="flight" /></b> <fmt:message key="change" /> 
		<b><fmt:message key="arrivalAirport" /></b> <fmt:message key="fault" />.
	</p>
	<form id="login_form" action="Airline" method="POST"></form>
	<div class="button_container">
		<form id="login_registration_form" action="Airline"></form>
		<form id="login_guest_form" action="Airline" method="POST"></form>
	</div>
	<script>dom("${sessionScope.locale.language}").initPage();</script>
</body>
</html>