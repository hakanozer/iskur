package models;

import props.Customer;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CustomerImpl implements ICustomer {

    DB db = new DB();
    @Override
    public int customerInsert(Customer customer) {
        int status = 0;
        try {
            String sql = " insert into customer values(null, ?, ?, ?, ?, ?) ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status = pre.executeUpdate();
        }
        catch (Exception ex){
            System.err.println("customerInsert Error: " + ex);
            if ( ex.toString().contains("UNIQUE") ) {
                status = -1;
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
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setString(1,"cid");
            status=pre.executeUpdate();
        }catch (Exception ex){
            System.out.println("delete error"+ ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        int status=0;
        try {

            String sql="INSERT INTO customer set null, name=?,surname=?,email=?,phone=?,address=?";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            status= pre.executeUpdate();

             pre.setString(1,customer.getName());
             pre.setString(2,customer.getSurname());
             pre.setString(3,customer.getEmail());
             pre.setString(4,customer.getPhone());
             pre.setString(5,customer.getAddress());
             return status;


        }catch (Exception ex){
            System.out.println("Update error"+ ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        Customer customer = new Customer();
        int status=0;
        try {
            String sql ="Select * from customer";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();

            while(rs.next()) {
                int cid= rs.getInt("Cid");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("E-Mail");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");

                Customer custumerx= new Customer(cid,name,surname,email,phone,address);
                customerList().add(custumerx);

            }



        }catch (Exception ex){
            System.out.println("customer List Error"+ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return null;
    }

    public DefaultTableModel customerTable(){

        DefaultTableModel tableModel= new DefaultTableModel();
        tableModel.addColumn("cid");
        tableModel.addColumn("name");
        tableModel.addColumn("surname");
        tableModel.addColumn("email");
        tableModel.addColumn("phone");
        tableModel.addColumn("address");

        for (Customer item :customerList()
             ) { Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
            tableModel.addRow(row);
        }



        return tableModel;
    }

    @Override
    public List<Customer> customerSearch(String data) {

        try {
            String sql= "select * from customer";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            customerList().clear();

            while (rs.next()){
                int cid =rs.getInt("Cid");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String  email= rs.getString("E-Mail");
                String phone= rs.getString("Phone");
                String address = rs.getString("Address");
                Customer customerx= new Customer(cid,name,surname,email,phone,address);
              customerList().add(customerx);
            }

        }catch (Exception ex){
            System.out.println("Customer error"+ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }

        return null;
    }
}
