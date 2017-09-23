<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<c:forEach items="${customers}" var="customer">
			<li>${customer.name}<a href="${pageContext.request.contextPath}/delete?id=${customer.id}">delete</a></li>
		</c:forEach>
	</ul>

	<form action="${pageContext.request.contextPath}/create" method="POST">
		<label>
			Name
			<input class="form-control" type="text" name="name">
		</label>
		<input class="btn btn-primary" type="submit" name="submit">
	</form>
</body>
</html>
