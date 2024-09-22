package com.gramfood.foodstargram.comment.service;

import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.comment.domain.Comment;
import com.gramfood.foodstargram.comment.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public Comment addComment(int postID, int userId, String contents) {
			Comment comment = Comment.builder()
									.postId(postID)
									.userID(userId)
									.contents(contents)
									.build();
			return commentRepository.save(comment);
	}
}
