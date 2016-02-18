<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">

</head>
<body style="background-color: #E6E6FA">
<div class="container">
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">My Hours Plan Reportes</h1>
            <div class="account-wall">
                <form class="form-signin" name="loginForm" action="<c:url value='/j_spring_security_check' />" method='POST'>
                <input type="text" class="form-control" placeholder="Usuario" name='username' required autofocus>
                <input type="password" class="form-control" placeholder="Contrase�a" name='password' required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Entrar</button>
                <label class="checkbox pull-left">
                    <input type="checkbox" value="recordarme">
                    Recordarme
                </label>
<!--                <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span> -->
         			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />   
                </form>
            </div>
<!--            <a href="#" class="text-center new-account">Create an account </a> -->
        </div>
    </div>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>		
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
</body>
</html>