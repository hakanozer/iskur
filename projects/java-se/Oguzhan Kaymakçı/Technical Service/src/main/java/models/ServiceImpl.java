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
        List<Customer> customerList = new ArrayList<>();//list
        try
        {
            String sql = "select * from customer order by cid desc";//sql
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

        if ( data != null && !data.equals("") ) {//int veya stringe
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
        try {
            String sql="select * from service where sid desc";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();

            serviceList().clear();
            while (rs.next()){
            int sid= rs.getInt("sid");
            int cid= rs.getInt("cid");
            String title= rs.getString("title");
            String info= rs.getString("info");
            int days= rs.getInt("days");
            String date= rs.getString("date");
            int status= rs.getInt("status");
            int price= rs.getInt("price");

            Service service = new Service(sid,cid,title,info,days,date,status,price);

            serviceList().add(service);

            }

        }catch (Exception ex){
            System.out.println("service list error"+ ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return serviceList();
    }

    @Override
    public int serviceInsert(Service service) {
        int status=0;
        try {
            String sql="INSERT INTO service values(null,?,?,?,?,?,?)";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());

            status=pre.executeUpdate();// güncelleme her veri girişinde
        }catch (Exception ex){
            System.out.println("insert error"+ ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }


        return status;
    }

    @Override
    public int serviceDelete(int cid) {
        int status=0;
        try {
            String sql="delete from service where cid=?";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            status= pre.executeUpdate();

        }catch (Exception ex){
            System.out.println("delete error"+ ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }

        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status=0;
        try {
            String sql="update service set sid=?, cid=?,title=?,info=?,days=?,date=?,status=?";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setInt(1,service.getSid());
            pre.setInt(2,service.getCid());
            pre.setString(3,service.getTitle());
            pre.setString(4,service.getInfo());
            pre.setInt(5,service.getDays());
            pre.setString(6,service.getDate());
            pre.setInt(7,service.getStatus());

            status=pre.executeUpdate();

        }catch (Exception ex){
            System.out.println("update error"+ex);
        }finally {
            db.close();
        }
        return 0;
    }


}
