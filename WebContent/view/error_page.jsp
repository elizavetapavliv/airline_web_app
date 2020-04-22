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
<title><fmt:message key="error"/></title>
<link href="view/styles.css" rel="stylesheet" />
</head>
<body>
	<h1 class="error">
		<b><fmt:message key="request"/></b> ${pageContext.errorData.requestURI}
	</h1>
	<p class="error">
		<b><fmt:message key="status"/>:</b> ${pageContext.errorData.statusCode}<br>
		<b><fmt:message key="exception"/>:</b> ${requestScope['javax.servlet.error.message']}<br>
	</p>
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<a href="${pageContext.request.contextPath}"><fmt:message key="back" /></a>
		</c:when>
		<c:otherwise>
			<a href="Airline"><fmt:message key="toHome" /></a>
		</c:otherwise>
	</c:choose>
</body>
</html>