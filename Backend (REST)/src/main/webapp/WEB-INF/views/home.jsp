<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company Name</title>
</head>
<body>
	<h1>This is the homepage. Welcome!</h1>
	<p>
		<a href="<c:url value='/products/shop-all'/>">View Products</a>
		<a href="<c:url value='/cart'/>">View Cart</a>
		<c:choose> 
			<c:when test="${currCustomer.email == null}">
				<a href="<c:url value='/account/login'/>">Login</a> 
				<a href="<c:url value='/account/register'/>">New User</a>
			</c:when>    
    		<c:otherwise>
    			<a href="<c:url value='/account/'/>">View Account</a> 
				<a href="<c:url value='/account/logout'/>">Logout</a> 	
		        <br />
   			 </c:otherwise>
		</c:choose>
</body>
</html>