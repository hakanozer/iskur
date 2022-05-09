package appPack;

public class Product {

    // nesne üretimi
    // nesneler üretilirken önce kurucu methodları çalışır.
    // kurucu methodlar sınıf adı ile aynı ada sahip olmalıdırlar.
    // void yada return almazlar.
    // constructor

    public Product() {
        System.out.println("Product Call");
        System.out.println( num1 );
    }

    public Product(int num) {
        System.out.println("Num :" + num);
    }

    int num1 = 30;


}
