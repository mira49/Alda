<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registration</title>
	<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>

	<div>

		<div> 
			<h1 id="Inscription-title"> Welcome on the web application</h1>
		</div>
		
		<div id="Inscription-form">
		<form method="post" action="Registration">
			<fieldset id="Fieldset">
				<legend>Inscription</legend>

				
				
				<output>Email <span class="requis">*</span> </output>
				<input type="text" id="email" name="email" value="" size="30"
					maxlength="30" placeholder="email" required="" />  
				<span id="error"class="erreur">${erreur['email']}</span>
					<br />

				<output>Password <span class="requis">*</span></output>
				<input type="password" id="password" name="password" value=""
					size="20" maxlength="20" placeholder="password" required="" /> 
				<span id="error" class="erreur">${erreur['password']}</span>
					 <br />

				<output>Confirm password <span class="requis">*</span></output>
				<input type="password" id="confirmation" name="confirmation"
					value="" size="20" maxlength="20"
				placeholder="password Confirmation" required="" /> 
				<span id="error" class="erreur">${erreur['confirmation']}</span>
					 <br />
					
					 <br /> <input type="submit" value="Registration" class="sansLabel" />

			</fieldset>
		</form>
		</div>
	</div>


</body>
</html>