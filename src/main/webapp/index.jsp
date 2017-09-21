<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="customerRepository" class="me.micopiira.hibernatetest.jpa.JpaCustomerRepository">
	<jsp:setProperty name="customerRepository"
	                 property="entityManagerFactory"
	                 value="${applicationScope['javax.persistence.EntityManagerFactory']}"/>
</jsp:useBean>

<c:if test="${not empty param['submit']}">
	<jsp:useBean id="customer" class="me.micopiira.hibernatetest.domain.Customer">
		<jsp:setProperty name="customer" property="*"/>
	</jsp:useBean>
	<% customerRepository.save(customer); %>
</c:if>


<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Document</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
	<ul>
		<c:forEach items="${customerRepository.findAll()}" var="customer">
			<li>${customer.name}</li>
		</c:forEach>
	</ul>

	<form method="POST">
		<label>
			Name
			<input class="form-control" type="text" name="name">
		</label>
		<input class="btn btn-primary" type="submit" name="submit">
	</form>
</body>
</html>
