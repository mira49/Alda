<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Home</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/headerBuyer.jsp" />
          <h1   class="welcome" >Welcome  </h1>
	
	<br>
		  	<div id="corpsSa">
		  
	<form action="homeBuyer" method="post" name="announcement_Form">
 <label style="padding: 15px;">Filter</label>
    
	
						
						<input type="text"
						id="factor_lower_price" name="factor_lower_price" size="20"
						placeholder="Price lower" maxlength="60" value="${fn:trim(factor[0])}"></input>
						<input type="text" id="factor_higher_price"
						name="factor_higher_price" size="20" placeholder="Price higher"
						maxlength="60" value="${fn:trim(factor[1])}"></input>
		<input type="text"
						id="factor_lower_surface" name="factor_lower_surface" size="20"
						placeholder="Surface lower" maxlength="60" value="${fn:trim(factor[3])}" ></input>
					<input type="text" id="factor_higher_surface"
						name="factor_higher_surface" size="20" placeholder="Surface higher"
						maxlength="60" value="${fn:trim(factor[4])}" ></input>
					<input type="text"
						id="factor_location" name="factor_location" size="20"
						placeholder="Location" maxlength="60" value="${fn:trim(factor[2])}"></input>
					<button type="submit" name="factor" value="">Search</button>
				</div>



    <c:choose>
            <c:when test="${ empty annoucement_user }">
                <p class="erreur">
No annonce register.</p>
            </c:when>
            <c:otherwise>
			<div  id="corpsSam">
				<select onchange="document.announcement_Form.submit();"
					name="select_option">
					<option value=All>All</option>
					<option value=lower_Price>lower_Price</option>
					<option value=higher_Price>higher_Price</option>
					<option value=Location>Location</option>
				</select>
			</form>
			</div>
			<br>
			<table>
				<tr>
					<th>name</th>
					<th>surface</th>
					<th>Price</th>
					<th>postal code</th>
					<th>Action</th>
				</tr>


				<c:forEach var="annoucement" items="${annoucement_user}" varStatus="status">
					<tr>
					
							<td><c:out value="${annoucement.name}" /></td>
							<td><c:out value="${annoucement.surface}" /></td>
							<td><c:out value="${annoucement.price}" /></td>
							<td><c:out value="${annoucement.postal_code}" /></td>
							
					
							
									

						<form method="get" action="contactBuyer" id="contact_Form">
						<td><button type="submit" class="btn btn-link " name="contact"
												value="${annoucement.id}">Contact</button>
												<input type="image" style="float:right; margin-top:7px; " name="view"
									value="${annoucement.id}"
									onclick="document.contact_Form.submit();"
									src="<c:url value="/inc/loupe.png"/>" alt="View" /> </td>
						</form>
								
					</tr>
				</c:forEach>



			</table>
			
		</c:otherwise>
        </c:choose>
        </div>
    </body>
</html>
