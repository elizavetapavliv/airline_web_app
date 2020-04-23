<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tag/brigade_tag.tld" prefix="brigadeTag"%>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>    
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="flightBrigade" /></title>
<link href="view/styles.css" rel="stylesheet">
<script src="view/script.js"></script>
</head>
<body>
	<jsp:include page="language.jsp" />
	<h1 class="info"><fmt:message key="brigadeInfo" /></h1>
	<form id="flight_id_brigade_form" action="Airline"></form>
	<c:if test="${not empty brigade}">
		<brigadeTag:getBrigade/>
	</c:if>
	<a href="Airline"><fmt:message key="toHome" /></a>
	<script>dom("${sessionScope.locale.language}").initPage();</script>
</body>
</html>