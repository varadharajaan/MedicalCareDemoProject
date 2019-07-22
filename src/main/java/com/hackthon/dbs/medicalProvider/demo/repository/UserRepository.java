package com.hackthon.dbs.medicalProvider.demo.repository;

import java.util.List;
import java.util.UUID;


import com.hackthon.dbs.medicalProvider.demo.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackthon.dbs.medicalProvider.demo.entity.User;

public interface UserRepository extends JpaRepository<User,UUID>{
	

	
	@Query("select users from User users where users.email = :email")
	User findByEmailIgnoreCase(@Param("email")String email);

	  List<User> findByPinCodeAndUserTypes(final String pinCode, final String userTypes);


}
