package models;

import props.Customer;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CustomerImpl implements ICustomer {
    DB db = new DB();
    List<Customer> cust = customerList();
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
            if (ex.toString().contains("UNIQUE")){
                status = -1;
            }
        }finally {
            db.close();
        }
        return status;
    }
    public DefaultTableModel model() {
        DefaultTableModel md = new DefaultTableModel();
        //add column
        md.addColumn("cId");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");

        cust = customerList();
        for (Customer item : cust) {
            Object[] row = {item.getcId(),item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            md.addRow(row);
        }

        return md;
    }


    @Override
    public int customerDelete(int cId) {
        int status = 0;
        try {
            String sql = " delete from customer where cid=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,cId);
            status = pre.executeUpdate();

        }
        catch (Exception ex){
            System.err.println("customerDelete Error: " + ex);
        }finally {
            db.close();
        }
        return status;

    }

    @Override
    public int customerUpdate(Customer customer, int cId) {
        int status = 0;
        try {
            String sql = " update customer set name= ?, surname= ?, email= ?, phone = ?, address = ? where cid=?  ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            pre.setInt(6,cId);
            status = pre.executeUpdate();

        }
        catch (Exception ex){
            System.err.println("customerUpdate Error: " + ex);
            if (ex.toString().contains("UNIQUE")){
                status = -1;
            }
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        List<Customer> cust = new ArrayList<>();
        try {
            String sql = "select * from customer";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();//satir ve sutun haline getirir. Iterator gibi calisir, tuketilince tekrar olusturmak gerekir.
            while (rs.next()){//son elemana kadar true doner, ilgili satir
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                Customer c = new Customer(cid,name,surname,email,phone,address);
                cust.add(c);
            }
        }catch (Exception ex){
            System.err.println("customerList error" + ex );
        }
        finally {
            db.close();
        }
        return cust;
    }


    @Override
    public List<Customer> customerSearch(String data) {
        return null;
    }
}
