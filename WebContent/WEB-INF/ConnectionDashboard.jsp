<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>User List</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
<body>

<div  class="connection"  >

      <form class="form-signin"  action="<c:url value="/dashboardconnection"/>" method="post">
       <h3 class="access" > ACCESS DASHBOARD</h3> <br>
        <input type="text"  class="form-control composantCentre" name="Pseudo"  placeholder="Pseudo" required autofocus> <br>
       <input type="password"  class="form-control composantCentre" name="Password" placeholder="Password" required>
		<div>					<span id="error" class=" composantCentre erreur">${error}</span>		</div>
		 <br> <br>                      
        <button class="btn btn-lg btn-primary btn-block composantCentre"  type="submit">Sign in</button><br>
      </form>

 </div>
    
 
	
</body>
</html>