package appPack;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    String name = "Ali";
    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.call();
    }

    public void call() {
        List<String> ls = new ArrayList<>();
        ls.add("İstanbul");
        ls.add("Adana");
        ls.add("İzmir");
        ls.add("Samsun");
        ls.add("Erzurum");
        ls.add("Isparta");
        System.out.println( this.name );
        String surname = "Bilmem";
        ls.forEach( item -> {
            System.out.println( item );
            callx();
        });
    }

    public void callx() {
        System.out.println( "CallX Call" );
    }

}
