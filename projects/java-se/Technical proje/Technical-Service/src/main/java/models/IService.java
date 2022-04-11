package models;

import props.Customer;
import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IService {



    List<Customer> serviceCustomerList();
    DefaultTableModel serviceCustomerTable(String data);

    //List<Service> serviceList();
      int serviceInsert(Service service);
    DefaultTableModel tablemodelOlustur();


    int serviceDelete(int sid);
    int serviceUpdate(Service service);
    List<Service> serviceList(int sid);
    DefaultTableModel serviceModelDelivered(String data);
    DefaultTableModel serviceModelInrepair(String data);
    DefaultTableModel serviceModelCompleted(String data);
   // Customer customerSingle(int cid);
}
