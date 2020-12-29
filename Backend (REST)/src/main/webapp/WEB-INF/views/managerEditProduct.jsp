<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing ${currProduct.name}</title>
</head>
<body>
	<h1>Editing ${currProduct.name}</h1>
	<form action="${pageContext.request.contextPath}/manager/view-products/${currProduct.id}/edit-product"
		method="post">
		<label for="name">Name:</label> <input type="text" name="name"
			id="name" value="${currProduct.name}">
		<p>
			<label for="brand">Brand:</label> <input type="text" name="brand"
				id="brand" value="${currProduct.brand}">
		<p>
			<label for="description">Description:</label> <input type="text"
				name="description" id="description"
				value="${currProduct.description}">
		<p>
			<label for="price">Price:</label> <input type="number" step="0.01"
				min="0.00" name="price" id="price" value="${currProduct.price}"><br />
		<p>
			<button type="submit" name="submit">Save Changes</button>
	</form>
	<p>
		<a href="<c:url value='/manager/view-products/${currProduct.id}'/>">View
			Product Details</a>
	<p>
		<a href="<c:url value='/manager/view-products'/>">View Other
			Products</a>
</body>
</html>