package model;

import props.Customer;

import java.util.List;

public interface ICustomer {
    int customerInsert(Customer customer);
    int customerUpdate(Customer customer);
    int customerDelete(int cid);
    List<Customer> customerList();
    List<Customer> customerSearch(String data);
}
