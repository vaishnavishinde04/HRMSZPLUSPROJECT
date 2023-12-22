package com.HRMS.service.IMPL;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.HRMS.dao.BankDAO;
import com.HRMS.model.BankMaster;
import com.HRMS.service.BankService;

@Service
public class BankServiceIMPL implements BankService {

    private static final Logger log = LoggerFactory.getLogger(BankServiceIMPL.class);

    @Autowired
    private BankDAO bankDao;

    @Override
    public List<BankMaster> findAllBanks() {
        try {
            return (List<BankMaster>) bankDao.findAll();
        } catch (DataAccessException e) {
            log.error("Failed to retrieve banks: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BankMaster addBank(BankMaster bank) {
        try {
            return bankDao.save(bank);
        } catch (DataAccessException e) {
            log.error("Failed to save bank: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BankMaster findById(int id) {
        Optional<BankMaster> bank = bankDao.findById(id);
        if (bank.isPresent()) {
            return bank.get();
        } else {
            log.error("Bank with ID {} not found.", id);
            return null;
        }
    }

    @Override
    public BankMaster updateBank(BankMaster bank, int id) {
        try {
            bank.setBankId(id);
            return bankDao.save(bank);
        } catch (DataAccessException e) {
            log.error("Failed to update bank with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBank(int id) {
        try {
            bankDao.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Failed to delete bank with ID {}: {}", id, e.getMessage());
            e.printStackTrace();
        }
    }
}
