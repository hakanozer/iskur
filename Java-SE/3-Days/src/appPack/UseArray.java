package appPack;

import java.util.Scanner;

public class UseArray {
    public static void main(String[] args) {

        // Array
        // bir değişken içinde birden fazla değer tutmak için kullanılır.
        // dizi tanımlama
        // Tür[] isim = { türDeğer, türDeğer, türDeğer };

        // kurallar
        // runtime halinde bir dizi içerisine veri ekleme yada çıkarma olamaz
        // oluşturulduktan sonra boyutu değişmez
        // içerisinde elemanlara index denilen int değer ile erişim sağlanır. 0 dan başlar.

        String[] arrx = new String[500];
        String[] arr = { "Ali", "Veli", "Hasan", "Ayşe", "Zehra" };

        arrx[0] = "Mehmet";
        System.out.println( arrx[0].length() );

        // item değerine ulaşma
        System.out.println( arr[4] );

        // boyut sayısı
        int size = arr.length;

        // value change
        arr[0] = "Erkan";

        // for loop items
        for( int i = 0; i < size; i++ ) {
            System.out.println( arr[i] );
        }

        // for each
        for( String item : arr ) {
            System.out.println("foreach :" + item );
        }

        // object arrays
        // her türlü veriyi bir dizi içerisinde toplamak için kullanılır.
        Object obj = "Ali";
        obj = 10;
        obj = 12.1;
        obj = new Scanner(System.in);

        Object[] objArr = { 100, "Ali", 13.5, true };
        for( Object item : objArr ) {
            if ( item instanceof String ) {
                ((String) item).length();
            }
            System.out.println( item );
        }

        // soru
        //  2 vize, 1 final notu
        // ortalama alınır, 50 'nin üstündeyse geçti yoksa kaldı. İf else kullanmadan yap
        Scanner read = new Scanner(System.in);
        float[] ex = new float[3];
        for( int i = 0; i < ex.length; i++ ) {
            System.out.println(i + 1 + ". notu giriniz");
            ex[i] = read.nextFloat();
        }

        float sum = 0;
        for( float itm : ex ) {
            sum += itm;
        }

        sum = sum / ex.length;
        String end = sum > 50 ? "Geçti" : "Kaldı";
        System.out.println(end);

    }
}
