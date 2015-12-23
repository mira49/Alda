<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 

   <div style="background-color: #efefef;" class=" sidebar col-sm-3 col-md-2 liste">
            <ul class="nav nav-sidebar">
            <li class="menuP">  Menu</li>
            <li class="menuLi"><a href="<c:url value="/dashboarduserList"/>">View all users </a></li>
            <li class="menuLi"><a href="<c:url value="/dashboardannonceList"/>">View all announces </a></li>
           
          </ul>
          
        </div>