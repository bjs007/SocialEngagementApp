package com.models;

public class Home implements Comparable<Home>{
	private Integer entry_id;
	private String entry_desc;
	private Integer entry_type;
	private Integer post_id;
	private String activity_desc;
	private String create_date_time;
	private Integer user_id;
	public Integer getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(Integer entry_id) {
		this.entry_id = entry_id;
	}
	public String getEntry_desc() {
		return entry_desc;
	}
	public void setEntry_desc(String entry_desc) {
		this.entry_desc = entry_desc;
	}
	public Integer getEntry_type() {
		return entry_type;
	}
	public void setEntry_type(Integer entry_type) {
		this.entry_type = entry_type;
	}
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public String getActivity_desc() {
		return activity_desc;
	}
	public void setActivity_desc(String activity_desc) {
		this.activity_desc = activity_desc;
	}
	public String getCreate_date_time() {
		return create_date_time;
	}
	public void setCreate_date_time(String create_date_time) {
		this.create_date_time = create_date_time;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public int compareTo(Home other){
		return create_date_time.compareTo(other.create_date_time);
	}
	@Override
	public String toString() {
		return entry_id+"&&"+entry_desc+"&&"+entry_type+"&&"+post_id+"&&"+activity_desc+"&&"+create_date_time+"&&"+user_id;
	}
	
	
}
