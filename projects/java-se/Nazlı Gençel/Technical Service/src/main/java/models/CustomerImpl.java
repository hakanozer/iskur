package models;

import props.Customer;
import utils.DB;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    List<Customer> customerList=new ArrayList<>();
    Customer customer=new Customer();

    @Override
    public int customerInsert(Customer customer) {
        int status=0;

        try {
            String sql="INSERT INTO customer VALUES(null,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());

            status=pre.executeUpdate();

        }catch (Exception ex){
            System.err.println("customerInsert Error:"+ex);
            if (ex.toString().contains("UNIQUE")){
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
            String sql="DELETE FROM customer WHERE cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            status=pre.executeUpdate();
        }catch (Exception ex){
            System.err.println("customerDelete Error:"+ex);

        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        int status=0;
        try {
            String sql="UPDATE customer SET name=?, surname=?,email=?,phone=?,address=? WHERE cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            pre.setInt(6,customer.getCid());
            status=pre.executeUpdate();
        }catch (Exception ex){
            System.err.println("customerUpdate Error:"+ex);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {

        try {
            String sql="select * from customer";
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
                Customer cus=new Customer(cid,name,surname,email,phone,address);
                customerList.add(cus);


            }
        }catch (Exception ex){
            System.err.println("customerList Error: "+ex.toString());
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel customerTable() {
        customerList();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cid");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("E-mail");
        model.addColumn("Phone");
        model.addColumn("Address");
        for ( Customer item : customerList ) {
            Object[] row = { item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress() };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public List<Customer> customerSearc(String data) {
        try {
            String sql="SELECT *FROM customer WHERE like";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            customerList.clear();
            while (rs.next()){
                int cid= rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");
                Customer customer=new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);
            }
        }catch (Exception ex){
            System.err.println("customerList Error: "+ex.toString());
            ex.printStackTrace();

        }finally {
            db.close();
        }
        return customerList;
    }
}
