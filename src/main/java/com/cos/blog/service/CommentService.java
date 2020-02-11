package com.cos.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.ReturnCode;
import com.cos.blog.model.comment.dto.ReqDetailDto;
import com.cos.blog.model.comment.dto.RespDetailDto;
import com.cos.blog.model.user.User;
import com.cos.blog.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private HttpSession session;
	
	public List<RespDetailDto> 댓글목록보기(int postId) {
		return commentRepository.findByPostId(postId);
	}
	
	public RespDetailDto 댓글쓰기(ReqDetailDto dto) {
		int result = commentRepository.save(dto);

		System.out.println("CommentService id : "+dto.getId());
		
		if(result == 1) { // 댓글쓰기 성공
			//  select
			return commentRepository.findById(dto.getId());
		}else { // 댓글쓰기 실패
			return null;
		}
	}
	
	public int 댓글삭제(int id, User principal) {
		
		// 해당 댓글은 누가 썻냐?
		RespDetailDto comment = commentRepository.findById(id);
		
		// 지금 로그인 주체는 누구냐?
		
		if(comment.getUserId() == principal.getId()) {
			return commentRepository.delete(id); // 1, 0, -1
		}else {
			return ReturnCode.권한없음; // -3
		}
		
		
	}
}
