package com.hackthon.dbs.medicalProvider.demo.repository;

import com.hackthon.dbs.medicalProvider.demo.entity.User;

public interface IUserDao {
	public void saveUser(User user);
	public User findById(String userId);
	
	public User login(String userId, String password);
}
