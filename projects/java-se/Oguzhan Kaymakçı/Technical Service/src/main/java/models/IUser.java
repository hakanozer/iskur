package models;

import props.User;

import java.util.List;

public interface IUser {

    int userUpdate( User user );
    boolean userLogin(String email, String password);

}
