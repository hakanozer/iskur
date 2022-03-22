package appPack;

import java.util.Random;
import java.util.Scanner;

public class MainApp {

    int number = 10;
    Random rd = new Random();

    public static void main(String[] args) {

        MainApp app = new MainApp();
        System.out.println( app.number );
        System.out.println("merhaba");
        Settings st = new Settings(50, 5);
        new Settings(50,5);

        int crp = new Settings(0, 0).carp(50, 5);
        System.out.println("Çarp :" + crp);
        System.out.println( crp );

    }

    public void call() {
        send();
    }

    public void send() {
        call();
    }

}
