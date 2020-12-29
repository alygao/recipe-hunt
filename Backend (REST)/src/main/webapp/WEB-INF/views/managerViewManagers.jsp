<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Viewing All Managers Registered</title>
</head>
<body>
<h1>Viewing All Managers Registered</h1>
<table>
		<thead>
			<tr>
				<td>Username</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Has Full Access?</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${managers}" var="m">
			<form action="${pageContext.request.contextPath}/manager/delete-manager/${m.username}" method="post">
				<tr>
					<td>${m.username}</td>
					<td>${m.firstName}</td>
					<td>${m.lastName}</td>
					<td>${m.hasFullAccess}</td>
					<td><c:if test="${manager.username != m.username}"><button type="submit" name="deleteManager">Delete Manager</button></c:if></td>
				</tr>
				</form> 
			</c:forEach>

		</tbody>
	</table>
	<a href="<c:url value='/manager/view-managers/edit'/>">Edit Manager</a>
	<c:if test = "${manager.hasFullAccess}"><a href="<c:url value='/manager/add-manager'/>">Add New Manager</a></c:if>
	<p>
	<a href="<c:url value='/manager/'/>">Go Back To Homepage</a>
</body>
</html>