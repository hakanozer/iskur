package props;

public class ServiceCustomer {
    Customer customer= new Customer();
    Service service = new Service();

    public ServiceCustomer() {
    }

    public ServiceCustomer(Customer customer, Service service) {
        this.customer = customer;
        this.service = service;
    }

    public ServiceCustomer(int sid, int cid, String title, String info, int days, String date, int status, String name, String surname, String email, String phone, String address) {


    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceCustomer{" +
                "customer=" + customer +
                ", service=" + service +
                '}';
    }
}
