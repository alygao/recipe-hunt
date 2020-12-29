<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="login" method="post">
		<label for="email">Email:</label> <input type="text" name="email"
			id="email"><br />
		<p>
			<label for="password">Password:</label> <input type="password"
				name="password" id="password""><br />
		<p>
			<button type="submit" name="submit">Login</button>
	</form>
	<a href="<c:url value='/'/>">Return Home</a>
</body>
</html>