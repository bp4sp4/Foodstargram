package com.gramfood.foodstargram.postlike.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gramfood.foodstargram.postlike.domain.PostLike;

import jakarta.transaction.Transactional;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    // SELECT count(*) FROM `like` WHERE `postId` = #{postId}
    public int countByPostId(int postId);
    
    // SELECT count(*) FROM `like` WHERE `userId` = #{userId} AND `postId` = #{postId}
    public int countByUserIdAndPostId(int userId, int postId);
    
    // SELECT * FROM `like` WHERE `postId` = #{postId} AND `userId` = #{userId}
    public Optional<PostLike> findByPostIdAndUserId(int postId, int userId);
    
    // transaction 
    // SELECT * FROM 'like' WHERE 'postId'  #{postId}
    // DELETE FROM 'like' WHERE 'postID' = #{postId}
    @Transactional
    public void deleteByPostId(int postId);
}
