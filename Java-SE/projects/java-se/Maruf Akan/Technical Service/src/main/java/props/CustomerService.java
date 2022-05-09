package props;

import java.util.List;

public class CustomerService {
    Customer customer;
    Service service;

    public CustomerService(){

    }

    public CustomerService(Customer customer, Service service) {
        this.customer = customer;
        this.service = service;
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


}
