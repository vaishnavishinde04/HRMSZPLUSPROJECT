package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.EmployeeMaster;
import java.util.List;
import java.util.Optional;


public interface EmployeeDAO extends CrudRepository<EmployeeMaster, Long>{

	Optional<EmployeeMaster> findById(long id);

}
