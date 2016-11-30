package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.models.Home;

public class HomeJdbcDao {
	@Value("${eventsJdbcString}")
	private String jdbcString;

	@Value("${dbUserName}")
	private String dbUserName;

	@Value("${dbPassword}")
	private String dbPassword;

	Logger logger= Logger.getLogger(EventsJdbcDao.class);
	
	public ArrayList<Home> getDashboardInfo()
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Home> homeList=null;
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
					.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					//.getConnection(jdbcString,dbUserName,dbPassword);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			try {
				CallableStatement cs = connection.prepareCall("{call getHomeInfo()}");
				boolean flag = cs.execute();
				ResultSet rs = null;
				homeList = new ArrayList<Home>();
				while(flag){
					rs = cs.getResultSet();
					int i = 0;
					while(rs.next()){
						Home home = new Home();
						
						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						home.setCreate_date_time(rs.getString("create_date_time"));
						home.setUser_id(rs.getInt("user_id"));
						
						homeList.add(home);
						i++;
					}
					flag = cs.getMoreResults();
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
		if(homeList.size()>0)
			return homeList;
		else
			return null;
	}
	
	public ArrayList<Home> browsebyDate(String date) throws ParseException{
		ArrayList<Home> ale = null;
		Statement statement = null;
	
		System.out.println("-------- Events JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Where is your MySQL JDBC Driver?");
			logger.error(e.getStackTrace());
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					//.getConnection(jdbcString,dbUserName,dbPassword);
			statement = connection.createStatement();
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		logger.debug("You made it, take control your database now!");

		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datetemp = null;
			
			datetemp = format.parse(date);
			CallableStatement cs = connection.prepareCall("{call getHomeInfo()}");
			boolean flag = cs.execute();
			ResultSet rs = null;
			ale = new ArrayList<Home>();
			while(flag){
				rs = cs.getResultSet();
				int i = 0;
				while(rs.next()){
					Home home = new Home();
					String str = rs.getString("create_date_time");
					if(str.equals(date)){
						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						//home.setComment_id(rs.getInt("comment_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						home.setCreate_date_time(rs.getString("create_date_time"));
						home.setUser_id(rs.getInt("user_id"));
						ale.add(home);
					}
					i++;
				}
				flag = cs.getMoreResults();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ale;
	}
	
	public ArrayList<Home> browsebyType(String date) throws ParseException{
		ArrayList<Home> ale = null;
		Statement statement = null;
	
		System.out.println("-------- Events JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Where is your MySQL JDBC Driver?");
			logger.error(e.getStackTrace());
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					//.getConnection(jdbcString,dbUserName,dbPassword);
			statement = connection.createStatement();
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		logger.debug("You made it, take control your database now!");

		try {
			CallableStatement cs = connection.prepareCall("{call getHomeInfo()}");
			boolean flag = cs.execute();
			ResultSet rs = null;
			ale = new ArrayList<Home>();
			while(flag){
				rs = cs.getResultSet();
				int i = 0;
				while(rs.next()){
					Home home = new Home();
					int num = rs.getInt("entry_type");
					if(date.equals("events") && num == 1){
						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						//home.setComment_id(rs.getInt("comment_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						home.setCreate_date_time(rs.getString("create_date_time"));
						home.setUser_id(rs.getInt("user_id"));
						ale.add(home);
					}
					else if(date.equals("broadcast") && num == 2){
						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						//home.setComment_id(rs.getInt("comment_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						home.setCreate_date_time(rs.getString("create_date_time"));
						home.setUser_id(rs.getInt("user_id"));
						ale.add(home);
					}
					else if(date.equals("discuss") && num == 3){
						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						//home.setComment_id(rs.getInt("comment_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						home.setCreate_date_time(rs.getString("create_date_time"));
						home.setUser_id(rs.getInt("user_id"));
						ale.add(home);
					}
					i++;
				}
				flag = cs.getMoreResults();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ale;
	}
	
	public boolean commentSubmit(String[] a, String comment) throws ParseException{
		boolean f = false;
		Statement statement = null;
	
		System.out.println("-------- Events JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Where is your MySQL JDBC Driver?");
			logger.error(e.getStackTrace());
			return false;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/social","root","liulei");
					//.getConnection(jdbcString,dbUserName,dbPassword);
			statement = connection.createStatement();
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return false;
		}

		logger.debug("You made it, take control your database now!");

		try {
			Statement stmt = connection.createStatement();
			int user_id = Integer.parseInt(a[7]);
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
			String comment_date_time = sdf.format(new Date());
			int post_id = Integer.parseInt(a[3]);
			String comments = comment;
			int event_type = Integer.parseInt(a[2]);
			String sql = "insert into comments(user_id,comment_date_time,post_id,comments,event_type) values("+user_id+",'"+comment_date_time+"',"+post_id+",'"+comments+"',"+event_type+")";
			int result = stmt.executeUpdate(sql);
			if(result > 0){
				f = true;
			}
			else{
				f = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
