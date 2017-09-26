<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty sessionScope.messages}">
	<jsp:useBean id="messages" scope="session" type="java.util.List<java.lang.String>"/>
	<c:forEach items="${messages}" var="message">
		<div class="alert alert-info" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<fmt:message key="${message}"/>
		</div>
	</c:forEach>
	<c:remove var="messages" scope="session"/>
</c:if>
