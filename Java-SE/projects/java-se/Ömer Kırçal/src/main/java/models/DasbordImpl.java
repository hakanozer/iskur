package models;

import props.Customer;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DasbordImpl {
    public List<Service> serviceList=new ArrayList<>();
    DB db=new DB();

    public DefaultTableModel customerModel() {

        serviceCustomerList();

        DefaultTableModel tableModel=new DefaultTableModel();

        tableModel.addColumn("sid");
        tableModel.addColumn("cid");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Title");
        tableModel.addColumn("Info");
        tableModel.addColumn("Days");
        tableModel.addColumn("Price");
        tableModel.addColumn("Status");


        for(Service item:serviceList){
            Object[] row={item.getSid(),item.getCid(),item.getCustomer().getName(),item.getCustomer().getSurname(),item.getTitle(),item.getCustomer().getAddress(),item.getCustomer().getPhone(),item.getInfo(),item.getDays(),item.getPrice(),item.getStatus()};
            tableModel.addRow(row);
        }

        return tableModel;
    }

    public List<Service> serviceCustomerList() {
        try {
            String sql="select * from service s join customer c on s.cid=c.cid order by sid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            serviceList.clear();
            while (rs.next()){
                int cid=rs.getInt("cid");
                int sid=rs.getInt("sid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String title=rs.getString("title");
                String info=rs.getString("info");
                String email=rs.getString("email");
                String address=rs.getString("address");
                String phone=rs.getString("phone");
                int days=rs.getInt("days");
                String date=rs.getString("date");
                int price=rs.getInt("price");
                int status=rs.getInt("status");

                Customer customer=new Customer(cid,name,surname,email,phone,address);
                Service service=new Service(sid,cid,title,info,days,date,price,status,customer);
                serviceList.add(service);
            }
        }catch (Exception e){
            System.err.println("customerList Error: "+e);
        }finally {
            db.close();
        }
        return serviceList;
    }
}
