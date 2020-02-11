package com.cos.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.ReturnCode;
import com.cos.blog.model.post.Post;
import com.cos.blog.model.post.dto.ReqUpdateDto;
import com.cos.blog.model.post.dto.ReqWriteDto;
import com.cos.blog.model.post.dto.RespListDto;
import com.cos.blog.model.user.User;
import com.cos.blog.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	public int 글쓰기(ReqWriteDto dto) {
		return postRepository.save(dto);
	}
	
	public List<RespListDto> 목록보기(){
		return postRepository.findAll();
	}
	
	public Post 상세보기(int id) {
		return postRepository.findById(id);
	}
	
	public Post 수정하기(int id, User principal) {
		Post post = postRepository.findById(id);
		
		if(principal.getId() == post.getUserId()) {
			return post;
		}else {
			return null;
		}
		
	}
	
	public int 수정완료(ReqUpdateDto dto, User principal) {
		Post post = postRepository.findById(dto.getId());
		
		if(principal.getId() == post.getUserId()) {
			return postRepository.update(dto); // 1, 0, -1
		}else {
			return ReturnCode.권한없음; // -3
		}
	}
	
	
	public int 삭제하기(int id, User principal) {
		// 동일인 체크 session의 principal.id == 해당 post.id로 select한 userId값
		Post post = postRepository.findById(id);
		
		if(principal.getId() == post.getUserId()) {
			return postRepository.delete(id);
		}else {
			return ReturnCode.권한없음;
		}
	}
}
