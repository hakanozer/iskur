package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {

    int serviceInsert(Service service);
    int serviceDelete(int sid);
    int serviceUpdate(Service service);
    List<Service> serviceList();
    DefaultTableModel serviceTable(int cid);

    DefaultTableModel serviceCustomerTable(String data);
    List<Customer> serviceCustomerList();

}
