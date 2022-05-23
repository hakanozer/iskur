package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private String name;
    private String email;
    private String password;

}
