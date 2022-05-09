package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {
    List<Customer> serviceCustomerList();
    DefaultTableModel servicesCustomerTable(String data);
    // ödev
    List<Service> serviceList();
    int serviceInsert( Service service );
    int serviceDelete( int cid );
    int serviceUpdate( Service service );

}
