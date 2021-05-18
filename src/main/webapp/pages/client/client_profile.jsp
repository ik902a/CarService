<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="title.profile" /></title>
</head>
<body>
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>
<div class="profile-button-group">
	<div class="profile-button">
		<form name="car" method="POST" action="controller">
			<input type="hidden" name="command" value="to_add_car" />
			<input type="submit" value=<fmt:message key="button.add_car"/> />
		</form>
	</div>
	<div class="profile-button">
		<form name="edit" method="POST" action="controller">
			<input type="hidden" name="command" value="to_edit_profile" /> 
			<input type="submit" value=<fmt:message key="button.edit"/> />
		</form>
	</div>
	</div>
	
	<div class="form">
	<form name="order" method="POST" action="controller">
	<input type="hidden" name="command" value="add_order" />
	
				<div class="form-title">
				<fmt:message key="title.profile" />
			</div>
			
		<div class="infofield"><fmt:message key="field.select_car" /></div>
		<div class="message">${ info_message }</div>
		<div>
			<select name="carOrder">
				<c:forEach var="car" items="${ carList }">
					<option value="${ car.key }"><c:out value="${ car.value }" /></option>
				</c:forEach>
			</select>
		</div>
		
		<div class="infofield"><fmt:message key="field.work_type" /></div>
		<div>
			<c:forEach var="work" items="${ workList }">
			<input type="radio" name="workOrder" value="${ work.key }" />
			<div class="radio-field"><fmt:message key="${ work.value }" /></div>
			</c:forEach>
		</div>
		
		<div class="infofield"><fmt:message key="fiel.add_comment" /></div>
		<textarea rows="5" name="messageOrder" ></textarea>
		
		<input type="submit" value=<fmt:message key="button.make_order"/> />
		
			<div class="error-message">
				<c:forEach var="error_message" items="${ errorMessageList }">
					<p><fmt:message key="${ error_message }" /></p>
				</c:forEach>
			</div>
	</form>
	</div>

	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>