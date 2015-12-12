<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connection</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>

	<div>
		<h1 id="Inscription-title"> Sign in</h1>
	</div>
	<div id="Inscription-form">
		<div id="error">
			${error}
		</div>

		
		<form method="post" action="Connexion">
			<fieldset id="Field">
				<legend>Connection</legend>

				<output>Email:</output>
				<input type="text" id="email" name="email" value="" size="20"
					maxlength="60" placeholder="exemple@exemple.exemple" required="" />
				<span id="error"class="erreur">${erreur['email']}</span>
					<br />

				<output>Password:</output>
				<input type="password" id="password" name="password" value=""
					size="20" maxlength="20" placeholder="password" required="" />
					<span id="error" class="erreur">${erreur['password']}</span>
					<a href="forgetting"> Forgotten password ? </a>
					 <br />
                <a  href="registration" > register ? </a>
                <br />
				<input type="submit" value="Connexion" class="sansLabel" /> <br />
			</fieldset>
			<br>
		</form>
	</div>
</body>
</html>