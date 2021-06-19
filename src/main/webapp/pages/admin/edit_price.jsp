<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="title.edit_price" /></title>
</head>
<body>
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>
	<div class="profile-button-group">
		<div class="profile-button">
			<form name="user" method="GET" action="controller">
				<input type="hidden" name="command" value="to_add_user" /> 
				<input type="submit" value='<fmt:message key="button.add_user"/>' />
			</form>
		</div>
		<div class="profile-button">
			<form name="price" method="GET" action="controller">
				<input type="hidden" name="command" value="to_add_price" /> 
				<input type="submit" value='<fmt:message key="button.add_price"/>' />
			</form>
		</div>
		<div class="profile-button">
			<form name="price" method="GET" action="controller">
				<input type="hidden" name="command" value="to_page_price" /> 
				<input type="submit" value='<fmt:message key="button.price_list"/>' />
			</form>
		</div>
	</div>
	<div class="form">
		<form name="editForm" method="POST" action="controller">
			<div class="form-title"><fmt:message key="title.edit_price" /></div>
			<div class="infofield"><fmt:message key="field.operation" /></div>
			<div><c:out value="${ price.operation }" /></div>
			<div class="infofield"><fmt:message key="field.price" /></div>
			<input type="text" name="price" value="${ price.price }" required 
				pattern="^[\d]*[.]?[\d]+{1,13}$" />
			<div class="infofield"><fmt:message key="field.work_type" /></div>	
			<div><fmt:message key="work.${ price.workType.workType }" /></div>
			<input type="hidden" name="id" value="${ price.priceId }" /> 
			<input type="submit" value='<fmt:message key="button.update"/>' 
				formaction="controller?command=update_price"/>
			<input type="submit" value='<fmt:message key="button.delete"/>' 
				formaction="controller?command=delete_price" />
			<c:if test="${errorMessage != null}">
				<div class="error-message"><fmt:message key="${ errorMessage }" /></div>
			</c:if>
		</form>
	</div>
	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>
