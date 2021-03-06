package com.controller;

/**
 * @author Dipanjan Karmakar
 * This controller is the main file for the Events module. All actions for the events module are handled here
 */

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
	
	/**
	 * In case the userId is not received from the login module, we use this userId. The userId is autowired from dbDetails.properties file
	 */
	@Value("${eventsStaticUserId}")
	private String staticUserId;
	
	Logger logger= Logger.getLogger(EventsController.class);
	
	/**
	 * Display the main Events page. 
	 * <p> Get the userId from the Login module. If not present use the static userId
	 * <p> Get the userName from the Login module. If not present use "UserAbc"
	 * <p> Check if the user is Admin and save it to the session for accessing in the jsp
	 * @param request			The servlet request object
	 * @param response			The servlet response object
	 * @param modelObj			The model object to bind various variables to it
	 * @return					The ModelAndView to display
	 * @throws Exception
	 */
	@RequestMapping("/events")
	public ModelAndView eventsHome(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception
	{

		ModelAndView model = new ModelAndView("eventsHome");
		modelObj.addAttribute("eventsForm", new Event());
		
		Object uId=request.getSession().getAttribute("session_user_id");
		if(uId==null)
		{
			uId=staticUserId;
			request.getSession().setAttribute("session_user_id", uId);
		}
		Boolean isUserAdm=eventsDao.isAdminUser(Integer.parseInt(uId.toString()));
		if(isUserAdm)
			request.getSession().setAttribute("isUserAdm", true);
		else
			request.getSession().setAttribute("isUserAdm", false);
		
		Object uName=request.getSession().getAttribute("user_name");
		String user_name=null;
		if(uName==null)
		{
				int user_id=Integer.parseInt(uId.toString());
				user_name=eventsDao.getUserNameFromId(user_id);
		}
		if(user_name==null || user_name.isEmpty())
			user_name="UserAbc";
		request.getSession().setAttribute("user_name", user_name);
		return model;
	}
	/**
	 * This function forwards the request to create an event
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it	
	 * @return				The ModelAndView to display
	 * @throws Exception
	 */
	@RequestMapping("/createEvents")
	public ModelAndView createEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("createEvent");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	/**
	 * This function saves the event to the database
	 * @param event			The Event form object that is received from the UI
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it
	 * @return				The ModelAndView to displayed
	 * @throws Exception
	 */
	@RequestMapping("/saveEvent")
	public ModelAndView saveEvents(@ModelAttribute("eventsForm") Event event,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		event.setCreated_date_time(new Date());
		String user_id=request.getSession().getAttribute("session_user_id").toString();
		event.setUser_id(Integer.parseInt(user_id));
		eventsDao.saveEvents(event);
		modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	/**
	 * This function fetches all active events from the database
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it
	 * @return				The ModelAndView to displayed
	 * @throws Exception
	 */
	@RequestMapping("/fetchEvent")
	public ModelAndView fetchAllEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsDisplay");
		model.addObject("message", "From eventsHome controller");
		logger.debug("Debug Inside the logger");
		logger.warn("Warn Inside the logger");
		Object isUserAdmin=request.getSession().getAttribute("isUserAdm");
		Boolean userAdmin=false;
		if(isUserAdmin!=null && isUserAdmin.toString().equals("true"))
			userAdmin=true;
		ArrayList<Event> eventList=eventsDao.getAllEventsDataFromDb(userAdmin);
		if(eventList!=null && eventList.size()!=0)
		{
			for(Event event:eventList)
			{
			logger.debug(event);
			}
		}
		modelObj.addAttribute("eventsList",eventList);
		return model;
	}
	/**
	 * This function fetches the data of the event that is to be edited
	 * @param event_id		The event_id of the event to be edited
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it
	 * @return				The ModelAndView to displayed
	 * @throws Exception
	 */
	@RequestMapping("/editEvent")
	public ModelAndView editEvent(@RequestParam(value="event_id") Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("editEvent");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Inside Event id : " + event_id);
		logger.warn("Warn Inside the logger");
		Event newEvent= new Event();
		newEvent.setEvent_id(event_id);
		modelObj.addAttribute("eventsForm", eventsDao.getEventsDataFromDb(newEvent).get(0));
		// In this way we can redirect to different controller
		//return new ModelAndView("redirect:/eventEdit?event_id="+event_id);
		return model;
	}
	
	/**
	 * This function will receive the updated event data from UI and persists to the database
	 * @param event			The updated event object
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it
	 * @return				The ModelAndView to displayed
	 * @throws Exception
	 */
	@RequestMapping("/saveEditedEvent")
	public ModelAndView saveEditEvent(@ModelAttribute("eventsForm") Event event,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("editEvent");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Warn Inside the logger");
		Integer user_id=Integer.parseInt(request.getSession().getAttribute("session_user_id").toString());
		event.setUser_id(user_id);
		String eventComment=event.getCommentToAdd();
		if(StringUtils.hasText(eventComment))
		{
			String user_name=request.getSession().getAttribute("user_name").toString();
			event.setCommentToAdd(user_name +"~"+eventComment);
		}
		eventsDao.saveEditedEvents(event);
		//modelObj.addAttribute("eventsForm", new Event());
		return new ModelAndView("redirect:/fetchEvent");
		
	}
	
	/**
	 * This function will delete the event from the database
	 * @param event_id			The eventId that should be deleted
	 * @param request			The servlet request object
	 * @param response			The servlet response object
	 * @param modelObj			The model object to bind various variables to it
	 * @return					The ModelAndView to displayed
	 * @throws Exception
	 */
	@RequestMapping("/deleteEvent")
	public ModelAndView deleteEvent(@RequestParam(value="event_id") Integer event_id,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("eventsHome");
		model.addObject("message", "From eventsHome controller");
		logger.warn("Inside Event id : " + event_id);
		Event newEvent= new Event();
		newEvent.setEvent_id(event_id);
		Boolean result=eventsDao.deletEventsDataFromDb(newEvent);
		logger.warn("Deleted >> " + result);
		return new ModelAndView("redirect:/fetchEvent");
	}
	
	/**
	 * This event will fetch the archived events from the database
	 * @param request		The servlet request object
	 * @param response		The servlet response object
	 * @param modelObj		The model object to bind various variables to it
	 * @return				The ModelAndView to displayed
	 */
	@RequestMapping("/fetchArchivedEvent")
	public ModelAndView fetchArchivedEvents(HttpServletRequest request,HttpServletResponse response,Model modelObj) 
	{

		ModelAndView model = new ModelAndView("eventsArchivedDisplay");
		ArrayList<Event> eventList=null;
		model.addObject("message", "From eventsHome controller");
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
		return model;
	}

}
