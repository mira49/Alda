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
						onclick="window.location='Deconnection'" class="sansLabel" /></td>

					<td><input type="button" value="Profil"
						onclick="window.location='User_Informations'" class="sansLabel" />
					</td>
					
					<td><input type="button" value="Add an Announcement"
						onclick="window.location='Add_Announcement'" class="sansLabel" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>