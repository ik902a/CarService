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
		<form name="addForm" method="POST" action="controller">
			<input type="hidden" name="command" value="add_user" />
			<div class="form-title"><fmt:message key="title.add_user" /></div>
			<div class="infofield"><fmt:message key="field.login" /></div>
			<input type="text" name="login" value="" required pattern="^[\w]{1,20}$"
				title='must include only letters or ciphers and have no more than 20 characters' />
			<div class="infofield"><fmt:message key="field.name" /></div>
			<input type="text" name="name" value="" required 
				pattern="^[a-zA-Zа-яёА-ЯЁ-\s]{1,45}$" />
			<div class="infofield"><fmt:message key="field.email" /></div>
			<input type="text" name="email" value="" required
				pattern="^[\w+&*-]+(?:\\.[\w+&*-]+)*@(?:[\w-]+.)+[a-z]{2,7}$" />
			<div class="infofield"><fmt:message key="field.phone" /></div>
			<input type="text" name="phone" value="" pattern="^[.\d]{9}$" />
			<div class="message"><fmt:message key="message.phone_rule" /></div>
			<div class="infofield"><fmt:message key="field.role" /></div>			
			<div>
				<input type="radio" id="admin" name="role" value="ADMIN">
				<label for="admin"><fmt:message key="user.admin" /></label><br/>
				<input type="radio" id="client" name="role" value="CLIENT">
				<label for="client"><fmt:message key="user.client" /></label><br/> 
				<input type="radio" id="manager" name="role" value="MANAGER">
				<label for="manager"><fmt:message key="user.manager" /></label><br/> 
				<input type="radio" id="mechanic" name="role" value="MECHANIC">
				<label for="mechanic"><fmt:message key="user.mechanic" /></label><br/> 
			</div>
			<div class="infofield"><fmt:message key="field.password" /></div>
			<input type="password" name="password" value="" required pattern="^(?=.*[\d])(?=.*[\w])(?=\S+).{4,20}$"
				title='must have from 4 to 20 characters, include at least one letter, at least one cipher and no spaces'/>
			<div class="infofield"><fmt:message key="field.password_confirming" /></div>
			<input type="password" name="passwordConfirming" value="" 
				required pattern="^(?=.*[\d])(?=.*[\w])(?=\S+).{4,20}$"/> 
			<input type="submit" value='<fmt:message key="button.add"/>'/>
			<div class="error-message">
				<c:forEach var="errorMessage" items="${ errorMessageList }">
					<p><fmt:message key="${ errorMessage }" /></p>
				</c:forEach>
			</div>
		</form>
	</div>
	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>
