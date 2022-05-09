package appPack;

import props.User;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        ArrayList<User> ls = new ArrayList<>();

        User u1 = new User();
        u1.setName("Ali");
        u1.setPassword("12345");
        u1.setEmail("ali@mail.com");
        u1.setAge(30);
        ls.add(u1);

        User u2 = new User("Erkan", "erkan@mail.com", "12345", 40);
        ls.add(u2);
        System.out.println( ls.size() );
        System.out.println( ls );

        System.out.println("==========================================");

        // forEach
        for( User item : ls ) {
            System.out.println( item.getName() + " " + item.getEmail() );
        }

        List<User> lsx = new ArrayList<>();
        User us = null;
        for (int i = 0; i < 10; i++) {
            us = new User();
            us.setName("ali"+i);
            us.setEmail("mail"+i);
            us.setAge(i);
            us.setPassword("123"+i);
            lsx.add(us);
            us = null; // bellekten hemen kaldır.
        }
        System.out.println( lsx );



    }
}
