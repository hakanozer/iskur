package models;

import props.Service;

import javax.swing.table.DefaultTableModel;

public interface IArchive {

    DefaultTableModel ArchiveTable();
    int serviceUpdate(Service service);
}
