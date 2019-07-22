package com.hackthon.dbs.medicalProvider.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hackthon.dbs.medicalProvider.demo.DTO.SuccessDTO;
import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;
import com.hackthon.dbs.medicalProvider.demo.exception.FileStorageException;
import com.hackthon.dbs.medicalProvider.demo.service.PrescriptionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Varadharajan
 *
 */
@RestController
@RequestMapping("/prescriptionManagement/v1")
public class PrescriciptionController {

	@Autowired
	PrescriptionService prescriptionService;
	private static final Logger logger = LoggerFactory.getLogger(PrescriciptionController.class);

	@ApiOperation(value = "create file metadata")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Given user id not found"),
			@ApiResponse(code = 500, message = "Server Error"), })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/uploadFile")
	public SuccessDTO uploadPrescription(@RequestParam("userId") @Valid final String userId,
			@RequestParam("file") final MultipartFile file) throws IOException {

		SuccessDTO response = prescriptionService.uploadDocument(UUID.fromString(userId), file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/prescriptionManagement/v1/downloadFile/")
				.path(String.valueOf(response.getId())).toUriString();
		response.setFileDownloadUri(fileDownloadUri);
		return response;
	}
	@ApiOperation(value = "create for multiple files")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Given user id not found"),
			@ApiResponse(code = 500, message = "Server Error"), })
	@PostMapping("/uploadMultipleFiles")
    public List<SuccessDTO> uploadMultipleFiles(@RequestParam("userId") @Valid final String userId,@RequestParam("files") MultipartFile[] files) throws IOException{
        return Arrays.asList(files)
                .stream()
                .map(file -> {
					try {
						return uploadPrescription(userId,file);
					} catch (IOException e) {
						throw new  FileStorageException("error");
					}
				})
                .collect(Collectors.toList());
    }

	@ApiOperation(value = "download file")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Given user id not found"),
			@ApiResponse(code = 500, message = "Server Error"), })
	
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@Valid @PathVariable final String fileId) {
        // Load file from database
        Prescription prescription = prescriptionService.getFile(UUID.fromString(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + prescription.getFileName() + "\"")
                .body(new ByteArrayResource(prescription.getData()));
    }
	

}
