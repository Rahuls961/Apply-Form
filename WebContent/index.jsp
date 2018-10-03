<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Use for Spring Tag Library like "form" -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%@ page import="java.time.LocalTime" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Responsive meta tag -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<spring:url value="/resources/css/main.css" var="mainCss" />
<link rel="stylesheet" href="${mainCss}" />
<title>BulutMD</title>
</head>
<body>


	<div class="container navbar-brand">
		<div>
			<form:form method="GET" action="/DealerForm/redirect">
				<button class="ui-btn" type="submit">
					Bayi Ön Başvurusu <br />Tıklayınız 
				</button>
			</form:form>
		</div>
	</div>
</body>
</html>