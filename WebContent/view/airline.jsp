<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
${pageContext.ELContext.importHandler.importClass('java.net.URLDecoder')}
<!DOCTYPE html>
<html>
<head>
<title>Airline</title>
<link href="view/styles.css" rel="stylesheet"/>
</head>
<body>
	<h1 class="info">Авиакомпания</h1>
	<p class="info">
		Последний сеанс: ${URLDecoder.decode(cookie['lastSessionDateTime'].getValue(), 'UTF-8')}<br>
		Количество посещений ресурса: ${URLDecoder.decode(cookie['numberOfVisits'].getValue(), 'UTF-8')}<br>
		Вы зашли как ${sessionScope['user'].getType()}
	</p>
	<ul class="menu">
		<c:if test="${sessionScope['user'].getType() != 'guest'}">
			<li><a href="Airline?page=brigade">Вывести информацию о бригаде заданного рейса</a></li>
		</c:if>
		<li><a href="Airline?page=flights">Вывести список всех рейсов</a></li>
		<c:if test="${sessionScope['user'].getType() == 'admin'}">
			<li><a href="Airline?page=airports">Изменить аэропорт назначения рейса, отменить рейс</a></li>
		</c:if>
		<li><a href="Airline?page=delayed">Вывести информацию о задержанных рейсах</a></li>
	</ul>
	<form action="Airline?page=logout" method = "POST">
		<input type="submit" value="Выйти">
	</form>
</body>
</html>