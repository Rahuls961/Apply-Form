<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setCharacterEncoding("UTF-8");
%>

<!-- Use for Spring Tag Library like "form" -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove();
		});
	}, 4000);
</script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/main.css" var="mainCss" />
<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

<link rel="stylesheet" href="${bootstrapCss}" />
<link rel="stylesheet" href="${mainCss}" />
<script type="text/javascript" src="${jqueryJS}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>

<!-- Responsive meta tag -->
<meta http-equiv="Content-Type;refresh"
	content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BulutMD</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg justify-content-between">
			<div>
				<a href="#" class="navbar-brand"><spring:message
						code="dealer.brand" /></a>
			</div>
			<div class="navbar-collapse navbar-nav">
				<div class="row" style="margin: 13px">
					<span style="float: right"> <a href="?lang=tr"> <img
							src="${contextPath}/resources/images/TR.png" />
					</a> | <a href="?lang=en"> <img
							src="${contextPath}/resources/images/EN.png" />
					</a>
					</span>
				</div>
			</div>
		</nav>
	</header>
	<div class="row container">
		<div class="col-xs-10 col-xs-offset-1">
			<!--Taken form data with ModelAttribute which name is newDealer -->
			<form:form id="newDealer" name="newDealer" modelAttribute="newDealer"
				action="" class="form-horizontal" method="POST">
				<legend class="container-title">
					<!-- Uses JSTL taglib for internationalization -->
					<strong><spring:message code="dealer.title" /></strong>
				</legend>

				<!-- Show Alert After SaveForm -->
				<c:if test="${not empty successInfo }">
					<div id="serverError" class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						${successInfo}
					</div>
				</c:if>

				<c:if test="${not empty serverError}">
					<div id="serverError" class="alert alert-danger" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						${serverError}
					</div>
				</c:if>
				<c:remove var="successInfo" scope="session" />
				<c:remove var="serverError" scope="session" />

				<!-- Check alert fields-->
				<spring:hasBindErrors name="newDealer">
					<div
						class="alert alert-danger ${not empty errors.allErrors?'':'hidden'}"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						Bazı alanları boş bıraktınız.Lütfen gerekli alanları doldurunuz.
					</div>
				</spring:hasBindErrors>

				<spring:bind path="*">
					<c:if test="${status.error}">
						<script type="text/javascript">
							window.onload = function() {
								document
										.getElementById(
												'${status.errors.fieldErrors[0].field}')
										.focus();
								document
										.getElementById(
												'${status.errors.fieldErrors[0].field}')
										.select();
								document
										.getElementById("whichRegionNeighorbood").value = "";
							};
						</script>
					</c:if>
				</spring:bind>

				<div class="row col-sm-12">
					<div class="form-group col-sm-6">
						<label for="identity"><spring:message
								code="dealer.identity" /><span>*</span></label>
						<spring:message code="placeholder.newDealer.identity"
							var="identity" />
						<form:input class="form-control" id="identity" path="identity"
							type="text" autocomplete="off" maxlength="11"
							placeholder="${identity}"
							oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
						<!--If It is not validate identity show will be errors-->
						<form:errors path="identity" cssClass="text-danger" />
					</div>

					<div class="form-group col-sm-6 left">
						<label for="birthOfDate"><spring:message
								code="dealer.birthOfDate" /><span>*</span></label>
						<div class="form:input-prepend">
							<form:input class="form-control" id="birthOfDate"
								path="birthOfDate" type="date" />
							<form:errors path="birthOfDate" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="row col-sm-12">
					<div class="form-group col-sm-6">
						<label for="firstName"><spring:message
								code="dealer.firstName" /><span>*</span></label>
						<spring:message code="placeholder.newDealer.firstName"
							var="firstName" />
						<form:input id="firstName" path="firstName" type="text"
							class="form-control" placeholder="${firstName}" />
						<form:errors path="firstName" cssClass="text-danger" />
					</div>
					<div class="form-group col-sm-6 left">
						<label for="lastName"><spring:message
								code="dealer.lastName" /><span>*</span></label>
						<spring:message code="placeholder.newDealer.lastName"
							var="lastName" />
						<form:input id="lastName" path="lastName" type="text"
							class="form-control" placeholder="${lastName}" />
						<form:errors path="lastName" cssClass="text-danger" />
					</div>
				</div>

				<div class="row col-sm-12">
					<div class="form-group col-sm-6">
						<label for="phoneNumber"><spring:message
								code="dealer.phoneNumber" /><span>*</span></label>
						<form:input id="phoneNumber" path="phoneNumber" type="text"
							autocomplete="off" maxlength="11" class="form-control"
							placeholder="0(___)-(___)-(____)"
							oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
						<form:errors path="phoneNumber" cssClass="text-danger" />
					</div>

					<div class="form-group col-sm-6 left">
						<label for="email"><spring:message code="dealer.email" /><span>*</span></label>
						<spring:message code="placeholder.newDealer.email" var="email" />
						<div class="form:input-prepend">
							<form:input id="email" path="email" type="email"
								class="form-control" placeholder="${email}" />
							<form:errors path="email" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="form-group col-sm-12">
					<label for="adress"><spring:message code="dealer.adress" /><span>*</span></label>
					<div class="form:input-prepend">
						<form:textarea id="adress" path="adress" type="text" rows="3"
							class="form-control" />
						<form:errors path="adress" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group col-sm-12">
					<label for="isRetailSale"><spring:message
							code="dealer.isRetailSale" /><span>*</span></label>
					<div class="form-check-input left"
						style="padding: 2px; color: #0B170B !important">
						<form:radiobuttons class="left" id="isRetailSale"
							path="isRetailSale" items="${conditionMapRetailSale}" />
						<br />
						<form:errors path="isRetailSale" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group col-sm-12">
					<label for="whyChoose"><spring:message
							code="dealer.whyChoose" /><span>*</span></label>
					<div class="form:input-prepend">
						<form:textarea id="whyChoose" path="whyChoose" type="text"
							rows="3" class="form-control" />
						<form:errors path="whyChoose" cssClass="text-danger" />
					</div>
				</div>

				<!-- Choose City / Town / Neighborhood -->
				<div class="row col-sm-12">
					<label for="whichRegion"><spring:message
							code="dealer.whichRegion" /><span>*</span></label>
					<!-- Dropdown Test -->
					  <div class="form-group col-sm-4">
						<spring:message code="placeholder.newDealer.whichRegion.city"
							var="city" />
						<c:if test="${not empty listCity}"></c:if>
						<form:select id="whichRegion" path="whichRegion" type="text"
							class="form-control">
							<form:option value="${city}" />
							<c:forEach var="cities" items="${listCity}">
								<form:option value="${ cities}">${cities}</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="whichRegion" cssClass="text-danger" />
					</div>

					<div class="form-group col-sm-4">
						<spring:message code="placeholder.newDealer.whichRegion.town"
							var="town" />
						<c:if test="${not empty listTown}"></c:if>
						<form:select id="whichRegion" path="whichRegion" type="text"
							class="form-control">
							<form:option value="${town}" />
							<c:forEach var="towns" items="${listTown}">
								<form:option value="${ towns}">${towns}</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="whichRegion" cssClass="text-danger" />
					</div>
					

					<!--  <div class="form-group col-sm-4">
						<spring:message code="placeholder.newDealer.whichRegion.city"
							var="city" />
						<form:input id="whichRegionNeighorbood" path="whichRegion"
							type="text" value="" class="form-control"
							placeholder="${city}" />
						<form:errors path="whichRegion" cssClass="text-danger" />
					</div>

					<div class="form-group col-sm-4">
						<spring:message code="placeholder.newDealer.whichRegion.town"
							var="town" />
						<form:input id="whichRegionNeighorbood" path="whichRegion"
							type="text" value="" class="form-control"
							placeholder="${town}" />
						<form:errors path="whichRegion" cssClass="text-danger" />
					</div>-->

					<div class="form-group col-sm-4">
						<spring:message
							code="placeholder.newDealer.whichRegion.neighorbood"
							var="neighorbood" />
						<form:input id="whichRegionNeighorbood" path="whichRegion"
							type="text" value="" class="form-control"
							placeholder="${neighorbood}" />
						<form:errors path="whichRegion" cssClass="text-danger" />
					</div>
				</div>
				<!-- end Choose City / Town / Neighborhood -->

				<div class="form-group col-sm-12">
					<label for="investmentAmount"><spring:message
							code="dealer.investmentAmount" /><span>*</span></label>
					<form:input id="investmentAmount" path="investmentAmount"
						type="number" class="form-control" />
					<form:errors path="investmentAmount" cssClass="text-danger" />
				</div>

				<div class="form-group col-sm-12">
					<label for="whatElseWant"><spring:message
							code="dealer.whatElseWant" /><span>*</span></label>
					<div class="form:input-prepend">
						<form:textarea id="whatElseWant" path="whatElseWant"
							type="text-area" rows="3" class="form-control" />
						<form:errors path="whatElseWant" cssClass="text-danger"
							cssStyle="display: inline-flex;" />
					</div>
				</div>

				<div class="form-group col-sm-12">
					<div class="col-lg-offset-4 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="<spring:message code="dealer.apply.button"/>" />
					</div>
				</div>
			</form:form>
		</div>
	</div>

	<footer>
		<div style="text-align: center">
			<strong> &copy; By <a href="https://www.bulutmd.com/">BulutMD.com</a>
				<strong>|</strong> 2018
			</strong>
		</div>
	</footer>
</body>
</html>