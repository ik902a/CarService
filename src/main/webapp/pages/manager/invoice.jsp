<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
<title><fmt:message key="title.profile" /></title>
</head>
<body>
	<c:if test="${ sessionScope.role != 'MANAGER'}">
		<jsp:forward page="../../index.jsp" />
	</c:if>
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>
	<div class="profile-button-group"></div>
	<div>
		<form name="invoiceForm" method="POST" action="controller">
			<input type="hidden" name="command" value="send_invoice" />
			<table>
				<tr>
					<th><div class="infofield"><fmt:message key="table.operation" /></div></th>
					<th><div class="infofield"><fmt:message key="table.price" /></div></th>
					<th><div class="infofield"><fmt:message key="table.select" /></div></th>
				</tr>
				<c:forEach var="price" items="${ priceList }">
					<tr>
						<td><c:out value="${ price.operation }" /></td>
						<td><c:out value="${ price.price }" /></td>
						<td><input type="checkbox" name="operation" value="${ price.priceId }" /></td>
					</tr>
				</c:forEach>
			</table>
			<div class="form">
				<input type="submit" value='<fmt:message key="button.send"/>' />
				<c:if test="${errorMessage != null}">
					<div class="error-message"><fmt:message key="${ errorMessage }" /></div>
				</c:if>
			</div>
		</form>
	</div>
	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>
