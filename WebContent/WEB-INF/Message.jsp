<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>message</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    </head>
    <body>
        <c:import url="/inc/headerUser.jsp" />      
          <c:import url="/inc/menuUser.jsp" />
          <div id="corpsDa">
          
        <c:choose>
            <c:when test="${ empty message_user }">
                <p class="erreur">
any message received.</p>
            </c:when>
            <c:otherwise>
	<div>
		<h1 id="Inscription-title">received messages</h1>
	</div>	<br><br>

 		
			
			
			
			<table >
				<tr>
					<th>Name</th>
					<th>Message </th>
				</tr>
					<c:forEach var="message"
						items="${ message_user}">
						<tr>
							<td><c:out value="${message.sender_message}" /></td>
							<td><c:out value="${message.message}" /></td>	
						</tr>
					</c:forEach>
			</table>  
			</c:otherwise>
        </c:choose>
        </div>
</body>
</html>