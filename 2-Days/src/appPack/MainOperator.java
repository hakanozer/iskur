package appPack;

public class MainOperator {
    public static void main(String[] args) {

       // artırma ve eksiltme operatörleri
       int num = 0;
       num++; // num = num + 1;
        System.out.println(num);
        System.out.println(num++);
        // ++ solda yazıldığında önce artır sonra yaz
        // ++ sağda yazıldığında önce yaz sonra artır

        int j = 0;
        ++j;
        j++;
        j++;
        System.out.println(j++);

        // mantıksal operatörler
        // ==, !=, <, >, >=, =<
        int a = 10;
        int b = 11;
        int c = 12;
        boolean status = a == b;
        System.out.println("== " + status);
        status = a != b;

        String name = "Ali";
        String adi = "Ali";
        //if ( name == adi )
        if ( !name.equals(adi) ) { // name == adi
        }

        status = a < b; // b a'dan büyükse
        status = a > b;

        status = a <= b; // b a'dan büyük yada eşit ise
        status = a >= b; // a b'den büyük yada eşit ise

        // tek satırlı if
        String namex = a > b ? "Ali" : "Serkan";

        // logic operatörler
        // || -> veya , && -> ve
        // veya ||
        status = a > 9 || b < a;
        // ve &&
        status = b > 10 && b < a;

        // iç içe kullanımı
        status = 11 > b || a > b && b > 10 || 5 > c;
        System.out.println(status);

    }
}
