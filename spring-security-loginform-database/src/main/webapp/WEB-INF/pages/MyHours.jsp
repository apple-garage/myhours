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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker.css">
	<link rel="stylesheet"href="http://code.jquery.com/ui/1.10.3/themes/flick/jquery-ui.min.css">
	<link rel="stylesheet"href="https://cdn.datatables.net/responsive/2.0.2/css/responsive.bootstrap.min.css">
	<link rel="stylesheet"href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">

	<style>
		
		.p{
			font-size: 2px;
			font-family:"HelvLightIBM";
		}
		.navbar-brand {
			padding: 0px;
		}
		.navbar-brand>img {
			height: 100%;
			padding: 15px;
			width: auto;
		}
		
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
		
		.box:last-child {
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
		
		td.details-control {
		    background: url('http://www.datatables.net/examples/resources/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		
		tr.shown td.details-control {
		    background: url('http://www.datatables.net/examples/resources/details_close.png') no-repeat center center;
		}
		
		@-webkit-keyframes rotation{
		    0% { -webkit-transform: rotate(0deg); -ms-transform: rotate(0deg); transform: rotate(0deg); }
		    100%{ -webkit-transform: rotate(360deg); -ms-transform: rotate(360deg); transform: rotate(360deg); }
		}
		
		@keyframes rotation{
		    0% { -webkit-transform: rotate(0deg); -ms-transform: rotate(0deg); transform: rotate(0deg); }
		    100%{ -webkit-transform: rotate(360deg); -ms-transform: rotate(360deg); transform: rotate(360deg); }
		}
		 
		.datepicker{z-index:1200 !important;}
		 
		#myTab li a { border-color: blue;background-color:#adbdcc; }
		 
		#myTab li.active a {border-bottom-color: transparent;background-color:#f2f2f2; }
		 
		.tab-pane {
		 	background-color:#f2f2f2;
		 	border-style: solid;
		 	border-color: blue;
		 	border-width: 0 1px 1px 1px;
		 	border-radius: 0 0 5px 5px;
		}
		 
	</style>

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
 									<button id="delUser" type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteUser()"><fmt:message key="myhours.menu3.delete"/></button>
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
								        <div class="col-md-6"><input accept="" id="hDate" type="date" class="form-control" name="holidayDate" data-provide="datepicker" data-date-format="yyyy-mm-dd" autocomplete="off"/></div>
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
							<form id="dHoliday" class="form-horizontal" role="form" data-toggle="validator" method="post" >
							
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
							<br />
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
<!--         </form> -->
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
		<br />
		<br />
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
                        <div class="form-inline" id="uploadForm_div" >
                        <h4><fmt:message key="myhours.menu1.option"/></h4>
                        <div class="form-group">
                         <input type="file" name="file"> 
                         </div>
                         	<button type="submit" id="submit" class="btn btn-primary" value="<fmt:message key="myhours.menu1.submit"/>" onClick="showLoadingAnimation('uploadForm_div')"><fmt:message key="myhours.menu1.submit"/></button>
                         <p></p>
                         </div>
                    <div id="uploadForm_div_wait" class="color1 box" align="center" style="display:none; ">
                      <span class="loader loader-quart-1 " align="center"></span><br /><fmt:message key="myhours.menu1.loading"/>                 
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
				
				<div class="col-md-12" style="background-color: white; border-style:solid;">
					<div class="form-group" style="margin-top: 10px;margin-bottom:50px;">
		               	<div class="col-md-3"><select name="reports" class="selectpicker" data-style="btn-primary" multiple data-max-options="1" title="<fmt:message key="myhours.menu3.selectReport"/>">
		                     <option value=0>Default</option>
		                     <option value=1>Mas de 40</option>
		                     <option value=2>Menos de 40</option>
		                     <option value=3>Holidays mal cargados</option>
		                     <option value=4>Mas de un proyecto</option>
	                    </select>
	                    </div>									
				        <label class="col-md-1 control-label"><fmt:message key="myhours.menu3.date"/>:</label>
				        <div class="col-md-2"><input accept="" id="startDate" type="date" class="form-control" name="dateStart" data-provide="datepicker" data-date-format="yyyy-mm-dd" autocomplete="off"/></div>
				        <label class="col-md-1 control-label"><fmt:message key="myhours.menu3.date"/>:</label>
				        <div class="col-md-2"><input accept="" id="endDate" type="date" class="form-control" name="dateEnd" data-provide="datepicker" data-date-format="yyyy-mm-dd" autocomplete="off"/></div>
						<button class="btn btn-primary pull-right col-md-2" type="submit" onclick="prepareReport()"><fmt:message key="myhours.menu3.search"/></button>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<div class="col-md-6">	                     
							<select name="manager" id="managerList" data-live-search="true" class="selectpicker col-md-5" multiple data-max-options="1" data-style="btn-primary" title="<fmt:message key="myhours.menu3.selectManager"/>"> 
							</select>
							<select name="country" id="countryList" class="selectpicker col-md-5" data-style="btn-primary" multiple data-max-options="1" title="<fmt:message key="myhours.menu3.countries"/>"> 
							</select>
						</div>
<!-- 						<select class="selectpicker" id="countryList"></select> -->
						<div class="btn-group btn-sm col-md-6">	
							<button id="btnHide" class="btn btn-primary" type="submit"><fmt:message key="myhours.menu3.hideColumns" /></button>
							<button id="btnShow" class="btn btn-primary" type="submit"><fmt:message key="myhours.menu3.showColumns" /></button>
						</div>	
					</div>
					
				</div>
				
				<div id="reportTable_container" style="background-color: white; border-style:solid;">
<!-- 				<label><input type="checkbox" value="1" name="name" checked="checked"><span>Name</span></label> -->
				
<!-- 				Placeholders for the default or selected report -->
					<table id="reportTable" class="table display " style="width: 100%; "></table>
				</div>
				<div align="center" id="reportTable_container_wait" style="display:none; width: 100%; ">
                      <p></p><span align="center" class="loader loader-quart-1"></span><br /><fmt:message key="myhours.menu1.loading"/>                
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
	<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
	<script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js "></script>
	
	
<script type="text/javascript">
	
	jsonData = [];
	report = {};
	
	function showLoadingAnimation(element) {
		if(this.state){
			document.getElementById(element).style.display = "block";
			document.getElementById(element+'_wait').style.display = "none";
			this.state = 0;
		}else{
			document.getElementById(element).style.display = "none";
			document.getElementById(element+'_wait').style.display = "block";
			this.state = 1;
		}
    }
    
//	begin jQuery initialization script
	$(function() {
			
// 			showLoadingAnimation() static property initialization
			showLoadingAnimation.state = 0;
			
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
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.name"/>'},


			            regexp: {
			                regexp: /^[a-zA-Z]+$/,
			                message: 'The username can only consist of alphabetical, number, dot and underscore'
			            }
	                },
	            },
	            lastname: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.lastname"/>'}
	                },
		            regexp: {
		                regexp: /^[a-zA-Z]+$/,
		                message: 'The username can only consist of alphabetical, number, dot and underscore'
		                
		                
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
	            			max:3,
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

	    var lastTab = localStorage.getItem('lastTab');
	    if (lastTab) {
	        $('[href="' + lastTab + '"]').tab('show');
	    };
	    
		loadManagers();
		loadUsers();
		loadCountry();
		prepareReport();
	});
//	end jQuery initialization script
	
	//limpiar pagina
	$(".modal").on('hidden.bs.modal', function(e) {
		$(document).find("input,textarea,select").val('').end();
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
			$("#deleteUser").show();
		clearForm();
		loadUsers();
	});
	
	//validaciones
	$('#newHolidayJ').bootstrapValidator({
    container: '#messages',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields:{
   		holidayDate: {
           validators: {
               notEmpty: {message: '<fmt:message key="myhours.menu3.error.holidayrequired "/>'}
           }
       	},
        description: {
       	   validators:{
       				 regexp:{
     					regexp: /^[A-Z0-9 a-z ñ]*$/,
		        		message: 'The username can consist of alphabetical and numbers'
      		 		 }  
     	   }
        }
   	}
       
	});
	  
	//mantener tab
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        localStorage.setItem('lastTab', $(this).attr('href'));
        $("#info").remove();
        $("#error").remove();
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
	
	function prepareReport(){
		
		showLoadingAnimation('reportTable_container');
		
// 		destroy any previously loaded DataTable
		if($.fn.DataTable.isDataTable("#reportTable")){
			$('#reportTable').DataTable().clear().destroy();
			$('#reportTable_container').empty();
			$('#reportTable_container').append("<table id=\"reportTable\" class=\"table display \" style=\"width: 100%\; \"></table>");
		};
		
//		sets to 0 if null
		selectedDateStart = (!$("#startDate").val()?0:$("#startDate").val());
		selectedDateEnd = (!$("#endDate").val()?0:$("#endDate").val());
		selectedManager = (!$('select[name=manager]').val()?0:$('select[name=manager]').val());
		selectedCountry = (!$('select[name=country]').val()?0:$('select[name=country]').val());
		
		var selectedReport = $('select[name=reports]').val();
		
		var opt = (null!=selectedReport?selectedReport[0]:0);	//set to '0' if listbox selection is not defined (eg when loading page for the first time)
	
// 		destinations of the ajax request
		var reportmap = {
			'0' : 'mywork',
			'1' : 'morethanforty',
			'2' : 'lessthanforty',
			'3' : 'noholidays',
			'4' : 'multipleprojects'
		};
		
		report = reportmap[opt];

		retrieveData(opt);

	};

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
// 					console.log("SUCCESS: ", data);
					JsonListF = data;
					var listItemsF = "";					
					for ( var i in data) {
						listItemsF += "<option value='" + data[i].id + "'>"+ data[i].holiday + "</option>";
					}
// 					console.log(listItemsF);
					$("#showHoliday").append(listItemsF);
					 if(listItemsF != "")
							document.getElementById('showHoliday').style.display = "inline";
						 else
							 document.getElementById('showHoliday').style.display = "none";
				},
	              error:function(request,status,error){
// 	              console.log(request.responseText);
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
		$("#showHoliday").hide();
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
// 				console.log("SUCCESS: ", data);	
				JsonCountry = data;
				var listItemsC;
	        for (var i in data){
	        	listItemsC+= "<option value='" + data[i].id + "'>" + data[i].country + "</option>";
	        }
	        $("#dropdownCountry").html(listItemsC);
	        $("#countryList").html(listItemsC);
	        $('.selectpicker').selectpicker('refresh');
			},
		});
	};
	
	function loadManagers(){
	    $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "managers.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {	
// 				console.log("SUCCESS ", this);	
				JsonCountry = data;
				var listItemsM;
	        for (var i in data){
	        	listItemsM+= "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
	        }
	        $("#managerList").html(listItemsM);
	        $('.selectpicker').selectpicker('refresh');
			},
		});
	};
	
	
