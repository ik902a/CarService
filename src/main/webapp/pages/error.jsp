<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="property.text" />
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
	<h1>ERROR</h1>
	<h2>Ooops. Something goes wrong.</h2>
	<br /> Exception: ${exception}
	<br /> Message from exception: ${exception.message}
	<div>
		<form action="controller" method="post">
			<input type="submit" value='<fmt:message key="button.log_out"/>'>
			<input type="hidden" name="command" value="logout">
		</form>
	</div>
</body>
</html>