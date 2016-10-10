package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.models.Student;
import com.models.Subject;

public class JDBCDao {
	
	Logger logger= Logger.getLogger(JDBCDao.class);
	
	public ArrayList<Student> getStudentDataFromDb(Student student)
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Student> stuList=null;
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
					.getConnection("jdbc:mysql://localhost:3307/Library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			try {
				Statement statement=connection.createStatement();
				StringBuilder selectTableSQL = new StringBuilder("SELECT * from students where 1=1 ");
				if(student.getFirst_name()!=null && !student.getFirst_name().isEmpty())
					selectTableSQL.append(" and first_name='"+student.getFirst_name()+ "'");
				if(student.getLast_name()!=null && !student.getLast_name().isEmpty())
					selectTableSQL.append(" and last_name='"+student.getLast_name()+ "'");
				if(student.getGender()!=null && !student.getGender().isEmpty())
					selectTableSQL.append(" and gender='"+student.getGender()+ "'");
				if(student.getStudent_id()!=null)
					selectTableSQL.append(" and student_id="+student.getStudent_id());
				System.out.println(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				stuList=new ArrayList<Student>();
				while (rs.next()) {

					String student_id= rs.getString("student_id");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String gender=rs.getString("gender");
					student=new Student();
					student.setStudent_id(Integer.parseInt(student_id));
					student.setFirst_name(first_name);
					student.setLast_name(last_name);
					student.setGender(gender);
					stuList.add(student);
					logger.warn(student);
				}
			} catch (SQLException e) {
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
		if(stuList.size()>0)
			return stuList;
		else
			return null;
	}
	
	public ArrayList<Subject> getSubjectDataFromDb(Subject subject)
	{

		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		ArrayList<Subject> subjectList=null;
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
					.getConnection("jdbc:mysql://localhost:3307/Library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			try {
				Statement statement=connection.createStatement();
				StringBuilder selectTableSQL = new StringBuilder("SELECT * from subject where 1=1 ");
				if(subject.getSubject_name()!=null && !subject.getSubject_name().isEmpty())
					selectTableSQL.append(" and subject_name='"+subject.getSubject_name()+ "'");
				if(subject.getProff_name()!=null && !subject.getProff_name().isEmpty())
					selectTableSQL.append(" and proff_name='"+subject.getProff_name()+ "'");
				if(subject.getTime()!=null && !subject.getTime().isEmpty())
					selectTableSQL.append(" and time='"+subject.getTime()+ "'");
				if(subject.getSubject_id()!=null)
					selectTableSQL.append(" and subject_id="+subject.getSubject_id());
				System.out.println(selectTableSQL.toString());

				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				subjectList=new ArrayList<Subject>();
				while (rs.next()) {

					String subject_id= rs.getString("subject_id");
					String subject_name = rs.getString("subject_name");
					String proff_name = rs.getString("proff_name");
					String time=rs.getString("time");
					subject=new Subject();
					subject.setSubject_id(Integer.parseInt(subject_id));
					subject.setSubject_name(subject_name);
					subject.setProff_name(proff_name);
					subject.setTime(time);
					subjectList.add(subject);
					logger.warn(subject);
				}
			} catch (SQLException e) {
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
		if(subjectList.size()>0)
			return subjectList;
		else
			return null;
	}

}
