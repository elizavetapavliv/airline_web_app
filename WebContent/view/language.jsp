<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form>
	<select name="language" onchange='this.form.submit()'>
		<c:choose>
			<c:when test="${sessionScope.locale.language=='ru'}">
				<option value='ru' selected>Русский
				<option value='en'>English
			</c:when>
			<c:otherwise>
				<option value='en' selected>English
				<option value='ru'>Русский
			</c:otherwise>
		</c:choose>
	</select>
</form>