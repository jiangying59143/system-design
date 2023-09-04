package com.example.mysql.service;

import com.example.mysql.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void testUpdate(String name, Integer age) {
        userRepo.updateAge(name, age);
    }

    public Integer testSelect(String name) {
        return userRepo.findByName(name).getAge();
    }

}
