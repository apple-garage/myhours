<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.ibm.web.controller.MainController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ibm.languages.text" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Bienvenido</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"crossorigin="anonymous">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker.css">
<link rel="stylesheet"href="http://code.jquery.com/ui/1.10.3/themes/flick/jquery-ui.min.css">
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

	<div class="modal fade" id="modalreate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="exampleModalLabel"><fmt:message key="myhours.menu3.createnewuser"/></h4>
							</div>
							<form id="crearForm" method="post" class="form-horizontal crearForm" action="newUser.html">
							<div class="modal-body">
								<input type="hidden" name="id"/>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.name"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="name" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.lastname"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="lastname" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.mail"/>:</label>
							        <div class="col-md-6"><input type="email" class="form-control" name="mail" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.user"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="user" /></div>
							    </div>
								<div class="form-group">
									<label class="col-md-3 control-label"><fmt:message key="myhours.menu3.password"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="password" /></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.confirmPassword"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="confirmPassword"/></div>
								</div>

									<div class="radio">
									<h4><fmt:message key="myhours.menu3.useractions"/></h4>
										<div class="control-group">
											<div>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox1" value="ROLE_USER" name="permissions"><fmt:message key="myhours.menu3.generatereports"/>
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox2" value="ROLE_CONFIG" name="permissions"><fmt:message key="myhours.menu3.createusers"/>
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" id="inlineCheckbox3" value="ROLE_ADMIN" name="permissions"><fmt:message key="myhours.menu3.createholidays"/>
											</label>
											</div>
										</div>
									</div>
									<div class="form-group">
							        <div class="col-md-9 col-md-offset-3">
							            <div id="messages1" class="messages"></div>
							        </div>
						    </div>
							</div>
							<div class="modal-footer">
								<div class="col-md-9 col-md-offset-3">
									<button type="button" class="btn btn-default" data-dismiss="modal" onClick="clearForm()"><fmt:message key="myhours.menu3.cancel"/></button>
									<button type="submit" class="btn btn-primary" id="createButton"><fmt:message key="myhours.menu3.save"/></button>
								</div>
							</div>
						</form>								
					</div>
					</div>
				</div> 


	<div class="modal fade" id="modalMUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				        <h4 class="modal-title" id="myModalLabel"><fmt:message key="myhours.menu3.modifyuser"/></h4>
				      </div>
				  <div class="modal-body">
					<div class="dropdown">
						<div class=" form-group has-feedback">
		     				<select name="dlstate" id="DLState" class="btn btn-primary dropdown-toggle input-sm" data-toggle="dropdown"  data-style="btn-info" style="width:570px" > 
                       		</select>
                        </div>						
							<form id="modifyForm" method="post" class="form-horizontal crearForm" action="modifUser.html">
							<div class="modal-body">
								<input type="hidden" name="id"/>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.name"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="name" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.lastname"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="lastname" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.mail"/>:</label>
							        <div class="col-md-6"><input type="email" class="form-control" name="mail" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.user"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control" name="user" readonly="readonly"/></div>
							    </div>
								<div class="form-group">
									<label class="col-md-3 control-label"><fmt:message key="myhours.menu3.password"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="password" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.confirmPassword"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control" name="confirmPassword" readonly="readonly"/></div>
								</div>
									<div class="radio">
									<h4><fmt:message key="myhours.menu3.useractions"/></h4>
										<div class="control-group">
											<div>
											<label class="checkbox-inline">
											  <input type="checkbox" class="modUser" id="ROLE_USER" value="ROLE_USER" name="permissions"><fmt:message key="myhours.menu3.generatereports"/>
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" class="modUser" id="ROLE_CONFIG" value="ROLE_CONFIG" name="permissions"><fmt:message key="myhours.menu3.createusers"/>
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" class="modUser" id="ROLE_ADMIN" value="ROLE_ADMIN" name="permissions"><fmt:message key="myhours.menu3.createholidays"/>
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
							        <button type="button" class="btn btn-default" data-dismiss="modal" onClick="clearForm()"><fmt:message key="myhours.menu3.cancel"/></button>
							        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteUser()"><fmt:message key="myhours.menu3.delete"/></button>
							        <button type="submit" class="btn btn-primary"><fmt:message key="myhours.menu3.save"/></button>
								</div>
							</div>
						</form>	
					</div>
				    </div>
				  </div>
				</div>
			</div>
	
	<div class="container">
