package com.gramfood.foodstargram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gramfood.foodstargram.common.FileManager;
import com.gramfood.foodstargram.dto.CardView;
import com.gramfood.foodstargram.post.domain.Post;
import com.gramfood.foodstargram.post.repository.PostRepository;
import com.gramfood.foodstargram.user.domain.User;
import com.gramfood.foodstargram.user.service.UserService;

@Service
public class PostService {

    private final PostRepository postRepository;
    private UserService userService; 

    // 생성자에서 UserService 주입
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
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
    public List<CardView> getPostList() {
    	
        List<Post> postList = postRepository.findAllByOrderByIdDesc();

        List<CardView> cardViewList = new ArrayList<>();

        for (Post post : postList) {
        	
            int userId = post.getUserId();
            User user = userService.getUserById(userId);

            CardView cardView = CardView.builder()
			                    .postId(post.getId())
			                    .userId(userId)
			                    .contents(post.getContents())
			                    .imagePath(post.getImagePath())
			                    .loginId(user.getLoginId())
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
}
