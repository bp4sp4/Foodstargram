package com.gramfood.foodstargram.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gramfood.foodstargram.common.HashingEncoder;
import com.gramfood.foodstargram.common.MD5HashingEncoder;
import com.gramfood.foodstargram.user.domain.User;
import com.gramfood.foodstargram.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
    private HashingEncoder encoder;
    private MD5HashingEncoder md5Encoder;


    public UserService(UserRepository userRepository, @Qualifier("md5Hashing") HashingEncoder encoder, MD5HashingEncoder md5Encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.md5Encoder = md5Encoder;
    }
    

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.selectCountByLoginId(loginId);
        return count != 0;
    }
    
	
	public int addUser(
			String loginId
			, String password
			, String name,
			String email) {
		
		String encryptPassword = encoder.encode(password);
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}
	

    public User getUser(String loginId, String password) {
    	
        String encryptPassword = md5Encoder.encode(password);
        
        return userRepository.selectUser(loginId, encryptPassword);
    }
    
    public User getUserById(int id) {
    	return userRepository.selectUserById(id);
    }
}
