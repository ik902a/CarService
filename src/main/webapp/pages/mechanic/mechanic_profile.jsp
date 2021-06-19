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
	<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
	<div id="header">
		<c:import url="../fragments/header.jsp" />
	</div>
	<div class="profile-button-group"></div>
	<div>
		<table>
			<tr>
				<th><div class="infofield"><fmt:message key="table.car" /></div></th>
				<th><div class="infofield"><fmt:message key="table.order" /></div></th>
				<th><div class="infofield"><fmt:message key="table.status" /></div></th>
				<th><div class="infofield"><fmt:message key="table.status_action" /></div></th>
			</tr>
			<c:choose>
			<c:when test="${ orderList == null }">
				<tr>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
					<td><fmt:message key="message.empty" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="order" items="${ orderList }">
					<tr>
						<td><c:out value="vin: ${order.car.vin }" /><br />
							<c:out value="${order.car.brand } " />
							<c:out value="${order.car.model }" /><br />
							<c:out value="year: ${order.car.yearProduction }" /><br />
							<c:out value="engine: ${order.car.fuelType }, " />
							<c:out value="${order.car.volumeEngine }" /><br />
							<c:out value="transmission: ${order.car.transmisionType }" /></td>
						<td><fmt:message key="work.${order.workType.workType }" /><br />
							<c:out value="${order.message }" /></td>
						<td><c:out value="${order.status }" /></td>
						<td><form name="update" method="POST" action="controller">
								<c:set var="order" value="${ order }" scope="session" />
								<input type="hidden" name="command" value="update_ready_order_status" />
								<input type="submit" value='<fmt:message key="button.ready"/>' />
							</form></td>
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
