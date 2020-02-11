package com.cos.blog.model.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqWriteDto{
	private String title;
	private String content;
	private int userId;
}
