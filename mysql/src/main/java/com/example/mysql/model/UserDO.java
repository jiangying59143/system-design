package com.example.mysql.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer age;

    public UserDO() {
    }

    public UserDO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
