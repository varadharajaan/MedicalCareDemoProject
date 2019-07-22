package com.hackthon.dbs.medicalProvider.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackthon.dbs.medicalProvider.demo.DTO.UserDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.Login;
import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.service.IUserService;



@RestController
@RequestMapping("/pharma")
public class UserAuthController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO login(@RequestBody Login login) {
		return  userService.login(login.getUserId(), login.getPassword());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return String.valueOf(user.getId());

	}

}
