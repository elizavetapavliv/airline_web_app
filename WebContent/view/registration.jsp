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
<title><fmt:message key="signUp" /></title>
<link href="view/styles.css" rel="stylesheet" />
<script src="view/script.js"></script>
</head>
<body>
	<div class="chat_language">
		<jsp:include page="language.jsp" />
	</div>
	<h1 class="info"><fmt:message key="signUp" /></h1>
	<p class="error">${adminCodeError}</p>
	<form id="registration_form" action="Airline" method="POST"></form>
	<br><br>
	<form id="registration_login_form" action="Airline"></form>
	<script>dom("${sessionScope.locale.language}").initPage();</script>
</body>
</html>