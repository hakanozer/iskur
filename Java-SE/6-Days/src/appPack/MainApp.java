package appPack;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        A a = new A();
        a.sum();

        Person person = new Person(10);
        person.name();
        //System.out.println(  );
        //System.out.println( person.amount() );

        Scanner scanner = new Scanner(System.in);
        System.out.println("Kenar -1 Giriniz");
        int k1 = scanner.nextInt();

        System.out.println("Kenar -2 Giriniz");
        int k2 = scanner.nextInt();

        System.out.println("Kenar -3 Giriniz");
        int k3 = scanner.nextInt();

        Ucgen uc = new Ucgen();
        uc.kenar(k1,k2,k3);
        System.out.println( "Üçgen mi ? " + uc.karar() );

        Hesapla hesapla = new Hesapla() {
            @Override
            public void kenar(int k1, int k2, int k3) {

            }
        };
        hesapla.karar();

    }
}

/*
* 1 - Kullanıcıdan 3 Kenar Değeri Alıyorsun
Bu 3 Değerden Üçgen Yapılıp Yapılamayacağını
Test Edip Sonucu Bastıracaksınız

Her 2 Kenarın Toplamı Diğer 3. Kenara Eşit/Fazlaysa
Üçgen Olur
*
* abstrack
* kurucu method
*
* 3,4,5
*
* */


