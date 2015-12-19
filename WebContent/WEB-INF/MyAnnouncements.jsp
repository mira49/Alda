<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>My announcements</title>
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
            <c:when test="${ empty annoucement_user }">
                <p class="erreur">
no annonce published.</p>
            </c:when>
            <c:otherwise>
	<div>
		<h1 id="Inscription-title">My announcements</h1>
	</div>
	<br><br>

 		
			<table >
				<tr>
					<th>Name</th>
					<th>Surface</th>
					<th>Postal code</th>
					<th>Price</th>
					<th>Delete</th>
				</tr>
				<form action="myAnnouncements" method="post" name="announcement_Form">
					<c:forEach var="annoucement"
						items="${ annoucement_user}">
						<tr>
							<td><c:out value="${annoucement.name}" /></td>
							<td><c:out value="${annoucement.surface}" /></td>
							<td><c:out value="${annoucement.postal_code}" /></td>
							<td><c:out value="${annoucement.price}" /></td>
							<td>
									<button type="submit" name="delete"
									value="${annoucement.id}"  onclick="document.announcement_Form.submit();" src="<c:url value="/CSS/supprimer.png"/>" alt="Supprimer" /> delete</button>.
									</td>	
						
									 <c:if test="${annoucement.sold=='0'}">
										<td><button type="submit" name="sold" value="${annoucement.id}" class="sansLabel"> Sold </button></td>
									</c:if>
									 <c:if test="${annoucement.sold=='1'}">
										<td> vendu </td>
									</c:if>
						</tr>
					</c:forEach>
				</form>
			</table>
			  </c:otherwise>
        </c:choose>
        	</div>
</body>
</html>