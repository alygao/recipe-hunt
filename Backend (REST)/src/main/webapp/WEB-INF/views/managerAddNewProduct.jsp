<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Product-${manager.username}</title>
</head>
<body>
	<h1>Add New Product</h1>
	<form
		action="${pageContext.request.contextPath}/manager/add-new-product"
		method="post">
		<label for="name">Name:</label> <input type="text" name="name"
			id="name">
		<p>
			<label for="brand">Brand:</label> <input type="text" name="brand"
				id="brand">
		<p>
			<label for="description">Description:</label> <input type="text"
				name="description" id="description">
		<p>
			<label for="price">Price:</label> <input type="number" step="0.01"
				min="0.00" name="price" id="price"><br />
		<p>
			<label for="inStock">How Many In Stock:</label> <input type="number"
				min="0" name="inStock" id="inStock"><br />
		<p>
			<button type="submit" name="submit">Add New Product</button>
	</form>
	<a href="<c:url value='/manager/view-products'/>">View Products</a>
	<a href="<c:url value='/'/>">Go to Company's Homepage</a>
</body>
</html>