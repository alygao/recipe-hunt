<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activate Your Account</title>
</head>
<body>
	<h1>Activate Account</h1>
	<p>Please enter your email below in order to activate your account</p>

	<form action="${pageContext.request.contextPath}/activate-account" method="post">
		<label for="email">Email:</label> <input type="text" name="email"
			id="email">
		<button type="submit" name="activateAccount">Activate Account</button>
	</form>
	<p>
	<a href="<c:url value='/'/>">Go to Company's homepage</a>
	
</body>
</html>