package exam;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Address implements IAddress {

    List<String> addresList = new ArrayList<>();

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Biti için \"e\" devam herhangi bir tuşa basınız");
        while(true) {
            System.out.println("Lütfen adres giriniz!");
            String data = scanner.nextLine();
            if ( data.equals("e") ) {
                break;
            }
            add(data);
        }
        list();
        delete();
    }

    @Override
    public void add(String data) {
        data = data.trim();
        addresList.add(data);
    }

    int i = 0;
    @Override
    public void list() {
        i = 0;
        addresList.forEach(item -> {
            System.out.println(i +"." + item);
            i++;
        });
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Silmek istediğiniz indexi seçiniz");
        int index = scanner.nextInt();
        addresList.remove(index);
        list();
    }
}
