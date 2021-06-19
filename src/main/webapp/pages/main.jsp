<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
<title><fmt:message key="title.main" /></title>
</head>
<body>
<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="fragments/header.jsp" />
	</div>
	<div class="profile-button-group"></div>
	<h1><fmt:message key="field.work_types" /></h1>
	<br>
	<hr>
	<div>
		<c:if test="${ priceList != null }">
			<c:forEach var="work" items="${ workList }">
				<h2><fmt:message key="work.${work.workType }" /></h2>
				<table>
					<tr>
						<th><div class="infofield"><fmt:message key="table.operation" /></div></th>
						<th><div class="infofield"><fmt:message key="table.price" /></div></th>
					</tr>
					<c:forEach var="price" items="${ priceList }">
						<c:if test="${ price.workType.workTypeId == work.workTypeId }">
							<tr>
								<td><c:out value="${price.operation }" /></td>
								<td><c:out value="${price.price }" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<br>
			</c:forEach>
		</c:if>
	</div>
	<div id="footer">
		<c:import url="fragments/footer.jsp" />
	</div>
</body>
</html>
