package com.example.mysql.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "t_order")
public class OrderDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long user_id;

    public OrderDO() {
    }

    public OrderDO(String name) {
        this.name = name;
    }

}
