package com.gramfood.foodstargram.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gramfood.foodstargram.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
	

	public List<Post> findAllByOrderByIdDesc();
	
}
