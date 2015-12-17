<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Dashboard</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
    <body>
        <c:import url="/CSS/header.jsp" />
          <h1   class="welcome" >Welcome</h1>
		  <br>
 <a href="<c:url value="/annonceList"/>"  >
 <img class="image"  src="<c:url value="/CSS/annonce.png"/>"   onMouseOver="changeimage('<c:url value='/CSS/adh.png'/>',this);" onMouseOut="changeimage('<c:url value='/CSS/annonce.png'/>',this);" /></a>
 <a href="<c:url value="/userList"/>"  >
 <img   src="<c:url value="/CSS/user.png"/>"  onMouseOver="changeimage('<c:url value='/CSS/userh.png'/>',this);" onMouseOut="changeimage('<c:url value='/CSS/user.png'/>',this);" /></a>
 
 <script type="text/javascript">

function changeimage(url,obj){

obj.src=url; 
}
</script>
    </body>
</html>