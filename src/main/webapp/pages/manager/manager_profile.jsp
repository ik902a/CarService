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
	
	<div>
		<table>
			<tr>
				<th><div class="infofield"><fmt:message key="table.car" /></div></th>
				<th><div class="infofield"><fmt:message key="table.order" /></div></th>
				<th><div class="infofield"><fmt:message key="table.status" /></div></th>
				<th><div class="infofield"><fmt:message key="table.status_action" /></div></th>
			</tr>
			<c:forEach var="order" items="${ orderList }">
				<tr>
					<td><c:out value="vin: ${ order.car.vin }" /><br /> 
					<c:out value="${ order.car.brand } " />
					<c:out value="${ order.car.model }" /><br/>
					<c:out value="year: ${ order.car.year }" /><br />
					<c:out value="engine: ${ order.car.fuelType }, " />
					<c:out value="${ order.car.volume }" /><br /> 
					<c:out value="transmission: ${ order.car.transmission }" /></td>

					<td><fmt:message key='<c:out value="work.${ order.workType.workType }"/>'/><br />
					 <c:out value="${order.message }" /></td>



					<c:if test="${ order.status == 'NEW'}">
					<td class="status"><c:out value="${order.status }" /></td>
					
					<td><div><form name="update" method="POST" action="controller">
							<input type="hidden" name="command" value="update_active_order_status" />
						<select> <!--  name="mechanic">-->
						<c:forEach var="mechanic" items="${ mechanicList }">
						<c:set var="mechanic" value="${ mechanic }" scope="session" />
							<option value="${ mechanic }"><c:out value="${ mechanic.login }"/></option>
						</c:forEach>
						</select>
						<c:set var="order" value="${ order }" scope="session" />
						<!--<input type="hidden" name="order" value="${ order }"/>-->
						<input type="submit" value='<fmt:message key="button.appoint"/>' />
					</form></div></td>
					</c:if>		
					
					<c:if test="${ order.status == 'ACTIVE'}">
					<td class="status"><c:out value="${order.status }" /></td>
					
					<td><div><form name="update" method="POST" action="controller">
						<input type="hidden" name="command" value="update_ready_order_status" />
						<input type="submit" value='<fmt:message key="button.ready"/>' disabled/>
					</form></div></td>
					</c:if>
					
					<c:if test="${ order.status == 'READY'}">
					<td class="status"><c:out value="${order.status }" /></td>
					
					<td><div><form name="update" method="POST" action="controller">
						<input type="hidden" name="command" value="update_comleted_order_status" />
						<input type="submit" value='<fmt:message key="button.ready"/>' />
					</form></div></td>
					</c:if>

				</tr>
			</c:forEach>
		</table>
	</div>


	<div id="footer">
		<c:import url="../fragments/footer.jsp" />
	</div>
</body>
</html>