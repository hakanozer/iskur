package models;

import props.User;

public interface IUser {
    int userUpdate(User user);
    boolean userLogin(String email,String password);


}
