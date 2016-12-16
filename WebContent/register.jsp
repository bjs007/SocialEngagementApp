<!-- 
* <h1>Registration Page</h1>
* This page contains a form for user to enter the profile information which is then added to the database.
* The user account is create in the Users Table in SQL Db, and then routed to login page to continue application use.
* @author  Nikita Tiwari
* @version 1.0
* 
-->
<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="css/regcss.css" />
</head>
<body>
      <div id="wrapper">  
  <form name="reg-form" class="reg-form" action="regis.jsp" method="post">
	
		<div class="header">
		<h1>Sign Up!</h1>
		<span></span>
		</div>
	
		<div class="content">
                    
		<input name="UserName" type="text" class="input username" placeholder="Full Name" required/>
		<br><h2><input type="radio" name="gender" value="male" checked> Male
		<input type="radio" name="gender" value="female" checked> Female</h2>
		<input name="dob" type="text" class="input username" placeholder="Date of Birth" required/>
		<input name="phone" type="text" class="input username" placeholder="Phone Number" required/>
		<input name="email" type="text" class="input username" placeholder="Email Address" required/>
		<input name="addr" type="text" class="input username" placeholder="Home Address" required/>
		<input name="emergname" type="text" class="input username" placeholder="Emergency Contact Name" required/>
		<input name="emergphone" type="text" class="input username" placeholder="Emergency Contact Phone No" required/>
		<input name="sPwd" type="password" class="input password" placeholder="Choose Password" required/>
		
                
		</div>

		<div class="footer">
		<input type="submit" name="submit" value="Create Account" class="button" />
		<br><input type="button" name="reg" value=" Back to Login" class="register" onClick="window.document.location.href='loginpage.jsp'"/>
		</div>
	
	</form>
	</div>
    </body>
</html>