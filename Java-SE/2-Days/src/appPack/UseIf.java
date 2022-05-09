package appPack;

public class UseIf {
    public static void main(String[] args) {

        // if karar kontrol mekanizması
        /*
        if ( koşul )
        {
            // koşul sağlandığıda çalışacak gövde
        }else {
            // koşul sağlanmadığında çalışacak gövde
        }
        */
        int a = 10;
        int b = 11;
        if ( a > 9 ) b--; else  b++;

        b = 11 - 1; // 10
        boolean k1 = b > 10; // true
        boolean k2 = a > b; // false
        boolean status = a > 9 ? k1 : k2;

        System.out.println(b);
        System.out.println(  a <= b );
        System.out.println(  b <= a );


        // else if
        String name = "";
        String surname = "";
        String email = "";
        if ( name.equals("") ) {
            System.out.println("name değeri giriniz!");
        }else if ( surname.equals("") ) {
            System.out.println("surname değeri giriniz!");
        }else if ( email.equals("") ) {
            System.out.println("email değeri giriniz!");
        }else {
            // yukarıdaki datalar dolu, formu gönder.
        }

        // switch
        int swt = 10;
        switch ( swt ) {
            case 9:
                System.out.println("9");
                break;
            case 8:
                System.out.println("8");
                break;
            default:
                System.out.println("Default Call");
        }

    }
}
