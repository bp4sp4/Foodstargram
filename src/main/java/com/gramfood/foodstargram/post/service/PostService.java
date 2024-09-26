package com.gramfood.foodstargram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gramfood.foodstargram.comment.dto.CommentView;
import com.gramfood.foodstargram.comment.service.CommentService;
import com.gramfood.foodstargram.common.FileManager;
import com.gramfood.foodstargram.dto.CardView;
import com.gramfood.foodstargram.post.domain.Post;
import com.gramfood.foodstargram.post.repository.PostRepository;
import com.gramfood.foodstargram.postlike.service.PosLikeService;
import com.gramfood.foodstargram.user.domain.User;
import com.gramfood.foodstargram.user.service.UserService;

@Service
public class PostService {

    private final PostRepository postRepository;
    private UserService userService; 
    private PosLikeService postLikeService;
    private CommentService commentService;

    // 생성자에서 UserService 주입
    public PostService(PostRepository postRepository, UserService userService, PosLikeService postLikeService, CommentService commentService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.postLikeService = postLikeService;
        this.commentService = commentService;
    }

    // 게시글 추가
    public Post addPost(int userId, String title, String contents, MultipartFile file) {
        String urlPath = null;

        // 파일이 null이 아니고 비어있지 않은 경우에만 파일 저장 처리
        if (file != null && !file.isEmpty()) {
            urlPath = FileManager.saveFile(userId, file);
        }
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .imagePath(urlPath)
                .build();

        return postRepository.save(post);
    }

    // 게시물을 내림차순
    public List<CardView> getPostList(int loginUserId) {
    	
        List<Post> postList = postRepository.findAllByOrderByIdDesc();

        List<CardView> cardViewList = new ArrayList<>();

        for (Post post : postList) {
        	
            int userId = post.getUserId();
            User user = userService.getUserById(userId);
            int likeCount = postLikeService.getLikeCount(post.getId());
            boolean isLike = postLikeService.isLikeByUserIdandPostId(loginUserId, post.getId());
            List<CommentView> commentList = commentService.getCommentListByPostId(post.getId());

            CardView cardView = CardView.builder()
			                    .postId(post.getId())
			                    .userId(userId)
			                    .contents(post.getContents())
			                    .imagePath(post.getImagePath())
			                    .loginId(user.getLoginId())
			                    .likeCount(likeCount)
			                    .isLike(isLike)
			                    .commentList(commentList)
			                    .build();

            cardViewList.add(cardView);
        }

        return cardViewList;
    }

    // id로 게시물이 있으면 나오게하고 아니면 null 반환
    public Post getPost(int id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		return post;
	}
    
    public boolean deletePost(int id, int userId) {
    	Optional<Post> optionalPost = postRepository.findByIdAndUserId(id, userId);
    	Post post = optionalPost.orElse(null);
    	
    	if(post !=null) {
    		// 좋아요 
    		postLikeService.deleteLikeByPostId(id);
    		// 댓글 
    		commentService.deleteCommentByPostID(id);
    		
    		FileManager.removeFile(post.getImagePath());
    		postRepository.delete(post);
    		return true;
    	} else {
    		return false;
    	}
    }
}
