package com.HRMS.service.IMPL;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.LoginDAO;
import com.HRMS.model.LoginMaster;

import jakarta.annotation.PostConstruct;

@Service
public class LoginDefaultDataService {

    @Autowired
    private LoginDAO logindao;

    
    @PostConstruct
    public void initializeDefaultData() {
        if (logindao.count() == 0) {
            LoginMaster defaultUser = new LoginMaster();
            defaultUser.setUserId(0);
            defaultUser.setUsername("Admin");
            defaultUser.setEmail("madhavlonkar2@gmail.com");
            String password=BCrypt.hashpw("Admin", BCrypt.gensalt());
            defaultUser.setPassword(password);
            defaultUser.setRole("Admin");
            logindao.save(defaultUser);
        }
    }
}
