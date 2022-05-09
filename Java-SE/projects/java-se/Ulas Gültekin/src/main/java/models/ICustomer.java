package models;

import jdk.internal.dynalink.linker.LinkerServices;
import props.Customer;

import java.util.List;

public interface ICustomer {
    int customerInsert(Customer customer);
    int customerDelete(int cid);
    int customerUpdate(Customer customer);
    List<Customer> customerList();
    List<Customer> customerSearch(String data);




}
