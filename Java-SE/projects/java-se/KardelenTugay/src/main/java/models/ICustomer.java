package models;

import props.Customer;

import java.util.List;

public interface ICustomer {
    int customerInsert(Customer customer);
    //odev
    int customerDelete(int cId);
    int  customerUpdate(Customer customer, int cId);
    List<Customer> customerList();
    List<Customer> customerSearch(String data);//ctrl f gibi her sutunu arar or like or like..

}
