package useInterface;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {

    static IUser objUser = null;
    static  String state;


    public static void main(String[] args) {

        System.out.println( state );
        System.out.println( state.length() );

        UserImpl user = new UserImpl();
        String name = user.userName(10);
        System.out.println( name );
        int sm = user.sum(10, 20);
        System.out.println( "Sm :" + sm );
        System.out.println( IUser.city );

        // interfaceler implements olarak verildiğinde davranışa aktarır?
        IUser us = new UserImpl();
        IProfile usx = new UserImpl();
        String namex = "Ali";
        usx.userProfile(10);

        // interface'in nesne haline gelmesi
        objUser = new IUser() {
            @Override
            public String userName(int uid) {
                return null;
            }

            @Override
            public boolean userChangePassword(int uid, String newPassword) {
                return false;
            }
        };
        objUser.userChangePassword(100, "");

        List<String> ls = new ArrayList<>();
        ls.forEach();


    }
}
