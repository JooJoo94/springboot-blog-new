package com.cos.blog.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 권한 테스트!!

@RestController
public class RoleController {
	
	@GetMapping("/admin/home")
	public String adminHome() {
		return "어드민 사용자입니다.";
	}
	
	@GetMapping("/user/home")
	public String userHome() {
		return "일반 사용자입니다.";
	}
}
