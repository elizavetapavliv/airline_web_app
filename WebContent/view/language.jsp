<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="view/styles.css" rel="stylesheet"/>
<form class="language">
	<select name="language" onchange='this.form.submit()'>
		<c:choose>
			<c:when test="${sessionScope['language']}">
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