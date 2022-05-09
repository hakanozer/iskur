package appPack;

public abstract class Account {

    // abstract
    // abstract -> anahtar kelimesi bir sınıfın içerisinde gövdesiz methodlar yazmaya olanak tanır.
    // Normal sınıf davranışına sahiptirler.
    // miras olarak verildiklerinde içerisindeki gövdersiz methodları doldurmak zorunda olunur.

    // abstract method
    public abstract int userNumber( );

    // Account Name
    public String name() {
        if ( userNumber() == 10 ) {
            return "Ali Bilmem";
        }else if ( userNumber() == 20 ) {
            return "Veli Birlirim";
        }
        return "";
    }


    // amount
    public int amount() {
        if ( userNumber() == 10 ) {
            return 1000000;
        }else if ( userNumber() == 20 ) {
            return 5000;
        }
        return 0;
    }


}
