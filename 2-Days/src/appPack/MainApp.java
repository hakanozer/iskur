package appPack;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        // kullanıcıdan gelen veriler String olarak bizlere ulaşır.
        // String ifadelerin diğer türlere dönüşümü için kullanılır.
        // String to int

        String stNumber = "40";
        int cNumber = Integer.parseInt(stNumber);
        System.out.println(cNumber + 10);

        // String to Float
        String stFloat = "34.6";
        float cFloat = Float.parseFloat(stFloat);
        System.out.println( cFloat + 10 );

        // ilkel türlerin kendi arasında dönüşümü
        int num1 = 150;
        float fNum1 = (float) num1;
        System.out.println(fNum1);

        // char to int
        char c = '9';
        String stC = String.valueOf(c);
        int cInt = Integer.parseInt(stC);
        //int cInt = (int) c;
        System.out.println(cInt);

        // kullanıcıdan karakter girişi istenir
        // girilen karakterin ascii kod karşılığı verilecek.
        Scanner read = new Scanner(System.in);
        System.out.println("Lütfen karakter giriniz!");
        String stChar = read.nextLine();

        if ( stChar.length() == 1 ) {
            // işlemler
            char cChar = stChar.charAt(0);
            int cIn = (int) cChar;
            System.out.println("Ascii :" + cIn);
        }else {
            System.out.println("Hatalı Girdiniz!");
        }



    }
}
