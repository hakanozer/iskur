package appPack;

public class B extends Base {

    public B() {
        System.out.println("B Call");
    }

    @Override
    public void write() {
        System.out.println("B write Call " + call());
    }
}
