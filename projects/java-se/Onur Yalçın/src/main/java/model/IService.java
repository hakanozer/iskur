package model;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {
    int serviceInsert(Service service);
    int serviceUpdate(Service service);
    int serviceDelete(int cid);
    List<Service> serviceList();
    List<Customer> serviceCustomerList();
    DefaultTableModel serviceCustomerTable(String  data);
}