// 	arg: 'index' -> array index corresponding to the selected report 
	function buildTable(index){
		
		showLoadingAnimation('reportTable_container');
		
		var headers = {
				'eid' : "Employee ID",
				'ename' : "Name",
				'country' : "Country",
				'manager' : "Manager",
				'week' : "Week",
				'holiday' : "Description",
				'h_dates' : "Dates",
				'h_hours' : "Required Hours",
				'ahours' : "Actual Hours",
				'thours' : "Total Hours"
			};
		
		var table = {};
		
		switch (index) {
	
			case '3': {
				table = $('#reportTable').DataTable(
						{
							data : jsonData[index],
							columns : [ {
								"className" : 'details-control',
								"orderable" : false,
								"data" : null,
								"defaultContent" : ''
							}, {
								"data" : "id",
								"title" : headers['eid']
							}, {
								"data" : "name",
								"title" : headers['ename']
							}, {
								"data" : "country",
								"title" : headers['country']
							}, {
								"data" : "manager",
								"title" : headers['manager']
							}, {
								"data" : "week",
								"title" : headers['week']
							}, {
								"data" : "holiday",
								"title" : headers['holiday']
							}, {
								"data" : "h_dates",
								"title" : headers['h_dates']
							}, {
								"data" : "h_hours",
								"title" : headers['h_hours']
							}, {
								"data" : "hours",
								"title" : headers['ahours']
							} ],
							"order" : [ [ 3, 'asc' ], [ 2, 'asc' ], [ 1, 'asc' ],
									[ 5, 'asc' ] ]
						});
			};break;
	
			default: {
	
				table = $('#reportTable').DataTable(
						{
							data : jsonData[index],
							columns : [ {
								"className" : 'details-control',
								"orderable" : false,
								"data" : null,
								"defaultContent" : ''
							}, {
								"data" : "id",
								"title" : headers['eid']
							}, {
								"data" : "name",
								"title" : headers['ename']
							}, {
								"data" : "country",
								"title" : headers['country']
							}, {
								"data" : "manager",
								"title" : headers['manager']
							}, {
								"data" : "week",
								"title" : headers['week']
							}, {
								"data" : "totalHours",
								"title" : headers['thours']
							} ],
							"order" : [ [ 3, 'asc' ], [ 2, 'asc' ], [ 1, 'asc' ],
									[ 5, 'asc' ] ]
						});
	
				// 			    event handler
				$('#reportTable tbody').off();
				$('#reportTable tbody').on('click', 'td.details-control',
						function() {
							var tr = $(this).closest('tr');
							var row = table.row(tr);
	
							if (row.child.isShown()) {
								row.child.hide();
								tr.removeClass('shown');
							} else {
								row.child(format(row.data())).show();
								tr.addClass('shown');
							}
						});
				// 		    	end event handler
	
			}	//end default case
		}	//end switch
	};

	function format(d) {
		var details = d.detail;
		var child = '<table cellpadding="5" cellspacing="0" border="0" style="font-size: 12px; width:100%; text-indent:75%;">';
		for ( var j in details) {
			child += '<tr><td>' + details[j].assignment + ': '
					+ details[j].hours + '</td></tr>';
		}
		return child + '</table>';
	};
	
	
