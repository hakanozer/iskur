package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IServiceTable {
    DefaultTableModel servicesTable(String data);
    List<Service> serviceList();
    int serviceDelete( int cid );




}
