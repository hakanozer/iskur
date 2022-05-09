package appPack;

public abstract class Hesapla {

    int k1, k2, k3 = 0;
    public abstract void kenar( int k1, int k2, int k3 );

    public boolean karar() {
        boolean status = k1 + k2 > k3 ? true : false;
        return status;
    }



}
