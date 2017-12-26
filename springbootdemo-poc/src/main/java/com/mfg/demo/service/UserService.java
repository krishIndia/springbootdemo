package com.mfg.demo.service;

import java.util.List;

import com.mfg.demo.model.User;

public interface UserService {

	void save(User user);
	
	User findById(Long id);

	List<User> getUsers();
	
	User findByUsername(String username);

	//User update(User user);
}
