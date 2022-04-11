package model;

import props.Customer;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomerModel {
    CustomerImpl customer=new CustomerImpl();
    static DefaultTableModel md=new DefaultTableModel();
    List<Customer> ls=null;
    public CustomerModel(){
        this.ls=customer.customerList();;
    }

    public void removeRow(int selectedRow) {
        md.removeRow(selectedRow);
    }

    public DefaultTableModel model(){

        //ilk sutunlarla yolla cıkmak gerek. sutunklar ne olack karar ver
        //hemn ardından satırları bellirt
        //int cid, String name, String surname, String email, String phone, String address
        //add column
        md.addColumn("cid");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");

        //row data demektri

        for (Customer item:ls) {
            Object[] row= {item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
            md.addRow(row);
        }

        return md;
    }

    public void addItem(Customer customer){
        ls.add(customer);
    }
    public void deleteItem(int cid){
        ls.remove(cid);
    }
}
