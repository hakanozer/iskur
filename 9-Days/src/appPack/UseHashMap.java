package appPack;

import java.util.*;

public class UseHashMap {
    public static void main(String[] args) {

        // Hash Map
        // key, val -> değerleri ile birlikte çalışır.
        // hashmap içinde ekleme algoritması eklenen değerin hashcode büyüklüğüne göre sıralar.
        // sıralama algoritması önemli değildir.
        HashMap<String, String> hm = new HashMap<>();
        Map<String, String> hmx = new HashMap<>();

        // add item
        hm.put("name", "Ali");
        hm.put("surname", "Bilmem");
        hm.put("age", "30");
        hm.put("email", "ali@mail.com");
        hm.put("status", "true");

        // get item
        String name = hm.get("name");
        System.out.println( name );

        // Set -> türü
        // benzersiz keyleri yada değerleri bir arada tutmak için kullanılır.
        Set<String> keys = hm.keySet();
        for ( String key : keys ) {
            String k = key;
            String v = hm.get(k);
            System.out.println("key : " + k + " Val: " + v + " HashCode: " + k.hashCode() );
        }

        System.out.println( hm.size() );
        System.out.println( hm );


        // List ve Hashmap birlikte kullanım
        List< HashMap<String, String> > ls = new ArrayList<>();
        HashMap<String, String > hm1 = new HashMap<>();
        hm1.put("name", "Veli" );
        hm1.put("surname", "Kaymakçı");
        ls.add(hm);
        ls.add(hm1);


        // kullanıcıdan sınırsız ürün girişi için bir uygulama yazılacaktır.
        // ürünler bir list olacak ve listin türü map olacaktır.
        // Her ürünün özellikleri farklı olabilir
        // Örn:
        // 1. ürün -> name, detail, price
        // 2. ürün -> name, category, price
        // .
        // .

        // e çıkış yap
        // girişler bittikten sonra liste içindeki tüm ürünleri detayları liste olarak yazdırılacaktır.

        Product pr = new Product();
        pr.call();
    }
}
