package models;

import props.Customer;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ICustomer {
    int customerInsert(Customer customer);
    int CustomerDelete(int cid);
    int customerUpdate (Customer customer);
    List<Customer> customerList();

    DefaultTableModel customerTablemodel();
}
