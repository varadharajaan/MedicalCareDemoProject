package com.hackthon.dbs.medicalProvider.demo.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hackthon.dbs.medicalProvider.demo.DTO.RetailerMapperRepository;
import com.hackthon.dbs.medicalProvider.demo.DTO.StatusTypes;
import com.hackthon.dbs.medicalProvider.demo.DTO.UserTypes;
import com.hackthon.dbs.medicalProvider.demo.entity.Prescription;
import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.repository.PrescriptionRepository;
import com.hackthon.dbs.medicalProvider.demo.repository.UserRepository;

@Component
public class CheckForNewPrescriptionEntry {

	@Autowired
	PrescriptionRepository prescRepo;

	@Autowired
	RetailerMapperRepository retailerMapper;

	@Value("${commission.onsale}")
	private String commissionThreshold;

	@Autowired
	UserRepository userRepo;

	public String getNewPrescriptionEntry() {
		List<Prescription> prescListRaiseAlert = new ArrayList<>();
		Iterable<Prescription> itr = prescRepo.findAll();

		for (Prescription p : itr) {
			if (StatusTypes.UPLOADED.toString().equalsIgnoreCase(p.getStatus().toString()))
				prescListRaiseAlert.add(p);
		}
		boolean isRecordFound = false;
		for (Prescription p : prescListRaiseAlert) {
			UUID id = p.getId();
			p.setStatus(StatusTypes.IN_PROGRESS.getType());
			prescRepo.save(p);
			String strS3Url = p.getS3URL();
			User usr = p.getOwner();
			String strPinCode = usr.getPinCode();

			// Logic to identify the correct retailer goes here
			List<User> rtlLsUser = userRepo.findByPinCodeAndUserTypes(strPinCode, UserTypes.RETAILER.getType());
			for (User us : rtlLsUser) {
				String strComOnSale = us.getCommissionOnSale();

				if (null != strComOnSale && !"".equals(strComOnSale)) {
					Float fComOnSale = Float.parseFloat(strComOnSale);
					Float fComThreshold = Float.parseFloat(commissionThreshold);
					if (fComOnSale <= fComThreshold) {
						RetailerMapper rMapper = new RetailerMapper(us.getId(), id, StatusTypes.IN_PROGRESS, strS3Url);
						retailerMapper.save(rMapper);
					}
				}
			}
			// End

			isRecordFound = true;
		}
		String strCheckPresc = "";
		if (isRecordFound)
			strCheckPresc = "Data added to RetailMapper";
		else
			strCheckPresc = "No New records";
		return strCheckPresc;
	}

}
