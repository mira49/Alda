<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>announcement</title>
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
			<h1 id="Inscription-title">Detailed announcement</h1>
		</div>

		<div class="infoperso ">
			<form role="form" method="post" action="add_Announcement"
				enctype="multipart/form-data">
				<fieldset id="Field">
					<legend>Informations for the annoucement</legend>
					
			
					
					<c:if test="${!empty current_annonce }">
					
					<label> Name :
					</label><input name="Name" value="${current_annonce.name }" disabled><br> <br>
					<label>Postal code :
					</label> <input type="text" name="postal_code"  value="${current_annonce.postal_code }"  disabled> <br>
					<br> <label>Town :
					</label><input name="Town" value="${current_annonce.town }" disabled><br> <br> 
					<label>	Price : 
					</label><input type="text" name="Price" value="${current_annonce.price }" disabled><br> <br>
					<label> Surface :
					</label><input type="text" name="Surface" value="${current_annonce.surface }"  disabled/><br> <br>
					<label> Description : </label>
					<textarea type="text" name="Description" disabled > ${current_annonce.description }  </textarea>
			<br><br>
					<label> Image 1 : </label>
			            <a href="<c:url value="/images/${ current_annonce.image1 }"/>">Show</a><br><br>
			        <label> Image 2 : </label>                 
			        	<a href="<c:url value="/images/${ current_annonce.image2 }"/>">Show</a>
			<c:if test="${ !empty current_annonce.image3 }">
					<br><br>
			        <label> Image 3 : </label>                 
			        	<a href="<c:url value="/images/${ current_annonce.image3 }"/>">Show</a>
					</c:if>
					
					</c:if>
		</div>
		</fieldset>
		</form>
	</div>

</body>
</html>