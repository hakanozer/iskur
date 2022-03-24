package appPack;

import java.util.Random;
import java.util.concurrent.Callable;

public class MainApp {
    public static void main(String[] args) {

        Object obj = new A();
        Object[] arr = { obj, 10, "Ali", true, new Random() };

        A a = new A();
        B b = new B();
        C c = new C();

        polimorfizm(a);
        polimorfizm(b);
        polimorfizm(c);
    }

    // polimorfizm( for eng Polymorphism ) -> çok biçimlilik
    public static void polimorfizm( Base base ) {
        if ( base instanceof A ) {
            A aa = (A) base;
            aa.sum(10,5);
        }
        base.write();
    }

}

/*

1 X interface yap
    bir tane method
    int action( int a, int b );

1 Base sınıfı yap
    X interfacesinden implemente al
    override et

1 Topla sınıfı yap
    Base den miras al
    action methodunu override et

Çıkar
Çarp
Böl

Main Fonksiyonu
    Topla tp = new Topla(30,20);
Polimorfizm
    polimorfizm(tp) {
        tp.action(); // sonucu yazdır
    }


 */
