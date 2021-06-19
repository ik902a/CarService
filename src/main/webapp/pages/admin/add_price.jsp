<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
<title><fmt:message key="title.add_price" /></title>
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
			<input type="hidden" name="command" value="add_price" />
			<div class="form-title"><fmt:message key="title.add_price" /></div>
			<div class="infofield"><fmt:message key="field.operation" /></div>
			<textarea rows="3" name="operation" required></textarea>
			<div class="infofield"><fmt:message key="field.price" /></div>
			<input type="text" name="price" value="" required 
				pattern="^[\d]*[.]?[\d]+{1,13}$" />
			<div class="infofield"><fmt:message key="field.select_work" /></div>	
			<div>
				<c:forEach var="work" items="${ workList }">
					<input type="radio" id="${ work.workTypeId }" name="workType" value="${ work.workTypeId }" />
					<label for="${ work.workTypeId }"><fmt:message key="work.${ work.workType }" /></label><br>
				</c:forEach>
			</div>
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
