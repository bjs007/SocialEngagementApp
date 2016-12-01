<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="loginstyle"/>

<html lang='en'>
<head>
    <title>
       Engage App
    </title>
    <link rel="stylesheet" type="text/css" href="css/logincss.css">
</head>
<body>

<div id="wrapper">

	<form name="login-form" class="login-form" action="dologin.jsp" method="post">
	
		<div class="header">
		<h1>Engage Login</h1>
		<span></span>
		</div>
		<div class="content">
		<input name="inputusername" type="text" class="input username"/><br><br>
		<input name="inputpassword" type="password" class="input password"/>
			
                
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