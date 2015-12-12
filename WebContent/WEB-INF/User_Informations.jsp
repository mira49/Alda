<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<div>
		<h1 id="Inscription-title">Add informations or change it</h1>
	</div>

	<div id="home">
		<form method="post" action="user_Informations">
			<fieldset id="Field">
				<legend>Informations</legend>
				<output>Email:</output>
				<input type="text" id="email" name="email" size="20" maxlength="60"
					required="" value=${sessionScope.user.email}></input> <br />
				<output>Name:</output>
				<input type="text" id="Name" name="Name" size="20" maxlength="60"
					required="" value=${sessionScope.user.name}> </input> <br />
				<output>FirstName:</output>
				<input type="text" id="FirstName" name="FirstName" size="20"
					maxlength="60" required="" value=${sessionScope.user.firstName}>
				</input> <br />
				<output>Address:</output>
				<input type="text" id="Address" name="Address" size="20"
					maxlength="60" required="" value=${sessionScope.user.address}>
				</input> <br />
				<output>Phone:</output>
				<input type="text" id="Phone" name="Phone" size="20" maxlength="60"
					required="" value=${sessionScope.user.phone}> </input> <br /> 
				<input type="submit" value="valider" class="sansLabel" /> <br />
			</fieldset>
			<br>
		</form>

		<form method="get" action="home_user">
			<input type="submit" value="retour" />
		</form>
	</div>
</body>
</html>