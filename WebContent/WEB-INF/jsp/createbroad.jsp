<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="css/logincss.css" />
</head>
<body>
      <div id="wrapper" >  
  <form name="broad-form" class="login-form" action="addbroad.jsp" method="post">
	
		<div class="header">
		<h1>Create a Broadcast Post</h1>
		<span></span>
		</div>
	
		<div class="content">
         <label style="font-family: 'Georgia', serif;
	font-weight: 300;
	font-size: 20px;
	line-height:34px;
	color: #000000;
	text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
	margin-bottom: 10px;">Title</label>           
		<input name="title" type="text" class="input username" style="width:250px" placeholder="Title" required/><br><br>
		<label style="font-family: 'Georgia', serif;
	font-weight: 300;
	font-size: 20px;
	line-height:34px;
	color: #000000;
	text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
	margin-bottom: 10px;">Description</label>
		<textarea name="description" style="width: 250px; height:200px;
	padding: 16px 25px;
	
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
	font-weight: 400;
	font-size: 14px;
	color: #9d9e9e;
	text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
	
	background: #fff;
	border: 1px solid #fff;
	border-radius: 5px; required></textarea>
		
		
                
		</div>

		<div class="footer">
		<input type="submit" name="submit" value="Create Broadcast" class="button" />
		</div>
	
	</form>
	</div>
    </body>
</html>