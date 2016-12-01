<%@ page language="java" import="java.sql.*" errorPage="" %>
<%

    Connection conn = null;
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central","coms514user","password");
    //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CDT
    String userid=request.getParameter("inputusername");
    String pwd=request.getParameter("inputpassword");
    
    Statement st = conn.createStatement();
    ResultSet rs;
    ResultSet rs2;
    
    Statement st2 = conn.createStatement();
   //	String name=(String)session.getAttribute("name");
  
   	
   
   
    
    rs = st.executeQuery("select * from users where email='"+ userid +"' and password='"+ pwd +"'");
    if (rs.next()) {
        session.setAttribute("userid",userid);
        session.setAttribute("userpass",pwd);
        session.setAttribute("name",rs.getString("name"));
     	String email=(String)session.getAttribute("userid");
     	String name=(String)session.getAttribute("name");
     	  
     	
    	rs2=st2.executeQuery("select userid from users where name='"+name+"' and email='"+email+"'");
       	rs2.next();
       	String useractualid=rs2.getString(1);
        
        //rs2=st.executeQuery("select name from uss where uid='"+ userid +"' and upass='"+ pwd +"'");
        //String po="select pos from uss where uid='"+userid+"' and upass='"+pwd+"'";
        String n=rs.getString("name");
      //  String po=rs.getString("pos");
       //response.sendRedirect("http://localhost:8080/SocialEngagement/welcome");
       //response.sendRedirect("http://proj-514-02.cs.iastate.edu:8080/welcome");
        
        if(useractualid.equals("121")){
        	 response.sendRedirect("http://localhost:8080/SocialEngagement/welcomeadmin");
        }
        else{
        	 response.sendRedirect("http://localhost:8080/SocialEngagement/welcome");
        }
       
      
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
    }
    else {
        out.println("Invalid password <a href='loginpage.jsp'>try again</a>");
    }
%>