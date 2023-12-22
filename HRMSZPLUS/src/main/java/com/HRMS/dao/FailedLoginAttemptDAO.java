package com.HRMS.dao;

import com.HRMS.model.FailedLoginAttempt;
import org.springframework.data.repository.CrudRepository;

public interface FailedLoginAttemptDAO extends CrudRepository<FailedLoginAttempt, Long> {

    FailedLoginAttempt findByUsername(String username);
}
