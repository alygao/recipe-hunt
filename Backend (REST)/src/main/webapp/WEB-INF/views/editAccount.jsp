<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Account</title>
</head>
<body>
	<h1>Edit Account Information</h1>
	<form action="${pageContext.request.contextPath}/account/edit-account"
		method="post">
		<label for="firstName">First Name:</label> <input type="text"
			name="firstName" id="firstName" value="${currCustomer.firstName}">
		<p>
			<label for="lastName">Last Name:</label> <input type="text"
				name="lastName" id="lastName" value="${currCustomer.lastName}">
		<p>
			<label for="address">Address:</label> <input type="text"
				name="address" id="address" value="${currCustomer.address}">
		<p>
			<label for="age">Age:</label> <input type="number" name="age"
				id="age" value="${currCustomer.age}">
		<p>
			<label for="skinType">Skin Type:</label> 
		<select
			name="skinType">
			<c:forEach items="${skinTypes}" var="skinType">
				<option value="${skinType}"
					${skinType == currCustomer.skinType ? 'selected' : ''}>${skinType}</option>
			</c:forEach>
		</select>
		<p>
			<label for="skinConcern">Skin Concern:</label>
		<select
			name="skinConcern">
			<c:forEach items="${skinConcerns}" var="skinConcern">
				<option value="${skinConcern}"
					${skinConcern == currCustomer.skinConcern ? 'selected' : ''}>${skinConcern}</option>
			</c:forEach>
		</select>
		<p>
			<button type="submit" name="makeChanges">Make Changes</button>
	</form>
	<a href="<c:url value='/account/'/>">View Account</a>

</body>
</html>