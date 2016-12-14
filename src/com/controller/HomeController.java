package com.controller;

import java.text.ParseException;

/**
 * @author Lei Liu
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


/**
* This controller is for Dashboard, which can be used to fetch information
* from events and broadcasts by execuating store procedure in the database
*/
@Controller
public class HomeController {

	@Autowired
	JDBCDao jdbcDao;
	@Autowired
	HomeJdbcDao homejdbcDao;

	@Value("${dbString}")
	  private String dbString;

	Logger logger= Logger.getLogger(HomeController.class);

	/**
	*solving the request from welcome.jsp
	*return to welcome.jsp with the list of results
	*/
	@RequestMapping("/welcome")
	public ModelAndView browseEvents(Model model){
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.getDashboardInfo();
		//System.out.println(list.toString());
		return new ModelAndView("welcome","list",list);
	}

	/**
	*solving the request after logging in as the admin
	*return to welcomeadmin.jsp with the list of results
	*/
	@RequestMapping("/welcomeadmin")
	public ModelAndView browseadmview(Model model){
		ArrayList<Home> list = new ArrayList<Home>();
		list = homejdbcDao.getDashboardInfo();
		//System.out.println(list.toString());
		return new ModelAndView("welcomeadmin","list",list);
	}

	/**
	*solving the request from Dashboard
	*return to selectbydate.jsp with list of results if there are some results
	*elso return to selectbydatefail.jsp if no results returned
	*/
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

	/**
	*solving the request from Dashboard
	*return to selectbytype.jsp with list of results if there are some results
	*else return to selectbytypefail.jsp if no resutls returned
	*/
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

	/**
	* solving the request from Dashboard
	* if the comments has been submitted successfully, then rturn true
	* else return false
	*/
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

	/**
	*this is a test method which is not included in the final application
	*/
	@RequestMapping("/joinin")
	public ModelAndView joinin(HttpServletRequest request){
		String eid = request.getParameter("eid");
		String typeid = request.getParameter("typeid");
		System.out.println(eid);
		System.out.println(typeid);
		return new ModelAndView();
	}
}
