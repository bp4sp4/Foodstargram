package com.gramfood.foodstargram.postlike.service;

import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.postlike.domain.PostLike;
import com.gramfood.foodstargram.postlike.repository.PostLikeRepository;

@Service
public class PosLikeService {
	
	private PostLikeRepository postLikeRepository;
	
	public PosLikeService(PostLikeRepository postLikeRepository) {
		this.postLikeRepository = postLikeRepository;
	}
	
	public PostLike addLike(int postId, int userId) {
			PostLike postLike = PostLike.builder()
								.postId(postId)
								.userId(userId)
								.build();
		
	return postLikeRepository.save(postLike);
			
	}
}
