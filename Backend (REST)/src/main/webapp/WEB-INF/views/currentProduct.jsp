<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${currProduct.name}</title>
</head>
<body>
	<h1>${currProduct.name}</h1>
	<h2>${currProduct.brand}</h2>
	<h4>${currProduct.description}</h4>
	<p>
	<h4>${currProduct.price}</h4>
	<p>
	<h4>In Stock: ${currProduct.inStock}</h4>
	<p>
	<form action="${pageContext.request.contextPath}/products/shop-all/${currProduct.id}" method="post">
		<c:if test = "${!currProduct.soldOut}"><button type="submit" name="addToShoppingCart">Add To Shopping Cart</button></c:if>
	</form>
	<p>
	<a href="<c:url value='/products/shop-all'/>">View Other Products</a>
	<a href="<c:url value='/cart'/>">View Cart</a>
	


</body>
</html>