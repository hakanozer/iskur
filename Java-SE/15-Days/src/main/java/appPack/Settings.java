package appPack;

// Thread sınıfından miras alındığında
// run() kesinlikle override edilmelidir.
public class Settings extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        }catch (Exception ex) {}
        System.out.println(" Thread Call ");
    }

}
