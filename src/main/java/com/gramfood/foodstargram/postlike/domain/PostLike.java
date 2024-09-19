package com.gramfood.foodstargram.postlike.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Insert into like () value ();
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="postlike")
@Entity
public class PostLike {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="postId")
	private int postId;
	@Column(name="userId")
	private int userId;
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
}
