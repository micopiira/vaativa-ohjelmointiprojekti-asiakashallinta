<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<%--@elvariable id="messages" type="java.util.List<java.lang.String>"--%>
<c:forEach items="${sessionScope.messages}" var="message">
	<div class="alert alert-info" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<fmt:message key="${message}"/>
	</div>
</c:forEach>
<c:remove var="messages" scope="session"/>
