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

		
<c:if test="${factor != null}">
			<div id="factor_announcement">

				<h1>Announcement</h1>
				<form action="announcement" method="post">
					
							<label style ="padding:15px;">Filter</label>
							<input type="text" id="factor_lower_price"
								name="factor_lower_price" size="20"  placeholder="Price lower" maxlength="60"
								value=${factor[0]}></input>
					

						
						<input type="text" id="factor_higher_price"
								name="factor_higher_price" size="20" placeholder="Price higher" maxlength="60"
								value=${factor[1]}></input><input type="text" id="factor_location"
								name="factor_location" size="20"  placeholder="Location"  maxlength="60"
								value=${factor[2]}></input><button type="submit" name="factor" value="">Search</button>
				</form>

			</div>
		</c:if>

			<div >

				<form method="post" action="announcement" name="announcement_Form">
					<select onchange="document.announcement_Form.submit();"
						name="select_option">
						<option value=All>All</option>
						<option value=lower_Price>lower_Price</option>
						<option value=higher_Price>higher_Price</option>
						<option value=Location>Location</option>
					</select>
				</form>
				<table >
					<tr>
						<th>name</th>
						<th>surface</th>
						<th>description</th>
						<th>Price</th>
						<th>postal code</th>
						<th>Favorite</th>
						<th>Contact</th>
					</tr>
					
						
							<c:forEach var="annoucement"
								items="${annoucement_user}">
								<tr>
								<form action="announcement" method="post">
								<td><c:out value="${annoucement.name}" /></td>
								<td><c:out value="${annoucement.surface}" /></td>
								<td><c:out value="${annoucement.description}" /></td>
								<td><c:out value="${annoucement.price}" /></td>
								<td><c:out value="${annoucement.postal_code}" /></td>
								<td><button type="submit" name="favorite" value="${sessionScope.user.email};${annoucement.id}"> Add </button></td>
								</form>
								
								<form method="get" action="contact" id="contact_Form">
									<td><button type="submit" name="contact" value="${annoucement.id}"> Contact </button></td>
								</form>
								
								</tr>
							</c:forEach>
						
						
						
				</table>
			</div>
		
	</div>
</body>
</html>