<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding Shipment for ${currProduct.name}</title>
</head>
<body>
	<h1>Adding Shipment for ${currProduct.name}</h1>
	<form action="${pageContext.request.contextPath}/manager/view-products/${currProduct.id}/add-shipment" method="post">
		<label>Insert Amount Shipped:</label>
		<input type="number" name="amountShipped" id="amountShipped" min="0">
		<button type="submit" name="addToShoppingCart">Add Shipment</button>
	</form>
	<p>
	<a href="<c:url value='/manager/view-products/${product.id}'/>">View Product Details</a>
	<p>
	<a href="<c:url value='/manager/view-products'/>">View Other Products</a>

</body>
</html>