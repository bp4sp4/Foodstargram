package com.gramfood.foodstargram.postlike;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gramfood.foodstargram.postlike.domain.PostLike;
import com.gramfood.foodstargram.postlike.service.PosLikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostLikeRestController {
	
	private PosLikeService postLikeService;
	
	public PostLikeRestController(PosLikeService postLikeService) {
		this.postLikeService = postLikeService;
	}
	
	@PostMapping("/post/like")
	public Map<String, String> postlike(
			@RequestParam("postId") int postId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		PostLike  postLike = postLikeService.addLike(postId, userId);
		
		Map<String ,String> resultMap = new HashMap<>();
		
		if(postLike != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	@DeleteMapping("/post/unlike")
	public Map<String, String> unlike(
			@RequestParam("postId") int postId, 
			HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(postLikeService.deleteLike(postId, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
