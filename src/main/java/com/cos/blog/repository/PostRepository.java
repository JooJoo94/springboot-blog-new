package com.cos.blog.repository;

import java.util.List;

import com.cos.blog.model.post.Post;
import com.cos.blog.model.post.dto.RespListDto;
import com.cos.blog.model.post.dto.ReqUpdateDto;
import com.cos.blog.model.post.dto.ReqWriteDto;

// MapperScan (메모리 로딩) @Repository
public interface PostRepository {
	
	public int save(ReqWriteDto dto);
	public List<RespListDto> findAll();
	public Post findById(int id);
	public int delete(int id);
	public int update(ReqUpdateDto dto);
}
