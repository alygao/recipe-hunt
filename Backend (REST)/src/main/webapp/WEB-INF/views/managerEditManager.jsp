<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<h1>Edit Profile:</h1>
	<form
		action="${pageContext.request.contextPath}/manager/view-managers/edit"
		method="post">
		<label for="firstName">First Name:</label> <input type="text"
			name="firstName" id="firstName"  value="${manager.firstName}">
		<p>
			<label for="lastName">Last Name:</label> <input type="text"
				name="lastName" id="lastName" value="${manager.lastName}">
		<p>
			<button type="submit" name="editManager">Save Changes</button>
		</p>
	</form>
	<p>
		<a href="<c:url value='/manager/view-managers'/>">Go Back To
			Viewing All Managers</a>
	<p>
		<a href="<c:url value='/manager/'/>">Go Back To Homepage</a>
	<p>
</body>
</html>