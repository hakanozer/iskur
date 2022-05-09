package appPack;


public class Settings {

    // kurucu method içerisine a ve b şeklinde 2 adet int değer al;
    // daha sayılar ile toplama, çikarma, çarpma ve bölme işlemi yaptırıp sonuçları yaz.
    // Settings st = new Settings(50,5); // sadece bu işlemden sonra
    // toplam: 55
    // çıkarma: 45
    // bölme: 10
    // çarpma: 500
    int a = 0;
    int b = 0;
    public Settings( int a, int b) {
        this.a = a;
        this.b = b;
        topla(a,b);
        cikar(a,b);
        bol(a,b);
        carp(a,b);
    }

    public int carp(int a, int b) {
        System.out.println("carp call");
        return a * b;
    }

    private void bol(int a, int b) {
    }

    private void cikar(int a, int b) {
    }

    private void topla(int a, int b) {
    }


}
