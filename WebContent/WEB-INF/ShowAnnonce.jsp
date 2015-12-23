<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Annonce List</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="<c:url value="/CSS/style.css"/>" />
</head>
<body>
	<c:import url="/inc/header.jsp" />
	<c:import url="/inc/menu.jsp" />
	<div id="corpsDa">
		<c:choose>
			<c:when test="${ empty annoucement_user }">
				<p class="erreur">No registered annonce.</p>
			</c:when>
			<c:otherwise>
				<h1>Announcement</h1>

				<form method="post" action="annonceList" name="announcement_Form">
					<select onchange="document.announcement_Form.submit();"
						name="select_option">
						<option value=All>All</option>
						<option value=lower_Price>lower_Price</option>
						<option value=higher_Price>higher_Price</option>
						<option value=Location>Location</option>
					</select>
				</form>
				<table>
					<tr>
						<th>name</th>
						<th>surface</th>
						<th>Price</th>
						<th>postal code</th>
						<th>description</th>
						<th>Action</th>

					</tr>


					<c:forEach var="annoucement"
						items="${ sessionScope.annoucement_user}">
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<td><c:out value="${annoucement.name}" /></td>
							<td><c:out value="${annoucement.surface}" /></td>
							<td><c:out value="${annoucement.price}" /></td>
							<td><c:out value="${annoucement.postal_code}" /></td>
							<td><c:out value="${annoucement.description}" /></td>
							<td class="action"><a
								href="<c:url value="/deleteUser"><c:param name="deleteA" value="${ annoucement.id }" /></c:url>">
									<img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
							</a></td>

						</tr>
					</c:forEach>
				</table>
				
			
	</div>
	</c:otherwise>
	</c:choose>
	</div>
</body>
</html>