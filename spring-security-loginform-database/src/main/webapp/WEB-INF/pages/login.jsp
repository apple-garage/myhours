<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ibm.languages.text" />

<!DOCTYPE html>
<html lang="${language}">
<head>
<title>Login Page</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">

</head>
<body style="background-color: #c4c4fc">
<div class="container">
        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>
            </select>
        </form>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title"><fmt:message key="login.title"/></h1>
            <div class="account-wall">
                <form class="form-signin" name="loginForm" action="<c:url value='/login' />" method='POST'>
                <input type="text" class="form-control" placeholder="<fmt:message key="login.user" />" name='username' required autofocus>
                <input type="password" class="form-control" placeholder="<fmt:message key="login.password" />" name='password' required>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.confirm" /></button>
         			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />   
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>		
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript">

$('#loginForm').bootstrapValidator({
	 message: 'This value is not valid',
	 feedbackIcons: {
		 valid: 'glyphicon glyphicon-ok',
		 invalid: 'glyphicon glyphicon-remove',
		 validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 usuario: {
			 validators: {
				 notEmpty: {
					 message: 'User is required'
				 }
			 }
		 },
		 password: {
			 validators: {
				 notEmpty: {
					 message: 'Password is required'
				 }
			 }
		 }
	 }
});

</script>

</body>
</html>