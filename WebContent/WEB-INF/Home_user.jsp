<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Home</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/headerUser.jsp" />
          <h1   class="welcome" >Welcome on your account
				${sessionScope.user.firstName} ${sessionScope.user.name} </h1>
		  <br>
		  <center>
 <a href="<c:url value="/add_Announcement"/>"  >
 <img  src="<c:url value="/inc/adda.png"/>"   onMouseOver="changeimage('<c:url value="/inc/addah.png"/>',this);" onMouseOut="changeimage('<c:url value="/inc/adda.png"/>',this);" /></a>
 <br>
 <a href="<c:url value="/myAnnouncements"/>"  >
 <img   src="<c:url value="/inc/myan.png"/>"  onMouseOver="changeimage('<c:url value="/inc/myanh.png"/>',this);" onMouseOut="changeimage('<c:url value="/inc/myan.png"/>',this);" /></a>
 <br> 
 <a href="<c:url value="/messages_user"/>"  >
 <img   src="<c:url value="/inc/msg.png"/>"  onMouseOver="changeimage('<c:url value="/inc/msgh.png"/>',this);" onMouseOut="changeimage('<c:url value="/inc/msg.png"/>',this);" /></a>
<br>
  <a href="<c:url value="/favorite"/>"  >
 <img   src="<c:url value="/inc/actu.png"/>"  onMouseOver="changeimage('<c:url value="/inc/actuh.png"/>',this);" onMouseOut="changeimage('<c:url value="/inc/actu.png"/>',this);" /></a>
 <br></center>
 <script type="text/javascript">

function changeimage(url,obj){

obj.src=url; 
}
</script>
    </body>
</html>
