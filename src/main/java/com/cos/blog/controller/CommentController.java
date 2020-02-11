package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RespCM;
import com.cos.blog.model.comment.dto.ReqDetailDto;
import com.cos.blog.model.comment.dto.RespDetailDto;
import com.cos.blog.model.user.User;
import com.cos.blog.service.CommentService;

// @Controller + @ResponseBody
@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/comment/write")
	public ResponseEntity<?> write(@RequestBody ReqDetailDto dto) {

		RespDetailDto comment = commentService.댓글쓰기(dto);

		if (comment != null) {
			comment.setStatus(new RespCM(200, "ok"));
			return new ResponseEntity<RespDetailDto>(comment, HttpStatus.OK);
		} else {
			RespDetailDto error = new RespDetailDto();
			error.setStatus(new RespCM(400, "fail"));
			return new ResponseEntity<RespDetailDto>(error, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/comment/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @AuthenticationPrincipal User principal) {

		int result = commentService.댓글삭제(id, principal);

		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else if (result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}

	}

}
