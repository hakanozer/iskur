package models;

import props.Service;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IArchive {
    List<Service> archiveList();
    DefaultTableModel archiveTable();
}
