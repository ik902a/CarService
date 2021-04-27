<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />

<html>
<head>
<title><fmt:message key="main.title" /></title>
</head>
<body>
	<%@ include file="fragments/header.jsp"%>
<c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />

	<h1>виды работ</h1>

	<%@ include file="fragments/footer.jsp"%>
</body>
</html>