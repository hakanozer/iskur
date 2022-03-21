package appPack;

import java.util.Random;

public class UseStatic {

    // Static
    // Program ayağa kalkmadan önce hazırlanırlar.
    // Class adı "." operatörü ile erişim sağlanır, kullanımı için nesne üretmeye gerek yoktur!
    // staticler oluştuktan sonra yok edilemez, çöp toplayıcı bunları silemez.
    // Javada staticler -> değişkenler, methodlar, nesneler olabilir.
    // Javada classlar static olmaz, dahili sınıflar static olur.


    public static String name = "Erkan";

    static Random rnd = new Random();

    public static int sum( int a, int b ) {
        int c = 50;
        return a + b + c;
    }


}
