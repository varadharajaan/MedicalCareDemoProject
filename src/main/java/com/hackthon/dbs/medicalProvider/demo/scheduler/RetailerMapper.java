package com.hackthon.dbs.medicalProvider.demo.scheduler;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hackthon.dbs.medicalProvider.demo.DTO.StatusTypes;

@Entity
@Table(name = "RetailerMapper")
public class RetailerMapper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "retailerId")
	private UUID retailerId;
	@Column(name = "prescriptionId")
	private UUID prescriptionId;
	
	@Column(name="status",nullable=false)
	private StatusTypes status;

	@Column(name = "s3url")
	private String s3url;

	public RetailerMapper() {

	}

	public RetailerMapper(final UUID retailerId, final UUID prescriptionId, final StatusTypes status, final String s3url) {
		this.retailerId = retailerId;
		this.prescriptionId = prescriptionId;
		this.status = status;
		this.s3url = s3url;
	}
	

	public StatusTypes getStatus() {
		return status;
	}

	public void setStatus(StatusTypes status) {
		this.status = status;
	}

	

	public String getS3url() {
		return s3url;
	}

	public void setS3url(String s3url) {
		this.s3url = s3url;
	}

	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(UUID retailerId) {
		this.retailerId = retailerId;
	}

	public UUID getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(UUID prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

}
