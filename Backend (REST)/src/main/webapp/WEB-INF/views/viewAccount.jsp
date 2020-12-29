<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Account</title>
</head>
<body>
	<h1>Your Account</h1>
	<label for="firstName">First Name:</label>
	<input type="text" name="firstName" id="firstName"
		value="${currCustomer.firstName}" readonly>
	<p>
		<label for="lastName">Last Name:</label> <input type="text"
			name="lastName" id="lastName" value="${currCustomer.lastName}"
			readonly>
	<p>
		<label for="email">Email:</label> <input type="text" name="email"
			id="email" value="${currCustomer.email}" readonly>
	<p>
		<label for="address">Address:</label> <input type="text"
			name="address" id="address" value="${currCustomer.address}" readonly>
	<p>
		<label for="age">Age:</label> <input type="number" name="age" id="age"
			value="${currCustomer.age}" readonly>
	<p>
		<label for="skinType">Skin Type:</label> <input type="text"
			name="skinType" id="skinType" value="${currCustomer.skinType}"
			readonly>
	<p>
		<label for="skinConcern">Skin Concern:</label> <input type="text"
			name="skinConcern" id="skinConcern"
			value="${currCustomer.skinConcern}" readonly>
	<p>
		<a href="<c:url value='/account/view-orders'/>">View Orders</a>
	<p>
		<a href="<c:url value='/account/edit-account'/>">Edit Account</a> <a
			href="<c:url value='/account/change-password'/>">Change Password</a>
	<p>
		<a href="<c:url value='/'/>">Return Home</a>
</body>
</html>