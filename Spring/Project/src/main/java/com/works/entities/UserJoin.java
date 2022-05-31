package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  username;
    private String useremail;
    private String rolename;

}