<!--         <form> -->
<!--           <select id="language" name="language" onchange="submit()"> -->
<%--               <option value="en" ${language == 'en' ? 'selected' : ''}>English</option> --%>
<%--               <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option> --%>
<!--           </select> -->
<!--         </form> -->
		<h1 class="text-center"><fmt:message key="myhours.title"/></h1>
		
		<h3 class="text-right" style="position: absolute; top: -5px; right: 80px; font-size:15px;"><fmt:message key="myhours.user"/>: ${pageContext.request.userPrincipal.name}        |</h3>
		
		<!-- For login user -->
		<c:url value="/login" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="position: absolute; top: -5px; right: 30px; font-size:15px">
				<a href="javascript:formSubmit()"><fmt:message key="myhours.logout"/></a>
			</h3>
		</c:if>
		
		<h1></h1>
		<ul class="nav nav-tabs nav-justified" id="myTab">
			<li class="active"><a data-toggle="tab" href="#home"><h4><fmt:message key="myhours.home"/></h4></a></li>
			<li><a data-toggle="tab" href="#menu1"><h4><fmt:message key="myhours.menu1"/></h4></a></li>
			<li><a data-toggle="tab" href="#menu2"><h4><fmt:message key="myhours.menu2"/></h4></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')"><li><a data-toggle="tab" href="#menu3"><h4><fmt:message key="myhours.menu3"/></h4></a></li></sec:authorize>
		</ul>

		<div class="tab-content">

			<div id="home" class="tab-pane fade">
			</div>

			<div id="menu1" class="tab-pane fade">
                <form action="uploadFile?${_csrf.parameterName}=${_csrf.token}"  method="post" enctype="multipart/form-data" id="form" role="form" style="text-align:center">
                        <div class="form-inline" id="hid" >
                        <h4><fmt:message key="myhours.menu1.option"/></h4>
                        <div class="form-group">
                         <input type="file" name="file"> 
                         </div>
                         	<input type="submit" id="submit" class="btn btn-primary" value="<fmt:message key="myhours.menu1.submit"/>" onClick="showStuff()">
                         </div>
                    <div class="color1 box" align="center">
                      <span class="loader loader-quart-1"  style="display:none" id="myP">Loading..</span>                 
                    </div>
				</form>	
				<c:if test="${info != null}">
					<h3><span class="label label-info" id="info" style="display:center"><fmt:message key="${info}"/></span></h3> 
				</c:if>
				<c:if test="${info != null}">
					<h3><span class="label label-danger" id="error" style="display:center"><fmt:message key="${error}"/></span></h3> 	
				</c:if>	   	
			</div>
			
			<div id="menu2" class="tab-pane fade" style="height: 500px">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle btn-block" type="button" data-toggle="dropdown"><fmt:message key="myhours.menu2.filter"/><span class="caret"></span></button>
					<ul class="dropdown-menu btn-block">
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://birt-reporter.mybluemix.net/frameset?__report=query40.rptdesign'"><fmt:message key="myhours.menu2.40hsreport"/></a></li>
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://birt-reporter.mybluemix.net/frameset?__report=multiProjects.rptdesign'"><fmt:message key="myhours.menu2.proyectreport"/></a></li>
						<li><a href="#"	onClick="document.getElementById('reportframe').src='http://birt-reporter.mybluemix.net/frameset?__report=holidays.rptdesign'"><fmt:message key="myhours.menu2.holidaysreport"/></a></li>
					</ul>
				</div>
				<c:if test="${info != null}">
					<h3><span class="label label-danger" id="error" style="display:center"><fmt:message key="${error}"/></span></h3> 
				</c:if>
				<iframe id="reportframe" style="border-width: 0px" position="fixed" src="" height="100%" width="100%"> </iframe>
			</div>
	
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div id="menu3" class="tab-pane fade">
			<!-- Crear Usuario -->
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCreate"><fmt:message key="myhours.menu3.createnewuser"/></button>
				
			<!-- Modificar Usuario -->
				<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#modalMUser"><fmt:message key="myhours.menu3.modifyuser"/></button>
			
			<!-- Crear feriados -->
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCreate"><fmt:message key="myhours.menu3.createholiday"/></button>
				<div class="modal fade" id="modalCreate" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
								<h4 class="modal-title"><fmt:message key="myhours.menu3.createholiday"/></h4>
							</div>
							<form id="newHoliday" method="post" class="form-horizontal" action="newHoliday.html">
							<div class="modal-body">
									<div class="form-group">
								        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.date"/>:</label>
								        <div class="col-md-6"><input accept="" id="holidayDate" type="date" class="form-control" name="holidayDate" data-provide="datepicker" data-date-format="yyyy-mm-dd" autocomplete="off"/></div>
									</div>
									<div class="form-group">
								        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.description"/>:</label>
								        <div class="col-md-6"><input type="text" class="form-control" name="description" /></div>
									</div>
									<div class="radio">
										<h4><fmt:message key="myhours.menu3.countries"/></h4>
	
										<div class="control-group">
											<div>
											<label class="checkbox-inline">
											  <input type="checkbox" value="1" name="pais[]"> Argentina   
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="2" name="pais[]"> Chile
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="3" name="pais[]">Colombia
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="4" name="pais[]"> Ecuador
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="5" name="pais[]"> Peru
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="6" name="pais[]"> Uruguay
											</label>
											<label class="checkbox-inline">
											  <input type="checkbox" value="7" name="pais[]"> Venezuela
											</label>
											</div>
										</div>
									</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="myhours.menu3.cancel"/></button>
								<button type="submit" class="btn btn-primary" id="cargarFeriado"><fmt:message key="myhours.menu3.save"/></button>
							</div>
							</form>
						</div>
					</div>
				</div>
		
			<!-- Modificar feriados -->
			<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#modalDelete"><fmt:message key="myhours.menu3.deleteholiday"/></button>
				<div class="modal fade" id="modalDelete" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title"><fmt:message key="myhours.menu3.deleteholiday"/></h4>
							</div>
							<form id="newHoliday" class="form-horizontal" role="form" data-toggle="validator" method="post" >
							
							<div class="modal-body">
								<div class="form-group">			
							        <label class="col-md-5 control-label"><fmt:message key="myhours.menu3.year"/>:</label>
							        <div class="col-md-4">
								      <input type="text" id="datepicker" class="form-control" name="holidayDate" required data-provide="datepicker" />
									</div>
								 </div>									
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
							<div class="help-block with-errors"></div>				
							<div class="dropdown">
								<select name="dlpais" id="dropdownCountry" class="btn btn-primary dropdown-toggle input-sm" data-toggle="dropdown" data-style="btn-info" style="width:570px" >
								</select>
							</div>
							<br>
							<div class=" form-group has-feedback">
							
							<div class="col-md-6">
								<select multiple name="mHoliday"  id="showHoliday" class="btn btn-primary dropdown-toggle input-sm" data-toggle="dropdown" accept-charset="utf-8" data-style="btn-info" style="width:570px; display:none">
                      				</select>
                      			</div>
                   			</div>
                       
						</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="myhours.menu3.cancel"/></button>
								<button type="button" class="btn btn-primary" id=delete onclick="deleteHoliday()"><fmt:message key="myhours.menu3.delete"/></button>
							</div>
							</form>
						</div>
					</div>
				</div>
			
			<div id="userinfo"></div>
			<c:if test="${info != null}">
				<h3><span class="label label-info" id="info" style="display:center"><fmt:message key="${info}"/></span></h3>
			</c:if>
			<c:if test="${error != null}">
			<h3><span class="label label-danger" id="error" style="display:center"><fmt:message key="${error}"/></span></h3>
			</c:if>
			</div>
			</sec:authorize>			
		</div>
	
	</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
	<script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
	<script src="https://cdn.jsdelivr.net/g/jquery.transition@1.7.2,jquery.collapse@1.1.1,bootstrap.datepicker-fork@1.3.0(js/bootstrap-datepicker.js)"></script>

