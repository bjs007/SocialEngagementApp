/**
 * @author Prateek Gupta
 */

package com.models;

import java.util.Date;

public class Discussion {
//discussionID

/*
    discussionID : Discussion ID created by User
    userID : Login User Id
    adminID : Id of Admin who will response to this discussion
    adminMessage : Admin response to this discussion
    message : user message created 
    time : time stamp when user created this message
    admintime : time stamp when admin response to this message
*/
	private Integer discussionID;
	private Integer userID;
	private Integer adminID;
	private String adminMessage;
	private String message;
	private Date time;
	private Date admintime;
	
	
	
	
	
	public Discussion() {
		super();
	}
	
	public Discussion(String message) {
		this.message = message;
	}

	public Discussion(Integer discussionID, Integer userID, String message, Date time) {
		super();
		this.discussionID = discussionID;
		this.userID = userID;
		this.message = message;
		this.time = time;
	}
	
	public Integer getDiscussionID() {
		return discussionID;
	}
	public void setDiscussionID(Integer discussionID) {
		this.discussionID = discussionID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAdminMessage() {
		return adminMessage;
	}

	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}

	public Date getAdmintime() {
		return admintime;
	}

	public void setAdmintime(Date admintime) {
		this.admintime = admintime;
	}

	@Override
	public String toString() {
		return "Discussion [discussionID=" + discussionID + ", userID=" + userID + ", adminID=" + adminID
				+ ", adminMessage=" + adminMessage + ", message=" + message + ", time=" + time + ", admintime="
				+ admintime + "]";
	}

	
	
	
	
	
	
}
