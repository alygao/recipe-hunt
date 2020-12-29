<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Orders</title>
</head>
<body>
	<h1>Viewing All Orders</h1>

	<p>
	<form
		action="${pageContext.request.contextPath}/manager/view-orders-email"
		method="post">
		<label for="email">Enter Customer Email:</label> <input type="text"
			id="email" name="email">
		<button type="submit" name="makeOrder">View Orders Attached
			To Email</button>
	</form>
	<p>
	<form
		action="${pageContext.request.contextPath}/manager/view-orders-id"
		method="post">
		<label for="orderId">Enter Order ID:</label> <input type="number"
			min="0" id="orderId" name="orderId">
		<button type="submit" name="makeOrder">View Order</button>
	</form>
	<p>
		<form
		action="${pageContext.request.contextPath}/manager/view-orders" method="get">
		<button type="submit" name="viewAllOrders">View All Orders</button>
	</form>
	<p>
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
			<c:forEach items="${allOrders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.sizeOfOrder}</td>
					<td>${order.total}</td>
					<td>${order.orderStatus}</td>
					<td><a
						href="<c:url value='/manager/view-orders/${order.orderId}'/>">View
							This Order</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href="<c:url value='/manager/'/>">View Homepage</a>
</body>
</html>