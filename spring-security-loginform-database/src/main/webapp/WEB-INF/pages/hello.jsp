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
<link rel="stylesheet" href="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"></link>


<style>


header{
    background-color: #16a085;
    height: 150px;
    line-height: 150px;
}


.box{
    width: 100%;
    height: 250px;
    padding-top: 100px;
    line-height: 100px;
    -webkit-box-shadow: 0px 21px 46px #000000;
    box-shadow: 0px 21px 46px #000000;
}

.box:last-child{
    -webkit-box-shadow: 0 0 0 #000;
    box-shadow: 0 0 0 #000;
}

/* loaders styles */
.loader{
    width: 40px;
    height: 40px;
    display: inline-block;
    vertical-align: middle;
    position: relative;
}

/* -------------------------------------------------------- */
/* LOADERS */
/* -------------------------------------------------------- */
/* Loader 1 */

.loader-quart-1{
    border-radius: 40px;
    border: 5px solid rgba(255,255,255,0.5);
       
}

.loader-quart-1:after{
    content: '';
    position: absolute;
    top: -5px; left: -5px; right: -5px; bottom: -5px;
    border: 5px solid transparent;
    border-top-color: #fff;
    border-radius: 40px;
    
    -webkit-animation: rotation 1s linear infinite;
    animation: rotation 1s linear infinite;
}

@-webkit-keyframes rotation{
    0% { -webkit-transform: rotate(0deg); -ms-transform: rotate(0deg); transform: rotate(0deg); }
    100%{ -webkit-transform: rotate(360deg); -ms-transform: rotate(360deg); transform: rotate(360deg); }
}

@keyframes rotation{
    0% { -webkit-transform: rotate(0deg); -ms-transform: rotate(0deg); transform: rotate(0deg); }
    100%{ -webkit-transform: rotate(360deg); -ms-transform: rotate(360deg); transform: rotate(360deg); }
}
 

</style>
</head>

<body style="background-color: #c4c4fc">
	<div class="container">
		
		<h1 class="text-center">My Hours Plan Reportes</h1>
		
		<h3 class="text-right" style="position: absolute; top: -5px; right: 75px;">Usuario: ${pageContext.request.userPrincipal.name}        |</h3>
		<h1></h1>

		<sec:authorize access="hasRole('ROLE_ADMIN')" />
		<!-- For login user -->
		<c:url value="/login" var="logoutUrl" />
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
				<a href="javascript:formSubmit()">Salir</a>
			</h3>
		</c:if>

		<ul class="nav nav-tabs nav-justified">

			<li class="active"><a data-toggle="tab" href="#home"><h4>Inicio</h4></a></li>
			<li><a data-toggle="tab" href="#menu1"><h4>Subir Archivo</h4></a></li>
			<li><a data-toggle="tab" href="#menu2"><h4>Generar Reporte</h4></a></li>
			<li><a data-toggle="tab" href="#menu3"><h4>Administrar</h4></a></li>


		</ul>

		<div class="tab-content">

			<div id="home" class="tab-pane fade">
			
			</div>

			
			<div id="menu1" class="tab-pane fade">
                <form action="uploadFile?${_csrf.parameterName}=${_csrf.token}"  method="post" enctype="multipart/form-data" id="form" role="form" style="text-align:center">
                        <div class="form-inline" id="hid" >
                        <h4>Seleccione el archivo de su ordenador</h4>
                        <div class="form-group">
                         <input type="file" name="file"> 
                         </div>
                         <input type="submit" id="submit" class="btn btn-primary" value="submit" onClick="showStuff()">
                         </div>
                   
				
                    <div class="color1 box" align="center">
                      <span class="loader loader-quart-1"  style="display:none" id="myP">Loading... </span>
                      
                    </div>
                    </form>				
			</div>
			<div id="menu2" class="tab-pane fade" style="height: 500px">

				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown">Elegir Filtro <span class="caret"></span></button>
					<ul class="dropdown-menu btn-block">
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=query40.rptdesign'">Empleados	con mas de 40hs</a></li>
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=multiProjects.rptdesign'">Empleados con mas de 1 proyecto</a></li>
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://localhost:8080/elreportador/frameset?__report=holidays.rptdesign'">Empleados sin los feriados cargados</a></li>
					</ul>
				</div>

				<iframe id="reportframe" style="border-width: 0px" position="fixed" src="" height="100%" width="100%"> </iframe>

			</div>

			<div id="menu3" class="tab-pane fade">

			<div id="exampleModal">
			
				<!-- Button trigger modal -->
			
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCrear">Crear Nuevo Usuario</button>

