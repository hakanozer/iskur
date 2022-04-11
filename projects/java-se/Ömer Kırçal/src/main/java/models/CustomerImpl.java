package models;



import props.Customer;
import props.User;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    private List<Customer> customerList=new ArrayList<>();



    @Override
    public int customerInsert(Customer customer) {
        int status=0;
        try {
            String sql="insert into customer values(null,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status=pre.executeUpdate();
        }catch (Exception e){
           System.err.println("customerInsert Error: "+e);
           if(e.toString().contains("UNIQUE")){
               status=-1;
           }
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerDelete(int cid) {
        int status=0;
        try {
            String sql="delete from customer where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            status=pre.executeUpdate();
        }catch (Exception e){
            System.err.println("customerDelete Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        int status=0;
        try {
            String sql="update customer set name=?, surname=?, email=?, phone=?, address=? where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getAddress());
            pre.setInt(5,customer.getCid());
            status=pre.executeUpdate();
        }catch (Exception e){
            System.err.println("customerUpdate Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        try {
            String sql="select * from customer order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            customerList.clear();
            while (rs.next()){
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");

                Customer customer=new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);
            }
        }catch (Exception e){
            System.err.println("customerList Error: "+e);
        }finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public List<Customer> customerSearch(String data) {
        try {
            String sql="select * from customer where like ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            customerList.clear();
            while (rs.next()){
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");

                Customer customer=new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);
            }
        }catch (Exception e){
            System.err.println("customerList Error: "+e);
        }finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel customerModel() {
        customerList();

        DefaultTableModel tableModel=new DefaultTableModel();

        tableModel.addColumn("cid");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("E-Mail");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Address");

        for(Customer item:customerList){
            Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
            tableModel.addRow(row);
        }

        return tableModel;
    }

}
