<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<title><fmt:message key="clientprofile.title" /></title>

</head>
<body>
<%@ include file="fragments/header.jsp"%>


	<form name="edit" method="POST" action="controller">
		<input type="hidden" name="command" value="editProfile" /> <input
			type="submit" value=<fmt:message key="clientprofile.edit"/> />
	</form>
	<br />
	<form name="car" method="POST" action="controller">
		<input type="hidden" name="command" value="addCar" /> <input
			type="submit" value=<fmt:message key="clientprofile.car"/> />
	</form>
	<br />
	<form name="order" method="POST" action="controller">
		<input type="hidden" name="command" value="addOrder" /> <input
			type="submit" value=<fmt:message key="clientprofile.order"/> />
	</form>
	<br />


<%@ include file="fragments/footer.jsp"%>
</body>
</html>