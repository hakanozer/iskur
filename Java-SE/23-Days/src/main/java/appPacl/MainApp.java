package appPacl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MainApp {

    public static void main(String[] args) {

        String[] dizi = { "Pzt", "Sal", "Çar", "Per", "Cum", "Cmt", "Paz" };
        Product pro = new Product("ali", 30, "ali@mail.com");
        System.out.println( pro );

        // date class
        Date date = new Date();
        System.out.println( date );


        //String stFormat = "ddMMYYYYHHmmssSSS";
        String stFormat = "dd-MM-YYYY HH:mm:ss SSS";
        DateFormat format = new SimpleDateFormat(stFormat);
        String st = format.format(date);
        System.out.println( st );

         LocalDate local = LocalDate.now();
         int day = local.getDayOfWeek().getValue();
         System.out.println( dizi[day-1] );

    }
}
