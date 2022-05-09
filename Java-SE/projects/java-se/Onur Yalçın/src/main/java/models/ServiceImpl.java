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
    List<Service> sls = new ArrayList<>();
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

            customerList.clear();
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
            System.err.println("serviceCustomerList Error: "+ex.toString());
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

        if (data != null && !data.equals("")){
            data = data.toLowerCase(Locale.ROOT);
            // arama sonuçlarını gönder
            List<Customer> subLs = new ArrayList<>();
            for (Customer item:ls){
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data) ){
                    subLs.add(item);
                }
            }
            ls = subLs;
        }

        for (Customer item:ls){
            Object[] row = {item.getcId(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};
            model.addRow(row);
        }
        return model;
    }

    @Override
    public DefaultTableModel serviceServiceTable() {
        DefaultTableModel smodel = new DefaultTableModel();

        smodel.addColumn("Sid");
        smodel.addColumn("Cid");
        smodel.addColumn("Title");
        smodel.addColumn("Info");
        smodel.addColumn("Days");
        smodel.addColumn("Date");
        smodel.addColumn("Status");

        sls = serviceList();
        for (Service item: sls){
            Object[] row = {item.getSid(),item.getCid(),item.getTitle(),item.getInfo(),item.getDays(),item.getDate(),item.getStatus()};
            smodel.addRow(row);
        }
        return smodel;
    }

    @Override
    public List<Service> serviceList() {
        List<Service> sls = new ArrayList<>();
        try {
            String sql = "select * from service order by sid desc";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();//satir ve sutun haline getirir. Iterator gibi calisir, tuketilince tekrar olusturmak gerekir.
            while (rs.next()){//son elemana kadar true doner, ilgili satir
                int sid = rs.getInt("sid");
                int cid = rs.getInt("cid");
                String title = rs.getString("title");
                String info = rs.getString("info");
                int days = Integer.parseInt(rs.getString("days"));
                String date = rs.getString("date");
                int status = Integer.parseInt(rs.getString("status"));

                Service s = new Service(sid,cid,title,info,days,date,status);
                sls.add(s);
            }
        }catch (Exception ex){
            System.err.println("serviceList error" + ex );
        }
        finally {
            db.close();
        }
        return sls;
    }

    @Override
    public int serviceInsert(Service service) {
        int status = 0;
        try {
            String sql = " insert into service values(null,?, ?, ?, ?, ?, ?) ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            status = pre.executeUpdate();

        }
        catch (Exception ex){
            System.err.println("serviceInsert Error: " + ex);
            if (ex.toString().contains("UNIQUE")){
                status = -1;
            }
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceDelete(int sid) {
        int status = 0;
        try {
            String sql = " delete from service where sid=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,sid);
            status = pre.executeUpdate();

        }
        catch (Exception ex){
            System.err.println("serviceDelete Error: " + ex);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service, int sid) {
        int status = 0;
        try {
            String sql = " update service set title= ?, info= ?, days= ?, date = ?, status = ? where sid=?  ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,service.getTitle());
            pre.setString(2,service.getInfo());
            pre.setInt(3,service.getDays());
            pre.setString(4,service.getDate());
            pre.setInt(5,service.getStatus());
            pre.setInt(6,sid);
            status = pre.executeUpdate();

        }
        catch (Exception ex){
            System.err.println("serviceUpdate Error: " + ex);
            if (ex.toString().contains("UNIQUE")){
                status = -1;
            }
        }finally {
            db.close();
        }
        return status;
    }
}
