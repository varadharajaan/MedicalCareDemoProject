package com.hackthon.dbs.medicalProvider.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface S3Services {
	public void downloadFile(String keyName);
	public void uploadFile(String keyName, MultipartFile file) throws  IOException;
}
