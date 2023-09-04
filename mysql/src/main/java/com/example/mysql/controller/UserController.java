package com.example.mysql.controller;

import com.example.mysql.dto.UserDto;
import com.example.mysql.model.UserDO;
import com.example.mysql.repo.UserRepo;
import com.example.mysql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/insert")
    public @ResponseBody UserDO insertUser(String name, Integer age) {
        UserDO userDO = new UserDO();
        userDO.setName(name);
        userDO.setAge(age);
        log.info("start insert");
        userDO = userRepo.save(userDO);
        return userDO;
    }

    @GetMapping("/{name}")
    public @ResponseBody UserDO findUser(@PathVariable("name") String name) {
        return userRepo.findByName(name);
    }
}
