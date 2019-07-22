package com.hackthon.dbs.medicalProvider.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.repository.UserRepository;
import com.hackthon.dbs.medicalProvider.demo.utils.PasswordUtils;

/**
 * 
 * @author Varadharajan
 *
 */
@Component
@Transactional
public class DataInitializerImpl  {

	@Autowired
	UserRepository userRepository;
	public
	 void initData() {
		
		User user = new User();
		user.setEmail("varathu09@gmail.com");
		user.setFirstName("varadharajan");
		user.setLastName("damotharan");
		
		user.setPassword(PasswordUtils.generateSecurePassword("123456", "salt"));
		
		userRepository.save(user);
		
		
	}

	
}
