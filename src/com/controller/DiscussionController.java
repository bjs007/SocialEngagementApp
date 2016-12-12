/**
 * @author Prateek Gupta
 */

package com.controller;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.DiscussionDao;
import com.models.Discussion;

@Controller
public class DiscussionController {

	@Autowired
	DiscussionDao discussionDao;
	
	/*
	static ID used for testing purpose
	*/
	private String staticUserId = "1215";
	
	/* Create the discussion id */
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	/*  Mapped Page : responsediscussion.jsp
	    Method Functionality : this method will save the discussion response by admin.
	*/
	@RequestMapping("/saveDiscussionAdmin")
	public ModelAndView saveDiscussionAdmin(@ModelAttribute("discussionForm") Discussion discussion, HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {
		
		ModelAndView model = new ModelAndView("discussion");
		discussion.setAdmintime(new Date());
		String user_id=request.getSession().getAttribute("session_user_id").toString();
		discussion.setAdminID(Integer.valueOf(user_id));
		discussionDao.savediscussionsAdmin(discussion);
		return model;
	}  
	
	/*  Mapped Page : discussion.jsp
	    Method Functionality : this method will show the layout of responsediscussion and append the discussionId with 
	    discussion form object.
	*/
	@RequestMapping("/responsediscussion")
	public ModelAndView responsediscussion(@RequestParam(value="discussionID") Integer discussionID,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("responsediscussion");
		model.addObject("message", "From responsediscussion controller");
	
		System.out.println(discussionID);
		Discussion d = new Discussion();
		d.setDiscussionID(discussionID);
		modelObj.addAttribute("discussionForm", d);
		return model;
	}
	
	/*  Mapped Page : discussion.jsp
	    Method Functionality : this method will show all the discussion of the Users in the table format
	    discussion form object.
	*/
	@RequestMapping("/responseDiscussion")
	public ModelAndView displayDiscussionAdmin(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("displayDiscussionAdmin");
		model.addObject("message", "From displayDiscussionAdmin controller");
		ArrayList<Discussion> myDiscussion = new ArrayList<Discussion>();
		
		ArrayList<Discussion> dis =discussionDao.getdiscussionsDataFromDb();
		for(Discussion d : dis){
			if(d.getAdminID() == null || d.getAdminID() == 0){
				myDiscussion.add(d);
			}
		}
		
		modelObj.addAttribute("discussionList",myDiscussion);
			
		return model;
		}
	
	/*  Mapped Page : discussion.jsp
	    Method Functionality : methods is used by menu page of discussion and check the user status,
	    this status will help the discussion page to nevigate further pages.
	*/

	@RequestMapping("/discussion")
	public ModelAndView discussion(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("discussion");
		model.addObject("message", "From eventsHome controller");
		Object uId=request.getSession().getAttribute("session_user_id");
		if(uId==null)
		{
			uId=staticUserId;
			request.getSession().setAttribute("session_user_id", uId);
		}
		Boolean isUserAdm=discussionDao.isAdminUser(Integer.parseInt(uId.toString()));
		if(isUserAdm)
			request.getSession().setAttribute("isUserAdm", true);
		else
			request.getSession().setAttribute("isUserAdm", false);
		
		
		return model;
	}
	
	/*  Mapped Page : addDiscussion.jsp
	    Method Functionality : methods is used to add discussion by the users.
	*/
	@RequestMapping("/addDiscussion")
	public ModelAndView CreateDiscussion(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("addDiscussion");
		model.addObject("message", "From addDiscussion controller");
		modelObj.addAttribute("saveDiscussion", new Discussion());
		return model;
		}
	
	/*  Mapped Page : addDiscussion.jsp
	    Method Functionality : this method will save the discussion on submit button in addDiscussion page, this will save
	    the user text message in table Discussion.
	*/
	@RequestMapping("/saveDiscussion")
	public ModelAndView saveDiscussion(@ModelAttribute("saveDiscussion") Discussion discussion,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("discussion");
		model.addObject("message", "From discussionHome controller");
		int discussionID = count.incrementAndGet(); 
		discussion.setDiscussionID(discussionID);
		discussion.setTime(new Date());
		String user_id=request.getSession().getAttribute("session_user_id").toString();
		discussion.setUserID(Integer.valueOf(user_id));
		System.out.println(discussion);
		discussionDao.savediscussionsUser(discussion);
		return model;
	}
	
	
    /*  Mapped Page : displayDiscussion.jsp
	    Method Functionality : this method will show all the discussion of the Users to admin
	*/
	@RequestMapping("/displayDiscussiontoadmin")
	public ModelAndView displayDiscussiontoadmin(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("displayDiscussion");
		model.addObject("message", "From eventsHome controller");
		ArrayList<Discussion> myDiscussion =discussionDao.getdiscussionsDataFromDb();
		modelObj.addAttribute("discussionList",myDiscussion);
		return model;
	}
	
	
	/*  Mapped Page : displayDiscussion.jsp
	    Method Functionality : this method will show all the discussion created by User and admin response
	*/
	@RequestMapping("/displayDiscussion")
	public ModelAndView displayDiscussion(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("displayDiscussion");
		model.addObject("message", "From eventsHome controller");
		ArrayList<Discussion> myDiscussion = new ArrayList<Discussion>();
		ArrayList<Discussion> dis =discussionDao.getdiscussionsDataFromDb();
		int id = Integer.valueOf(request.getSession().getAttribute("session_user_id").toString());
		for(Discussion d : dis){
			if(id == d.getUserID()){
				myDiscussion.add(d);
			}
		}
		System.out.println(myDiscussion);
		modelObj.addAttribute("discussionList",myDiscussion);
		return model;
	}
	
	
}
