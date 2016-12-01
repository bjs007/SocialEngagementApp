package com.controller;

import java.text.ParseException;

/**
 * @author Dipanjan Karmakar
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.HomeJdbcDao;
import com.dao.JDBCDao;
import com.models.Home;
import com.models.Student;
import com.models.Subject;



@Controller
public class HomeController {
	
	@Autowired
	JDBCDao jdbcDao;
	@Autowired
	HomeJdbcDao homejdbcDao;
	
	@Value("${dbString}")
	  private String dbString;
	
	Logger logger= Logger.getLogger(HomeController.class);
 
	//@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from Controller  **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	//@RequestMapping("/welcome")
	public ModelAndView hello(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("welcome");
		model.addObject("message", "From new function");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		logger.warn("dbString >> " + dbString);
		return model;
	}
	
	@RequestMapping("/contact")
	public ModelAndView springTiles(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("contact");
		model.addObject("message", "From new function");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		return model;
	}
	/*
	 * 
	 * New code to merge with ComS 514
	 */
	
	@RequestMapping("/fetchStudent")
	public ModelAndView fetchStudent(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("student");
		model.addObject("message", "From fetchStudent function");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		modelObj.addAttribute("studentForm", new Student());

		return model;
	}

	@RequestMapping("/getStudentData")
	public ModelAndView getStudentData(@ModelAttribute("studentForm") Student student , HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {

		ModelAndView modelObj = new ModelAndView("student");
		logger.warn("Got value from form :" + student.getFirst_name());
		modelObj.addObject("newMessage", "Got details");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		model.addAttribute("studentFormstudentForm", new Student());
		ArrayList<Student> studentObj=jdbcDao.getStudentDataFromDb(student);
		for(Student instance:studentObj)
			logger.warn(instance);
		model.addAttribute("isDataPresent", true);
		model.addAttribute("student", studentObj);
		return modelObj;
	}

	@RequestMapping("/fetchSubject")
	public ModelAndView fetchSubject(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("subject");
		model.addObject("message", "From fetchSubject function");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		modelObj.addAttribute("subjectForm", new Subject());

		return model;
	}

	@RequestMapping("/getSubjectData")
	public ModelAndView getSubjectData(@ModelAttribute("subjectForm") Subject subject , HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {

		ModelAndView modelObj = new ModelAndView("subject");
		logger.warn("Got value from form :" + subject.getSubject_name());
		modelObj.addObject("newMessage", "Got details");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		model.addAttribute("subjectForm", new Subject());
		ArrayList<Subject> subjectObj=jdbcDao.getSubjectDataFromDb(subject);
		for(Subject instance:subjectObj)
			logger.warn(instance);
		model.addAttribute("isDataPresent", true);
		model.addAttribute("subject", subjectObj);
		return modelObj;
	}
	
	@RequestMapping("/welcome")
	public ModelAndView browseEvents(Model model){  
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.getDashboardInfo();
		//System.out.println(list.toString());
		return new ModelAndView("welcome","list",list);
	} 
	@RequestMapping("/welcomeadmin")
	public ModelAndView browseadmview(Model model){  
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.getDashboardInfo();
		//System.out.println(list.toString());
		return new ModelAndView("welcomeadmin","list",list);
	} 
	
	
	@RequestMapping("/selectbydate")
	public ModelAndView selectbydate(HttpServletRequest request) throws ParseException{
		String date = request.getParameter("user_date");
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.browsebyDate(date);
		ModelAndView mav = null;
		if(list.size() > 0)
			mav = new ModelAndView("selectbydate","list",list);
		else
			mav = new ModelAndView("selectbydatefail");
		return mav;
	}
	
	@RequestMapping("/selectbytype")
	public ModelAndView selectbytype(HttpServletRequest request) throws ParseException{
		String date = request.getParameter("events types");
		System.out.println(date);
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.browsebyType(date);
		ModelAndView mav = null;
		if(list.size() > 0)
			mav = new ModelAndView("selectbytype","list",list);
		else
			mav = new ModelAndView("selectbytypefail");
		return mav;
	}
	
	@RequestMapping("/comsubmit")
	public ModelAndView commentSubmit(HttpServletRequest request) throws ParseException{
		String string1 = request.getParameter("info");
		String string2 = request.getParameter("infoarea");
		System.out.println(string1);
		System.out.println(string2);
		boolean list = homejdbcDao.commentSubmit(string1.split("&&"), string2);
		ArrayList<Home> list2 = new ArrayList<Home>();
		list2 = homejdbcDao.getDashboardInfo();
		ArrayList<Object> result = new ArrayList();
		if(list){
			result.add("true");
		}
		else{
			result.add("false");
		}
		result.add(list2);
		
		ModelAndView mav = null;
		if(list)
			mav = new ModelAndView("welcome1","result",result);
		else
			mav = new ModelAndView("welcome1","result",result);
		return mav;
	}
	
	@RequestMapping("/joinin")
	public ModelAndView joinin(HttpServletRequest request){
		String eid = request.getParameter("eid");
		String typeid = request.getParameter("typeid");
		System.out.println(eid);
		System.out.println(typeid);
		return new ModelAndView();
	}
}