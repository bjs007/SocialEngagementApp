package com.dao;
/**
* @author Lei Liu
*/
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
import java.util.Collections;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.models.Home;

/**
* This is the class for jdbc, which is used for fetching data from database
*/
public class HomeJdbcDao {
	@Value("${eventsJdbcString}")
	private String jdbcString;

	@Value("${dbUserName}")
	private String dbUserName;

	@Value("${dbPassword}")
	private String dbPassword;

	Logger logger= Logger.getLogger(HomeJdbcDao.class);

	/**
	*Get events and broadcasts from the database by executing store procedure in the database
	*/
	public ArrayList<Home> getDashboardInfo()
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Home> homeList=new ArrayList<Home>();
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
					//.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					.getConnection(jdbcString,dbUserName,dbPassword);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}


		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			try {

				CallableStatement cs = connection.prepareCall("{call getHomeInfo()}");
				ResultSet rs = cs.executeQuery();
				//homeList = new ArrayList<Home>();
					int i = 0;
					while(rs.next()){
						Home home = new Home();

						home.setEntry_id(rs.getInt("entry_id"));
						home.setEntry_desc(rs.getString("entry_desc"));
						home.setEntry_type(rs.getInt("entry_type"));
						home.setPost_id(rs.getInt("post_id"));
						home.setActivity_desc(rs.getString("activity_desc"));
						SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
						String str = rs.getString("create_date_time");
						if(str.charAt(0) > '9' || str.charAt(0) < '0'){
							Date cfg= parser.parse(str);
							parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							str = parser.format(cfg);
						}

						home.setCreate_date_time(str);
						home.setUser_id(rs.getInt("user_id"));

						homeList.add(home);
						i++;
					}

				cs.close();
				rs.close();

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
		Collections.sort(homeList, Collections.reverseOrder());
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(homeList.size()>0)
			return homeList;
		else
			return null;
	}

	/**
	*Get events and broadcasts from the database which match the date
	*/
	public ArrayList<Home> browsebyDate(String date) throws ParseException{
		ArrayList<Home> ale = new ArrayList<Home>();
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
					//.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					.getConnection(jdbcString,dbUserName,dbPassword);
			statement = connection.createStatement();
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		logger.debug("You made it, take control your database now!");

		try {
			System.out.println(date);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datetemp = null;

			datetemp = format.parse(date);

			CallableStatement cs = connection.prepareCall("{call getInfoByDateBroadcast()}");
			ResultSet rs = cs.executeQuery();
				int i = 0;
				while(rs.next()){
					Home home = new Home();
					String str = rs.getString("create_date_time").split(" ")[0];
					System.out.println("parsed:"+str);
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

			cs = connection.prepareCall("{call getInfoByDateEvent()}");
				rs = cs.executeQuery();
			 i = 0;
				while(rs.next()){
					Home home = new Home();
					String str = rs.getString("create_date_time");

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date cfg= parser.parse(str);
					parser = new SimpleDateFormat("yyyy-MM-dd");
					str = parser.format(cfg);
					System.out.println(str);
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

	/**
	*get Events or Broadcasts which match the type which is specified by front page request
	*/
	public ArrayList<Home> browsebyType(String date) throws ParseException{
		ArrayList<Home> ale = new ArrayList<Home>();
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
					//.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					.getConnection(jdbcString,dbUserName,dbPassword);
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
			ResultSet rs = null;
			rs = cs.executeQuery();
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
					i++;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(ale, Collections.reverseOrder());
		return ale;
	}

	/**
	*submit the comment
	*insert the comment into table of comment
	*/
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
					//.getConnection("jdbc:mysql://proj-514-02.cs.iastate.edu:3306/socialDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","coms514user","password");
					.getConnection(jdbcString,dbUserName,dbPassword);
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
			System.out.println(a[0] + " | "+ a[1] +" | "+a[2] +" | "+a[3]+" | "+a[4] + " | "+a[5]+" | "+a[6]);
			int user_id = Integer.parseInt(a[6]);
			String comment_date_time = a[5];
			int post_id = Integer.parseInt(a[3]);
			String comments = comment;
			int event_type = Integer.parseInt(a[2]);
			String sql = "insert into comments(user_id,date_time,post_id,comment_string,module_type) values("+user_id+",'"+comment_date_time+"',"+post_id+",'"+comments+"',"+event_type+")";
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
