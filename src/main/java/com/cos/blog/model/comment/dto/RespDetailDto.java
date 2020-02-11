package com.cos.blog.model.comment.dto;

import java.sql.Timestamp;

import com.cos.blog.model.RespCM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespDetailDto{
	
	private RespCM status;
	
	private int id;
	private int userId;
	private int postId;
	private String content;
	private Timestamp createDate;
	private String username;
}
