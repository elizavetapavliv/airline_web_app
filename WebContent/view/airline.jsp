<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
${pageContext.ELContext.importHandler.importClass('java.net.URLDecoder')}
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="airline" /></title>
<link href="view/styles.css" rel="stylesheet" />
</head>
<body>
	<div class="chat_language">
		<jsp:include page="chat_form.jsp" />
		<jsp:include page="language.jsp" />
	</div>
	<h1 class="info">
		<fmt:message key="airline" />
	</h1>
	<p class="info">
		<fmt:message key="lastSession" />
		: ${URLDecoder.decode(cookie['lastSessionDateTime'].getValue(), 'UTF-8')}<br>
		<fmt:message key="numberOfVisits" />
		: ${cookie['numberOfVisits'].getValue()}<br>
		<fmt:message key="role" />
		<fmt:message key="${sessionScope.user.getType()}" />
	</p>
	<ul class="menu">
		<c:if test="${sessionScope.user.getType() != 'guest'}">
			<li><a href="Airline?page=brigade"><fmt:message
						key="brigadeInfo" /></a></li>
		</c:if>
		<li><a href="Airline?page=flights"><fmt:message
					key="allFlights" /></a></li>
		<c:if test="${sessionScope.user.getType() == 'admin'}">
			<li><a href="Airline?page=airports"><fmt:message
						key="updateCancel" /></a></li>
		</c:if>
		<li><a href="Airline?page=delayed"><fmt:message
					key="delayedFlights" /></a></li>
	</ul>

	<fmt:message key="logOut" var="logOut" />
	<form action="Airline?page=logout" method="POST">
		<input type="submit" value="${logOut}">
	</form>
</body>
</html>