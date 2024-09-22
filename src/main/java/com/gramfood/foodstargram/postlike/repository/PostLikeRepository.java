package com.gramfood.foodstargram.postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gramfood.foodstargram.postlike.domain.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {

}
