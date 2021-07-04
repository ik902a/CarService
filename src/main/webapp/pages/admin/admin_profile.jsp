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
	<c:if test="${ sessionScope.role != 'ADMIN'}">
		<jsp:forward page="../../index.jsp" />
	</c:if>
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
		<form name="searchForm" method="POST" action="controller">
			<input type="hidden" name="command" value="search_user" />
			<div class="form-title"><fmt:message key="title.profile" /></div>
			<div class="infofield"><fmt:message key="field.search_user" /></div>
			<input type="text" name="login" value="" required pattern="^[\w]{1,20}$"
				title='must include only letters or ciphers and have no more than 20 characters' />
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
				<th><div class="infofield"><fmt:message key="table.login" /></div></th>
				<th><div class="infofield"><fmt:message key="table.name" /></div></th>
				<th><div class="infofield"><fmt:message key="table.email" /></div></th>
				<th><div class="infofield"><fmt:message key="table.phone" /></div></th>
				<th><div class="infofield"><fmt:message key="table.role" /></div></th>
				<th><div class="infofield"><fmt:message key="table.status" /></div></th>
			</tr>
			<c:choose>
			<c:when test="${ userList == null }">
				<tr>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
				</tr>
			</c:when>
			<c:otherwise>
			<c:forEach var="user" items="${ userList }">
				<tr>
					<td><c:out value="${user.userId }" /></td>
					<td><a href='<c:url value="/controller?command=to_edit_user&id=${ user.userId }"/>'>
						<c:out value="${user.login }" /></a></td>
					<td><c:out value="${user.name }" /></td>
					<td><c:out value="${user.email }" /></td>
					<td><c:out value="${user.phone }" /></td>
					<td><c:out value="${user.role }" /></td>
					<td><c:out value="${user.status }" /></td>
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
