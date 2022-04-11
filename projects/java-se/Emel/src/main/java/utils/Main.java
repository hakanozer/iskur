package utils;

import models.UserImpl;
import props.User;

public class Main {
    public static void main(String[] args) {
        new Generator();
        UserImpl us = new UserImpl();
        User u = new User(0, "Mustafa", "Gültekin", "mustafa@gmail.com", "12345");
        int status = us.userInsert(u);
        if ( status > 0) {
            System.out.println("Ekleme Başarılı");
        }else {
            System.out.println("Ekleme hatası");
        }
    }
}
