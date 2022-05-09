package models;


import props.ServiceCustomer;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {



    List<ServiceCustomer> customerServiceList(int statuss) throws SQLException;
    DefaultTableModel servCusTable(String data );

}
