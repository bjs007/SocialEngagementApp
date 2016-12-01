<%@ page import="java.util.ArrayList,com.models.*" language="java" contentType="text/html; charset=UTF-8"
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
            background-color: white;
			margin-left:10px;
        }
		.right{
           margin-right: 10px;

            height: 23px;
            margin-left: 310px;
            background-color: white;
        }
        body{
            font: 16px/1.5 "Mircisoft Yahei";
        }
        img{
            display: block;
        }
        a{
            text-decoration: none;
            text-underline: none;

        }
        .demo{
            width: 600px;
            /*height: 400px;*/
            border: 1px solid #ddd;
            margin:0 auto;
        }
        .title{
            overflow: hidden;
        }
        .title img{
            float: left;
            margin-right: 10px;
        }

        .title .name{
            font-weight: bold;
        }
        .title .intro{
            color:#999;
        }
        .content h1{
            color: blue;
        }
        .content a{
            display: inline-block;
            height: 20px;
            border: 1px solid #ddd;
            background-color: #ddd;
            color: #000;
        }
        .comment{
            overflow: hidden;
            margin-top: 20px;
        }
        .comment img{
            float: left;
        }
</style>
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
var begin = 2;
function ab(){
	var mydocument = document;
    var mylist = mydocument.getElementById("tt");
    var docFragments = mydocument.createDocumentFragment();
    var sum = mydocument.getElementById("sum").value;
    if((sum - begin) > 1 ){
    	for (var i = begin; i < begin + 2; ++i) {
            var liItem = mydocument.createElement("div");
            var str = mydocument.getElementById(begin).value;
            var strarray = str.split("&&");
            if(strarray[2] == "1"){
            	liItem.innerHTML = "<div class=\"demo\"><div class=\"title\"><img src=\"images/1.png\" alt=\"\" class=\"head\"/><p class=\"name\">"+strarray[7]+"</p><p class=\"intro\">"+strarray[6]+"</p></div><div class=\"content\"><p>"+strarray[5]+"</p></div><div style=\"text-align:right\"><a href=\"joinin?eid="+strarray[3]+"&typeid="+strarray[2]+"\">->Join in!<-</a></div><div class=\"comment\" align=\"center\"><form method=\"get\" action=\"${pageContext.request.contextPath}/comsubmit\"><input type=\"hidden\" name = \"info\" value = \""+str+"\"><textarea id=\"\" name = \"infoarea\" style=\"width:300px;height:80px;\"></textarea><input type=\"submit\" value=\"SUBMIT COMMENTS\"></form></div></div><br>" ;            }
            else if(strarray[2] == "2"){
            	liItem.innerHTML = "<div class=\"demo\"><div class=\"title\"><img src=\"images/2.png\" alt=\"\" class=\"head\"/><p class=\"name\">"+strarray[7]+"</p><p class=\"intro\">"+strarray[6]+"</p></div><div class=\"content\"><p>"+strarray[5]+"</p></div><div style=\"text-align:right\"><a href=\"joinin?eid="+strarray[3]+"&typeid="+strarray[2]+"\">->Join in!<-</a></div><div class=\"comment\" align=\"center\"><form method=\"get\" action=\"${pageContext.request.contextPath}/comsubmit\"><input type=\"hidden\" name = \"info\" value = \""+str+"\"><textarea id=\"\" name = \"infoarea\" style=\"width:300px;height:80px;\"></textarea><input type=\"submit\" value=\"SUBMIT COMMENTS\"></form></div></div><br>" ;            }
            docFragments.appendChild(liItem);
        }
    	begin += 2;
    }
    else if((sum - begin) == 1){
        var liItem = mydocument.createElement("div");
        var str = mydocument.getElementById(begin).value;
        var strarray = str.split("&&");
        if(strarray[2] == "1"){
        	liItem.innerHTML = "<div class=\"demo\"><div class=\"title\"><img src=\"images/1.png\" alt=\"\" class=\"head\"/><p class=\"name\">"+strarray[7]+"</p><p class=\"intro\">"+strarray[6]+"</p></div><div class=\"content\"><p>"+strarray[5]+"</p></div><div style=\"text-align:right\"><a href=\"joinin?eid="+strarray[3]+"&typeid="+strarray[2]+"\">->Join in!<-</a></div><div class=\"comment\" align=\"center\"><form method=\"get\" action=\"${pageContext.request.contextPath}/comsubmit\"><input type=\"hidden\" name = \"info\" value = \""+str+"\"><textarea id=\"\" name = \"infoarea\" style=\"width:300px;height:80px;\"></textarea><input type=\"submit\" value=\"SUBMIT COMMENTS\"></form></div></div><br>" ;            }
        else if(strarray[2] == "2"){
        	liItem.innerHTML = "<div class=\"demo\"><div class=\"title\"><img src=\"images/2.png\" alt=\"\" class=\"head\"/><p class=\"name\">"+strarray[7]+"</p><p class=\"intro\">"+strarray[6]+"</p></div><div class=\"content\"><p>"+strarray[5]+"</p></div><div style=\"text-align:right\"><a href=\"joinin?eid="+strarray[3]+"&typeid="+strarray[2]+"\">->Join in!<-</a></div><div class=\"comment\" align=\"center\"><form method=\"get\" action=\"${pageContext.request.contextPath}/comsubmit\"><input type=\"hidden\" name = \"info\" value = \""+str+"\"><textarea id=\"\" name = \"infoarea\" style=\"width:300px;height:80px;\"></textarea><input type=\"submit\" value=\"SUBMIT COMMENTS\"></form></div></div><br>" ;            }
        docFragments.appendChild(liItem);
        begin += 1;
    }
    else{
    	alert("No more records");
    }
    mylist.appendChild(docFragments);
}
</script>
</head>
<body>
<div class="main">
    <div class="left">
        <form action="${pageContext.request.contextPath}/selectbydate" method="get">
        	<center>Date Filter: <input type="date" name="user_date"/><input type="submit" value="Date Filter"/></center>
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
				<option value="discuss">Discuss</option>
			</select>
			<input type="submit" value="Type Filter"/>
		</form>
		</center>
    </div>
