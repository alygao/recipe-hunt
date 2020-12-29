<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>

	<h1>Create Account</h1>
	<form action="register" method="post">
		<label for="firstName">First Name:</label>
		<input type="text" name="firstName" id="firstName">
		<p>
		<label for="lastName">Last Name:</label>
		<input type="text" name="lastName" id="lastName">
		<p>
		<label for="email">Email:</label>
		<input type="text" name="email" id="email">
		<p>		
		<label for="password">Password:</label>
		<input type="password" name="password" id="password"><br/>
		<p>
		<label for="address">Address:</label>
		<input type="text" name="address" id="address">
		<p>	
		<label for="age">Age:</label>
		<input type="number" name="age" id="age" min = "1">
		<p>		
		<label for="skinType">Skin Type:</label>
		<select name = "skinType" id = "skinType">
               <option value = "Normal Skin">Normal Skin</option>
               <option value = "Dry Skin">Dry Skin</option>
               <option value = "Combination Skin">Combination Skin</option>
               <option value = "Oily Skin">Oily Skin</option>
        </select>
        <p>
        <label for="skinConcern">Skin Concern:</label>
		<select name = "skinConcern" id = "skinConcern">
               <option value = "Enlarged Pores" >Enlarged Pores</option>
               <option value = "Uneven Texture">Uneven Texture</option>
               <option value = "Dark Spots">Dark Spots</option>
               <option value = "Lines and Wrinkles">Lines and Wrinkles</option>
               <option value = "Dehydrated Skin">Dehydrated Skin</option>
               <option value = "Acne Prone Skin">Acne Prone Skin</option>
        </select>
        <p>
		<button type="submit" name="makeAccount">Make Account</button>
	</form>
	<a href="<c:url value='/'/>">Return Home</a>
</body>
</html>