package model;

import props.User;

import java.util.List;

public interface IUser {
    //sqlde bir update,insert,delete komutu çalıştırıldığında etkilenen satır sayısı geriye döner
    int userInsert(User user);
    int userUpdate(User user);
    int userDelete(int uid);
    List<User> userList();
    boolean userLogin(String email,String password);
    User userSingle(int uid);

}
