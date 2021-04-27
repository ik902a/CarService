<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<c:set var="role" value="guest" scope="session" />
	<jsp:forward page="controller?command=to_main" />
</body>
</html>
