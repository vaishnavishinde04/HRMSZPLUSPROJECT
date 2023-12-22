package com.HRMS.service.IMPL;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.HRMS.dao.DepartmentDAO;
import com.HRMS.model.DepartmentMaster;
import com.HRMS.service.DepartmentService;

@Service
public class DepartmentServiceIMPL implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceIMPL.class);

    @Autowired
    private DepartmentDAO departmentDao;

    @Override
    public List<DepartmentMaster> findAllDepartments() {
        try {
            return (List<DepartmentMaster>) departmentDao.findAll();
        } catch (DataAccessException e) {
            log.error("Failed to retrieve departments: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DepartmentMaster addDepartment(DepartmentMaster department) {
        try {
            return departmentDao.save(department);
        } catch (DataAccessException e) {
            log.error("Failed to save department: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DepartmentMaster findById(int id) {
        Optional<DepartmentMaster> department = departmentDao.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            log.error("Department with ID {} not found.", id);
            return null;
        }
    }

    @Override
    public DepartmentMaster updateDepartment(DepartmentMaster department, int id) {
        try {
            department.setDepartmentId(id);
            return departmentDao.save(department);
        } catch (DataAccessException e) {
            log.error("Failed to update department with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteDepartment(int id) {
        try {
            departmentDao.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Failed to delete department with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
        }
    }
}
