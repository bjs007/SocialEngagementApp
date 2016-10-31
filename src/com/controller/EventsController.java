package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.EventsJdbcDao;

@Controller
public class EventsController {
	
	@Autowired
	private EventsJdbcDao eventsDao;
	
	Logger logger= Logger.getLogger(EventsController.class);
	
	@RequestMapping("/events")
	public ModelAndView eventsHome(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		return model;
	}

}
