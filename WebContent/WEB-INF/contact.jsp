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
        <c:import url="/inc/headerUser.jsp" />      
          <c:import url="/inc/menuUser.jsp" />
          <div id="corpsDa">
			<h1 id="Inscription-title">Contact</h1>
			
			 <br>
<div class="contactImage">
  <a id="mail" ><img  title="Post by email" src="<c:url value="/inc/mail.png"/>"  /></a>
										
					<c:if test="${current_announce.user.phone != null}">
						  <a id="phone" > <img  title="Post by phone" src="<c:url value="/inc/tel.jpg"/>"  /></a>
						  				</c:if>		

	  <a   > <img  title="Post by chat, The seller is online" src="<c:url value="/inc/chat.png"/>"  /></a>
 
 </div>
 
 <form class="contact" role="form" method="post" action="contact"  >
<fieldset id="Field">
				<legend>Contact by post</legend>
<label  > Announce :  </label><input  name="Name" value="${current_announce.name}" disabled ><br> <br>
<input type="hidden"  name="annonces" value="${current_announce.id}">
<label > Description :  </label><textarea style="width:60%;" type="text" name="Message" requiered> </textarea><br> 
 <button type="submit" value="send message" class="btn register"> Send Message </button>
</fieldset>
</form>
		
		
 <h3 id="mail">Email :  ${current_announce.user.email} </h3>
  <h3 id="phone">Phone :  ${current_announce.user.phone} </h3>	
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