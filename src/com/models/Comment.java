/**
 * 
 */
package com.models;

import java.util.Date;

import com.enums.ModuleEnum;

/**
 * @author dipanjankarmakar
 *
 */
public class Comment {
	Integer commentId;
	Integer userId;
	Date date_time;
	Integer postId;
	String commentString;
	ModuleEnum module_type;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getCommentString() {
		return commentString;
	}
	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}
	public ModuleEnum getModule_type() {
		return module_type;
	}
	public void setModule_type(ModuleEnum module_type) {
		this.module_type = module_type;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId
				+ ", date_time=" + date_time + ", postId=" + postId
				+ ", commentString=" + commentString + ", module_type="
				+ module_type + "]";
	}
}
