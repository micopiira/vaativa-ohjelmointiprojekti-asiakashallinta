<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title><fmt:message key="title"/></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/bundle.css">
	<script src="${pageContext.request.contextPath}/dist/bundle.js"></script>
</head>
<body>
<div class="container pt-2">
	<c:forEach items="${sessionScope.messages}" var="message">
		<div class="alert alert-info" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<fmt:message key="${message}"/>
		</div>
	</c:forEach>
	<c:remove var="messages" scope="session"/>

	<h1><fmt:message key="title"/></h1>
	<%--@elvariable id="customers" type="java.util.List<me.micopiira.hibernatetest.domain.Customer>"--%>
	<c:choose>
		<c:when test="${not empty customers}">
			<table class="table table-sm table-striped table-responsive table-hover">
				<thead>
					<tr>
						<th><fmt:message key="customer.id"/></th>
						<th><fmt:message key="customer.name"/></th>
						<th><fmt:message key="actions"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customers}" var="customer">
						<tr>
							<th scope="row">${customer.id}</th>
							<td>${customer.name}</td>
							<td>
								<a class="btn btn-outline-danger"
								   href="${pageContext.request.contextPath}/delete?id=${customer.id}"
								   aria-label="<fmt:message key="delete"/>">
									<i class="fa fa-trash" aria-hidden="true"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p class="text-center text-muted"><fmt:message key="no.customers"/></p>
		</c:otherwise>
	</c:choose>

	<h2><fmt:message key="add.new.customer"/></h2>
	<form action="${pageContext.request.contextPath}/create" method="POST">
		<div class="form-group">
			<label for="name"><fmt:message key="customer.name"/></label>
			<input type="text" name="name" class="form-control" id="name" aria-describedby="name">
		</div>
		<button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
	</form>
</div>
</body>
</html>
