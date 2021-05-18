<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="title.edit_profile" /></title>
</head>
<body>
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>

	<div class="form">
		<form name="loginForm" method="POST" action="controller">
			<input type="hidden" name="command" value="update_client" />

			<div class="form-title"><fmt:message key="title.edit_profile" /></div>

			<div class="infofield"><fmt:message key="field.login" /></div>
			<input type="text" name="login" value=${ login } required pattern="^[\w]{1,20}$"
				title='must include only letters or ciphers and have no more than 20 characters' />

			<div class="infofield"><fmt:message key="field.name" /></div>
			<input type="text" name="name" value=${ name } required pattern="^[a-zA-Zа-яёА-ЯЁ-\s]{1,45}$" />

			<div class="infofield"><fmt:message key="field.email" /></div>
			<input type="text" name="email" value=${ email } 
			required pattern="^[\w+&*-]+(?:\\.[\w+&*-]+)*@(?:[\w-]+.)+[a-z]{2,7}$" />

			<div class="infofield"><fmt:message key="field.phone" /></div>
			<input type="text" name="phone" value=${ phone } required pattern="^[.\d]{9}$" />
			<div class="message"><fmt:message key="message.phone_rule" /></div>

			<input type="submit" value='<fmt:message key="button.update"/>' />

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