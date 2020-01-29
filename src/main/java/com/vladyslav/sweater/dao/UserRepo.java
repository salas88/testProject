package com.vladyslav.sweater.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vladyslav.sweater.entity.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	
	List<User> findByTag(String tag);

}
