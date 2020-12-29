<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<h1>Change Password</h1>
<h4>Hi ${manager.firstName}, please change your password below:</h4>

	<form action="${pageContext.request.contextPath}/manager/view-managers/change-password"
		method="post">
		<label for="oldPassword">Old Password</label> <input type="password"
			name="oldPassword" id="oldPassword">
		<p>
		<label for="newPassword">New Password</label> <input type="password"
			name="newPassword" id="newPassword">
		<p>
		<button type="submit" name="changePassword">Change Password</button>
	</form>
	<p>
		<c:if test = "${manager.password != 'password'}"><a href="<c:url value='/manager/view-managers'/>">Go Back To
			Viewing All Managers</a></c:if>
	<p>
		<c:if test = "${manager.password != 'password'}"><a href="<c:url value='/manager/'/>">Go Back To Homepage</a></c:if>
	<p>
</body>
</html>