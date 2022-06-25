package model;

import props.Customer;
import props.User;
import utils.DB;
import utils.Util;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerImpl implements ICustomer{
    DB db = new DB();
    ServiceImpl service = new ServiceImpl();
    List<Customer> ls = new ArrayList<>();
    List<Customer> lsSearch = new ArrayList<>();
    public CustomerImpl(){
        ls = service.serviceCustomerList();
        lsSearch = ls;

    }
    @Override
    public int customerInsert(Customer customer){
        ls = lsSearch;
        int status=0;
        try {
            String sql="INSERT INTO customer values(null,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println( "customerUpdate Error : " + ex );
            if( ex.toString().contains( "UNIQUE" ) && ex.toString().contains( "email" ))
                status = -1;
            else if ( ex.toString().contains( "UNIQUE" ) && ex.toString().contains( "phone" ))
                status = -2;
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        ls=lsSearch;
        int status = 0;
        try{
            String sql=" UPDATE customer SET name= ?,surname = ?,email = ?, phone =?, address =? where cid=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            pre.setInt(6,customer.getCid());
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println( "customerUpdate Error : " + ex );
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerDelete( int cid ) {
        int status = 0;
        try{
            String sql=" DELETE FROM customer WHERE cid = ? ";
            PreparedStatement pre = db.connect().prepareStatement( sql );
            pre.setInt(1, cid );
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println("customerDelete Error : " + ex);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        List<Customer> ls0 = new ArrayList<>();
        try{
            String sql = " SELECT * FROM customer ";
            PreparedStatement pre = db.connect().prepareStatement( sql );
            ResultSet rs= pre.executeQuery();
            while ( rs.next() ){
                int cid = rs.getInt("cid" );
                String name = rs.getString("name" );
                String surname = rs.getString("surname" );
                String email = rs.getString("email" );
                String phone = rs.getString("phone" );
                String address = rs.getString("address" );
                Customer c = new Customer( cid,name,surname,email,phone,address );
                ls0.add(c);
            }
        }catch ( Exception ex ){
            System.out.println( "customerList Error : " + ex );
        }
        finally {
            db.close();
        }
        return ls0;
    }

    @Override
    public List<Customer> customerSearch(String data) {
        List<Customer> ls = new ArrayList<>();
        String searchData = "%" + data + "%";
        try{
            String sql = " SELECT * FROM customer WHERE name LIKE ? or surname LIKE ? or email LIKE ? or phone LIKE ? or address LIKE ?";
            PreparedStatement pre = db.connect().prepareStatement( sql );
            pre.setString(1,searchData);
            pre.setString(2,searchData);
            pre.setString(3,searchData);
            pre.setString(4,searchData);
            pre.setString(5,searchData);
            ResultSet rs = pre.executeQuery();
            while ( rs.next() ) {
                int cid = rs.getInt("cid" );
                String name = rs.getString("name" );
                String surname = rs.getString("surname" );
                String email = rs.getString("email" );
                String phone = rs.getString("phone" );
                String address = rs.getString("address" );
                Customer c = new Customer( cid,name,surname,email,phone,address );
                ls.add( c );
            }
            if( ls.size()>0 )
                return ls;
        }
        catch ( Exception ex ){
            System.out.println( "customerList Error : " + ex );
        }
        finally {
            db.close();
        }
        return null;
    }

    @Override
    public DefaultTableModel customerTable() {
        ls = lsSearch;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("cid");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");
        for ( Customer item : ls ) {
            Object[] row = {item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            md.addRow(row);
        }
        return md;
    }
}
