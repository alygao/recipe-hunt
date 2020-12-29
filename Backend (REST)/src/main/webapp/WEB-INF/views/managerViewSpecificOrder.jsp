<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Viewing Order ID: ${currOrder.orderId}</title>
</head>
<body>
	<h1>Viewing Order ID: ${currOrder.orderId}</h1>
	<p> 
	Number of Items: ${currOrder.sizeOfOrder} <br>
	Totals: ${currOrder.total} <br>
	Customer Email: ${currOrder.customerEmail} 
	</p>
	<form
		action="${pageContext.request.contextPath}/manager/view-orders/${currOrder.orderId}/change-order-status"
		method="post">
		<label for="updateOrderStatus">Update Order Status:</label> 
		<select
			name="updateOrderStatus">
			<c:forEach items="${orderStatuses}" var="orderStatus">
				<option value="${orderStatus}"
					${orderStatus == currOrder.orderStatus ? 'selected' : ''}>${orderStatus}</option>
			</c:forEach>
		</select>

		<button type="submit" name="updateOrderStatus">Update Order
			Status</button>
	</form>
	<p>
	<table>
		<thead>
			<tr>
				<td>Product Id</td>
				<td>Product Name</td>
				<td>Brand</td>
				<td>Price</td>
				<td>Quantity</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${currOrder.items}" var="item">
				<tr>
					<td>${item.product.id}</td>
					<td>${item.product.name}</td>
					<td>${item.product.brand}</td>
					<td>${item.product.price}</td>
					<td>${item.quantity}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value='/manager/view-orders'/>">View Other Orders</a>
</body>
</html>