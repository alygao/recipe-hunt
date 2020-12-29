<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop All</title>
</head>
<body>
	<h1>Shop All</h1>
	<form action="${pageContext.request.contextPath}/products/shop-all/search-results" method="get">
		<td><input type="text" name="search" id="search" value =""></td>
		<td><button type="submit" name="search">Search</button></td>
	</form>
	<table>
		<thead>
			<tr>
				<td>Product Name</td>
				<td>Brand</td>
				<td>Price</td>
				<td>Status</td>
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
					<td><a href="<c:url value= '/products/shop-all/${product.id}'/>">More Info</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<p>
	<a href="<c:url value='/cart'/>">View Cart</a>
	<a href="<c:url value='/'/>">Return Home</a>
</body>
</html>