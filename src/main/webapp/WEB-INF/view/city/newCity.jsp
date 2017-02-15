<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring forms :: Cities</title>

<!-- Bootstrap -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"
						title="<s:message code="title"></s:message>"><s:message
								code="title"></s:message></a>
			</div>
			
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<s:url value="/cities" />"
						title="<s:message code="navbar.cities"></s:message>"><s:message
								code="navbar.cities"></s:message></a></li>
				
					<li><a href="<s:url value="/cities/new" />"
						title="<s:message code="navbar.newCity"></s:message>"><s:message
								code="navbar.newCity"></s:message></a></li>
					
					<li><a href="<s:url value="/providers/new" />"
						title="<s:message code="navbar.newProvider"></s:message>"><s:message
								code="navbar.newProvider"></s:message></a></li>
				</ul>
				
				
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="true"><s:message
								code="navbar.language"></s:message> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="?locale=en"><img src="blank.gif"
									class="flag flag-gb"
									alt="<s:message code="english"></s:message>" /> <s:message
										code="english"></s:message></a></li>
							<li><a href="?locale=es"><img src="blank.gif"
									class="flag flag-es"
									alt="<s:message code="spanish"></s:message>" /> <s:message
										code="spanish"></s:message></a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container">
	<div class="jumbotron">
			<h1 title="<s:message code="title.newCity"></s:message>"><s:message code="title.newCity"></s:message></h1>
			<p title="<s:message code="subtitle.newCity"></s:message>"><s:message code="subtitle.newCity"></s:message></p>
		</div>

	<c:url var="post_city"  value="/cities/new" />
	<sf:form method="post" modelAttribute="city" action="${post_city}">
		<div class="form-group">
			<label for="nombre">Nombre</label>
			<sf:input path="nombre" placeholder="Nombre" />
			<sf:errors path="nombre" cssClass="error" />
		</div>
		<div class="form-group">
			<label for="cpostal">CPostal</label>
			<sf:input path="cpostal" placeholder="Cpostal" />
			<sf:errors path="cpostal" cssClass="error" />
		</div>
<!-- 		<div class="form-group"> -->
<!-- 			<label for="idprovincia">IdProvincia</label> -->
<%-- 			<sf:input path="idprovincia" placeholder="Idprovincia" /> --%>
<%-- 			<sf:errors path="idprovincia" cssClass="error" /> --%>
<!-- 		</div> -->
		<sf:button><s:message code="btn.create"></s:message></sf:button>
	</sf:form>
</div>
	<footer class="footer">
		<div class="container">
			<p class="text-muted">&copy; 2015 Eugenia Pérez</p>
		</div>
	</footer>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>
</html>