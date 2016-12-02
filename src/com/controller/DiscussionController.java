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
	private String staticUserId = "1215";
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	
	@RequestMapping("/saveDiscussionAdmin")
	public ModelAndView saveDiscussionAdmin(@ModelAttribute("discussionForm") Discussion discussion, HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {
		
		ModelAndView model = new ModelAndView("discussion");
		discussion.setAdmintime(new Date());
		String user_id=request.getSession().getAttribute("session_user_id").toString();
		discussion.setAdminID(Integer.valueOf(user_id));
		discussionDao.savediscussionsAdmin(discussion);
		return model;
	}  
	
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
	
	@RequestMapping("/addDiscussion")
	public ModelAndView CreateDiscussion(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("addDiscussion");
		model.addObject("message", "From addDiscussion controller");
		//logger.warn("dbString >> " + dbString);
		modelObj.addAttribute("saveDiscussion", new Discussion());
		return model;
		}
		
	@RequestMapping("/saveDiscussion")
	public ModelAndView saveDiscussion(@ModelAttribute("saveDiscussion") Discussion discussion,HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("discussion");
		model.addObject("message", "From discussionHome controller");
		int discussionID = count.incrementAndGet(); 
		discussion.setDiscussionID(discussionID);
		discussion.setTime(new Date());
		String user_id=request.getSession().getAttribute("session_user_id").toString();
		discussion.setUserID(Integer.valueOf(user_id));
	//	System.out.println(user_id);
		System.out.println(discussion);
		discussionDao.savediscussionsUser(discussion);
		return model;
	}
	
	

	@RequestMapping("/displayDiscussiontoadmin")
	public ModelAndView displayDiscussiontoadmin(HttpServletRequest request,HttpServletResponse response,Model modelObj) throws Exception {

		ModelAndView model = new ModelAndView("displayDiscussion");
		model.addObject("message", "From eventsHome controller");
		ArrayList<Discussion> myDiscussion =discussionDao.getdiscussionsDataFromDb();
		modelObj.addAttribute("discussionList",myDiscussion);
		return model;
	}
	
	
	
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
	//	ArrayList<Discussion> discussionList=displayDiscussion.getdiscussionsDataFromDb();
		modelObj.addAttribute("discussionList",myDiscussion);
		//modelObj.addAttribute("eventsForm", new Event());
		return model;
	}
	
	
}
