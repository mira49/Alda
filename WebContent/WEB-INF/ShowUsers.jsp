<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>User List</title>
           <link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/CSS/style.css"/>" />
    </head>
    <body >
            <c:import url="/inc/header.jsp" />
            <c:import url="/inc/menu.jsp" />
    
      
    
        <div id="corpsDa">
        <c:choose>
            <c:when test="${ empty users }">
                <p class="erreur">No registered user.</p>
            </c:when>
            <c:otherwise>
            	<h1>Users</h1>
            <table>
                <tr>
                    <th>Name</th>
                    <th>FirstName</th>
                    <th>Adress</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th class="action">Action</th>                    
                </tr>
                <c:forEach items="${ users }" var="users" varStatus="boucle">
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td><c:out value="${ users.name }"/></td>
                    <td><c:out value="${ users.firstName }"/></td>
                    <td><c:out value="${ users.address }"/></td>
                    <td><c:out value="${users.phone }"/></td>
                    <td><c:out value="${users.email }"/></td>
                  
                      <td class="action">
                        <a href="<c:url value="/dashboarddeleteUser"><c:param name="delete" value="${ users.id }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>