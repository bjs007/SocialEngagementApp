package com.models;

/**
 * @author Dipanjan Karmakar
 */

import java.util.Date;

public class Event {
	private Integer event_id;
	private String event_desc;
	private Date created_date_time;
	private Integer user_id;
	private String resources_needed;
	private String place;
	private Date event_date_time;
	private Boolean is_archived;
	private Boolean is_resources_satisfied;
	public Integer getEvent_id() {
		return event_id;
	}
	
	public String getEvent_desc() {
		return event_desc;
	}

	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}

	public Date getCreated_date_time() {
		return created_date_time;
	}

	public void setCreated_date_time(Date created_date_time) {
		this.created_date_time = created_date_time;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getResources_needed() {
		return resources_needed;
	}

	public void setResources_needed(String resources_needed) {
		this.resources_needed = resources_needed;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getEvent_date_time() {
		return event_date_time;
	}

	public void setEvent_date_time(Date event_date_time) {
		this.event_date_time = event_date_time;
	}

	public Boolean getIs_archived() {
		return is_archived;
	}

	public void setIs_archived(Boolean is_archived) {
		this.is_archived = is_archived;
	}

	public Boolean getIs_resources_satisfied() {
		return is_resources_satisfied;
	}

	public void setIs_resources_satisfied(Boolean is_resources_satisfied) {
		this.is_resources_satisfied = is_resources_satisfied;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	@Override
	public String toString() {
		return "Event [event_id=" + event_id + ", event_desc=" + event_desc
				+ ", created_date_time=" + created_date_time + ", user_id="
				+ user_id + ", resources_needed=" + resources_needed
				+ ", place=" + place + ", event_date_time=" + event_date_time
				+ ", is_archived=" + is_archived + ", is_resources_satisfied="
				+ is_resources_satisfied + "]";
	}
}
