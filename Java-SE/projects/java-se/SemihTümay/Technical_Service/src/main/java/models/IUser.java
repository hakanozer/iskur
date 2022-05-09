package models;

import props.User;

public interface IUser {
    boolean userLogin(String email, String password);
    int userUpdate(User user);
}
