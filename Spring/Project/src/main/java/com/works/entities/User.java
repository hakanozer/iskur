package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    @Length(message = "Min 3 Max 33 Giribilirsin", min = 3, max = 33)
    @NotBlank(message = "Adı bilgisi giriniz!")
    private String name;

    @NotBlank(message = "email alanı doldurun")
    @Email(message = "E-Mail formatı hatalı")
    private String email;

    @NotBlank(message = "password alanı doldurun")
    @Length(message = "Min 3 Max 10", min = 3, max = 10)
    private String password;

}
