<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<title><fmt:message key="signUp.title" /></title>

</head>
<body>
<%@ include file="fragments/header.jsp"%>

	<form name="loginForm" method="POST" action="controller">
		<input type="hidden" name="command" value="signup" />
		<fmt:message key="signUp.login" />
		<br />
		<input type="text" name="login" value="" /> 
		<br />
		<fmt:message key="signUp.name" />
		<br />
		<input type="text" name="name" value="" /> 
		<br />
		<fmt:message key="signUp.email" />
		<br />
		<input type="text" name="email" value="" /> 
		<br />
		<fmt:message key="signUp.password" />
		<br />
		<input type="password" name="password" value="" /> 
		<br />
		<fmt:message key="signUp.passwordConfirming" />
		<br />
		<input type="password" name="passwordConfirming" value="" /> 
		<br />
		<input type="submit" value=<fmt:message key="signUp.sign_up"/> />
		<br />
		${ errorEmailMessage }
	    <br />
	    ${ errorPasswordMessage }
	    <br />
	    ${ errorPasswordConfirmingMessage }
	</form>


<%@ include file="fragments/footer.jsp"%>
</body>
</html>