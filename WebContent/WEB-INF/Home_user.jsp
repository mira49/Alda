<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<div id="home">
		<div id="body-application">
			<div id="home_user">
				<h1 id="Inscription-title">Welcome on your account
					${sessionScope.user.firstName} ${sessionScope.user.name}</h1>
			</div>
			<table id="home_table">
				<tr>
					<td><input type="button" value="Deconnection"
						onclick="window.location='deconnection'" class="sansLabel" /></td>

					<td><input type="button" value="Profil"
						onclick="window.location='user_Informations'" class="sansLabel" />
					</td>

					<td><input type="button" value="Add an Announcement"
						onclick="window.location='add_Announcement'" class="sansLabel" />
					</td>
				</tr>
			</table>
			<h1>Your list of announcement</h1>
			<table>
				<tr>
					<th>name</th>
					<th>surface</th>
					<th>description</th>
					<th>emplacement</th>
				</tr>

				<c:forEach var="annoucement" items="${ sessionScope.annoucement_user}">
					<tr>
						<td> <c:out value="${annoucement.name}"/> </td>
						<td> <c:out value="${annoucement.surface}" /></td>
						<td> <c:out value="${annoucement.description}" /></td>
						<td> <c:out value="${annoucement.emplacement}" /></td>
						<td> <input type="button" value="delete"
						onclick="window.location='home_user.delete()'" class="sansLabel" /> </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>