package appPack;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Action ac = new Action();
        System.out.println( ac.age );
        System.out.println( ac.f1 );

        // dahili sınıf çağrılması
        Action.Settings st = new Action().new Settings();
        System.out.println( st.d1 );

        // final anahtar kelimesi
        // sabit bir değişken kurulması istendiğinde değerlendirilir.
        final String city = "Ankara";
        // city = "İstanbul";
        final String state;
        state = "Beşiktaş";

        System.out.println( UseStatic.name );

        int rnd = UseStatic.rnd.nextInt(100);
        int rnd1 = UseStatic.rnd.nextInt(100);
        System.out.println( rnd );

        int sm = UseStatic.sum(40, 50);
        System.out.println( "Sum :"+ sm );

        // nesne üretimi
        Product pr = new Product();





    }

}
