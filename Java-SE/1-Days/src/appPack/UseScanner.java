package appPack;

import java.util.Scanner;

public class UseScanner {
    public static void main(String[] args) {

        // kullanıcıdan konsolda veri alımı için kullanılır.
        // nesne üretilerek kullanılır.
        Scanner read = new Scanner(System.in);

        System.out.println("Lütfen adınızı giriniz!");
        String name = read.nextLine();

        System.out.println("Lütfen adınızı giriniz!");
        String surname = read.nextLine();

        System.out.println("Lütfen yaşınızı giriniz!");
        int age = read.nextInt();
/*
        System.out.println("Adınız : " + name + " Soyadınız : " + surname + " Yaş: " + age);
*/
        System.out.printf("Adınız: %s Soyadınız: %s Yaşınız: %s\n", name, surname, age);
    }
}