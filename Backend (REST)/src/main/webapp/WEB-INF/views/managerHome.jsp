<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager's Homepage</title>
</head>
<body>
	<h1>Welcome ${manager.firstName}</h1>
	<a href="<c:url value='/manager/logout'/>">Logout</a>
	<p>
	<a href="<c:url value='/manager/view-products'/>">View Products</a>
	<a href="<c:url value='/manager/view-orders'/>">View Orders</a>
	<a href="<c:url value='/manager/view-managers'/>">View Managers</a>
	<p>
	<a href="<c:url value='/'/>">Go to Company's Homepage</a>
</body>
</html>