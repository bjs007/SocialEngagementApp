
<%@ page language="java" import="java.sql.*" errorPage="" %>
<%@ page import ="java.sql.*" %>
<%
    String name = request.getParameter("UserName");    
    String pwd = request.getParameter("sPwd");
    String addr = request.getParameter("addr");
    String email = request.getParameter("email");
    String dob= request.getParameter("dob");
    String phone= request.getParameter("phone");
    String ename= request.getParameter("emergname");
    String ephone= request.getParameter("emergphone");
    String gender= request.getParameter("gender");
   
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection conn1 = null;
    conn1 = DriverManager.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central","coms514user","password");
    Statement st = conn1.createStatement();
    ResultSet rs;
    try{
        rs=st.executeQuery("select * from users where email='"+email+"'");
        if(rs.next()){
            request.setAttribute("uerror","Username exists. Try again");
            response.sendRedirect("register.jsp");
        }
         else{
    int i;
    i= st.executeUpdate("insert into users(usertype,name,dob,gender,phone,email,address,emergencycontact_name,emergencycontact_phone,password) values('user','"+name+"','"+dob+"','"+gender+"','"+phone+"','"+email+"','"+addr+"','"+ename+"','"+ephone+"','"+pwd+"')");
    if (i>0) {
        //session.setAttribute("userid", user);
        //response.sendRedirect("+user+.jsp");
        //out.print("Registration Successful");
        response.sendRedirect("http://localhost:8080/SocialEngagement/hello");
    } else {
        //response.sendRedirect("index.jsp");
        out.print("Error. Please Enter details again");
    }
        }
    }
    catch(Exception e)
    {
        out.print("Error. Try again");
        e.printStackTrace();
        
    }
    
%>