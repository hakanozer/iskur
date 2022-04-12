import model.ServiceImpl;
import props.Service;

public class MainClass {
    public static void main(String[] args) {
        ServiceImpl service=new ServiceImpl();
        for ( int i = 12; i < 25; i++ ) {
            Service service1 = new Service(2,i,"ali","atabakan",i,"25042022",2,0);
            Service service2 = new Service(2,i,"veli","yıldız",i,"15112021",3,i);
            service.serviceInsert(service1);
            service.serviceInsert(service2);
            System.out.println(service.archiveServiceList());
        }
    }
}
