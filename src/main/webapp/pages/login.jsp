<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="title.log_in" /></title>
</head>
<body>
<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="fragments/header.jsp" />
	</div>
	
<div class="form">
	<form name="loginForm" method="POST" action="controller">
		<input type="hidden" name="command" value="login" />
		<div class="form-title"><fmt:message key="title.log_in" /></div>
		
		<div class="infofield"><fmt:message key="field.login" /></div>
		<input type="text" name="login" value="" /> 
		
		<div class="infofield"><fmt:message key="field.password" /></div>
		<input type="password" name="password" value="" /> 

		<input type="submit" value=<fmt:message key="button.log_in"/>/>
		
		<div class="error-message"><fmt:message key='<c:out value="${ error_message }"/>'/></div>
	</form>
</div>
	
	<div id="footer">
		<c:import url="fragments/footer.jsp" />
	</div>
</body>
</html>