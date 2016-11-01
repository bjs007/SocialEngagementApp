package com.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.EventsJdbcDao;
import com.models.Event;
import com.models.Student;

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
	
	@RequestMapping("/createEvents")
	public ModelAndView createEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	@RequestMapping("/saveEvent")
	public ModelAndView saveEvents(@ModelAttribute("eventForm") Event event,HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		event = new Event();
		event.setEvent_desc("Test event");
		event.setCreated_date_time(new Date());
		eventsDao.saveEvents(event);
		//logger.warn("dbString >> " + dbString);
		return model;
	}


}
