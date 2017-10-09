<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="customers" scope="request" type="java.util.List<me.micopiira.hibernatetest.domain.Customer>"/>

<h1><fmt:message key="title"/></h1>
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
					<td><c:out value="${customer.fullName}"/></td>
					<td>
						<a class="btn btn-outline-danger"
						   href="<c:url value="/delete?id=${customer.id}"/>"
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
