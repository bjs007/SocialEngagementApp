package com.dao;
/**
 * @author Prateek Gupta
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import com.models.Discussion;



public class DiscussionDao {
	

		public static ArrayList<Discussion> disc = new ArrayList<Discussion>();

		
		@Value("${eventsJdbcString}")
		private String jdbcString;

		@Value("${dbUserName}")
		private String dbUserName;

		@Value("${dbPassword}")
		private String dbPassword;


		 
		/*
		    This method is by the controller to save the discussion response by the Admin Users in Discussion Table
		*/
		public String savediscussionsAdmin(Discussion discussion)
		{

			System.out.println("-------- discussions JDBC Connection Testing ------------");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager.getConnection(jdbcString,dbUserName,dbPassword);
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				

				try {
									
					String updatediscussionsSQL = "update Discussion set adminMessage = ?,admintime =?,adminID = ?  where discussionID ="+discussion.getDiscussionID();
					
					PreparedStatement pstmt = connection.prepareStatement(updatediscussionsSQL);

					//discussion description
					if(discussion.getAdminMessage()!=null)
						pstmt.setString(1,discussion.getAdminMessage());
					
					if(discussion.getAdmintime()!=null)
						pstmt.setString(2,discussion.getAdmintime().toString());
					
					//resources-needed
					if(discussion.getAdminID()!=null)
						pstmt.setInt(3,discussion.getAdminID());
					;

					boolean updated = pstmt.execute();

					System.out.println("Updated >> "+updated);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Failed to make connection!");
			} 
			
			
			for( Discussion d : disc){
				if(d.getDiscussionID() == discussion.getDiscussionID()){
				
					System.out.println("final object :"+d);
				}
			}
			
			System.out.println("saved discussion Admin");
			return "Success"; 	
		}
		
		
		
		
		/*
		    This method is by the controller to save the new discussion made by Normal Users in Discussion Table
		*/

		public String savediscussionsUser(Discussion discussion)
		{

			System.out.println("-------- discussions JDBC Connection Testing ------------");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager.getConnection(jdbcString,dbUserName,dbPassword);
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				
				
				try {
					String insertdiscussionsSQL = "Insert into Discussion (discussionID,userID,message,time) values(?,?,?,?)";
					
					PreparedStatement pstmt = connection.prepareStatement(insertdiscussionsSQL);

					//discussion description
					if(discussion.getDiscussionID()!=null)
						pstmt.setInt(1,discussion.getDiscussionID());
					else
						pstmt.setInt(1,0);

					//user-id
					if(discussion.getUserID()!=null)
						pstmt.setInt(2,discussion.getUserID());
					else
						pstmt.setInt(2,0);

					//resources-needed
					if(discussion.getMessage()!=null)
						pstmt.setString(3,discussion.getMessage());
					else
						pstmt.setString(3,null);
					
					if(discussion.getTime()!=null)
						pstmt.setString(4,discussion.getTime().toString());
					
					boolean updated = pstmt.execute();

					System.out.println("Updated >> "+updated);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Failed to make connection!");
			} 
			
			return "Success"; 
			
		}
        /*
		    This method is by the controller to fetch all the discussion in the Discussion Table
		*/
		public ArrayList<Discussion> getdiscussionsDataFromDb()
		{
			
			System.out.println("-------- MySQL JDBC Connection Testing ------------");
			ArrayList<Discussion> discussionList=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						//.getConnection("jdbc:mysql://localhost:3307/Library","root","password");
						.getConnection(jdbcString,dbUserName,dbPassword);
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");

				try {
					Statement statement=connection.createStatement();
					StringBuilder selectTableSQL = new StringBuilder("select * from socialDb.Discussion");

					ResultSet rs = statement.executeQuery(selectTableSQL.toString());
					discussionList=new ArrayList<Discussion>();
					while (rs.next()) {

						Discussion discussion= new Discussion();
						Integer discussionid= rs.getInt("DiscussionID");
						discussion.setDiscussionID(discussionid);
						
						Integer user_id=rs.getInt("UserId");
						discussion.setUserID(user_id);
						
						SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
						System.out.println(rs.getString("Time"));
						Date created_date_time= parser.parse(rs.getString("Time"));
						
						discussion.setTime(created_date_time);
						String message=rs.getString("Message");
						
						discussion.setMessage(message);
						Integer adminId =rs.getInt("adminID");
						if(adminId == 0){
							adminId = null;
						}
						
						discussion.setAdminID(adminId);
						String adminMessage = rs.getString("adminMessage");
						
						discussion.setAdminMessage(adminMessage);
						System.out.println(rs.getString("admintime"));
						
						String time_admin = rs.getString("admintime");
						if(time_admin == null){
							discussion.setAdmintime(null);
							
						}else{
							Date created_date_time_admin= parser.parse(rs.getString("admintime"));
							discussion.setAdmintime(created_date_time_admin);
						}
						discussionList.add(discussion);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Failed to make connection!");
			}
		
			return discussionList;
		}

		
		/*
		    This method is by the controller to check the Login user status and rights 
		*/
		public boolean isAdminUser(Integer userId)
		{
			boolean result=false;
			
			System.out.println("-------- MySQL JDBC Connection Testing Prateek ------------");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return result;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						//.getConnection("jdbc:mysql://localhost:3307/Library","root","password");
						.getConnection(jdbcString,dbUserName,dbPassword);
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return result;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");

				try {
					String selectTableSQL = new String("SELECT * from users where userid= ? ");
					PreparedStatement statement=connection.prepareStatement(selectTableSQL);
					if(userId!=null)
						statement.setInt(1, userId);

					System.out.println(selectTableSQL.toString());

					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						String userType=rs.getString("usertype");
						if(userType.equalsIgnoreCase("admin"))
							return true;
						break;
					}
					return result;
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}    
			return result; 
		}
		
		
}
