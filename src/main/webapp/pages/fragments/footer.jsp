<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rick" uri="customtags" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<rick:custom-stamp/>
</body>
</html>