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
<%

Connection conn = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn = DriverManager.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central","coms514user","password");

Statement st = conn.createStatement();
ResultSet rs;
try{
    rs=st.executeQuery("select title,posttime,postdesc,broadcastid from broadcasts order by posttime desc limit 7");
 
    	 while(rs.next()){ 
    	  String datehere=rs.getString(2);
    	  SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	   
    	  java.util.Date result=formater.parse(datehere);
    	// SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	//String dates=formatter.format(result);%>
    	 <div id="brcontainer"  style=" margin:0 auto;" >
    <p id="title"> <%= rs.getString(1) %></p>
	
	<p id="timetext"><%out.print(result); %></p>
	
	<p id="descript"><%= rs.getString(3) %></p>
	<br>
	<p id="linker"> <a href="deletebroad.jsp?id=<%=rs.getString(4) %>">Delete</a>
</div>
<br><br><br>
    	
    <% } 
}
catch(Exception e)
{
    out.print("Error. Try again");
    
}
%>
</body>
</html>
