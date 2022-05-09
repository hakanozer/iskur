package appPack;

public class Action extends Base {

    // Erişim belirteçleri
    // public -> Tüm paket ve sınıflardan erişim vardır.
    // protected -> Sadece kendi paketi içinde erişim vardır.
    // private -> Sadece kendi sınıfı içinde erişim vardır.
    // default -> protected ile benzer tek farkı dahili sınıflarda görünmedir.

    public String name = "Ali";
    protected int age = 90;
    private boolean status = true;
    float f1 = 10.5f;

    // dahili sınıf
    // sınıf içinde sınıf oluşturma
    public class Settings {
        double d1 = 100.5;
    }

    public void send() {

    }


}
