package com.hackthon.dbs.medicalProvider.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.hackthon.dbs.medicalProvider.demo.Utility;

@Service
public class S3ServicesImpl implements S3Services {
	
	private Logger logger = LoggerFactory.getLogger(S3ServicesImpl.class);
	
	@Autowired
	private AmazonS3 s3client;

	@Value("${jsa.s3.bucket}")
	private String bucketName;

	@Override
	public void downloadFile(String keyName) {
		
		try {
			
            System.out.println("Downloading an object");
            S3Object s3object = s3client.getObject(new GetObjectRequest(
            		bucketName, keyName));
            System.out.println("Content-Type: "  + 
            		s3object.getObjectMetadata().getContentType());
            Utility.displayText(s3object.getObjectContent());
            logger.info("===================== Import File - Done! =====================");
            
        } catch (AmazonServiceException ase) {
        	logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
        	logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        } catch (IOException ioe) {
        	logger.info("IOE Error Message: " + ioe.getMessage());
		}
	}

	@Override
	public void uploadFile(String keyName,MultipartFile file) throws IOException {
		
		try {
			File convFile = new File(file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			
	        s3client.putObject(new PutObjectRequest(bucketName, keyName, convFile));
	        logger.info("===================== Upload File - Done! =====================");
	        
		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }
	}

}
