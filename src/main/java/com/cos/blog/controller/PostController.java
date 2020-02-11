package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.blog.model.RespCM;
import com.cos.blog.model.post.dto.ReqUpdateDto;
import com.cos.blog.model.post.dto.ReqWriteDto;
import com.cos.blog.model.user.User;
import com.cos.blog.service.CommentService;
import com.cos.blog.service.PostService;

// 시큐리티 구현 완료

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping({"", "/", "/post"})
	public String posts(Model model) {
		// model = RequestDispatcher + request.setAttribute

		model.addAttribute("posts", postService.목록보기());
		
		return "/post/list";
	}
	
	@GetMapping("/post/{id}")
	public String post() {
		return "/post/detail";
	}
	
	// 인증 체크
	@GetMapping("/post/write")
	public String write() {
		return "/post/write";
	}
	
	// 인증 체크
	@PostMapping("/post/write")
	public ResponseEntity<?> write(@RequestBody ReqWriteDto dto, @AuthenticationPrincipal User principal) {		
		
		dto.setUserId(principal.getId());		
		int result = postService.글쓰기(dto);
		
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);	
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}		
		
	}
	
	// 인증 체크
	@GetMapping("/post/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		
		model.addAttribute("comments", commentService.댓글목록보기(id));
		model.addAttribute("post", postService.상세보기(id));
		
		return "/post/detail";
	}
	
	@DeleteMapping("/post/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @AuthenticationPrincipal User principal){
		
		int result = postService.삭제하기(id, principal);
		
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);	
		}else if(result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 인증 체크, 동일인 체크
	@GetMapping("/post/update/{id}")
	public String update(@PathVariable int id, Model model, @AuthenticationPrincipal User principal) {
		
		// postId 로 select 해서 post 가져오기 필요 - Model에 담기 필요
		model.addAttribute("post", postService.수정하기(id, principal));
			
		return "/post/update";
	}
	
	
	@PutMapping("/post/update")
	public ResponseEntity<?> update(@RequestBody ReqUpdateDto dto, @AuthenticationPrincipal User principal){
		
		int result = postService.수정완료(dto, principal);
		
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);	
		}else if(result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
	}
}







