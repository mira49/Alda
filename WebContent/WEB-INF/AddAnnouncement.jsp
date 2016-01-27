<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Add announcement</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/CSS/style.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>
	<c:import url="/inc/headerUser.jsp" />
	<c:import url="/inc/menuUser.jsp" />
	<div id="corpsDa">
		<div>
			<h1 id="Inscription-title">Here you can add an announcement</h1>
		</div>

		<div class="infoperso ">
			<form role="form" method="post" action="add_Announcement"
				enctype="multipart/form-data">
				<fieldset id="Field">

					<legend>Informations for the annoucement</legend>
					
					<c:if test="${empty current_annonce }">
						<label> Name : <span class="requis">*</span>
						</label>
						<input name="Name" required autofocus>
						<br>
						<br>
						<label>Postal code : <span class="requis">*</span>
						</label>
						<input type="text" name="postal_code" required>
						<br>
						<br>
						<label>Town : <span class="requis">*</span>
						</label>
						<input name="Town" required>
						<br>
						<br>
						<label> Price : <span class="requis">*</span>
						</label>
						<input type="text" name="Price" required>
						<br>
						<br>
						<label> Surface : <span class="requis">*</span>
						</label>
						<input type="text" name="Surface" required>
						<br>
						<br>
						<label> Description : </label>
						<textarea type="text" name="Description"> </textarea>
						<br>
						<label> Picture 1 : <span class="requis">*</span>
						</label>
						<input type="file" name="picture1" required>
						<br>
						<label> Picture 2 : <span class="requis">*</span>
						</label>
						<input type="file" name="picture2" required>
						<a>Click here to Add a picture</a>
						<br />
						<span id="error" class="erreur">${erreur['picture1']}</span>
						<span id="error" class="erreur">${erreur['picture2']}</span>
						<span id="error" class="erreur">${erreur['picture3']}</span>
						<label id="picture3"> Picture 3 : </label>
						<input id="picture3" type="file" name="picture3">
						<br>
					</c:if>

					<c:if test="${!empty current_annonce }">
						
						<label> Name : <span class="requis">*</span>
						</label>
						<input name="Name" value="${current_annonce.name }" required
							autofocus>
						<br>
						<br>
						<label>Postal code : <span class="requis">*</span>
						</label>
						<input type="text" name="postal_code"
							value="${current_annonce.postal_code }" required>
						<br>
						<br>
						<label>Town : <span class="requis">*</span>
						</label>
						<input name="Town" value="${current_annonce.town }" required>
						<br>
						<br>
						<label> Price : <span class="requis">*</span>
						</label>
						<input type="text" name="Price" value="${current_annonce.price }"
							required>
						<br>
						<br>
						<label> Surface : <span class="requis">*</span>
						</label>
						<input type="text" name="Surface"
							value="${current_annonce.surface }" required />
						<br>
						<br>
						<label> Description : </label>
						<textarea type="text" name="Description"> ${current_annonce.description } </textarea>
						<br>
						
						<input type="hidden" name="image1" value="${current_annonce.image1 }" />
						<input type="hidden" name="image2" value="${current_annonce.image2 }" />
						<input type="hidden" name="image3" value="${current_annonce.image3 }" />
						<input type="hidden" name="id" value="${current_annonce.id }" />
						
						
						<br />
						<span id="error" class="erreur">${erreur['picture1']}</span>
						<span id="error" class="erreur">${erreur['picture2']}</span>
						<span id="error" class="erreur">${erreur['picture3']}</span>
						<label id="picture3"> Picture 3 : </label>
						<input id="picture3" type="file" name="picture3">
						<br>
					</c:if>
					<button class="btn register" type="submit">Register</button>
		</div>
		</fieldset>
		</form>
	</div>

	<script>
		jQuery(document).ready(function() {
			/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
			$("label#picture3").hide();
			$("input#picture3").hide();

			jQuery('a').click(function() {
				$("a").hide();
				$("label#picture3").show();
				$("input#picture3").show();
			});
		});
	</script>
</body>
</html>