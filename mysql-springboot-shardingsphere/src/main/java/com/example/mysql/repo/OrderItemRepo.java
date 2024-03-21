package com.example.mysql.repo;

import com.example.mysql.model.OrderItemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Erniu
 * @date 2021/03/04 16:17
 */
@Repository
public interface OrderItemRepo extends JpaRepository<OrderItemDO, Long> {

    @Override
    @Modifying
    <S extends OrderItemDO> S save(S s);
}
