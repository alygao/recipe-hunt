<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Viewing ${currProduct.name}</title>
</head>
<body>
	<h1>Viewing ${currProduct.name}</h1>
	<h2>${currProduct.brand}</h2>
	<h4>${currProduct.description}</h4>
	<p>
	<h4>${currProduct.price}</h4>
	<h4>In Stock: ${currProduct.inStock}</h4>
	<p>
	<a href="<c:url value='/manager/view-products/${currProduct.id}/edit-product'/>">Edit Product</a>
	<a href="<c:url value='/manager/view-products/${currProduct.id}/add-shipment'/>">Add Shipment</a>
	<p>
	<a href="<c:url value='/manager/view-products'/>">View Other Products</a>
	<p>
	<a href="<c:url value='/manager/'/>">Return to Homepage</a>

</body>
</html>