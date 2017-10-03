<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="${pageContext.request.locale.language}">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title><fmt:message key="title"/></title>
	<link rel="stylesheet" href="<c:url value="/dist/bundle.css"/>"/>
	<script src="<c:url value="/dist/bundle.js"/>"></script>
</head>
<body>
	<div class="container pt-2">
		<jsp:include page="/WEB-INF/jsp/_messages.jsp"/>
		<jsp:include page="_table.jsp"/>
		<jsp:include page="_form.jsp"/>
	</div>
</body>
</html>
