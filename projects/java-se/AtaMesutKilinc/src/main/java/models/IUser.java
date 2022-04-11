package models;

import props.User;

import java.util.List;

public interface IUser {
    //prop döndürcex tek tek özellikleri yazmak için


    int userUpdate(User user);
    boolean userLogin(String email,String password);  //user dönmesi bir seçenek olabilir. email. password dön



}
