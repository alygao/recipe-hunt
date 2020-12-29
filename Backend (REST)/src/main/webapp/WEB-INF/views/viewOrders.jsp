<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Orders</title>
</head>
<body>
<h1>These are your orders: </h1>
<table>
		<thead>
			<tr>
				<td>Order ID</td>
				<td>Number of Items</td>
				<td>Total</td>
				<td>Order Status</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customerOrders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.sizeOfOrder}</td>
					<td>${order.total}</td>
					<td>${order.orderStatus}</td>
					<td><c:if test = "${order.orderStatus == 'Processing'}"><a href="<c:url value='/account/view-orders/${order.orderId}/cancel-order'/>">Cancel Order</a></c:if></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href="<c:url value='/'/>">Return Home</a>
</body>
</html>