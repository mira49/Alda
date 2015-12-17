<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REGENERATION PASSWORD</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
<div  class="connection"  >

      <form class="form-signin"  action="<c:url value="/forgetting"/>" method="post">
       <h3 class="access" > REGENERATION PASSWORD</h3> <br>
        <input type="text"   maxlength="60" class="form-control composantCentre" name="email"  placeholder="exemple@exemple.exemple" required autofocus>
	      
	     <h5> <a  class=" composantCentre" href="connexion" >  Sign in ? </a></h5>
		<div><span id="error" class=" composantCentre erreur">${err['email']}</span>		</div> <br> <br>                      
        <button class="btn btn-lg btn-primary btn-block composantCentre"  type="submit">Send</button><br>
      </form>

 </div>
    


</body>
</html>
      