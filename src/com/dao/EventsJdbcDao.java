package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.models.Event;

public class EventsJdbcDao {

	@Value("${eventsJdbcString}")
	private String jdbcString;

	@Value("${dbUserName}")
	private String dbUserName;

	@Value("${dbPassword}")
	private String dbPassword;

	Logger logger= Logger.getLogger(EventsJdbcDao.class);

	public String saveEvents(Event event)
	{

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
			connection = DriverManager.getConnection(jdbcString,dbUserName,dbPassword);
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			logger.debug("You made it, take control your database now!");

			try {
				String insertEventsSQL = "Insert into events (event_desc,created_date_time,user_id,resources_needed,place,event_date_time,is_archived,is_resources_satisfied) values(?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(insertEventsSQL);

				//event description
				if(event.getEvent_desc()!=null && !event.getEvent_desc().isEmpty())
					pstmt.setString(1,event.getEvent_desc());
				else
					pstmt.setString(1,null);

				//created date
				pstmt.setString(2,new Date().toString());

				//user-id
				if(event.getUser_id()!=null && event.getUser_id()>0)
					pstmt.setString(3,event.getUser_id().toString());
				else
					pstmt.setString(3,null);

				//resources-needed
				if(event.getResources_needed()!=null && !event.getResources_needed().isEmpty())
					pstmt.setString(4,event.getResources_needed());
				else
					pstmt.setString(4,null);

				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(5,event.getPlace());
				else
					pstmt.setString(5,null);

				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(6,event.getPlace());
				else
					pstmt.setString(5,null);

				//event-date
				if(event.getEvent_date_time()!=null)
					pstmt.setString(6,event.getEvent_date_time().toString());
				else
					pstmt.setString(6,null);

				//is-archived
				pstmt.setString(7,String.valueOf(event.getIs_archived()==null?"false":event.getIs_archived()));

				//is_resources_satisfied
				pstmt.setString(8,String.valueOf(event.getIs_resources_satisfied()==null?"false":event.getIs_resources_satisfied()));

				//int[] updateCounts = pstmt.executeBatch();
				boolean updated = pstmt.execute();

				System.out.println();
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

	public ArrayList<Event> getEventsDataFromDb()
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Event> eventList=null;
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
				StringBuilder selectTableSQL = new StringBuilder("SELECT * from events");

				System.out.println(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				eventList=new ArrayList<Event>();
				while (rs.next()) {

					Event event= new Event();

					Integer event_id= rs.getInt("event_id");
					event.setEvent_id(event_id);

					String event_desc = rs.getString("event_desc");
					event.setEvent_desc(event_desc);

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
					Date created_date_time= parser.parse(rs.getString("created_date_time"));
					event.setCreated_date_time(created_date_time);

					Integer user_id=rs.getInt("user_id");
					event.setUser_id(user_id);

					String resources_needed=rs.getString("resources_needed");
					event.setResources_needed(resources_needed);

					String place=rs.getString("place");
					event.setPlace(place);

					Date event_date_time= parser.parse(rs.getString("event_date_time"));
					event.setEvent_date_time(event_date_time);

					Boolean is_archived=rs.getBoolean("is_archived");
					event.setIs_archived(is_archived);

					Boolean is_resources_satisfied=rs.getBoolean("is_resources_satisfied");
					event.setIs_resources_satisfied(is_resources_satisfied);


					eventList.add(event);
					logger.warn(event);
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
		if(eventList.size()>0)
			return eventList;
		else
			return null;
	}
	public ArrayList<Event> getEventsDataFromDb(Event evt)
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Event> eventList=null;
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
				StringBuilder selectTableSQL = new StringBuilder("SELECT * from events where 1=1 ");
				if(evt.getEvent_id()!=null)
					selectTableSQL.append(" and event_id='"+evt.getEvent_id()+ "'");

				System.out.println(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				eventList=new ArrayList<Event>();
				while (rs.next()) {

					Event event= new Event();

					Integer event_id= rs.getInt("event_id");
					event.setEvent_id(event_id);

					String event_desc = rs.getString("event_desc");
					event.setEvent_desc(event_desc);

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
					Date created_date_time= parser.parse(rs.getString("created_date_time"));
					event.setCreated_date_time(created_date_time);

					Integer user_id=rs.getInt("user_id");
					event.setUser_id(user_id);

					String resources_needed=rs.getString("resources_needed");
					event.setResources_needed(resources_needed);

					String place=rs.getString("place");
					event.setPlace(place);

					Date event_date_time= parser.parse(rs.getString("event_date_time"));
					event.setEvent_date_time(event_date_time);

					Boolean is_archived=rs.getBoolean("is_archived");
					event.setIs_archived(is_archived);

					Boolean is_resources_satisfied=rs.getBoolean("is_resources_satisfied");
					event.setIs_resources_satisfied(is_resources_satisfied);


					eventList.add(event);
					logger.warn(event);
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
		if(eventList.size()>0)
			return eventList;
		else
			return null;
	}
	
	public Boolean deletEventsDataFromDb(Event evt)
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		Integer count=0;
		ArrayList<Event> eventList=null;
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
				StringBuilder selectTableSQL = new StringBuilder("delete from events where 1=1 ");
				if(evt.getEvent_id()!=null)
					selectTableSQL.append(" and event_id='"+evt.getEvent_id()+ "'");

				System.out.println(selectTableSQL.toString());

				count = statement.executeUpdate(selectTableSQL.toString());
				logger.warn("deleted "+ count + " number of records");
				}
			catch(Exception e)
			{
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			

		} else {
			System.out.println("Failed to make connection!");
		}
		
		if(count>0)
			return true;
		else
			return false;
		
	}
	
	public String saveEditedEvents(Event event)
	{

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
			connection = DriverManager.getConnection(jdbcString,dbUserName,dbPassword);
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			logger.debug("You made it, take control your database now!");

			try {
				String insertEventsSQL = "update events set event_desc=?,created_date_time=?,user_id=?,resources_needed=?,place=?,event_date_time=?,is_archived=?,is_resources_satisfied=? where event_id=?";
				PreparedStatement pstmt = connection.prepareStatement(insertEventsSQL);

				//event description
				if(event.getEvent_desc()!=null && !event.getEvent_desc().isEmpty())
					pstmt.setString(1,event.getEvent_desc());
				else
					pstmt.setString(1,null);

				//created date
				pstmt.setString(2,new Date().toString());

				//user-id
				if(event.getUser_id()!=null && event.getUser_id()>0)
					pstmt.setString(3,event.getUser_id().toString());
				else
					pstmt.setString(3,null);

				//resources-needed
				if(event.getResources_needed()!=null && !event.getResources_needed().isEmpty())
					pstmt.setString(4,event.getResources_needed());
				else
					pstmt.setString(4,null);

				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(5,event.getPlace());
				else
					pstmt.setString(5,null);

				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(6,event.getPlace());
				else
					pstmt.setString(5,null);

				//event-date
				if(event.getEvent_date_time()!=null)
					pstmt.setString(6,event.getEvent_date_time().toString());
				else
					pstmt.setString(6,null);

				//is-archived
				pstmt.setString(7,String.valueOf(event.getIs_archived()==null?"false":event.getIs_archived()));

				//is_resources_satisfied
				pstmt.setString(8,String.valueOf(event.getIs_resources_satisfied()==null?"false":event.getIs_resources_satisfied()));
				
				pstmt.setInt(9,event.getEvent_id());

				//int[] updateCounts = pstmt.executeBatch();
				int updated = pstmt.executeUpdate();

				System.out.println();
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
}
