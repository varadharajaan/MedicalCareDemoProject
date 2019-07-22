package com.hackthon.dbs.medicalProvider.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hackthon.dbs.medicalProvider.demo.DTO.StatusTypes;

/**
 * 
 * @author varadharajan
 *
 */
@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity{
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name="s3URL", nullable=false)
	private String s3URL;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Lob
    private byte[] data;
	
	private String fileType;
	
	private String fileName;
	
	public User getOwner() {
		return user;
	}

	public void setOwner(User owner) {
		this.user = owner;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getS3URL() {
		return s3URL;
	}

	public void setS3URL(String s3url) {
		s3URL = s3url;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((s3URL == null) ? 0 : s3URL.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescription other = (Prescription) obj;
		if (s3URL == null) {
			if (other.s3URL != null)
				return false;
		} else if (!s3URL.equals(other.s3URL))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prescription [s3URL=" + s3URL + ", status=" + status + "]";
	}

}
