package com.example.mysql.repo;

import com.example.mysql.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wang Erniu
 * @date 2021/03/04 16:17
 */
@Repository
public interface UserRepo extends JpaRepository<UserDO, Long> {

    @Transactional
    @Modifying
    @Query(value = "update UserDO set age = :age where name = :name")
    void updateAge(@Param("name") String name, @Param("age") Integer age);

    UserDO findByName(String name);

    @Override
    @Modifying
    <S extends UserDO> S save(S s);
}
