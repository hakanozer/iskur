package models;

import props.Customer;

import java.util.List;


public interface ICustomer {

    int customerInsert (Customer customer);

    //ödev
    int customerDelete (int cid);
    int customerUpdate ( Customer customer );
    List<Customer> customerList();
    List<Customer> customerSearch(String data); //orlike yapılmalı (arama için)

}
