<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<h2><fmt:message key="customer.add"/></h2>
<form action="<c:url value="/create"/>" method="POST">
	<div class="form-group">
		<label for="fname"><fmt:message key="customer.fname"/></label>
		<input type="text" name="fname" class="form-control" id="fname" aria-describedby="fname">
	</div>
	<div class="form-group">
		<label for="lname"><fmt:message key="customer.lname"/></label>
		<input type="text" name="lname" class="form-control" id="lname" aria-describedby="lname">
	</div>
	<button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
</form>