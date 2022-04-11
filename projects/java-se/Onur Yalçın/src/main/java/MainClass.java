import model.CustomerImpl;
import model.ServiceImpl;
import props.Service;

public class MainClass {
    public static void main(String[] args) {
        ServiceImpl service=new ServiceImpl();
        //int sid, int cid, String title, String info, int days, String date, int status, int price
        Service service1=new Service(1,2,"test","test info",5,"10.10.10",2,25);
        System.out.println(service.serviceUpdate(service1));
        //System.out.println(service1);
    }
}
