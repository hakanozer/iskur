package models;

import props.Customer;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceImpl implements IService {

    DB db = new DB();
    List<Customer> ls = new ArrayList<>();
    List<Customer> lsSearch = new ArrayList<>();
    public ServiceImpl(){
        ls = serviceCustomerList();
        lsSearch = ls;
    }

    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try
        {
            String sql = "select * from customer order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                int cid=rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer = new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);
            }
        }
        catch (Exception ex)
        {
            System.err.println("customerList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel serviceCustomerTable(String data) {
        ls = lsSearch;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cid");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("E-mail");
        model.addColumn("Phone");
        model.addColumn("Address");

        if ( data != null && !data.equals("") ) {
            data = data.toLowerCase();
            // arama sonuçlarını gönder
            List<Customer> subLs =  new ArrayList<>();
            for (Customer item:ls){
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data) )
                {
                    subLs.add(item);
                }
            }
            ls = subLs;
        }

        for ( Customer item : ls ) {
            Object[] row = { item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress() };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public List<Service> serviceList() {
        return null;
    }

    @Override
    public int serviceInsert(Service service) {
        return 0;
    }

    @Override
    public int serviceDelete(int cid) {
        return 0;
    }

    @Override
    public int serviceUpdate(Service service) {
        return 0;
    }


}
