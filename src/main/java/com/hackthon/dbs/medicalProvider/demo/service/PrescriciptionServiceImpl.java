package com.hackthon.dbs.medicalProvider.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hackthon.dbs.medicalProvider.demo.DTO.StatusTypes;
import com.hackthon.dbs.medicalProvider.demo.DTO.SuccessDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;
import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.exception.FileStorageException;
import com.hackthon.dbs.medicalProvider.demo.exception.MyFileNotFoundException;
import com.hackthon.dbs.medicalProvider.demo.repository.PrescriptionRepository;
import com.hackthon.dbs.medicalProvider.demo.repository.UserRepository;

/**
 * 
 * @author Varadharajan
 *
 */
@Component
@Transactional
public class PrescriciptionServiceImpl implements PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	S3Services s3Services;
	
	
	@Value("${jsa.s3.key}")
	private String downloadKey;
	
	
	
	
	
	
	@Override
	public SuccessDTO uploadDocument(@Valid UUID userId, MultipartFile file) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String[] typeArray = fileName.split("\\.");

		// Check if the file's name contains invalid characters
		if (fileName.contains("..")) {
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}

		Prescription prescription = new Prescription();
		prescription.setStatus(StatusTypes.UPLOADED.getType());
		prescription.setCreatedAt(Instant.now());
		prescription.setData(file.getBytes());
		prescription.setS3URL("url");
		prescription.setFileType(typeArray[1]);
		prescriptionRepository.save(prescription);

		System.out.println("---------------- START UPLOAD FILE ----------------");
		
		s3Services.uploadFile(String.valueOf(prescription.getId())+"."+typeArray[1], file);
		
		prescription.setOwner(getUser(userId));
		SuccessDTO dto = new SuccessDTO();
		dto.setId(prescription.getId());
		return dto ;
	}

	public Prescription getFile(UUID fileId) {
		
		System.out.println("---------------- START DOWNLOAD FILE ----------------");
		s3Services.downloadFile(String.valueOf(fileId)+".txt");
		return prescriptionRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
		
	}
	
	public User getUser(UUID fileId) {
		return userRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("user not found with id " + fileId));
	}

}
