<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div class="form-title"><fmt:message key="title.edit_profile" /></div>
			<div class="infofield"><fmt:message key="field.login" /></div>
			<input type="text" name="login" value="${ userData.login }" required pattern="^[\w]{1,20}$"
				title='must include only letters or ciphers and have no more than 20 characters' />
			<div class="infofield"><fmt:message key="field.name" /></div>
			<input type="text" name="name" value="${ userData.name }" required 
				pattern="^[a-zA-Zа-яёА-ЯЁ-\s]{1,45}$" />
			<div class="infofield"><fmt:message key="field.email" /></div>
			<input type="text" name="email" value="${ userData.email }" required
				pattern="^[\w+&*-]+(?:\\.[\w+&*-]+)*@(?:[\w-]+.)+[a-z]{2,7}$" />
			<div class="infofield"><fmt:message key="field.phone" /></div>
			<input type="text" name="phone" value="${ userData.phone }" pattern="^[.\d]{9}$" />
			<div class="message"><fmt:message key="message.phone_rule" /></div>
			<div class="infofield"><fmt:message key="field.role" /></div>
			<div>
				<c:choose>
					<c:when test="${ userData.role == 'ADMIN'}">
						<fmt:message key="user.admin" />
					</c:when>
					<c:when test="${ userData.role == 'CLIENT'}">
						<fmt:message key="user.client" />
					</c:when>
					<c:when test="${ userData.role == 'MANAGER'}">
						<fmt:message key="user.manager" />
					</c:when>
					<c:otherwise>
						<fmt:message key="user.mechanic" />
					</c:otherwise>
				</c:choose>
			</div>
			<input type="hidden" name="id" value="${ userData.userId }" /> 
			<input type="hidden" name="oldLogin" value="${ userData.login }"  />
			<input type="hidden" name="role" value="${ userData.role }" /> 
			<input type="hidden" name="status" value="${ userData.status }" />
			<input type="submit" value='<fmt:message key="button.update"/>' 
				formaction="controller?command=update_user"/>
			<input type="submit" value='<fmt:message key="button.delete"/>' 
				formaction="controller?command=delete_user" />
		</form>
		<div class="error-message">
			<c:forEach var="errorMessage" items="${ errorMessageList }">
				<p><fmt:message key="${ errorMessage }" /></p>
			</c:forEach>
		</div>
	</div>	
	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>
