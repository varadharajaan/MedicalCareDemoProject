package com.hackthon.dbs.medicalProvider.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackthon.dbs.medicalProvider.demo.DTO.UserDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.repository.IUserDao;
import com.hackthon.dbs.medicalProvider.demo.service.IUserService;
import com.hackthon.dbs.medicalProvider.demo.utils.PasswordUtils;


@Service
@Transactional
public class UserService implements IUserService {

	private static final int SALT_SIZE = 30;
	static final String salt = PasswordUtils.getSalt(SALT_SIZE);
	
	@Autowired
	private IUserDao userDao;

	@Override
	public void saveUser(User user) {
		String securePassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
		user.setPassword(securePassword);
		userDao.saveUser(user);
	}

	@Override
	public User findById(String userId) {
		return userDao.findById(userId);
	}

	@Override
	public UserDTO login(String userId, String password) {
		String securePassword = PasswordUtils.generateSecurePassword(password, salt);
		User user= userDao.login(userId, securePassword);
		
		UserDTO dto = new UserDTO();
		if(null!=user) {
		dto.setId(user.getId());
		dto.setSuccess(true);
		}
		
		return dto;
	}
}
