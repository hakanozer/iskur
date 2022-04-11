package models;
import props.Customer;
import props.Service;
import javax.swing.table.DefaultTableModel;
import java.util.List;
public interface IArchive {
    DefaultTableModel serviceCustomerTable(String data);
    List<Service> serviceList();
}
