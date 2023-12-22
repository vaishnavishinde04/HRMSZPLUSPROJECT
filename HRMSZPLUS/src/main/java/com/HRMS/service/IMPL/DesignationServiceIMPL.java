package com.HRMS.service.IMPL;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.HRMS.dao.DesignationDAO;
import com.HRMS.model.DesignationMaster;
import com.HRMS.service.DesignationService;

@Service
public class DesignationServiceIMPL implements DesignationService {

    private static final Logger log = LoggerFactory.getLogger(DesignationServiceIMPL.class);

    @Autowired
    private DesignationDAO designationDao;

    @Override
    public List<DesignationMaster> findAllDesignations() {
        try {
            return (List<DesignationMaster>) designationDao.findAll();
        } catch (DataAccessException e) {
            log.error("Failed to retrieve designations: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DesignationMaster addDesignation(DesignationMaster designation) {
        try {
            return designationDao.save(designation);
        } catch (DataAccessException e) {
            log.error("Failed to save designation: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DesignationMaster findById(int id) {
        Optional<DesignationMaster> designation = designationDao.findById(id);
        if (designation.isPresent()) {
            return designation.get();
        } else {
            log.error("Designation with ID {} not found.", id);
            return null;
        }
    }

    @Override
    public DesignationMaster updateDesignation(DesignationMaster designation, int id) {
        try {
            designation.setDesignationId(id);
            return designationDao.save(designation);
        } catch (DataAccessException e) {
            log.error("Failed to update designation with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteDesignation(int id) {
        try {
            designationDao.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Failed to delete designation with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
        }
    }
}
