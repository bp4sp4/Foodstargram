package com.gramfood.foodstargram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.comment.domain.Comment;
import com.gramfood.foodstargram.comment.dto.CommentView;
import com.gramfood.foodstargram.comment.repository.CommentRepository;
import com.gramfood.foodstargram.user.domain.User;
import com.gramfood.foodstargram.user.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository, UserService useService) {
		this.commentRepository = commentRepository;
		this.userService = useService;
	}
	
	public Comment addComment(int postID, int userId, String contents) {
			Comment comment = Comment.builder()
									.postId(postID)
									.userID(userId)
									.contents(contents)
									.build();
			return commentRepository.save(comment);
	}
	
	public List<CommentView> getCommentListByPostId(int postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			
			int userId = comment.getUserID();
			User user = userService.getUserById(userId);
			
			CommentView commentView = CommentView.builder()
									.commentId(comment.getId())
									.userId(userId)
									.contents(comment.getContents())
									.loginId(user.getLoginId())
									.build();
			
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
	public void deleteCommentByPostID(int postId) {
		commentRepository.deleteByPostId(postId);
	}
}
