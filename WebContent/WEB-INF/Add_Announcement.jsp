<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
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
					<output>Name: <span class="requis">*</span></output>
					<input type="text" id="Name" name="Name" size="20" maxlength="60"
						required="" value=""}></input> <br />
					<output>Postal code: <span class="requis">*</span></output>
					<input type="number" id="postal_code" name="postal_code" size="20"
						maxlength="5" required="" value=""}> </input> <br />
					<output>Town: <span class="requis">*</span></output>
					<input type="text" id="Town" name="Town" size="20" maxlength="60"
						required="" value=""}> </input> <br />
					<output>Price: <span class="requis">*</span></output>
					<input type="number" id="Price" name="Price" size="20"
						maxlength="60" required="" value=""}> </input> <br />
					<output>Surface: <span class="requis">*</span></output>
					<input type="number" id="Surface" name="Surface" size="20"
						maxlength="60" required="" value=""}> </input> <br />
					<output>Description:</output>
					<textarea type="text" id="Description" name="Description" size="20"
						maxlength="2000" value=""}>
					</textarea>
					<br />

					<output>Picture 1: <span class="requis">*</span> </output>
					<input type="file" required="" id="picture1" name="picture1" /> <br />
					<output>Picture 2: <span class="requis">*</span></output>
					<input type="file" required="" id="picture2" name="picture2" /><a>Click
						here to Add a picture</a> <br />

					<output id="picture3">Picture 3: </output>
					<input type="file" id="picture3" name="picture3" /> <br /> <input
						type="submit" value="Add" class="sansLabel" /> <br />
				</fieldset>
				<br>
			</form>

			<form method="get" action="home_user">

				<input type="submit" value="retour" />
			</form>
		</div>
	</div>
	<script>
		jQuery(document).ready(function() {
			/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
			$("output#picture3").hide();
			$("input#picture3").hide();

			jQuery('a').click(function() {
				$("a").hide();
				$("input#picture3").show();
				$("output#picture3").show();
			});
		});
	</script>

</body>
</html>