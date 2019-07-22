package com.hackthon.dbs.medicalProvider.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
	
	@RequestMapping("/")
	public String firstPage() {
		return "Hello medicalStore application successfully deployed and started...";
	}
	

}
