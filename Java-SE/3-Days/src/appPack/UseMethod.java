package appPack;

public class UseMethod {
    public static void main(String[] args) {

        Actions ac = new Actions();
        ac.noParams();

        int x = 100;
        int y = 80;
        int sm = ac.sum(x, y);
        if ( ac.sum(2,7) > 9 ) {

        }
        System.out.println( sm );

        String space = ac.spaceParams("Adres-1", "Adres-2",  "Adres-3", "Adres-4");
        System.out.println( space );

    }
}
