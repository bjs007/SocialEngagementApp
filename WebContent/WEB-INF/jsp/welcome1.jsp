<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
        .main{

            height: 24px;
width:100%;
        }
        .left{
            float: left;
            width: 300px;
            height: 23px;
            
			margin-left:10px;
        }
.right{
           margin-right: 10px;

            height: 23px;
            margin-left: 350px;
            
        }
		.eventmain{
		height:150px;
		width:100%;}
		.eventlefttop{
		float:left;
		width:50px;
		height:25px;
		background-color:white;
		margin-left:10px;}
		.eventmiddle{
		float:left;
		height:102px;
		width:98%;
		background-color:white;
		margin-left:10px;
		}
		.eventbuttom{
		float:left;
		height:35px;
		width:98%;
		background-color:white;
		margin-left:10px;
		}
		.eventrighttop{
		margin-right: 10px;
		height: 25px;
		margin-left: 60px;
		background-color: white;
		}

    </style>
</head>
<body>
<div class="main">
    <div class="left">
        <form action="selectbydate" method="get">
        	Date Filter: <input type="date" name="user_date"}"/><input type="submit" value="Filter"/>
        </form>
    </div>
    <div class="right">
    <center>
       <form action="" method="get">				
        	 Type Filter:
        	 <select name="events types">
        	 	<option value="empty"></option>
				<option value="events">Events</option>
				<option value="broadcast">Broadcast</option>
				
			</select>
			<input type="submit" value="Filter"/>
		</form>
		</center>
    </div>
</div>
<br/>
<center>Oops! Comment is not submitted. Please try again!</center>
</body>
</html>