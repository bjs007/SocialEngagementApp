package com.models;

public class Subject {

	private Integer subject_id;
	private String subject_name;
	private String proff_name;
	private String time;
	public Integer getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getProff_name() {
		return proff_name;
	}
	public void setProff_name(String proff_name) {
		this.proff_name = proff_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", subject_name="
				+ subject_name + ", proff_name=" + proff_name + ", time="
				+ time + "]";
	}
}
