<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Profil</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/headerUser.jsp" />      
          <c:import url="/inc/menuUser.jsp" />
          <div id="corpsDa">
	<div>
		<h1 id="Inscription-title">Add informations or change it</h1>
	</div>

 <div  class="infoperso ">
<form  role="form" method="post" action ="user_Informations"  >
<fieldset id="Field">
				<legend>Informations</legend>

    <label > Email :  </label> <input    type="text"  name="email"  value=${sessionScope.user.email} required autofocus disabled> <br> <br>
    <label  > Name :  </label><input  name="Name" value=${sessionScope.user.name} ><br> <br>
	<label > FirstName :  </label><input type="text"  name="FirstName" value=${sessionScope.user.firstName} ><br>  <br>
	<label > Adress :  </label><textarea type="text" name="Address" value=${sessionScope.user.address} > </textarea><br> 
	<label > Phone :  </label><input type="text" name="Phone" value=${sessionScope.user.phone}  ><br> <br>
	<button class="btn register"  type="submit" >Register</button> 
	
</div>
</fieldset>
</form>
	</div>
</body>
</html>