package models;

import props.Customer;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements IService {
    DB db = new DB();
    List<Customer> ls = new ArrayList<>();

    public ServiceImpl() {

    }

    public List<Service> serviceList() {
        return null;
    }

    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try {
            String sql = "select * from customer order by cid desc ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            customerList.clear();
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
            System.err.println("customerList Error: " + ex.toString());
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel serviceCustomerTable(String data) {
        List<Customer> ls = serviceCustomerList();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cid");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("E-mail");
        model.addColumn("Phone");
        model.addColumn("Address");

        if (data != null && data.equals("")) {
            //arama sonuçlarını gönder
            List<Customer> subLs = serviceCustomerList();
            for (Customer item : ls) {

            }
        }
        for (Customer item : ls) {
            Object[] row = {item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            model.addRow(row);
        }


        return model;
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

    public TableModel serviceCustomerTablo(Object o) {

    }

}
