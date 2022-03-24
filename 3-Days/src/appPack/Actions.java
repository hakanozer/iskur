package appPack;

import java.util.Random;

public class Actions {

    public Actions() {
        System.out.println(num);
    }

    int num = 10;
    Random rd = new Random();

    // parametreli
    // parametresiz
    // return ve parametreli

    // kurulum
    /*
    (Erişim Belirteci) (Geri Dönüş Türü) (Method Adı) ( Parametreler )
    {
        // method tetiklendiğinde yapılacak iş
    }
     */
    // methdolar tetiklenmeden içleri çalışmazlar

    // parametresiz ve returnsüz method

    /**
     * Fonksiyonun çalışma amacını
     * <b>Belkelediği</b> parametreleri
     *
     * @Yazar:  Ali Dev.
     * @return void
     */
    public void noParams() {
        System.out.println("No Params Call");
    }

    // return'süz parametreli
    public void params( String data ) {
        System.out.println( data );
    }

    // params and return

    /**
     * iki sayıyı toplar
     * @param a
     * @param b
     * @return int veri türü döner
     */
    public int sum( int a, int b ) {
        return a + b;
    }

    // array fnc
    public int arrFnc( int a, String[] arr ) {
        int sum = arr.length + a;
        return sum;
    }

    // sonsuz tek tür parametre
    public String spaceParams( String... datas ) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            if ( i != datas.length - 1 ) {
                //stData += datas[i] + " "; // stData = stData + item;
                sb.append(datas[i]);
                sb.append(" ");
            }else {
                sb.append(datas[i]);
                //stData += datas[i]; // stData = stData + item;
            }
        }
        return sb.toString();
    }



}
