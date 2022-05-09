package appPack;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        // Collections
        // ArrayList
        // Generic Types
        Settings<String> set = new Settings<>();
        set.action("Ali");

        ArrayList lss = new ArrayList();
        ArrayList<String> ls = new ArrayList<>();
        List<String> lsx = new ArrayList<>();


        // item add
        ls.add("Istanbul");
        ls.add("Adana");
        ls.add("Gaziantep");
        ls.add("Hatay");
        ls.add("Ordu");
        ls.add("Nevşehir");
        ls.add("Samsun");
        ls.add("Zonguldak");
        ls.add("Van");
        ls.add("Mersin");

        // get item
        System.out.println( ls.get(0) );
        //System.out.println( new String[]{}[0] );

        // size
        int size = ls.size();
        System.out.println( size );

        // all item write
        for (int i = 0; i < ls.size(); i++) {
            System.out.println( ls.get(i) );
        }
        System.out.println("=====================");
        for ( String item : ls ) {
            System.out.println( item );
        }
        // java 8 for
        System.out.println("=====================");
        ls.forEach( item -> {
            System.out.println("Java 8 :" +item);
        } );

        // remove item
        // ls.remove("İstanbul");
        // ls.remove(0);

        // sıralama
        // sözlük dizilimine göre yapar
        // bu işlem ile birlikte index yerleri de değişir.
        // sıralama ilkel türler ve String için geçerlidir.
        Collections.sort(ls);
        // tam terse (reverse) göre sıralama
        Collections.reverse(ls);

        // index göre eleman ekleme
        ls.add(1, "Zanguldak");

        // ArrayList değerini aktarma
        ArrayList<String> allList = new ArrayList<>();
        allList.add("Bursa");
        allList.add("Trabzon");
        ls.addAll(allList);

        System.out.println("=======================");
        System.out.println( ls.size() );
        int siz = ls.size();
        siz = 0;
        for( int i = 0; i < siz; i++ ) {
            //if ( ls.size() == 0 ) break;
            System.out.println("i : " + i);
            ls.remove(0);
        }
        // tüm elemanları silme
        // ls.clear();

        // item elemanının var olup olmadığına bakar
        boolean status = ls.contains("Bursa");
        System.out.println( "Bursa durum : " + status );

        // arama ve index bulma
        int indexOf = ls.indexOf("Bursas");
        System.out.println("indexOf :" + indexOf );

        // listeyi object dizisine dönüştürme
        //String[] arr = (String[]) ls.toArray();

        // Iterator dönüştürme
        Iterator<String> iterator = ls.iterator();
        while ( iterator.hasNext() ) {
            System.out.println( "itr :" + iterator.next() );
        }

        while ( iterator.hasNext() ) {
            System.out.println( "itr -:" + iterator.next() );
        }

        // listenin içerisinde belirli sayıdaki itemleri farklı bir listeye at
        List<String> subList = ls.subList(0, 5);
        System.out.println( "subList : "+ subList );

        // belirlenen listenin içindeki elemanları bul sil
        // ls.removeAll(subList);


        System.out.println( ls );


    }
}

// kullanıcıdan sınırsız sayıda adres girişi için bir uygulama yapılacaktır
// kullanıcıya "adres giriniz" sorusu sorulur.
// kullanıcı adresi girer
// daha sonra girişi bitirmek için "e" devam için herhangi bir tuşa basınız diye sorulur.
// "e" girilmiş ise uygulama daha önce girdiği adresleri listeler
// bu aşamadan sonra silmek istediğiniz adres indexi giriniz diye sorulur.
// girilen index ile adres silinir ve kalan adresler tekrar listelenir.
// bu uygulama oop prensiplerine göre yazılmalıdır.