// 	ajax calls for reports data, which will be finally stored as member elements in jsonData[] array
// 	arg: 'index' -> jsonData array index corresponding to the selected report 
	function retrieveData(index) {
	
		$.ajax({
			type : "POST",
			contentType : "application/json",
			dataType : 'json',
			data : {
				'manager' : selectedManager,
				'country' : selectedCountry,
				'dateStart' : selectedDateStart,
				'dateEnd' : selectedDateEnd
			},
			url : report + ".html",
			success : function(data) {

				jsonData[index] = data;
				buildTable(index);
			},
		});
		
	};
	
	
	function loadUsers() {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			dataType : 'json',
			url : "listUsers.html",
			timeout : 100000,
			success : function(data) {
				// 				console.log("SUCCESS: ", data);	
				JsonList = data;
				var listItems = "<option value=' '>Select user</option>";
				for ( var i in data) {
					listItems += "<option value='" + data[i].id + "'>"
							+ data[i].lastname + ", " + data[i].name
							+ "</option>";
				}
				$("#DLState").html(listItems);
				$("#DLState").find('[value=0]').hide();
			},
		});
	};

	$("#linkUser").click(
			function() {
				var username = $.trim($("#username").text());
				$("#modifRadio").hide();
				$("#deleteUser").hide();
				$("#delUser").prop('disabled', true);
				$("#DLState").prop('disabled', true);
				$(".modUser").prop('disabled', false);
				$(".changeUser").removeAttr("readonly");
				$('select[name=dlstate]').find(
						'option:contains(' + username + ')').attr("selected",
						true).change();
			});

	$("#modUser").click(
			function() {
				$("#DLState").prop('disabled', false);
				$(".modUser").prop('disabled', true);
				$(".modUser").removeAttr("readonly");
				$('select[name=dlstate]').find(
						'option:contains(' + username + ')').attr("selected",
						true).change();
			});

	$("#DLState").change(function() {
		clearForm();
		$(".modUser").prop('disabled', false);
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
				for ( var j in JrolList) {
					var rol = JrolList[j];
					document.getElementById(rol.rol).checked = true;
				}
			}
		}
	});

	function deleteUser() {
		var seleccion = ($('select[name=dlstate]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data : {
				'user' : seleccion
			},
			url : "deleteUser.html",
			success : function(data) {
				loadUsers();
			},
		});
	};
</script>

</body>
</html>
