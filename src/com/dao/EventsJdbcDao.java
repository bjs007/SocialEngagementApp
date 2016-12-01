package com.dao;

/**
 * @author Dipanjan Karmakar
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.enums.ModuleEnum;
import com.models.Comment;
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

				//event-date
				if(event.getEvent_date_time()!=null)
				{
					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date dateToSet=parser.parse(event.getEvent_date_time().toString());
					logger.warn("Java date : " + dateToSet);
					java.sql.Timestamp timeStampToSet=new java.sql.Timestamp( dateToSet.getTime());
					logger.warn("SQL date : " + timeStampToSet);
					pstmt.setTimestamp(6,timeStampToSet);
				}
				else
					pstmt.setString(6,null);

				//is-archived
				pstmt.setString(7,String.valueOf(event.getIs_archived()==null?"false":event.getIs_archived()));

				//is_resources_satisfied
				pstmt.setString(8,String.valueOf(event.getIs_resources_satisfied()==null?"false":event.getIs_resources_satisfied()));

				//int[] updateCounts = pstmt.executeBatch();
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
				StringBuilder selectTableSQL = new StringBuilder("select * from socialDb.events where is_archived='false';");

				logger.warn(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				eventList=new ArrayList<Event>();
				while (rs.next()) {

					Event event= new Event();

					Integer event_id= rs.getInt("event_id");
					event.setEvent_id(event_id);

					String event_desc = rs.getString("event_desc");
					event.setEvent_desc(event_desc);

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date created_date_time= parser.parse(rs.getString("created_date_time"));
					event.setCreated_date_time(created_date_time);

					Integer user_id=rs.getInt("user_id");
					event.setUser_id(user_id);

					String resources_needed=rs.getString("resources_needed");
					event.setResources_needed(resources_needed);

					String place=rs.getString("place");
					event.setPlace(place);
					
					java.sql.Timestamp dateTimeToSet=rs.getTimestamp("event_date_time");
					Date event_date_time= new Date(dateTimeToSet.getTime());
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

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date created_date_time= parser.parse(rs.getString("created_date_time"));
					event.setCreated_date_time(created_date_time);

					Integer user_id=rs.getInt("user_id");
					event.setUser_id(user_id);

					String resources_needed=rs.getString("resources_needed");
					event.setResources_needed(resources_needed);

					String place=rs.getString("place");
					event.setPlace(place);
					
					java.sql.Timestamp dateTimeToSet=rs.getTimestamp("event_date_time");
					Date eventTimeStamp=new Date(dateTimeToSet.getTime());
					//Date event_date_time= parser.parse(rs.getString("event_date_time"));
					event.setEvent_date_time(eventTimeStamp);

					Boolean is_archived=rs.getBoolean("is_archived");
					event.setIs_archived(is_archived);

					Boolean is_resources_satisfied=rs.getBoolean("is_resources_satisfied");
					event.setIs_resources_satisfied(is_resources_satisfied);

					//get comments data
					String selectCommentSQL = new String("SELECT * from comments where module_type=?  and post_id=? order by comment_id");
					PreparedStatement pstmt = connection.prepareStatement(selectCommentSQL);
					pstmt.setInt(1, ModuleEnum.EVENTS.value());
					pstmt.setInt(2, event.getEvent_id());
					
					ResultSet commRs = pstmt.executeQuery();
					while (commRs.next()) {

						String commentId = commRs.getString("comment_id");
						String userId = commRs.getString("user_id");
						String dateTime=commRs.getString("date_time");
						String post_id=commRs.getString("post_id");
						String commentString=commRs.getString("comment_string");
						String moduleType=commRs.getString("module_type");
						
						Comment comment=new Comment();
						comment.setCommentId(Integer.parseInt(commentId));
						comment.setUserId(Integer.parseInt(userId));
						SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
						comment.setDate_time(df.parse(dateTime));
						comment.setPostId(Integer.parseInt(post_id));
						comment.setCommentString(commentString);
						comment.setModule_type(ModuleEnum.fromValue(Integer.parseInt(moduleType)));
						logger.debug("Comment for eventId "+ event.getEvent_id() + " >> "+ event.toString() );
						if(event.getPrevComments()==null || event.getPrevComments().isEmpty()){
							event.setPrevComments(new ArrayList<Comment>());
						}
						event.getPrevComments().add(comment);
					}

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
				StringBuilder deleteEventsSql = new StringBuilder("delete from events where");
				if(evt.getEvent_id()!=null)
					deleteEventsSql.append(" event_id='"+evt.getEvent_id()+ "'");

				System.out.println(deleteEventsSql.toString());

				count = statement.executeUpdate(deleteEventsSql.toString());
				logger.warn("deleted events "+ count + " number of records");
				
				statement=connection.createStatement();
				StringBuilder deleteCommentsSql = new StringBuilder("delete from comments where ");
				if(evt.getEvent_id()!=null)
					deleteCommentsSql.append(" module_type=1 and post_id="+evt.getEvent_id());

				System.out.println(deleteCommentsSql.toString());

				count = statement.executeUpdate(deleteCommentsSql.toString());
				logger.warn("deleted comments "+ count + " number of records");
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
				//String insertEventsSQL = "update events set event_desc=?,created_date_time=?,user_id=?,resources_needed=?,place=?,event_date_time=?,is_archived=?,is_resources_satisfied=? where event_id=?";
				String insertEventsSQL = "update events set event_desc=?,created_date_time=?,resources_needed=?,place=?,event_date_time=?,is_archived=?,is_resources_satisfied=? where event_id=?";
				PreparedStatement pstmt = connection.prepareStatement(insertEventsSQL);

				//event description
				if(event.getEvent_desc()!=null && !event.getEvent_desc().isEmpty())
					pstmt.setString(1,event.getEvent_desc());
				else
					pstmt.setString(1,null);

				//created date
				pstmt.setString(2,new Date().toString());

				//user-id
				/*if(event.getUser_id()!=null && event.getUser_id()>0)
					pstmt.setString(3,event.getUser_id().toString());
				else
					pstmt.setString(3,null);*/

				//resources-needed
				if(event.getResources_needed()!=null && !event.getResources_needed().isEmpty())
					pstmt.setString(3,event.getResources_needed());
				else
					pstmt.setString(3,null);

				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(4,event.getPlace());
				else
					pstmt.setString(4,null);

				//event-date
				if(event.getEvent_date_time()!=null)
				{
					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date dateToSet=parser.parse(event.getEvent_date_time().toString());
					logger.warn("Java date : " + dateToSet);
					java.sql.Timestamp timeStampToSet=new java.sql.Timestamp( dateToSet.getTime());
					logger.warn("SQL date : " + timeStampToSet);
					pstmt.setTimestamp(5,timeStampToSet);
					
				}
				else
					pstmt.setString(5,null);

				//is-archived
				pstmt.setString(6,String.valueOf(event.getIs_archived()==null?"false":event.getIs_archived()));

				//is_resources_satisfied
				pstmt.setString(7,String.valueOf(event.getIs_resources_satisfied()==null?"false":event.getIs_resources_satisfied()));

				pstmt.setInt(8,event.getEvent_id());

				//int[] updateCounts = pstmt.executeBatch();
				int updated = pstmt.executeUpdate();
				logger.warn("Updated events :" + updated);
				
				if(StringUtils.hasText(event.getCommentToAdd()))
				{
					String insertCommentSQL = "INSERT INTO comments (user_id,date_time,post_id,comment_string,module_type) VALUES (?,?,?,?,?)";
					pstmt = connection.prepareStatement(insertCommentSQL);
					
					if(event.getUser_id()!=null && event.getUser_id()>0)
						pstmt.setString(1,event.getUser_id().toString());
					else
						pstmt.setString(1,null);
					
					pstmt.setString(2,new Date().toString());
					
					pstmt.setInt(3, event.getEvent_id());
					pstmt.setString(4, event.getCommentToAdd());
					
					pstmt.setInt(5, ModuleEnum.EVENTS.value());
					boolean inserted = pstmt.execute();
					logger.warn("Saved comments records >> "+ inserted);
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
		return "Success";
	}
	public boolean isAdminUser(Integer userId)
	{
		boolean result=false;

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
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
	public ArrayList<Event> getArchivedEventsDataFromDb()
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
				StringBuilder selectTableSQL = new StringBuilder("select * from socialDb.events where is_archived='true'");

				logger.warn(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				eventList=new ArrayList<Event>();
				while (rs.next()) {

					Event event= new Event();

					Integer event_id= rs.getInt("event_id");
					event.setEvent_id(event_id);

					String event_desc = rs.getString("event_desc");
					event.setEvent_desc(event_desc);

					SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
					Date created_date_time= parser.parse(rs.getString("created_date_time"));
					event.setCreated_date_time(created_date_time);

					Integer user_id=rs.getInt("user_id");
					event.setUser_id(user_id);

					String resources_needed=rs.getString("resources_needed");
					event.setResources_needed(resources_needed);

					String place=rs.getString("place");
					event.setPlace(place);

					java.sql.Timestamp dateTimeToSet=rs.getTimestamp("event_date_time");
					Date event_date_time= new Date(dateTimeToSet.getTime());
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

	/**
	 * @param user_id
	 * @return
	 */
	public String getUserNameFromId(Integer user_id) 
	{
		boolean result=false;

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
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
				String selectTableSQL = new String("SELECT * from users where userid= ? ");
				PreparedStatement statement=connection.prepareStatement(selectTableSQL);
				if(user_id!=null)
					statement.setInt(1, user_id);

				System.out.println(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String userName=rs.getString("name");
					if(userName.isEmpty())
						return null;
					else
						return userName;
				}
				return null;
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
		return null;
	}
}