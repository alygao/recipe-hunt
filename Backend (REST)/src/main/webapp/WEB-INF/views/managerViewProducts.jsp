<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Products</title>
</head>
<body>
	<h1>Current Products</h1>
		<table>
		<thead>
			<tr>
				<td>Product Name</td>
				<td>Brand</td>
				<td>Price</td>
				<td>Status</td>
				<td>Quantity</td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.brand}</td>
					<td>${product.price}</td>
					<td>${product.status}</td>
					<td>${product.inStock}</td>
					<td><a href="<c:url value='/manager/view-products/${product.id}'/>">View Product</a></td>
					<td><c:if test = "${manager.hasFullAccess}"><a href="<c:url value='/manager/view-products/${product.id}/delete-product'/>">Delete Product</a></c:if></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<p>
	<a href="<c:url value='/manager/add-new-product'/>">Add New Product</a>
	<p>
	<a href="<c:url value='/manager'/>">Return To Homepage</a>

</body>
</html>