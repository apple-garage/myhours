<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ibm.web.controller.MainController"%>
<!DOCTYPE html>
<html>
<head>
<title>Bienvenido</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

<style>
</style>

</head>


<body style="background-color: #E6E6FA">
	<div class="container">

		<h1 class="text-center">Bienvenido ${pageContext.request.userPrincipal.name}</h1>
		<h1></h1>

		<sec:authorize access="hasRole('ROLE_USER')" />
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>


		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="position: absolute; top: -5px; right: 10px;">
				<a href="javascript:formSubmit()">Logout</a>
			</h3>
		</c:if>

		<ul class="nav nav-tabs nav-justified">

			<li class="active"><a data-toggle="tab" href="#home"><h4>Inicio</h4></a></li>
			<li><a data-toggle="tab" href="#menu1"><h4>Subir Archivo</h4></a></li>
			<li><a data-toggle="tab" href="#menu2"><h4>Generar Reporte</h4></a></li>
			<li><a class="disabled" style="cursor: not-allowed"	data-toggle="tooltip" data-placement="top" title="Proximamente"><h4>Administrar</h4></a></li>


		</ul>

		<div class="tab-content">

			<div id="home" class="tab-pane fade"></div>

			<div id="menu1" class="tab-pane fade">

<!-- 
				<form action="uploadFile?${_csrf.parameterName}=${_csrf.token}"  method="post" enctype="multipart/form-data" id="form" role="form">
					<div class="fileinput fileinput-new input-group" data-provides="fileinput">
					  <div class="form-control" data-trigger="fileinput"><i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
					  <span class="input-group-addon btn btn-default btn-file"><span class="fileinput-new">Select file</span><span class="fileinput-exists">Change</span><input type="file" name="..."></span>
					  <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
					</div>
					<br>
					<input class="btn btn-default" type="submit" value="Upload">${message}					
				</form>
 -->


				<h4>Select file from your computer</h4>
				<form action="uploadFile?${_csrf.parameterName}=${_csrf.token}"  method="post" enctype="multipart/form-data" id="form" role="form" style="text-align:center">
					<div class="form-inline">
						<div class="form-group">
							<input type="file" name="file">
						</div>
						<input type="submit" class="btn btn-primary" value="submit">
					</div>
				</form>

	

			</div>
			<div id="menu2" class="tab-pane fade" style="height: 500px">

				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown">Elegir Filtro <span class="caret"></span>
					</button>
					<ul class="dropdown-menu btn-block">
						<li><a href="#"
							onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=default.rptdesign'">Empleados	con mas de 40hs</a></li>
						<li><a href="#"
							onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=forty.rptdesign'">Empleados con mas de 1 proyecto</a></li>
						<li><a href="#"
							onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=forty.rptdesign'">Empleados sin los feriados cargados</a></li>
					</ul>
				</div>

				<iframe id="reportframe" style="border-width: 0px" position="fixed" src="" height="100%" width="100%"> </iframe>

			</div>

			<div id="menu3" class="tab-pane fade">

				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Crear nuevo usuario</button>

				<div class="modal fade" id="exampleModal" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">New message</h4>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label for="recipient-name" class="control-label">Recipient:</label>
										<input type="text" class="form-control" id="recipient-name">
									</div>
									<div class="form-group">
										<label for="message-text" class="control-label">Message:</label>
										<textarea class="form-control" id="message-text"></textarea>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Send
									message</button>
							</div>
						</div>
					</div>
				</div>


			</div>

		</div>


	</div>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>


</body>
</html>