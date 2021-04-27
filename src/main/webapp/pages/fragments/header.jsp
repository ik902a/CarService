<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<div align="center">
	<form name="languageForm" method="POST" action="controller">
		<ul>

			<c:choose>
				<c:when test="${sessionScope.locale == 'en_US'}">
					<a href="<c:url value="/controller?command=change_locale&locale=ru_RU"/>"
						class="ru"><fmt:message key="header.language" /></a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/controller?command=change_locale&locale=en_US"/>"
						class="en"><fmt:message key="header.language" /></a>
				</c:otherwise>
			</c:choose>
		</ul>
	</form>
</div>

<div align="right">
		<c:choose>
			<c:when test="${ sessionScope.role == 'guest'}">
				<form name="loginForm" method="POST" action="controller">
					<input type="hidden" name="command" value="to_login" /> <input
						type="submit" value=<fmt:message key="header.log_in"/> />
				</form>
				<br />
				<form name="signupForm" method="POST" action="controller">
					<input type="hidden" name="command" value="to_signup" /> <input
						type="submit" value=<fmt:message key="header.sign_up"/> />
				</form>
				<br />
			</c:when>
			<c:otherwise>
				<form name="loginForm" method="POST" action="controller">
					<input type="hidden" name="command" value="to_profile" /> <input
						type="submit" value=<fmt:message key="header.profile"/> />
				</form>
				<br />
				<form name="signupForm" method="POST" action="controller">
					<input type="hidden" name="command" value="logout" /> <input
						type="submit" value=<fmt:message key="header.log_out"/> />
				</form>
				<br />
			</c:otherwise>
		</c:choose>
	
	<hr />
</div>

</body>
</html>
