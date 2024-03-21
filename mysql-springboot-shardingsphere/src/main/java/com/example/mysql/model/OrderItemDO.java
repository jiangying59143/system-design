package com.example.mysql.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "t_order_item")
public class OrderItemDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long user_id;

    Long order_id;

    public OrderItemDO() {
    }

    public OrderItemDO(String name) {
        this.name = name;
    }

}
