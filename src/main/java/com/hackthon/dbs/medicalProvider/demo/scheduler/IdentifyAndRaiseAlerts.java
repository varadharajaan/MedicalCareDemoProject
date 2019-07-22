package com.hackthon.dbs.medicalProvider.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IdentifyAndRaiseAlerts {
	
	@Autowired
	CheckForNewPrescriptionEntry checkForNewEntry;
	
	private static final Logger logger = LoggerFactory.getLogger(IdentifyAndRaiseAlerts.class);
	
	 @Scheduled(fixedRate = 180000, initialDelay = 30000)
	 public void scheduleTaskWithFixedDelay() {
		 
		 logger.info("I am scheduling the application");
		 String strVal = checkForNewEntry.getNewPrescriptionEntry();
		 logger.info("scheduleTaskWithFixedDelay result is"+strVal);
	 }
	

}
