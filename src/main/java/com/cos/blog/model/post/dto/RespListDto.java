package com.cos.blog.model.post.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespListDto {
	private int id;
	private String title;
	private String content;
	private int userId;
	private Timestamp createDate;
	private String username;
}
