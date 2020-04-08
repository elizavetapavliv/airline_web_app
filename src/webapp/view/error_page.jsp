<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet error</title>
<link href="view/styles.css" rel="stylesheet" />
</head>
<body>
	<h1 class="error">
		<b>Request failed:</b> ${pageContext.errorData.requestURI}
	</h1>
	<p class="error">
		<b>Status code:</b> ${pageContext.errorData.statusCode}</br>
		<b>Exception:</b> ${requestScope['javax.servlet.error.message']}</br>
	</p>
	<c:choose>
		<c:when test="${empty sessionScope['user']}">
			<a href="${pageContext.request.contextPath}">Назад</a>
		</c:when>
		<c:otherwise>
			<a href="Airline">На главную</a>
		</c:otherwise>
	</c:choose>
</body>
</html>