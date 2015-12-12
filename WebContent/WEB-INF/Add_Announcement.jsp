<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<h1 id="Inscription-title">Here you can add an announcement</h1>
			</div>
			
			<form method="post" action="add_Announcement">
				<fieldset id="Field">
					<legend>Informations pour the annoucement</legend>

					<output>Name:</output>
					<input type="text" id="Name" name="Name" size="20" maxlength="60"
						required="" value=""}></input> <br />
					<output>Location:</output>
					<input type="text" id="Location" name="Location" size="20" maxlength="60"
						required="" value=""}> </input> <br />
					<output>Surface:</output>
					<input type="number" id="Surface" name="Surface" size="20"
						maxlength="60" required="" value=""}>
					</input> <br />
					<output>Description:</output>
					<input type="textArea" id="Description" name="Description" size="20"
						maxlength="2000" required="" value=""}>
					</input> <br />
					
					<input type="submit" value="Add" class="sansLabel" /> <br />
				</fieldset>
				<br>
			</form>
		</div>
	</div>
</body>
</html>