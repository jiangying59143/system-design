package com.example.mysql.repo;

import com.example.mysql.model.OrderDO;
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
public interface OrderRepo extends JpaRepository<OrderDO, Long> {

    @Override
    @Modifying
    <S extends OrderDO> S save(S s);
}
