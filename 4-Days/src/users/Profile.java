package users;

import appPack.Action;
import appPack.Base;

public class Profile extends Base {

    public void call() {
        // System.out.println( surname );
        Action ac = new Action();
        System.out.println( ac.name );

        // dahili sınıf çağrılması
        Action.Settings st = new Action().new Settings();
        // System.out.println( st.d1 );


    }

}
