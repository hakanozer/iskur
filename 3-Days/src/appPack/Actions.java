package appPack;

public class Actions {

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



}
