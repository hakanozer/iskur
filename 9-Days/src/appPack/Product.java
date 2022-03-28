package appPack;

import java.util.*;

public class Product {

    List<HashMap<String, Object>> ls = new ArrayList<>();

    void call() {

        Scanner sc = new Scanner(System.in);
        for (int i = 0; true; i++ ) {

            System.out.println(i + ". Ürün bilgisini giriniz!");
            HashMap<String, Object> hm = new HashMap<>();
            for (int j = 0; true; j++) {
                System.out.println(i + ". Ürün özellik adını giriniz");
                String attrName = sc.nextLine();
                System.out.println(i + ". Ürün özellik değerini giriniz");
                String attrVal = sc.nextLine();
                hm.put(attrName, attrVal);
                System.out.println("Özellik Çıkış için e giriniz");
                String exit = sc.nextLine();
                if ( exit.equals("e") ) {
                    break;
                }
            }
            ls.add(hm);
            System.out.println("Ürün Çıkış için e giriniz");
            String exitProduct = sc.nextLine();
            if (exitProduct.equals("e")) {
                break;
            }
        }

        for ( HashMap<String, Object> item : ls ) {
            Set<String> keys = item.keySet();
            StringBuilder builder = new StringBuilder();
            for( String key : keys ) {
                String val = ""+ item.get(key);
                builder.append(key);
                builder.append(" ");
                builder.append(val);
                builder.append(", ");
            }
            System.out.println(builder.toString());
        }

    }



}
