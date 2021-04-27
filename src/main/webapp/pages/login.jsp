<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="logIn.title" /></title>
</head>
<body>
<%@ include file="fragments/header.jsp"%>

<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<form name="loginForm" method="POST" action="controller">
		<input type="hidden" name="command" value="login" />
		
		<fmt:message key="logIn.login" />
		<br />
		<input type="text" name="login" value="" /> 
		<br />

		<fmt:message key="logIn.password" />
		<br />
		<input type="password" name="password" value="" /> 

		<br />
		<input type="submit" value=<fmt:message key="logIn.log_in"/> />
		<br />
		${errorUserMessage}
	</form>

<%@ include file="fragments/footer.jsp"%>
</body>
</html>