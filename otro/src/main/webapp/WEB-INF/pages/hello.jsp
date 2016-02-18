<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ibm.web.controller.MainController"%>
<%@page import="com.ibm.employee.model.Employee" %>
<%@page language="java" import="java.util.*" %>
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
		


		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="position: absolute; top: -5px; right: 10px;">
				<a href="javascript:formSubmit()">Salir</a>
			</h3>
		</c:if>

		<ul class="nav nav-tabs nav-justified">

			<li class="active"><a data-toggle="tab" href="#home"><h4>Inicio</h4></a></li>
			<li><a data-toggle="tab" href="#menu1"><h4>Subir Archivo</h4></a></li>
			<li><a data-toggle="tab" href="#menu2"><h4>Generar Reporte</h4></a></li>
			<li><a data-toggle="tab" href="#menu3" data-placement="top" title="Proximamente"><h4>Administrar</h4></a></li>


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

		<button class="btn btn-primary" type="button" onClick="ocultarBarra">Ocultar</button>

		<div class="progress" id="ocultar">
		  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">70%</div>
		</div>

		<div class="progress">
		  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">40%</div>
		</div>

	

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

			<div id="exampleModal">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCrear" data-whatever="@mdo">Crear Nuevo Usuario</button>

				<div class="modal fade" id="modalCrear" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">Nuevo Usuario</h4>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label for="recipient-name" class="control-label">Nombre:</label>
										<input type="text" class="form-control" id="recipient-name">
									</div>
									<div class="form-group">
										<label for="recipient-surname" class="control-label">Apellido:</label>
										<input type="text" class="form-control" id="recipient-surname">
									</div>
									<div class="form-group">
										<label for="recipient-mail" class="control-label">Mail:</label>
										<input type="text" class="form-control" id="recipient-mail">
									</div>									
									<div class="form-group">
										<label for="recipient-username" class="control-label">Usuario:</label>
										<input type="text" class="form-control" id="recipient-username">
									</div>
									<div class="form-group">
										<label for="recipient-password" class="control-label">Contraseña:</label>
										<input type="password" class="form-control" id="recipient-password">
									</div>
									<div class="radio">
									<h4>Accesos del Usuario</h4>


									<div>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox1" value="option1"> Generar Reportes
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox2" value="option2"> Crear Usuarios
									</label>
									</div>
									<div>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox3" value="option3"> Administrar Feriados
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox" id="inlineCheckbox3" value="option3"> Cargar archivos
									</label>
									</div>
								
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
								<button type="button" class="btn btn-primary">Crear</button>
							</div>
						</div>
					</div>
				</div>
				</div>
				
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myOtherModal">
				  Modificar Usuario
				</button>
				
				<!-- Modal -->
				<div class="modal fade" id="myOtherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				        <h4 class="modal-title" id="myModalLabel">Modificaciones</h4>
				      </div>
				      <div class="modal-body">

						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown">Seleccionar Usuario<span class="caret"></span>
							</button>
							<ul class="dropdown-menu btn-block">
								<%
									Employee ebo = new Employee();
									java.util.List<String> list = new ArrayList();
									list.add("hola");
									list.add("chau");
									
								 %>
								
								<li><a href="#">Empleados</a>
								<a href="#"> <%for(String txt : list ){%>
  										<li><%=txt%><li>
									<%}%></a>
								</li>
								
								 
								
							</ul>
						</div>
						
						
						<div>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> Generar Reportes
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox2" value="option2"> Crear Usuarios
							</label>
						</div>
						<div>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox3" value="option3"> Administrar Feriados
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox3" value="option3"> Cargar archivos
							</label>
						</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary">Save changes</button>
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

<script type="text/javascript">
	
	$(document).ready(function(){
	$(".modal").on('hidden.bs.modal', function (e) {
		$(this).find("input,textarea,select").val('').end();
		$('input:checkbox').removeAttr('checked');
		})
	})

	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}


</script>




</body>
</html>