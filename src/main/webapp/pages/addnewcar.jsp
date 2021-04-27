<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<title><fmt:message key="addnewcar.title" /></title>

</head>
<body>
<%@ include file="fragments/header.jsp"%>

	<form name="addCarForm" method="POST" action="controller">
	
		<input type="hidden" name="command" value="add_car" />
		<fmt:message key="addnewcar.vin" />
		<br />
		<input type="text" name="vin" value="" /> 
		<br />
		<fmt:message key="addnewcar.brand" />
		<br />
		<input type="text" name="brand" value="" /> 
		<br />
		<fmt:message key="addnewcar.model" />
		<br />
		<input type="text" name="model" value="" /> 
		<br />
		<fmt:message key="addnewcar.year" />
		<br />
		<input type="text" name="year" value="" /> 
		<br />
		<fmt:message key="addnewcar.fuel" />
		<br />
		<input type="text" name="fuel" value="" /> 
		<br />
		<fmt:message key="addnewcar.volume" />
		<br />
		<input type="text" name="volume" value="" /> 
		<br />
		<fmt:message key="addnewcar.transmission" />
		<br />
		<input type="text" name="transmission" value="" /> 
		<br />
		<input type="submit" value=<fmt:message key="addnewcar.add"/> />
		<br />

	</form>


<%@ include file="fragments/footer.jsp"%>
</body>
</html>