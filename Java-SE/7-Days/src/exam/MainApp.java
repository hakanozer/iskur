package exam;

public class MainApp {
    public static void main(String[] args) {

        Topla topla = new Topla();
        Cikar cikar = new Cikar();

        setup(topla, 5,9);
        setup(cikar, 5,9);

    }

    public static void setup( Base base, int a, int b ) {
        int ac = base.call(a, b);
        System.out.println("Sonuç: " + ac);
    }
}
