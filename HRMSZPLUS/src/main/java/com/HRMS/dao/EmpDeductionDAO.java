package com.HRMS.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.EmpDeductionMaster;

public interface EmpDeductionDAO extends CrudRepository<EmpDeductionMaster, Long> {
	Optional<EmpDeductionMaster> findById(Integer id);
	List<EmpDeductionMaster> findByEmployeeEmpId(Long employeeId);
	EmpDeductionMaster findByDeductionDeductionIdAndEmployeeEmpId(long deductionId, long employeeId);
}
