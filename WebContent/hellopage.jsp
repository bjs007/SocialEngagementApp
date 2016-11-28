<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<html lang='en'>
<head>
    <meta charset="UTF-8" /> 
    <title>
       Engage App
    </title>
    <link rel="stylesheet" type="text/css" href="logincss.css" />
</head>
<body>

<div id="wrapper">

	<form name="login-form" class="login-form" action="dologin.jsp" method="post">
	
		<div class="header">
		<h1>Engage Login</h1>
		<span></span>
		</div>
		<div class="content">
		<input name="sUserName" type="text" class="input username" placeholder="Username" required/>
		<div class="user-icon"></div>
		<input name="sPwd" type="password" class="input password" placeholder="Password" required/>
		<div class="pass-icon"></div>	
                
		</div>

		<div class="footer">
		<input type="submit" name="submit" value="Login" class="button" />
		<input type="button" name="reg" value="Register" class="register" onClick="window.document.location.href='register.jsp'"/>
		</div>
	
	</form>

</div>
<div class="gradient"></div>


</body>
</html>