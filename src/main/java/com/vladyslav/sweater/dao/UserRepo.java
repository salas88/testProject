package com.vladyslav.sweater.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vladyslav.sweater.entity.Message;

public interface UserRepo extends CrudRepository<Message, Integer>{
	
	List<Message> findByTag(String tag);

}
