import models.UserImpl;
import props.User;
import utils.DB;
import utils.Generator;

import java.util.List;

public class MainClass {
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

        User c = new User(1, "Hasan", "Gültekin", "ali@gmail.com", "12345");
        int statusUpdate = us.userUpdate(c);
        if ( statusUpdate > 0) {
            System.out.println("Update Başarılı");
        }else {
            System.out.println("Update hatası");
        }

        List<User> ls = us.userList();
        for ( User item : ls ) {
            System.out.println( item.getEmail() );
        }

        // user login control
        boolean loginStatus = us.userLogin("ali@gmail.com", "12345");
        System.out.println("Login status : " + loginStatus);

        // user single
        User profile = us.userSingle(1);
        System.out.println( profile );
    }
}
