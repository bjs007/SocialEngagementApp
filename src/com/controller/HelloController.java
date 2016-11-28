package com.controller;

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

//import com.dao.JDBCDao;
//import com.models.Student;
//import com.models.Subject;



@Controller
public class HelloController {
	//@Autowired
	//JDBCDao jdbcDao;
	
	//@Value("${dbString}")
	 // private String dbString;
	
	Logger logger= Logger.getLogger(HelloController.class);
 
	//@RequestMapping("/welcome")

	@RequestMapping("/hello")
	public ModelAndView hello(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("hello");
		model.addObject("message", "From new function");
		//logger.debug("Debug Inside the logger");
		//logger.warn("NEWLOG");
		//logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		return model;
	}
	
	@RequestMapping("/createBroadcast")
	public ModelAndView createBroadcast(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("createBroadcast");
		model.addObject("message", "From new function");
		//logger.debug("Debug Inside the logger");
		//logger.warn("NEWLOG");
		//logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		return model;
	}
}
	
	