</div>
<br/>
<div id="tt">
	<%
	ArrayList<Home> arraylist = (ArrayList<Home>)request.getAttribute("list");
	if(arraylist.size() >= 2)
	for(int i = 0 ; i < 2; i++){
		Home home = arraylist.get(i);
		%>
	<div class="demo">
		<div class="title">
		<%
		if(home.getEntry_type() == 1){
		%>
		<img src="images/1.png" alt="" class="head"/>
		<%
		}
		else if(home.getEntry_type() == 2){
		%>
		<img src="images/2.png" alt="" class="head"/>
		<%
		}
		%>
		<p class="name">User: <%=home.getUser_id() %></p>
		<p class="intro">Post Date: <%=home.getCreate_date_time() %></p>
	</div>
	<div class="content">
		<p><%=home.getActivity_desc() %></p>
	</div>
		<div style="text-align:right"><a href="joinin?eid=<%=home.getPost_id() %>&typeid=<%=home.getEntry_type()%>">->Join in!<-</a></div>
	
	<div class="comment" align="center">
		<form method="get" action="${pageContext.request.contextPath}/comsubmit">
			<input type="hidden" name = "info" value = "<%=home.toString() %>">
			<textarea id="" name = "infoarea" style="width:300px;height:80px;"></textarea>
	    	<input type="submit" value="SUBMIT COMMENTS">
		</form>
	</div>
	</div>
	<br>
<%
	}
	else if(arraylist.size() == 1){
		Home home = arraylist.get(0);
	%>
<div class="demo">
	<div class="title">
	<%
	if(home.getEntry_type() == 1){
	%>
	<img src="images/1.png" alt="" class="head"/>
	<%
	}
	else if(home.getEntry_type() == 2){
	%>
	<img src="images/2.png" alt="" class="head"/>
	<%
	}
	%>
	<p class="name">User: <%=home.getUser_id() %></p>
	<p class="intro">Post Date: <%=home.getCreate_date_time() %></p>
</div>
<div class="content">
	<p><%=home.getActivity_desc() %></p>
</div>
	<div style="text-align:right"><a href="joinin?eid=<%=home.getPost_id() %>&typeid=<%=home.getEntry_type()%>">->Join in!<-</a></div>

<div class="comment" align="center">
	<form method="get" action="${pageContext.request.contextPath}/comsubmit">
		<input type="hidden" name = "info" value = "<%=home.toString() %>">
		<textarea id=""  name = "infoarea" style="width:300px;height:80px;"></textarea>
    	<input type="submit" value="SUBMIT COMMENTS">
	</form>
</div>
</div>
<br>
<%
	}
	if(arraylist.size() > 2)
	for(int i = 2; i < arraylist.size(); i++){
%>
	<input type="hidden" id = "<%=i %>" value = "<%=arraylist.get(i).toString() %>">
<%
	}
%>
	<input type="hidden" id ="sum" value="<%=arraylist.size() %>">
</div>
<div align="center">
<input type="image" src="images/4.png" onclick="ab()">
</div>

</body>
</html>