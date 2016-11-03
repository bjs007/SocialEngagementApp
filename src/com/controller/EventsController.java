package com.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.EventsJdbcDao;
import com.models.Event;

@Controller
public class EventsController {
	
	@Autowired
	private EventsJdbcDao eventsDao;
	
	Logger logger= Logger.getLogger(EventsController.class);
	
	@RequestMapping("/events")
	public ModelAndView eventsHome(@RequestParam(required=false) Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("eventsForm", new Event());
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
	public ModelAndView saveEvents(@ModelAttribute("eventForm") Event event,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//event = new Event();
		//event.setEvent_desc("Test event");
		event.setCreated_date_time(new Date());
		eventsDao.saveEvents(event);
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	@RequestMapping("/fetchEvent")
	public ModelAndView fetchAllEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsDisplay");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//event = new Event();
		//event.setEvent_desc("Test event");
		//logger.warn("dbString >> " + dbString);
		ArrayList<Event> eventList=eventsDao.getEventsDataFromDb();
		for(Event event:eventList)
		{
			logger.debug(event);
		}
		modelObj.addAttribute("eventsList",eventList);
		//modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	
	
	@RequestMapping("/editEvent")
	public ModelAndView editEvent(@RequestParam(value="event_id") Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Inside Event id : " + event_id);
		logger.warn("Warn Inside the logger");
		Event newEvent= new Event();
		newEvent.setEvent_id(event_id);
		modelObj.addAttribute("eventsForm", eventsDao.getEventsDataFromDb(newEvent).get(0));
		//modelObj.addAttribute("eventsForm", new Event());
		//return model;
		return new ModelAndView("redirect:/events?event_id="+event_id);
	}
	
	@RequestMapping("/deleteEvent")
	public ModelAndView deleteEvent(@RequestParam(value="event_id") Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Inside Event id : " + event_id);
		logger.warn("Warn Inside the logger");
		Event newEvent= new Event();
		newEvent.setEvent_id(event_id);
		Boolean result=eventsDao.deletEventsDataFromDb(newEvent);
		//modelObj.addAttribute("eventsForm", new Event());
		//return model;
		logger.warn("Deleted >> " + result);
		return new ModelAndView("redirect:/fetchEvent");
	}



}
