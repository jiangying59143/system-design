package com.example.mysql;

import com.example.mysql.model.UserDO;
import com.example.mysql.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

@SpringBootTest
class MysqlApplicationTests {



    @Autowired
    UserRepo userRepo;

    static Long[] ids = new Long[100];

    @Test
    void testInsert() {
        for (int i = 0; i < 100; i++) {
            UserDO userDO = new UserDO("test"+ System.currentTimeMillis() + "-" + i, i);
            userRepo.save(userDO);
            System.out.println(userDO.getId());
            ids[i] = userDO.getId();
        }
    }

    @Test
    @DependsOn("testInsert")
    void testSelect() {
        for (Long id : ids) {
            System.out.println(id + ":" + userRepo.findById(id).get().getName());
        }
    }

}
