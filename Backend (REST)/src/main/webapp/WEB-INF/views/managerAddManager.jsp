<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Manager</title>
</head>
<body>
	<h1>Add New Manager:</h1>
	<h4>The default password for any new manager will be: "password"</h4>
	<form action="${pageContext.request.contextPath}/manager/add-manager"
		method="post">
		<label for="username">Username:</label> <input type="text"
			name="username" id="username">
		<p>
			<label for="firstName">First Name:</label> <input type="text"
				name="firstName" id="firstName">
		<p>
			<label for="lastName">Last Name:</label> <input type="text"
				name="lastName" id="lastName">
		<p>
			<label for="hasFullAccess">Has Full Access?</label> <input
				type="checkbox" name="hasFullAccess" id="hasFullAccess" value="hasFullAccess">
		<p>
			<button type="submit" name="addManager">Add New Manager</button>
		</p>
	</form>
	<p>
	<a href="<c:url value='/manager/view-managers'/>">Go Back To Viewing All Managers</a>
	<p>
	<a href="<c:url value='/manager/'/>">Go Back To Homepage</a>
	<p>


</body>
</html>