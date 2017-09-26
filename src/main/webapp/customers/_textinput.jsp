<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="constraintViolations" type="java.util.List<javax.validation.ConstraintViolation<me.micopiira.hibernatetest.domain.Customer>>"--%>

<c:forEach items="${constraintViolations}" var="constraintViolation">
	<c:if test="${constraintViolation.propertyPath eq param.fieldName}">
		<c:set var="isInvalid" value="${true}"/>
	</c:if>
</c:forEach>

<label for="${param.fieldName}"><fmt:message key="${param.label}"/></label>
<input type="text"
       name="${param.fieldName}"
       class="form-control ${isInvalid ? 'is-invalid' : not empty constraintViolations ? 'is-valid' : ''}"
       id="${param.fieldName}"
       aria-describedby="${param.fieldName}"
		value="${param[param.fieldName]}">
<c:if test="${isInvalid}">
	<div class="invalid-feedback">
		<c:forEach items="${constraintViolations}" var="constraintViolation">
			<c:if test="${constraintViolation.propertyPath eq param.fieldName}">
				${constraintViolation.message}
			</c:if>
		</c:forEach>
	</div>
</c:if>