<script type="text/javascript">

	function showStuff() {
		document.getElementById("hid").style.visibility = "hidden";
		document.getElementById('myP').style.display = "block";
    }

	$(function() {
			//validaciones
			$('.crearForm').bootstrapValidator({
			container: '.messages',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            name: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.name"/>'}
	                }
	            },
	            lastname: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.lastname"/>'}
	                }
	            },
	            mail: {
	                validators: {
	                    emailAddress: {message: '<fmt:message key="myhours.menu3.error.mailformat"/>'}
	                }
	            },
	            user: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.user"/>'},
	                }
	            },
	            password: { 
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.password"/>'},
	                    stringLength: {
	                        min: 6,
	                        message: '<fmt:message key="myhours.menu3.error.passwordlength"/>'
	                    },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: '<fmt:message key="myhours.menu3.error.passwordidentical"/>'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                	notEmpty: {message: '<fmt:message key="myhours.menu3.error.password"/>'},
	                    identical: {
	                        field: 'password',
	                        message: '<fmt:message key="myhours.menu3.error.passwordidentical"/>'
	                    }
	                }
	            },
	            permissions:{
	            	validators: {
	            		choice: {
	            			min:1,
	            			max:1,
	            			message: '<fmt:message key="myhours.menu3.error.rol"/>'
	            			
	            		}
	            	}
	            	
	            }
	        }
	    });
			
		$("#datepicker").datepicker( {
		    format: "yyyy",
		    viewMode: "years", 
		    minViewMode: "years"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
			 var seleccionPais = ($('select[name=dlpais]').val());
	 		if ( seleccionPais !='0'){
	    		findHoliday();
	 		}
		});
						
		//validaciones
		$('#newHoliday').bootstrapValidator({
        container: '#messages',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
       	holidayDate: {
               validators: {
                   notEmpty: {message: '<fmt:message key="myhours.menu3.error.holidayrequired "/>'}
               }
           },
		});
		  
		//mantener tab
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        localStorage.setItem('lastTab', $(this).attr('href'));
	        $("#info").remove();
	        $("#error").remove();
	    });

	    var lastTab = localStorage.getItem('lastTab');
	    if (lastTab) {
	        $('[href="' + lastTab + '"]').tab('show');
	    };
	    
		//limpiar pagina
		$(".modal").on('hidden.bs.modal', function(e) {
			$(this).find("input,textarea,select").val('').end();
			$('input:checkbox').removeAttr('checked');
			$(".form-group").removeClass('has-success');
			$(".form-group").removeClass('has-error');
			$(".glyphicon").removeClass('glyphicon-ok');
			$(".glyphicon").removeClass('glyphicon-remove');
			$("#DLState").val("0");
			$("#dropdownCountry").val("0");
			$("#showHoliday").val("0");
			
	        $('.messages').find('small.help-block').hide();
	        $('.messages').find('i.form-control-feedback').hide(); 
	        
 			$("#delUser").prop('disabled', false);
			$("#DLState").prop('disabled', false);
 			$(".modUser").prop('disabled', false);
 			
 			$(".modUser").attr('readonly', true); 			

 			$("#modifRadio").show();
			clearForm();
		});		 		

		loadUsers();
		loadCountry();
	});
	
    function deleteHoliday(){
        var seleccion = ($('select[name=mHoliday]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'holiday':seleccion},
			url : "deleteHoliday.html",
			success : function(data) {
			alert("Se elimino correctamente!");
				findHoliday();
				$("#showHoliday").empty();
 				document.getElementById('showHoliday').style.display = "none";
 			},
 		});
    };
    
	$("#dropdownCountry").change(function() {
	 var seleccionDate = ($('select[name=holidayDate]').val());
	 	
 		if ( seleccionDate !='0'){
			findHoliday ();
 		}
	});
	
	function findHoliday (){
		var seleccionPais = ($('select[name=dlpais]').val());
		var year= $("#datepicker").val();	
		$("#showHoliday").html('');
			 $.ajax({
				type : "GET",	
				contentType :"application/json;charset=ISO-8859-1",
				url : "holidays.html",
				data : {"seleccionPais":seleccionPais, "year":year},
				dataType : 'json',
				success : function(data) {
					console.log("SUCCESS: ", data);
					JsonListF = data;
					var listItemsF = "";					
					for ( var i in data) {
						listItemsF += "<option value='" + data[i].id + "'>"+ data[i].holiday + "</option>";
					}
					console.log(listItemsF);
					$("#showHoliday").append(listItemsF);
					 if(listItemsF != "")
							document.getElementById('showHoliday').style.display = "inline";
						 else
							 document.getElementById('showHoliday').style.display = "none";
				},
	              error:function(request,status,error){
	              console.log(request.responseText);
	              }
			});
    }; 

	function formSubmit() {
		localStorage.clear();
		document.getElementById("logoutForm").submit();
	};
	
	function clearForm(){
		document.getElementById("crearForm").reset();
		document.getElementById("modifyForm").reset();
		$('input:checkbox').removeAttr('checked');
	};
	
	function deleteUser(){
		var seleccion = ($('select[name=dlstate]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'user':seleccion},
			url : "deleteUser.html",
			success : function(data) {
				loadUsers();
				$("#userinfo").html(data);
			},
		});
	};
	
	function loadCountry(){
	    $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "countries.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {	
				console.log("SUCCESS: ", data);	
				JsonCountry = data;
				var listItemsC= "<option value='0'>Seleccionar un pais</option>";
	        for (var i in data){
	        	listItemsC+= "<option value='" + data[i].id + "'>" + data[i].country + "</option>";
	        }
	        $("#dropdownCountry").html(listItemsC);
			},
		});
	};
	
	function loadUsers(){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "listUsers.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);	
				JsonList = data;
				var listItems = "<option value=' '>Select user</option>";
	        for (var i in data){
	        	listItems+= "<option value='" + data[i].id + "'>" + data[i].lastname +", " + data[i].name + "</option>";
	        }
	        $("#DLState").html(listItems);
			},
		});
	};
	
	$("#linkUser").click(function(){
		var username = $.trim($("#username").text());

		$("#modifRadio").hide();
		$("#delUser").prop('disabled', true);
		$("#DLState").prop('disabled', true);
		$(".modUser").prop('disabled', false);
		$(".changeUser").removeAttr("readonly");
		$('select[name=dlstate]').find('option:contains('+ username +')').attr("selected", true).change();
	});

	$("#modUser").click(function(){
		$("#DLState").prop('disabled', false);
		$(".modUser").prop('disabled', true);
		$(".modUser").removeAttr("readonly");
		$('select[name=dlstate]').find('option:contains('+ username +')').attr("selected", true).change();
	});
		
	$("#DLState").change(function() {
		var seleccion = ($('select[name=dlstate]').val());
		for (i in JsonList) {
			if (JsonList[i].id == seleccion) {
				$('input[name="id"]').val(JsonList[i].id);
				$('input[name="name"]').val(JsonList[i].name);
				$('input[name="lastname"]').val(JsonList[i].lastname);
				$('input[name="user"]').val(JsonList[i].user);
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
	};
	
</script>

</body>
</html>