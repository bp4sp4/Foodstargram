package com.gramfood.foodstargram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gramfood.foodstargram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
