<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Announcement</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/CSS/style.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>
	<c:import url="/inc/headerUser.jsp" />
	<c:import url="/inc/menuUser.jsp" />
	<div id="corpsDa">


		<h1>Actuality</h1>

		<form action="announcement" method="post">
			<fieldset id="Field">
				<legend>properties that match your criteria</legend>
				<c:if test="${empty factor}">
					<label style="padding: 15px;">Enter your criteria</label>
				</c:if>
				<c:if test="${!empty factor}">
					<label style="padding: 15px;">Edit your criteria</label>
				</c:if>

				<input type="text" id="factor_lower_price" name="factor_lower_price"
					size="20" placeholder="Price lower" maxlength="60"
					value="${fn:trim(factor[0])}"></input> <input type="text"
					id="factor_higher_price" name="factor_higher_price" size="20"
					placeholder="Price higher" maxlength="60"
					value="${fn:trim(factor[1])}"></input><input type="text"
					id="factor_location" name="factor_location" size="20"
					placeholder="Location" maxlength="60" value="${fn:trim(factor[2])}"></input>
				<button type="submit" name="factor" value="">Save</button>
		</form>


		<c:if test="${empty factor}">
			<p class="erreur">Please register your criteria.</p>
		</c:if>
		<c:if test="${!empty factor}">
			<c:choose>
				<c:when test="${ empty annoucement_user_factor }">
					<p class="erreur">No annonce correspond to your criteria.</p>
				</c:when>
				<c:otherwise>
					<div>


						<table>
							<tr>
								<th>name</th>
								<th>surface</th>
								<th>description</th>
								<th>Price</th>
								<th>postal code</th>
								<th>Favorite</th>
								<th>Contact</th>
							</tr>


							<c:forEach var="annoucement" items="${annoucement_user_factor}"
								varStatus="status">
								<tr>
									<form action="announcement" method="post">
										<td><c:out value="${annoucement.name}" /></td>
										<td><c:out value="${annoucement.surface}" /></td>
										<td><c:out value="${annoucement.description}" /></td>
										<td><c:out value="${annoucement.price}" /></td>
										<td><c:out value="${annoucement.postal_code}" /></td>

										<c:if
											test="${annoucement.user.email != sessionScope.user.email}">
											<c:if test="${liste[status.index] == false}">
												<td><input type="image" name="favorite"
													value="${sessionScope.user.email};${annoucement.id}"
													src="<c:url value="/inc/add.jpg"/>" alt="Add" /></td>
											</c:if>

											<c:if test="${liste[status.index] == true}">
												<td><input type="image" name="favoriteRemove"
													value="${sessionScope.user.email};${annoucement.id}"
													src="<c:url value="/inc/remove.jpg"/>" alt="Remove" /></td>
											</c:if>
									</form>

									<form method="get" action="contact" id="contact_Form">
										<td><button type="submit" name="contact"
												value="${annoucement.id}">Contact</button></td>
									</form>
									</c:if>
									<c:if
										test="${annoucement.user.email == sessionScope.user.email}">
										<td><c:out value="Yours" />
											</button></td>
										<td><c:out value="Yours" /></td>
									</c:if>
								</tr>
							</c:forEach>



						</table>
				</c:otherwise>
			</c:choose>
		</c:if>
		</fieldset>
	</div>


	<div id="corpsDa">


		<fieldset id="Field">
			<legend>the latest announcements</legend>

			<c:choose>
				<c:when test="${ empty annoucement_user }">
					<p class="erreur">no annonce published.</p>
				</c:when>
				<c:otherwise>

					<form method="post" action="announcement" name="announcement_Form">
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
							<th>description</th>
							<th>Price</th>
							<th>postal code</th>
							<th>Favorite</th>
							<th>Contact</th>
						</tr>


						<c:forEach var="annoucement" items="${annoucement_user}"
							varStatus="status">
							<tr>
								<form action="announcement" method="post">
									<td><c:out value="${annoucement.name}" /></td>
									<td><c:out value="${annoucement.surface}" /></td>
									<td><c:out value="${annoucement.description}" /></td>
									<td><c:out value="${annoucement.price}" /></td>
									<td><c:out value="${annoucement.postal_code}" /></td>

									<c:if
										test="${annoucement.user.email != sessionScope.user.email}">
										<c:if test="${liste2[status.index] == false}">
											<td><input type="image" name="favorite"
												value="${sessionScope.user.email};${annoucement.id}"
												src="<c:url value="/inc/add.jpg"/>" alt="Add" /></td>
										</c:if>

										<c:if test="${liste2[status.index] == true}">
											<td><input type="image" name="favoriteRemove"
												value="${sessionScope.user.email};${annoucement.id}"
												src="<c:url value="/inc/remove.jpg"/>" alt="Remove" /></td>
										</c:if>
								</form>
								<form method="get" action="contact" id="contact_Form">
									<td><button type="submit" name="contact"
											value="${annoucement.id}">Contact</button></td>
								</form>
								</c:if>
								<c:if
									test="${annoucement.user.email == sessionScope.user.email}">
									<td><c:out value="Yours" />
										</button></td>
									<td><c:out value="Yours" /></td>
								</c:if>
							</tr>
						</c:forEach>



					</table>
				</c:otherwise>
			</c:choose>
		</fieldset>
	</div>
	<script type="text/javascript">
		function changeimage(url, obj) {

			obj.src = url;
		}
	</script>
</body>
</html>