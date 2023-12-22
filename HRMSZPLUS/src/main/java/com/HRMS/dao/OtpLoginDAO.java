package com.HRMS.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.HRMS.model.OtpLoginMaster;


public interface OtpLoginDAO extends CrudRepository<OtpLoginMaster, Integer>{

	public OtpLoginMaster findByName(String Username);

	@Transactional
	@Modifying
    @Query("DELETE FROM OtpLoginMaster otp WHERE otp.ts < :cutoffTime")
    void deleteExpiredOtpRecords(@Param("cutoffTime") Timestamp cutoffTime);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM FailedLoginAttempt acc WHERE acc.ts < :cutoffTime")
	void deleteLockedAccounts(@Param("cutoffTime") Timestamp cutoffTime);

	@Transactional
    @Modifying
    @Query("DELETE FROM OtpLoginMaster otp WHERE otp.name = :username")
    void deleteByUsername(@Param("username") String username);


}
