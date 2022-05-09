package model;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ICustomerService {
    List<props.CustomerService> customerServiceList(int inpStatus);
    DefaultTableModel serviceCustomerTable(String data);
}
