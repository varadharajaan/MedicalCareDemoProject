package com.hackthon.dbs.medicalProvider.demo.service;

import com.hackthon.dbs.medicalProvider.demo.DTO.UserDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.User;

public interface IUserService {

	public void saveUser(User user);

	public User findById(String userId);

	public UserDTO login(String userId, String password);
}
