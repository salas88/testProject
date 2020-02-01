package com.vladyslav.sweater.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vladyslav.sweater.entity.User;


public interface UserRepo extends JpaRepository<User,Long> {
	
	User findByUsername(String username);

}
