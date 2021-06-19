<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
<title><fmt:message key="title.price" /></title>
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
		<form name="priceForm" method="POST" action="controller">
			<input type="hidden" name="command" value="search_price" />
			<div class="form-title"><fmt:message key="title.price" /></div>
			<input type="text" name="operation" value="" pattern="^[0-9a-zA-Zа-яёА-ЯЁ .,!?;:-]{0,100}$"/> 
			<input type="submit" value='<fmt:message key="button.search"/>' />
			<c:if test="${errorMessage != null}">
				<div class="error-message"><fmt:message key="${ errorMessage }" /></div>
			</c:if>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th><div class="infofield"><fmt:message key="table.id" /></div></th>
				<th><div class="infofield"><fmt:message key="table.operation" /></div></th>
				<th><div class="infofield"><fmt:message key="table.price" /></div></th>
				<th><div class="infofield"><fmt:message key="table.work_type" /></div></th>
			</tr>
			<c:choose>
			<c:when test="${ priceList == null }">
				<tr>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="price" items="${ priceList }">
					<tr>
						<td><c:out value="${price.priceId }" /></td>
						<td><a href='<c:url value="/controller?command=to_edit_price&id=${ price.priceId }"/>'>
							<c:out value="${price.operation }" /></a></td>
						<td><c:out value="${price.price }" /></td>
						<td><fmt:message key="work.${price.workType.workType }" /></td>
					</tr>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>
