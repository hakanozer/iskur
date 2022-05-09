package models;

import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IDashboard {
    List<Service> serviceDeliverList();
    DefaultTableModel serviceDeliverTable();

    List<Service> serviceNewList();
    DefaultTableModel serviceNewTable();
}
