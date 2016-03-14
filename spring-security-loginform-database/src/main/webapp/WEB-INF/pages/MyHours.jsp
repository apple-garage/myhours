<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ibm.web.controller.MainController"%>
<!DOCTYPE html>
<html>
<head>
<title>Bienvenido</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"crossorigin="anonymous">
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

.loader{
    width: 40px;
    height: 40px;
    display: inline-block;
    vertical-align: middle;
    position: relative;
}

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
		
		<h1 class="text-center">My Hours Reports</h1>
		
		<h3 class="text-right" style="position: absolute; top: -5px; right: 75px; font-size:15px;">Usuario: ${pageContext.request.userPrincipal.name}        |</h3>
		
		<!-- For login user -->
		<c:url value="/login" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="position: absolute; top: -5px; right: 30px; font-size:15px">
				<a href="javascript:formSubmit()">Salir</a>
			</h3>
		</c:if>
		
		<h1></h1>
		<ul class="nav nav-tabs nav-justified" id="myTab">
			<li class="active"><a data-toggle="tab" href="#home"><h4>Inicio</h4></a></li>
			<li><a data-toggle="tab" href="#menu1"><h4>Subir Archivo</h4></a></li>
			<li><a data-toggle="tab" href="#menu2"><h4>Generar Reporte</h4></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')"><li><a data-toggle="tab" href="#menu3"><h4>Administrar</h4></a></li></sec:authorize>
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
                         <input type="submit" id="submit" class="btn btn-primary" value="Procesar" onClick="showStuff()">
                         </div>
                    <div class="color1 box" align="center">
                      <span class="loader loader-quart-1"  style="display:none" id="myP">Cargando</span>                 
                    </div>
				</form>	
					<h3><span class="label label-info" id="info" style="display:center">${info}</span></h3> 
					<h3><span class="label label-danger" id="error" style="display:center">${error}</span></h3> 		   	
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
				<h3><span class="label label-danger" id="error" style="display:center">${error}</span></h3> 
				<iframe id="reportframe" style="border-width: 0px" position="fixed" src="" height="100%" width="100%"> </iframe>
			</div>
	
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div id="menu3" class="tab-pane fade">
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCrear">Crear Nuevo Usuario</button>

				<div class="modal fade" id="modalCrear" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel">Nuevo Usuario</h4>
							</div>
							<form id="crearForm" method="post" class="form-horizontal" action="newUser.html">
							<div class="modal-body">
								<input type="hidden" name="id"/>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Nombre:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="nombre" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Apellido:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="apellido" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Mail:</label>
							        <div class="col-md-6"><input type="email" class="form-control" name="mail" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Usuario:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="usuario" /></div>
							    </div>
								<div class="form-group">
									<label class="col-md-3 control-label">Contraseña:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="password" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Confirme contraseña:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="confirmPassword" /></div>
								</div>

									<div class="radio">
									<h4>Permisos del Usuario</h4>
										<div class="control-group">
											<div>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox1" value="ROLE_USER" name="permisos"> Generar Reportes
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox2" value="ROLE_CONFIG" name="permisos"> Configurador
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox3" value="ROLE_ADMIN" name="permisos"> Administrador
											</label>
											</div>
										</div>
									</div>
									<div class="form-group">
							        <div class="col-md-9 col-md-offset-3">
							            <div id="messages"></div>
							        </div>
						    </div>
							</div>
							<div class="modal-footer">
								<div class="col-md-9 col-md-offset-3">
									<button type="button" class="btn btn-default" data-dismiss="modal" onClick="clearForm()">Cancelar</button>
									<button type="submit" class="btn btn-primary" id="createButton">Crear</button>
								</div>
							</div>
						</form>								
					</div>
					</div>
				</div> 
				
				<!-- Modificar Usuario -->
				<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#modalMUsuario">Modificar Usuario</button>
				
				<div class="modal fade" id="modalMUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
						<div class=" form-group has-feedback">
		     				<select name="dlstate" id="DLState" class="btn btn-primary dropdown-toggle input-sm" data-toggle="dropdown"  data-style="btn-info" style="width:570px" > 
                       		</select>
                        </div>						
							<form id="crearForm" method="post" class="form-horizontal" action="modifUser.html">
							<div class="modal-body">
								<input type="hidden" name="id"/>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Nombre:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="nombre" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Apellido:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="apellido" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Mail:</label>
							        <div class="col-md-6"><input type="email" class="form-control" name="mail" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Usuario:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="usuario" /></div>
							    </div>
								<div class="form-group">
									<label class="col-md-3 control-label">Contraseña:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="password" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label">Confirme contraseña:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="confirmPassword" /></div>
								</div>
									<div class="radio">
									<h4>Permisos del Usuario</h4>
										<div class="control-group">
											<div>
											<label class="checkbox-inline">
											  <input type="checkbox" id="ROLE_USER" value="ROLE_USER" name="permisos"> Generar Reportes
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="ROLE_CONFIG" value="ROLE_CONFIG" name="permisos"> Configurador
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="ROLE_ADMIN" value="ROLE_ADMIN" name="permisos"> Administrador
											</label>
											</div>
										</div>
									</div>
									<div class="form-group">
							        <div class="col-md-9 col-md-offset-3">
							            <div id="messages"></div>
							        </div>
						    </div>
							</div>
							<div class="modal-footer">
								<div class="col-md-9 col-md-offset-3">
							        <button type="button" class="btn btn-default" data-dismiss="modal" onClick="clearForm()">Cancelar</button>
							        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteUser()">Eliminar</button>
							        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
								</div>
							</div>
						</form>	
					</div>
				    </div>
				  </div>
				</div>
			</div>
				<h3><span class="label label-info" id="info" style="display:center">${info}</span></h3> 
				<h3><span class="label label-danger" id="error" style="display:center">${error}</span></h3> 
			</div>
			</sec:authorize>			
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
			//validaciones
			$('#crearForm').bootstrapValidator({
	        container: '#messages',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            nombre: {
	                validators: {
	                    notEmpty: {message: 'El campo nombre es requerido'}
	                }
	            },
	            apellido: {
	                validators: {
	                    notEmpty: {message: 'El campo apellido es requerido'}
	                }
	            },
	            mail: {
	                validators: {
	                    emailAddress: {message: 'No es una direccion de mail valida'}
	                }
	            },
	            usuario: {
	                validators: {
	                    notEmpty: {message: 'El campo usuario es requerido'},
	                }
	            },
	            password: { 
	                validators: {
	                    notEmpty: {message: 'El campo contraseña es requerido'},
	                    stringLength: {
	                        min: 6,
	                        message: 'La contraseña debe tener al menos 6 caracteres'
	                    },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: 'Las contraseñas no coinciden'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                	notEmpty: {message: 'El campo contraseña es requerido'},
	                    identical: {
	                        field: 'password',
	                        message: 'Las contraseñas no coinciden'
	                    }
	                }
	            }
	        }
	    });
		loadUsers();

		//mantener tab
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        localStorage.setItem('lastTab', $(this).attr('href'));
	        $("#info").remove();
	        $("#error").remove();
	    });

	    var lastTab = localStorage.getItem('lastTab');
	    if (lastTab) {
	        $('[href="' + lastTab + '"]').tab('show');
	    }
	    
		//limpiar pagina
		$(".modal").on('hidden.bs.modal', function(e) {
			$(this).find("input,textarea,select").val('').end();
			$('input:checkbox').removeAttr('checked');
			$(".form-group").removeClass('has-success');
			$(".form-group").removeClass('has-error');
			$(".glyphicon").removeClass('glyphicon-ok');
			$(".glyphicon").removeClass('glyphicon-remove');
			$("#DLState").val("0");
			$("#dropdownPais").val("0");
			loadUsers();

		})		 		
			$("#dropdownUsuarios").onClick(function() {
				$("#dropdownUsuarios").val()
				{
					alert(user);
				}
			});
	});

	function formSubmit() {
		localStorage.clear();
		document.getElementById("logoutForm").submit();
	}
	
	function clearForm(){
		document.getElementById("crearForm").reset();
	}
	
	function deleteUser(){
		var seleccion = ($('select[name=dlstate]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'user':seleccion},
			url : "deleteUser.html",
			success : function(data) {
				loadUsers();
			},
		});
		
	}
	function loadUsers(){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "test.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);	
				JsonList = data;
				var listItems= "<option value=' '>Selecciones un usuario</option>";
	        for (var i in data){
	        	listItems+= "<option value='" + data[i].id + "'>" + data[i].lastname +", " + data[i].name + "</option>";
	        }
	        $("#DLState").html(listItems);
			},
		});
	}
		
	$("#DLState").change(function() {
		var seleccion = ($('select[name=dlstate]').val());
		for (i in JsonList) {
			if (JsonList[i].id == seleccion) {
				$('input[name="id"]').val(JsonList[i].id);
				$('input[name="nombre"]').val(JsonList[i].name);
				$('input[name="apellido"]').val(JsonList[i].lastname);
				$('input[name="usuario"]').val(JsonList[i].user);
				$('input[name="mail"]').val(JsonList[i].mail);
				$('input[name="password"]').val(JsonList[i].password);
				$('input[name="confirmPassword"]').val(JsonList[i].password);
				var JrolList = JsonList[i].rol;
				for (var j in JrolList) {
					var rol = JrolList[j];
					document.getElementById(rol.rol).checked = true;
				}
			}
		}

	});
	
</script>

</body>
</html>