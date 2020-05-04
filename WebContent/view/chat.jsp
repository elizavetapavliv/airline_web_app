<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="chatTitle" /></title>
<link href="view/styles.css" rel="stylesheet" />
<script src="script/chat_script.js"></script>
</head>
<body>
	<jsp:include page="language.jsp" />
	<h1 class="info">
		<c:choose>
			<c:when test="${sessionScope.user.getType() == 'admin'}">
				<fmt:message key="chatUsers" />
				<c:set var="userName" value="Admin"></c:set>
			</c:when>
			<c:otherwise>
				<fmt:message key="chatAdmin" />
				<c:set var="userName" value="${sessionScope.user.getEmail()}"></c:set>
			</c:otherwise>
		</c:choose>
	</h1>
		<c:if test="${sessionScope.user.getType() == 'admin'}">
			<p class="info">
				<fmt:message key="chatAdminInfo" />
			</p>
		</c:if>	
	<textArea id="chatWindow" rows="10" readonly></textArea>
	<div>
		<input id="chatInput" placeholder="<fmt:message key="message"/>"/>
		<button id="send" ><fmt:message key="send"/></button>
	</div>
	<script>userName = "$userName"; you = "<fmt:message key="you"/>";</script>
	<a href="Airline"><fmt:message key="toHome" /></a>
</body>
</html>