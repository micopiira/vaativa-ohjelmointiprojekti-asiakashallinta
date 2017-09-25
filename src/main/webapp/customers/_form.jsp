<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<h2><fmt:message key="customer.add"/></h2>
<form action="${pageContext.request.contextPath}/create" method="POST">
	<div class="form-group">
		<label for="name"><fmt:message key="customer.name"/></label>
		<input type="text" name="name" class="form-control" id="name" aria-describedby="name">
	</div>
	<button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
</form>