<!-- Modal -->

				<div class="modal fade" id="modalCrear" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">Nuevo Usuario</h4>
							</div>
							<form id="crearForm" role="form" data-toggle="validator" novalidate="true" method="post" >
							<div class="modal-body">
									<div class="form-group has-feedback">
										<label class="control-label" for="inputName">Nombre:</label>
										<input id="inputName" type="text" class="form-control" pattern="[A-Za-z]{1,}" name="nombre" data-error="Utilize caracteres validos [a-z]" required="">
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>				
									</div>
									<div class="form-group has-feedback">
										<label class="control-label" for="inputName">Apellido:</label>
										<input id="inputApellido" type="text" class="form-control" name="apellido" pattern="[A-Za-z]{1,}" data-error="Utilize caracteres validos [a-z]" required="">
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>		
										<div class="help-block with-errors"></div>		
									</div>		
									<div class="form-group has-feedback">
										<label class="control-label" for="inputEmail">Mail:</label>
										<input id="inputEmail" class="form-control" type="email"  required="" data-error="Ingrese un email valido" placeholder= "mail@ejemplo.com">						
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>	
										<div class="help-block with-errors"></div>
									</div>	

									<div class="form-group has-feedback">
										<label class="control-label" for="inputUser">Usuario:</label>
										<input id="inputUser" type="text" class="form-control" name="usuario"  required="">
										<span class="glyphicon form-control-feedback" aria-hidden="true">
									</span>							
									</div>

									<div class="form-group ">
										<label class="control-label" for="inputPassowrd">Contraseña:</label>
										<div class= "form-inline row">

											<div class="form-group col-sm-6 has-feedback">
												<input id="inputPassword" type="password" class="form-control" name="contraseña"  placeholder="Contraseña" data-minlength="6" data-toggle="validator" required="">
												<span class="help-block">6 caracteres minimo</span>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>	
											</div>
											<div class="form-group col-sm-6 has-feedback">
												<input id="inputPasswordConfirm" type="password" class="form-control" name="contraseña"  placeholder="Confirmar" data-toggle="validator"
												data-match-error="Las contraseñas no coinciden" data-match ="#inputPassword" required="">
												<span class="help-block with-errors"></span>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>	
											</div>
										</div>					
									</div>

									<div class="radio">
									<h4>Permisos del Usuario</h4>

									<div class="control-group">
										<div>
										<label class="checkbox-inline">
										  <input type="checkbox" id="inlineCheckbox1" value="option1" name="permisos"> Generar Reportes
										</label>
										<label class="checkbox-inline">
										  <input type="checkbox" id="inlineCheckbox2" value="option2" name="permisos"> Crear Usuarios
										</label>

										<label class="checkbox-inline">
										  <input type="checkbox" id="inlineCheckbox3" value="option3" name="permisos"> Administrar Feriados
										</label>
										<label class="checkbox-inline">
										  <input type="checkbox" id="inlineCheckbox3" value="option3" name="permisos"> Cargar archivos
										</label>
										</div>

									</div>

							</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
								<button type="submit" class="btn btn-primary" id="createButton">Crear</button>
							</div>
								</form>
						</div>
					</div>
				</div>
				</div> 
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#myOtherModal">Modificar Usuario</button>
				
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
							<button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown" id="dropdownUsuarios">Seleccionar Usuario<span class="caret"></span>
							</button>
							<ul class="dropdown-menu btn-block">
								<li><a href="#">Empleados</a></li>
								
							</ul>
						</div>
						<br>
						<br>
						<br>
						<div>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" value="option1"> Generar Reportes</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox2" value="option2"> Crear Usuarios</label>

							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox3" value="option3"> Administrar Feriados</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox3" value="option3"> Cargar archivos</label>
						</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
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
	<script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>


<script type="text/javascript">


	function showStuff() {
		document.getElementById("hid").style.visibility = "hidden";
		document.getElementById('myP').style.display = "block";
    }

	$(function() {	
		$(".modal").on('hidden.bs.modal', function(e) {
			$(this).find("input,textarea,select").val('').end();
			$('input:checkbox').removeAttr('checked');
			$(".form-group").removeClass('has-success');
			$(".form-group").removeClass('has-error');
			$(".glyphicon").removeClass('glyphicon-ok');
			$(".glyphicon").removeClass('glyphicon-remove');

		})		 		
			$("#dropdownUsuarios").onClick(function() {
			$("#dropdownUsuarios").val()
			{
				alert(user);
			}
		})

		//Submit del Logout

		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	})
	
</script>

</body>
</html>