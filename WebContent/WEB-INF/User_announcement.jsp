<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />
<link type="text/css" rel="stylesheet" href="CSS/bootstrap.css" />
</head>
<body>
	<div id="home">
		<div id="home_user">
			<h3 id="Inscription-title">Welcome on your account
				${sessionScope.user.firstName} ${sessionScope.user.name}</h3>
		</div>
		<div id="home_content">
			<div id="home_options">
				<table id="home_table">
					<tr>
						<td><input type="button" value="Deconnection"
							onclick="window.location='deconnection'" class="sansLabel" /></td>
					</tr>

					<tr>
						<td><input type="button" value="Profil"
							onclick="window.location='user_Informations'" class="sansLabel" /></td>
					</tr>

					<tr>
						<td><input type="button" value="Add announcement"
							onclick="window.location='add_Announcement'" class="sansLabel" /></td>
					</tr>

					<tr>
						<td><input type="button" value="Announcements"
							onclick="window.location='announcement'" class="sansLabel" /></td>
					</tr>
					
					 <tr><td><input type="button" value="Home"
						onclick="window.location='home_user'" class="sansLabel" /></td>
					 </tr>
				</table>
			</div>


			<div id="home_announcement">
				<h4>Announcement</h4>

				<form method="post" action="announcement" name="announcement_Form">
					<select onchange="document.announcement_Form.submit();"
						name="select_option">
						<option value=All>All</option>
						<option value=lower_Price>lower_Price</option>
						<option value=higher_Price>higher_Price</option>
						<option value=Location>Location</option>
					</select>
				</form>
				<table class="table">
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
		<c:if test="${factor != null}">
			<div id="factor_announcement">

				<h1>Factors of research</h1>
				<form action="announcement" method="post">
					<table>
						<tr>
							<td><output>Price lower</output></td>
						</tr>
						<tr>
							<td><input type="text" id="factor_lower_price"
								name="factor_lower_price" size="20" maxlength="60"
								value=${factor[0]}></input></td>
						</tr>

						<tr>
							<td><output>Price higher</output></td>
						</tr>
						<tr>
							<td><input type="text" id="factor_higher_price"
								name="factor_higher_price" size="20" maxlength="60"
								value=${factor[1]}></input></td>
						</tr>
						<tr>
							<td><output>Location</output></td>
						</tr>
						<tr>
							<td><input type="text" id="factor_location"
								name="factor_location" size="20" maxlength="60"
								value=${factor[2]}></input></td>
						</tr>

						<tr>
							<td><button type="submit" name="factor" value="">Search</button></td>
						</tr>
					</table>
				</form>

			</div>
		</c:if>
	</div>
</body>
</html>