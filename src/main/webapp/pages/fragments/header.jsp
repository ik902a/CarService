<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
</head>
<body >
<div >
<div id="header-name"><fmt:message key="header.name" /></div>
	<div class="header">
		<c:choose>
			<c:when test="${ sessionScope.role == 'GUEST'}">
				<form name="loginForm" method="GET" action="controller">
					<input type="hidden" name="command" value="to_login" /> 
					<input class="header-button" type="submit" value='<fmt:message key="button.log_in"/>' />
				</form>
				<form name="signupForm" method="GET" action="controller?command=to_signup">
					<input type="hidden" name="command" value="to_signup" /> 
					<input class="header-button"type="submit" value='<fmt:message key="button.sign_up"/>' />
				</form>
			</c:when>
			<c:otherwise>
				<form name="toProfile" method="GET" action="controller?command=to_profile">
					<input type="hidden" name="command" value="to_profile" /> 
					<input class="header-button"type="submit" value='<fmt:message key="button.profile"/>' />
				</form>
				<form name="signoutForm" method="GET" action="controller?command=logout">
					<input type="hidden" name="command" value="logout" /> 
					<input class="header-button"type="submit" value='<fmt:message key="button.log_out"/>' />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="header" >
		<form name="languageForm" method="GET" action="controller">
			<ul>
				<c:choose>
					<c:when test="${sessionScope.locale == 'en_US'}">
						<a href="<c:url value="/controller?command=change_locale&locale=ru_RU"/>" class="ru">
						<fmt:message key="header.language" /></a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/controller?command=change_locale&locale=en_US"/>" class="en">
						<fmt:message key="header.language" /></a>
					</c:otherwise>
				</c:choose>
			</ul>
		</form>
	</div>
</div>
</body>
</html>
