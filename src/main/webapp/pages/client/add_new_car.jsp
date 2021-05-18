<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="title.add_car" /></title>
</head>
<body>
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>

<div class="form">
	<form name="addCarForm" method="POST" action="controller">
		<input type="hidden" name="command" value="add_car" />
		
		<div class="form-title"><fmt:message key="title.add_car" /></div>
		
		<div class="infofield"><fmt:message key="field.vin" /></div>
		<input type="text" name="vin" value="" required pattern="^(?i)[A-HJ-NPR-Z0-9]{17}$"/> 

		<div class="infofield"><fmt:message key="field.brand" /></div>
		<input type="text" name="brand" value="" required pattern="^[0-9a-zA-Zа-яёА-ЯЁ\-\s]{1,45}$"/> 
		
		<div class="infofield"><fmt:message key="field.model" /></div>
		<input type="text" name="model" value="" required pattern="^[0-9a-zA-Zа-яёА-ЯЁ\-\s]{1,45}$"/> 
		
		<div class="infofield"><fmt:message key="field.year" /></div>
		<input type="text" name="year" value="" required pattern="^[\d]{4}$"
		title='must have 4 ciphers'/> 
		
		<div class="infofield"><fmt:message key="field.fuel" /></div>
		<div>
			<input type="radio" name="fuel" value="benzin"><fmt:message key="engine.benzin" />
			<br />
			<input type="radio" name="fuel" value="diesel"><fmt:message key="engine.diesel" />
			<br /> 
			<input type="radio" name="fuel" value="electric"><fmt:message key="engine.electric" />
		</div>

		<div class="infofield"><fmt:message key="field.volume" /></div>
		<input type="text" name="volume" value="" required pattern="^[\d]*[.,]?[\d]+{1,45}$"
		title='must include only ciphers and characters "." or ","'/> 
		
		<div class="infofield"><fmt:message key="field.transmission" /></div>
		<div>
			<input type="radio" name="transmission" value="manual"><fmt:message key="transmission.manual" />
			<br />
			<input type="radio" name="transmission" value="automatic"><fmt:message key="transmission.automatic" />
			<br />
		</div>
		
		<input type="submit" value='<fmt:message key="button.add_car"/>' />
		
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