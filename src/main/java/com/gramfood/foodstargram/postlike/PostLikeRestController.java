package com.gramfood.foodstargram.postlike;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostLikeRestController {
	@PostMapping("/post/like")
	public Map<String, String> postlike(@RequestParam("postId") int postId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
	}
}
