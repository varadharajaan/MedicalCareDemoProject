package com.hackthon.dbs.medicalProvider.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;
import com.hackthon.dbs.medicalProvider.demo.repository.PrescriptionRepository;

@Component
public class MarketPlaceCLRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MarketPlaceCLRunner.class);

	@Override
	public void run(String... args) throws Exception {
		

	}

//	@Autowired
//	private PrescriptionRepository prescRepository;
//	
//	@Autowired
//	private RetailerMapperRepository retailMapperRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		
//		prescRepository.save(new Prescription("http://abc.def.com", "Uploaded","4567"));
//		prescRepository.save(new Prescription("http://abc.ghi.com", "Accepted","8910"));
//
//		
//		retailMapperRepo.save(new RetailerMapper(222, 456));
//
//	}

}
