<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<h2><fmt:message key="customer.add"/></h2>
<form action="<c:url value="/create"/>" method="POST">
	<div class="form-row">
		<div class="col">
			<div class="form-group">
				<jsp:include page="_textinput.jsp">
					<jsp:param name="fieldName" value="firstName"/>
					<jsp:param name="label" value="customer.firstName"/>
				</jsp:include>
			</div>
		</div>
		<div class="col">
			<div class="form-group">
				<jsp:include page="_textinput.jsp">
					<jsp:param name="fieldName" value="lastName"/>
					<jsp:param name="label" value="customer.lastName"/>
				</jsp:include>
			</div>
		</div>
	</div>
	<button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
</form>

<c:remove var="constraintViolations" scope="session"/>