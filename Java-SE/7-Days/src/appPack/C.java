package appPack;

public class C extends Base {

    public C() {
        System.out.println("C Call");
    }

    @Override
    public void write() {
        System.out.println("C write Call " + call());
    }
}