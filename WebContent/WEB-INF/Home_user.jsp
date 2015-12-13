<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
<link type="text/css" rel="stylesheet" href="CSS/bootstrap.css" />
</head>
<body>
	<div id="home">
		<div id="home_user">
			<h3 id="Inscription-title">Welcome on your account
				${sessionScope.user.firstName} ${sessionScope.user.name}</h3>
		</div>
		<div id="home_content">
		<div id="home_options">
			<table id="home_table">
				<tr>
					<td><input type="button" value="Deconnection"
						onclick="window.location='deconnection'" class="sansLabel" /></td>
				 </tr>

					<tr><td><input type="button" value="Profil"
						onclick="window.location='user_Informations'" class="sansLabel" /></td>
					 </tr>

					<tr><td><input type="button" value="Add announcement"
						onclick="window.location='add_Announcement'" class="sansLabel" /></td>
					 </tr>
					 
					 <tr><td><input type="button" value="Announcements"
						onclick="window.location='announcement'" class="sansLabel" /></td>
					 </tr>
			</table>
			</div>
			<div id="home_announcement">
				<h4>My announcements</h4>
			<table class="table">
				<tr>
					<th>name</th>
					<th>surface</th>
					<th>description</th>
					<th>emplacement</th>
					<th>Delete</th>
				</tr>
				<form action="home_user" method="post">
					<c:forEach var="annoucement"
						items="${ sessionScope.annoucement_user}">
						<tr>
							<td><c:out value="${annoucement.name}" /></td>
							<td><c:out value="${annoucement.surface}" /></td>
							<td><c:out value="${annoucement.description}" /></td>
							<td><c:out value="${annoucement.price}" /></td>
							<td><button type="submit" name="delete"
									value="${annoucement.id}">delete</button></td>
						</tr>
					</c:forEach>
				</form>
			</table>
			</div>
		</div>
	</div>
</body>
</html>