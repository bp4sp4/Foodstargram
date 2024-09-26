package com.gramfood.foodstargram.postlike.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.postlike.domain.PostLike;
import com.gramfood.foodstargram.postlike.repository.PostLikeRepository;

import jakarta.transaction.Transactional;

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
	
	public int getLikeCount(int postId) {
		return postLikeRepository.countByPostId(postId);
	}
	
	 public boolean deleteLike(int postId, int userId) {
	        Optional<PostLike> optionalLike = postLikeRepository.findByPostIdAndUserId(postId, userId);
	        PostLike postLike = optionalLike.orElse(null);
	        
	        if (postLike != null) {
	            postLikeRepository.delete(postLike);
	            return true;
	        } else {
	            return false;
	        }
	    }
	
	// 특정 userId와 postId가 일치하는 행 조회
	
	public boolean isLikeByUserIdandPostId(int userId, int postId) {
		// 특정 사용자가 특정 게시글에 좋아요를 했는지 안했는지
		// insert
		// select
		// update
		int count =  postLikeRepository.countByUserIdAndPostId(userId, postId);
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void deleteLikeByPostId(int postId) {
		postLikeRepository.deleteByPostId(postId);
	}
		
}
