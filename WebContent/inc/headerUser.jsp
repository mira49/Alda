<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	     <header class="headerr" >
		
		<div id="dash" >
		<h4  > PLATFORM</h4>  
		</div> 
		<div id="option"  >
		<h5>
		<a  href="<c:url value="/user_Informations"/>" > Profil</a>&nbsp;&nbsp;&nbsp;
		<a  href="<c:url value="/home_user"/>"> Home </a>&nbsp;&nbsp;
		<a  href="<c:url value="/deconnection"/>"> Deconnection </a>&nbsp;
		</h5>
	    </div>
	    </header>