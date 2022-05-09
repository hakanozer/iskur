package appPack;

public class A extends Base {

    public A() {
        super(100);
        System.out.println("A Call :" + num);
    }


    @Override
    public void write() {
        System.out.println("A write Call " + call());
    }

    public int sum( int a, int b ) {
        int sm = a + b;
        if (sm > 10) {
            write();
        }else {
            super.write();
        }
        return sm;
    }

}
