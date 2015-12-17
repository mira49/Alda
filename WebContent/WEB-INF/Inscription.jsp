<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REGISTRATION</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
</head>
<body>
<div  class="connection"  >

      <form class="form-signin"  action="<c:url value="/registration"/>" method="post">
       <h3 class="access" > REGISTRATION</h3> <br>
        <input type="text"   maxlength="60" class="form-control composantCentre" name="email"  placeholder="exemple@exemple.exemple" required autofocus> <br>
       <input type="password"   maxlength="20" class="form-control composantCentre" name="password" placeholder="Password" required> <br>
	    <input type="password"   maxlength="20" class="form-control composantCentre" name="password_confirm" placeholder="password Confirmation" required>
	      
	      <a  class=" composantCentre" href="connexion" > Sign in ? </a>
		<div><span id="error" class=" composantCentre erreur">${erreur['email']}</span>		</div>
		<div><span id="error" class=" composantCentre erreur">${erreur['password']}</span>		</div> 
		<div><span id="error" class=" composantCentre erreur">${erreur['confirmation']}</span>		</div>  <br> <br>                      
        <button class="btn btn-lg btn-primary btn-block composantCentre"  type="submit">Sign up</button><br>
      </form>

 </div>
    


</body>
</html>