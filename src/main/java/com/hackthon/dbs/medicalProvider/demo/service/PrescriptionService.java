package com.hackthon.dbs.medicalProvider.demo.service;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.hackthon.dbs.medicalProvider.demo.DTO.SuccessDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;

/**
 * 
 * @author Varadharajan
 *
 */
public interface PrescriptionService {
	
	 SuccessDTO uploadDocument(@Valid UUID userId,
	            MultipartFile file) throws IOException;

	Prescription getFile(UUID fileId);

}
