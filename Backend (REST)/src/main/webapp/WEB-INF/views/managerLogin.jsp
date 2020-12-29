<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Login</title>
</head>
<body>
	<h1>Manager Login</h1>
	<form action="${pageContext.request.contextPath}/manager/" method="post">
		<label for="username">Username:</label> <input type="text" name="username"
			id="username"><br />
		<p>
			<label for="password">Password:</label> <input type="password"
				name="password" id="password""><br />
		<p>
			<button type="submit" name="submit">Login</button>
	</form>
	<a href="<c:url value='/'/>">Go to Company's Homepage</a>
</body>
</html>