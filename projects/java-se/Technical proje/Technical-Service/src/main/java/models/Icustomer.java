package models;

import props.Customer;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface Icustomer {

    int customerInsert(Customer customer);

    //ödev

    int customerDelete(int cid);
    int customerUpdate(Customer customer);
    List<Customer> customerList();
    List<Customer> customerSearch(String data);
    Customer customerSingle(int cid);
    DefaultTableModel tablemodelOlustur();



}
