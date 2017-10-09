<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="bindingResult" scope="request" type="org.springframework.validation.BindingResult"/>

<label for="${param.fieldName}"><fmt:message key="${param.label}"/></label>
<input type="text"
       name="${param.fieldName}"
       class="form-control ${bindingResult.hasFieldErrors(param.fieldName) ? 'is-invalid' : bindingResult.hasErrors() ? 'is-valid' : ''}"
       id="${param.fieldName}"
       aria-describedby="${param.fieldName}"
		value="${param.value}">
<c:if test="${bindingResult.hasFieldErrors(param.fieldName)}">
	<div class="invalid-feedback">
		<c:forEach items="${bindingResult.getFieldErrors(param.fieldName)}" var="error">
			${error.defaultMessage}
		</c:forEach>
	</div>
</c:if>