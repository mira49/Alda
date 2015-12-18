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
			<h3 id="Inscription-title">Contact</h3>
			<div id="message_content">
				<div id="message_form">
				<form method="post" action="contact">
					<table>
					<input type="hidden" id="annonces" name="annonces" value="${current_announce.id}"> </input>
						<tr><td><output>Announce:</td><td> ${current_announce.name}</output></td></tr>
						<tr><td><output>Solder:</td><td> ${current_announce.user.name}</output></td></tr>
						<tr><td><output>Description:</td><td> ${current_announce.description}</output></td></tr>
						<tr><td><output>Message:</output></td>
						<td><input type="text" id="Message" name="Message" maxlength="600"
							required="" value=""> </input>
						 <button type="submit" value="send message" class="sansLabel"> Send Message </button></td></tr>
					 </table>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>