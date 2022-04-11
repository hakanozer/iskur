package models;

import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import props.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import props.User;
import utils.DB;
import utils.Util;

import javax.swing.table.DefaultTableModel;

public class CustomerImpl implements Icustomer {
    DB db = new DB();

    @Override
    public int customerInsert(Customer customer) {
        int status = 0;

        try {
            String sql = "insert into customer values(null,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSurname());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getAddress());
            status = pre.executeUpdate();

        } catch (Exception ex) {
            System.err.println("Customer Insert Error" + ex);
            if (ex.toString().contains("UNIQUE")) {
                status = -1;
            }
            // ex.printStackTrace();
        } finally {
            db.close();
        }

        return status;
    }

    //==================================================================================
    @Override
    public int customerDelete(int cid) {
        int status = 0;
        try {
            String sql = "Delete from customer where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, cid);
            status = pre.executeUpdate();
        } catch (Exception ex) {
            System.out.println("customerDelete Error: " + ex);
        } finally {
            db.close();
        }
        return status;
    }

//========================================================================================

    @Override
    public int customerUpdate(Customer customer) {
        int status = 0;
        try {
            String sql = "Update customer set name=?,surname=?,email=?,phone=?,address=? where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSurname());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getAddress());
            pre.setInt(6,customer.getCid());
           status= pre.executeUpdate();
        } catch (Exception ex) {
            System.out.println("customerUpdate Error: " + ex);
        } finally {
            db.close();
        }
        return status;
    }

    //======================================================================================

    @Override
    public List<Customer> customerList() {
        List<Customer> customerList = new ArrayList<>();
        try {
            String sql = "Select*from customer";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer = new Customer(cid, name, surname, email, phone, address);
                customerList.add(customer);

            }
        } catch (Exception ex) {
            System.out.println("customerList Error:" + ex);
        } finally {
            db.close();
        }
        return customerList;
    }
//=======================================================================================
    @Override
    public List<Customer> customerSearch(String data) {
        List<Customer> customerList=new ArrayList<>();
        try {

            String sql="Select*from customer where name like? or surname like ? or mail ? or phone like ? ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,data+"%");
            pre.setString(2,data+"%");
            pre.setString(3,data+"%");
            pre.setString(4,data+"%");

            ResultSet rs=  pre.executeQuery();
            while (rs.next()){
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer = new Customer(cid, name, surname, email, phone, address);
                customerList.add(customer);
            }
        }catch (Exception ex){
            System.out.println("customerSearch Error: "+ex);
        }finally {
            db.close();
        }
       return customerList;
    }

      //===================================================================================================

    /**
     * If the customer is not null, according to cid parameter returns Customer object  by querying on the customer table
     * @param cid
     * @return customer
     */
    @Override
    public Customer customerSingle(int cid) {

        try {
            String sql="Select * from customer where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                int cid1=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer = new Customer(cid, name, surname, email, phone, address);
                return customer;
            }

        }catch (Exception ex){
            System.out.println("customerSingle Error: "+ex);
        }finally {
            db.close();
        }
        return null;
    }

    @Override
    public DefaultTableModel tablemodelOlustur() {
        List<Customer> lst=new ArrayList<>();
          DefaultTableModel tableModel=new DefaultTableModel();
          tableModel.addColumn("Customer no");
          tableModel.addColumn("Name");
          tableModel.addColumn("Surname");
          tableModel.addColumn("E-Mail");
          tableModel.addColumn("Phone");
          tableModel.addColumn("Address");
          lst=customerList();
          for(Customer item:lst){
              Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
              tableModel.addRow(row);
          }
      return tableModel;

    }
}