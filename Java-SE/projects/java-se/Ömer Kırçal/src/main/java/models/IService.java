package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {
    List<Customer> serviceCustomerList();
    DefaultTableModel serviceCustomerTable(String data);
    DefaultTableModel serviceUpdateDeleteTable();
    int serviceInsert(Service service);
    int serviceDelete(int cid);
    int serviceUpdate (Service service);
    List<Service> serviceList();

}
