package com.cos.blog.model.comment;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
public class Comment {
	private int id;
	private int userId;
	private int postId;
	private String content;
	private Timestamp createDate;
	
	@Builder
	public Comment(int userId, int postId, String content) {
		this.userId = userId;
		this.postId = postId;
		this.content = content;
	}
	
}
