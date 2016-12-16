<!-- 
* <h1>Broadcast Home</h1>
* This code is to display all the Broadcasts for any user. The data is fetched from the 
* database and displayed. The code also fetches records for each broadcast id from the Comments Table in SQL Db
* and displays it in order which it was posted.  
* 
*
* @author  Nikita Tiwari
* @version 1.0
* 
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,java.text.SimpleDateFormat"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/brcss.css">
</head>
<body>

<p id="title" style="color:#FDFEFE"> Broadcasts Home</p>
  /**
   * This script creates a JDBC Connection to the database, and extracts records from Broadcast Table.
   * It also extracts records from the Comments Table to display each Broadcast posts's comments
   *
   */

<%System.out.println(session.getAttribute("name"));
Connection conn = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central","coms514user","password");

Statement st = conn.createStatement();
ResultSet rs;
Statement st2 = conn.createStatement();
ResultSet rs2;
Statement st3 = conn.createStatement();
ResultSet rs3;
try{
    rs=st.executeQuery("select title,posttime,postdesc,broadcastid from broadcasts order by posttime desc limit 7");
 
    	 while(rs.next()){ 
    		 String cbid=rs.getString(4);
    		 //System.out.println(cbid);
    		 rs2=st2.executeQuery("select user_id,date_time,comment_string from comments where module_type=2 and post_id='"+cbid+"'");
    		
    		
    		 
    	  String datehere=rs.getString(2);
    	  SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	   
    	  java.util.Date result=formater.parse(datehere);
    	// SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	//String dates=formatter.format(result);%>
	
    	 <div id="brcontainer"  style=" margin:0 auto;" >
    <p id="title"> <%= rs.getString(1) %></p>
	
	<p id="timetext"><%out.print(result); %></p>
	
	<p id="descript"><%= rs.getString(3) %></p>
</div><br>
 
<div id="commcontainer" style="width:450px; height:100px auto; margin:0 auto;">
<p id="commenttitle"> Comments</p>
</div>
<%while(rs2.next()){
    			 String value=rs2.getString(3);
    			 String timev=rs2.getString(2);
    			 String userids=rs2.getString(1);
    			  rs3=st3.executeQuery("select name from users where userid='"+userids+"'");
    			  rs3.next();
    			  String name=rs3.getString(1);
    			 // System.out.println(rs3.getString(1));%>
  <div id="commcontainer" style="width:450px; height:100px auto; margin:0 auto;">
<p id="commenttitle"> <%out.print(value); %></p>
<p id="timetext"><%out.print(name); %> wrote at <%out.print(timev); %>
</div>
<%}%>
<div style="width:450px; height:100px auto; margin:0 auto;">
<form action="postcomment.jsp?brid=<%out.print(cbid);%>&uid=121" method="post">
<textarea name="comment" style="width:450px; height:50px;"></textarea>
<input type="submit" value="Post"></input>
</form>
</div>

 
<br><br>
    	
    <% } 
}
catch(Exception e)
{
    out.print("Error. Try again");
    e.printStackTrace();
    
}
%>
</body>
</html>
