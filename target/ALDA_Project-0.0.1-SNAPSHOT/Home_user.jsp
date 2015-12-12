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
	<div id="body-application">
		<div id="home_user">Welcome on your account
			${sessionScope.user.firstName} ${sessionScope.user.name}</div>
		<form method="post" action="Deconnection">
			<input type="submit" value="Deconnection" />
		</form>

		<form method="get" action="User_Informations">
			<input type="submit" value="Profil" />
		</form>
	</div>
</body>
</html>