<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Add announcement</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    </head>
    <body>
        <c:import url="/inc/headerBuyer.jsp" />  
          <div id="corpsDa">
			<h1 id="Inscription-title">Contact</h1>
			
			 <br>
<div class="Image">
  <a id="mail" ><img  title="Post by email" src="<c:url value="/inc/mail.png"/>"  /></a>
  <h3 id="mail">Email :  ${current_announce.user.email} </h3>
  <br>
  <br>
										
					<c:if test="${current_announce.user.phone != null}">
						  <a id="phone" > <img  title="Post by phone" src="<c:url value="/inc/tel.jpg"/>"  /></a>
						  				</c:if>		<h3 id="phone">Phone :  ${current_announce.user.phone} </h3>	

	
 
 </div>
 

		
		
 
  
	</div>
	<script>
		jQuery(document).ready(function() {
		
			$("h3#mail").hide();
			$("h3#phone").hide();
			jQuery('a#phone').click(function() {
				$("h3#phone").show();
			});
			
			jQuery('a#mail').click(function() {
				$("h3#mail").show();
			});
		});
	</script>
</body>
</html>