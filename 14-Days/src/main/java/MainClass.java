import models.UserImpl;
import props.User;
import utils.DB;
import utils.Generator;

public class MainClass {
    public static void main(String[] args) {

        new Generator();
        UserImpl us = new UserImpl();
        User u = new User(0, "Ulaş", "Gültekin", "ulas@gmail.com", "12345");
        int status = us.userInsert(u);
        if ( status > 0) {
            System.out.println("Ekleme Başarılı");
        }else {
            System.out.println("Ekleme hatası");
        }
    }
}
