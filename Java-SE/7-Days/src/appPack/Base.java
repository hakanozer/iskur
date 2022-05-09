package appPack;

public class Base {

    public Base() {
        System.out.println("Base Call");
    }

    int num = 0;
    public Base( int num ) {
        this.num = num;
    }

    public void write() {
        System.out.println("Base write Call");
    }

    public String call() {
        return "Class Settings";
    }

}
