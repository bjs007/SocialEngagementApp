<!-- 
* <h1>Comments</h1>
* This code retrieves received parameters like broadcast ID and user ID when a user comments on a page. These parameters 
* are used to add records in the Comments Table in the database with other details like the timestamp.
* @author  Nikita Tiwari
* @version 1.0
* 
-->

<%@ page language="java" import="java.sql.*" errorPage="" %>
<%

    Connection conn = null;
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central","coms514user","password");
    //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CDT
    String bid=request.getParameter("brid");
    System.out.println(bid);
    int no = Integer.parseInt(bid);
    String uid=request.getParameter("uid");
    String commenter=request.getParameter("comment");
    Statement st = conn.createStatement();
    Statement st2 = conn.createStatement();
	String name=(String)session.getAttribute("name");
	String email=(String)session.getAttribute("userid");
	
    ResultSet rs2;
	
	rs2=st2.executeQuery("select userid from users where name='"+name+"' and email='"+email+"'");
	rs2.next();
	String userid=rs2.getString(1);
    String inquery="insert into comments(user_id,date_time,post_id,comment_string,module_type) values('"+userid+"',now(),'"+no+"','"+commenter+"',2)";
    int i=0;
    i=st.executeUpdate(inquery);
    if(i>0){
    	System.out.println("SUCCESS");
    }
    else
    	System.out.println("NOT CREATED");
    
    if(userid.equals("121")){
    	// response.sendRedirect("http://localhost:8080/SocialEngagement/broad");
    	 response.sendRedirect("http://proj-514-02.cs.iastate.edu:8080/SocialEngagement/broad");
    }
    else{
    	 //response.sendRedirect("http://localhost:8080/SocialEngagement/broaduser");
    	 response.sendRedirect("http://proj-514-02.cs.iastate.edu:8080/SocialEngagement/broaduser");
    }
   
   /* rs = st.executeQuery("select * from users where email='"+ userid +"' and password='"+ pwd +"'");
    if (rs.next()) {
        session.setAttribute("userid",userid);
        session.setAttribute("userpass",pwd);
        session.setAttribute("name",rs.getString("name"));
        //rs2=st.executeQuery("select name from uss where uid='"+ userid +"' and upass='"+ pwd +"'");
        //String po="select pos from uss where uid='"+userid+"' and upass='"+pwd+"'";
        String n=rs.getString("name");
      //  String po=rs.getString("pos");
       //response.sendRedirect("http://localhost:8080/SocialEngagement/welcome");
       //response.sendRedirect("http://proj-514-02.cs.iastate.edu:8080/welcome");
      response.sendRedirect("http://localhost:8080/SocialEngagement/welcome");*/
       //if(po.equals("Manager")){
           // response.sendRedirect("mghome.jsp");
            
            
        //}
        //else{
        	//out.println("NOPE");
            //response.sendRedirect("pt2.jsp");
        //}
        //session.setAttribute("userid","inputusername");
       
        //out.println("welcome " + userid);
        //out.println("<a href='logout.jsp'>Log out</a>");
        //response.sendRedirect("pt2.jsp");
   
    //else {
      //  out.println("Invalid password <a href='loginpage.jsp'>try again</a>");
    //}
%>