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

	<%@ include file="css/stylesheet.css" %>

</style>

    <script src="<c:url value="src/main/webapp/resources/js/scripts.js"/>"></script>



</head>

<body style="background-color: #325c80">

<div class="modal fade" id="modalCreateU" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
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
							        <div class="col-md-6"><input type="text" class="form-control modUser" name="name" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.lastname"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control modUser" name="lastname" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.mail"/>:</label>
							        <div class="col-md-6"><input type="email" class="form-control changeUser modUser" name="mail" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.user"/>:</label>
							        <div class="col-md-6"><input type="text" class="form-control changeUser modUser" name="user" readonly="readonly"/></div>
							    </div>
								<div class="form-group">
									<label class="col-md-3 control-label"><fmt:message key="myhours.menu3.password"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control changeUser modUser" name="password" readonly="readonly"/></div>
							    </div>
							    <div class="form-group">
							        <label class="col-md-3 control-label"><fmt:message key="myhours.menu3.confirmPassword"/>:</label>
							        <div class="col-md-6"><input type="password" class="form-control changeUser modUser" name="confirmPassword" readonly="readonly"/></div>
								</div>
									<div id="modifRadio" class="radio">
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
 									<button id="dUser" type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteUser()"><fmt:message key="myhours.menu3.delete"/></button>
							        <button type="submit" class="btn btn-primary"><fmt:message key="myhours.menu3.save"/></button>
								</div>
							</div>
						</form>	
					</div>
				    </div>
				  </div>
				</div>
			</div>
	
	
	<div class="modal fade" id="modalCreate" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title"><fmt:message key="myhours.menu3.createholiday"/></h4>
							</div>
							<form id="newHolidayJ" method="post" class="form-horizontal newHolidayJ" action="newHoliday.html">
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
				
				
				<div class="modal fade" id="modalDelete" tabindex="-1"	role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title"><fmt:message key="myhours.menu3.deleteholiday"/></h4>
							</div>
							<form id="deleteHoliday" class="form-horizontal" role="form" data-toggle="validator" method="post" >
							
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
								<select name="mHoliday"  id="showHoliday" class="btn btn-primary dropdown-toggle input-sm" data-toggle="dropdown" accept-charset="utf-8" data-style="btn-info" style="width:570px; display:none">
                      				<option value="0" disabled>
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
	
	
	
	<div class="container">
<!--         <form> -->
<!--           <select id="language" name="language" onchange="submit()"> -->
<%--               <option value="en" ${language == 'en' ? 'selected' : ''}>English</option> --%>
<%--               <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option> --%>
<!--           </select> -->
<!--        </form> -->
		<nav class="navbar navbar-default navbar-fixed-top">
		 <a class="navbar-brand" href="http://www.ibm.com/ar-es/"><img src="http://brocon-it.com/images/partner/ibm_logo.png"></a>
		 <a>|<fmt:message key="myhours.title"/></a>
		<a id="linkUser" class="text-right" style="position: absolute; top:12px ; right: 75px; font-size:15px;" data-toggle="modal" data-target="#modalMUser"><fmt:message key="myhours.user"/>: ${pageContext.request.userPrincipal.name}        |</a>
		<label id="username" style="visibility:hidden"> ${pageContext.request.userPrincipal.name}</label>
		
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
		</nav>
		<h1></h1>
		<br>
		<br>
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
                         	<button type="submit" id="submit" class="btn btn-primary" value="<fmt:message key="myhours.menu1.submit"/>" onClick="showStuff()"><fmt:message key="myhours.menu1.submit"/></button>
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
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCreateU"><fmt:message key="myhours.menu3.createnewuser"/></button>
			<!-- Modificar Usuario -->
				<button type="button" id="modUser" class="btn btn-info btn-block" data-toggle="modal" data-target="#modalMUser"><fmt:message key="myhours.menu3.modifyuser"/></button>
			<!-- Crear feriados -->
				<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalCreate"><fmt:message key="myhours.menu3.createholiday"/></button>
			<!-- Modificar feriados -->
			<button type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#modalDelete"><fmt:message key="myhours.menu3.deleteholiday"/></button>
				
	
				

			
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js "></script>


<script type="text/javascript">

	<%@ include file="js/scripts.js" %>
	
</script> 

</body>
</html>