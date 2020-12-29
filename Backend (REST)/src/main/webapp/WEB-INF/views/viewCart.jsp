<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>
<table>
		<thead>
			<tr>
				<td>Product Name</td>
				<td>Price</td>
				<td>Quantity</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${shoppingCart.shoppingCartProducts}" var="item">
					<form action="${pageContext.request.contextPath}/cart/update/${item.product.id}" method="post">
				<tr>
					<td>${item.product.name}</td>
					<td>${item.product.price}</td>
					<td><input type="number" name="quantity" id="quantity" min="0" max="${item.product.inStock}" value ="${item.quantity}"></td>
					<td><button type="submit" name="updateItem">Update Item</button></td>
					<td><a href="<c:url value='/cart/${item.product.id}'/>">Remove From Cart</a></td>
				</tr>
					</form>
			</c:forEach>

		</tbody>
	</table>
	<p>
	<label for="subtotal">SubTotal:</label>
	 <input type="text" id="subtotal" name="subtotal" value="${shoppingCart.subtotal}" readonly>
	<p>
	<form action="${pageContext.request.contextPath}/make-order" method="get">
		<c:if test="${currCustomer.email == null}">
		<label for="customerEmail">Your Email:</label> 
		<input type="text" name="customerEmail" id="customerEmail">
		<p>
		<label for="customerAddress">Your Address:</label> 
		<input type="text" name="customerAddress" id="customerAddress">
		<p>
		</c:if>
		<button type="submit" name="makeOrder">Make Order</button>
	</form>
	<a href="<c:url value='/'/>">Return Home</a>
	<a href="<c:url value='/products/shop-all'/>">Add More Products</a>
</body>
</html>