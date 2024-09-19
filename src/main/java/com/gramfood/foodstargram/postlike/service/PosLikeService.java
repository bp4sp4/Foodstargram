package com.gramfood.foodstargram.postlike.service;

import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.postlike.repository.PostLikeRepository;

@Service
public class PosLikeService {
	
	private PostLikeRepository postLikeRepository;
	
	public PostLikeService(PostLikeRepository postLikeRepository ) {
		this.postLikeRepository = postLikeRepository;
	}
	
	public Like addPostLike(int postId, int userId) {
		
		Like like = like.bullder()
				.postId
		
		Like result = likeRepositry.save(like)
	}
}
