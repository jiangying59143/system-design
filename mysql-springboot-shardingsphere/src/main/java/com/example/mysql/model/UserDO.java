package com.example.mysql.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "t_user")
public class UserDO {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Integer age;

    public UserDO() {
    }

    public UserDO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
