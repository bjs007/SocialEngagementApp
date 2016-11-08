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
	//public ModelAndView eventsHome(@RequestParam(required=false) Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception
	public ModelAndView eventsHome(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception
	{
		String staticUserId="4321";

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("eventsForm", new Event());
		Object uId=request.getSession().getAttribute("user_id");
		if(uId==null)
			request.getSession().setAttribute("user_id", staticUserId);
		Boolean isUserAdm=eventsDao.isAdminUser(Integer.parseInt(staticUserId));
		if(isUserAdm)
			request.getSession().setAttribute("isUserAdm", true);
		else
			request.getSession().setAttribute("isUserAdm", false);
		//String user_id=request.getSession().getAttribute("user_id").toString();
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
	public ModelAndView saveEvents(@ModelAttribute("eventsForm") Event event,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//event = new Event();
		//event.setEvent_desc("Test event");
		event.setCreated_date_time(new Date());
		String user_id=request.getSession().getAttribute("user_id").toString();
		event.setUser_id(Integer.parseInt(user_id));
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

		ModelAndView model = new ModelAndView("editEvent");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Inside Event id : " + event_id);
		logger.warn("Warn Inside the logger");
		Event newEvent= new Event();
		newEvent.setEvent_id(event_id);
		modelObj.addAttribute("eventsForm", eventsDao.getEventsDataFromDb(newEvent).get(0));
		//modelObj.addAttribute("eventsForm", new Event());
		//return new ModelAndView("redirect:/eventEdit?event_id="+event_id);
		return model;
	}
	
	@RequestMapping("/saveEditedEvent")
	public ModelAndView saveEditEvent(@ModelAttribute("eventsForm") Event event,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("editEvent");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Warn Inside the logger");
		eventsDao.saveEditedEvents(event);
		//modelObj.addAttribute("eventsForm", new Event());
		return new ModelAndView("redirect:/fetchEvent");
		
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
	@RequestMapping("/fetchArchivedEvent")
	public ModelAndView fetchArchivedEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) 
	{

		ModelAndView model = new ModelAndView("eventsArchivedDisplay");
		ArrayList<Event> eventList=null;
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//event = new Event();
		//event.setEvent_desc("Test event");
		//logger.warn("dbString >> " + dbString);
		try{
			eventList=eventsDao.getArchivedEventsDataFromDb();
			if(eventList!=null){
				for(Event event:eventList)
				{
					logger.debug(event);
				}
			}
			else
				logger.warn("Eventlist is null");
		}catch(Exception e)
		{
			logger.error("Error in fetchArchivedEvents ",e);
		}
		if(eventList==null || eventList.isEmpty())
			eventList=new ArrayList<Event>();
		modelObj.addAttribute("eventsList",eventList);
		//modelObj.addAttribute("eventsForm", new Event());
		return model;
	}



}
