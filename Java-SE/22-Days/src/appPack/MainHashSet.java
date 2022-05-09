package appPack;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

public class MainHashSet {
    public static void main(String[] args) {

        // HashSet
        // Set
        Set<String> sets = new HashSet<>();
        String kardelen = new String("Kardelen");
        sets.add("Oğuz");
        sets.add("Onur");
        sets.add("Ulaş");
        sets.add("Ömer");
        sets.add("Ömer");
        sets.add("Ömer");
        sets.add("Ömer");
        sets.add(kardelen);
        sets.add(kardelen);
        sets.add(kardelen);
        sets.add(kardelen);

        int size = sets.size();
        System.out.println(size);
        System.out.println( sets );


        // object Set
        Set<User> users = new LinkedHashSet<>();
        User ali = new User("ali", "bilmem");
        User ahmet = new User("ali", "bilmem");

        users.add( ali );
        users.add( new User("veli", "bilmem") );
        users.add( new User("hasan", "bilmem") );
        users.add( new User("mehmet", "bilmem") );
        users.add( ali );

        int siz = users.size();
        System.out.println( siz );

        users.forEach( item -> {
            System.out.println( item.getName() );
        });

        // vector
        Vector<String> vector = new Vector<>();
        vector.add("Maruf");
        vector.add("Ata");
        vector.add("Emel");
        vector.add("Nazlı");
        vector.add("Nazlı");
        vector.add("Nazlı");

        System.out.println( vector );
    }
}
