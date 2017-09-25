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
	<jsp:include page="/_messages.jsp"/>
	<jsp:include page="_table.jsp"/>
	<jsp:include page="_form.jsp"/>
</div>
</body>
</html>