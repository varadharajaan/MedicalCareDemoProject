package com.hackthon.dbs.medicalProvider.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hackthon.dbs.medicalProvider.demo.service.S3Services;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages= {"com.hackthon.dbs.medicalProvider"})
public class DemoApplication implements CommandLineRunner {
	@Autowired
	S3Services s3Services;
	
	@Value("${jsa.s3.uploadfile}")
	private String uploadFilePath;
	
	@Value("${jsa.s3.key}")
	private String downloadKey;

	@Autowired
    private DataInitializerImpl dataInitializer;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	 @Override
	    public void run(String... args) {
	        dataInitializer.initData();
	    }
	 
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
