<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div id="main">
		<h1>Виды работ</h1>
	</div>
	<div id="footer">
		<c:import url="fragments/footer.jsp" />
	</div>
</body>
</html>