package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {
    List<Customer> serviceCustomerList();
    DefaultTableModel serviceCustomerTable(String data);
    List<Service> serviceList();
    DefaultTableModel serviceServiceTable();
    //odev

    int serviceInsert(Service service);
    int serviceDelete(int sid);
    int serviceUpdate(Service service, int sid);
}
