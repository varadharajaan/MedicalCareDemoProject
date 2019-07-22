package com.hackthon.dbs.medicalProvider.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

}
