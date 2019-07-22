package com.hackthon.dbs.medicalProvider.demo.DTO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackthon.dbs.medicalProvider.demo.scheduler.RetailerMapper;

@Repository
public interface RetailerMapperRepository extends CrudRepository<RetailerMapper, Integer>{

}
