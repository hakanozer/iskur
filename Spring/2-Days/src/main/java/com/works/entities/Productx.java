package com.works.entities;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Productx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String title;
    private int price;
    @Column(length = 500)
    private String detail;


}



