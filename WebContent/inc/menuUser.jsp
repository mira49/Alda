<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 

   <div style="background-color: #efefef;" class=" sidebar col-sm-3 col-md-2 liste">
            <ul class="nav nav-sidebar">
            <li class="menuP">  Menu</li>
            <li class="menuLi"><a href="<c:url value="/myAnnouncements"/>">My announcements </a></li>
            <li class="menuLi"><a href="<c:url value="/messages_user"/>">My messages </a></li>
            <li class="menuLi"><a href="<c:url value="/favorite"/>">Favorite </a></li>
            <li class="menuLi"><a href="<c:url value="/announcement"/>">Actuality </a></li>
            <li class="menuLi"><a href="<c:url value="/chat"/>">Chat </a></li>
          </ul>
          
        </div>