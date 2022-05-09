package model;

import props.Customer;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceImpl implements IService{
    DB db = new DB();
    List<Customer> ls = new ArrayList<>();
    List<Customer> lsSearch = new ArrayList<>();
    List<Service> lsService = new ArrayList<>();
    List<Service> lsSearchService = new ArrayList<>();
    public ServiceImpl(){
        ls = serviceCustomerList();
        lsSearch = ls;
        lsService = serviceList();
        lsSearchService = lsService;
    }
    @Override
    public int serviceInsert(Service service) {
        int status = 0;
        try{
            String sql="INSERT INTO service values( null, ?, ?, ?, ?, ?, ? ,? )";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            pre.setInt(7,service.getPrice());
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println( "serviceInsert Error : " + ex );
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status = 0;
        try{
            String sql=" UPDATE service SET cid= ?,title=?, info = ?,days =?, date =?, status =?, price =? where sid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());//veritabanına gondereceğimiz değer bir string ise setString kullanmamı gerekir
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            pre.setInt(7,service.getPrice());
            pre.setInt(8,service.getSid());
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println( "serviceUpdate Error : "+ex );
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceDelete(int sid) {
        int status = 0;
        try{
            String sql = "DELETE FROM service WHERE sid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,sid);
            status = pre.executeUpdate();
        }catch ( Exception ex ){
            System.out.println("serviceDelete Error : "+ex);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Service> serviceList() {
        List<Service> ls = new ArrayList<>();
        try{
            String sql = "SELECT * FROM service ORDER BY sid desc ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            while ( rs.next() ){
                int sid = rs.getInt("sid");
                int cid = rs.getInt("cid");
                String info = rs.getString("info");
                String title = rs.getString("title");
                int days = rs.getInt("days");
                String date = rs.getString("date");
                int status = rs.getInt("status");
                int price = rs.getInt("price");
                Service s = new Service( sid,cid,title,info,days,date,status,price );
                ls.add(s);
            }
        }catch (Exception ex ){
            System.out.println( "serviceList Error : " + ex );
        }
        finally {
            db.close();
        }
        return ls;
    }

    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try
        {
            String sql = "select * from customer order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while( rs.next() )
            {
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer = new Customer(cid,name,surname,email,phone,address);
                customerList.add( customer );
            }
        }
        catch ( Exception ex )
        {
            System.err.println("customerList Error: " + ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel serviceCustomerTable(String data) {
        ls=lsSearch;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("cid");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");
        if (data != null && !data.equals("")) {
            List<Customer> subLs = new ArrayList<>();
            for ( Customer item : ls ) {
                if ( item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data) ) {
                    subLs.add(item);
                }
            }
            ls = subLs;
        }
        for (Customer item : ls) {
            Object[] row = {item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            md.addRow(row);
        }
        return md;
    }

    @Override
    public DefaultTableModel serviceTable(String data) {
        lsService = lsSearchService;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("sid");
        md.addColumn("cid");
        md.addColumn("title");
        md.addColumn("info");
        md.addColumn("days");
        md.addColumn("date");
        md.addColumn("status");
        md.addColumn("price");

        if (data != null && !data.equals("")) {
            List<Service> subLs = new ArrayList<>();
            for (Service item : lsService) {
                if (item.getTitle().toLowerCase(Locale.ROOT).contains(data)
                        || item.getInfo().toLowerCase(Locale.ROOT).contains(data)) {
                    subLs.add(item);
                }
            }
            lsService = subLs;
        }
        for ( Service item : lsService ) {
            Object[] row = {item.getSid(),item.getCid(), item.getTitle(), item.getInfo(), item.getDays(), item.getDate(), item.getStatus(),item.getPrice()};
            md.addRow(row);
        }
        return md;
    }

    @Override
    public List<Service> archiveServiceList() {
        List<Service> ls=new ArrayList<>();
        try{
            String sql="SELECT * FROM service where status = 4 ORDER BY sid desc ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            while (rs.next()){
                int sid=rs.getInt("sid");
                int cid=rs.getInt("cid");
                String info=rs.getString("info");
                String title=rs.getString("title");
                int days=rs.getInt("days");
                String date=rs.getString("date");
                int status=rs.getInt("status");
                int price=rs.getInt("price");
                Service s=new Service(sid,cid,title,info,days,date,status,price);
                ls.add(s);
            }
        }catch (Exception e){
            System.out.println("serviceList Error : "+e);
        }
        finally {
            db.close();
        }
        return ls;
    }

}
