package models;

import props.Customer;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardImpl implements IDashboard{
    DB db = new DB();
    @Override
    public List<Service> serviceDeliverList() {
        List<Service> service = new ArrayList<>();
        try {
            String sql = "SELECT customer.cid,sid,name,surname,email,phone,title,date,status FROM service INNER JOIN customer ON service.cid = customer.cid where status=2";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();//satir ve sutun haline getirir. Iterator gibi calisir, tuketilince tekrar olusturmak gerekir.
            while (rs.next()){//son elemana kadar true doner, ilgili satir
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int sid = rs.getInt("sid");
                String title = rs.getString("title");
                String date = rs.getString("date");
                int status = Integer.parseInt(rs.getString("status"));
                Customer c = new Customer(cid,name,surname,email,phone);
                Service s = new Service(sid,cid,title,date,status,c);
                service.add(s);
            }
        }catch (Exception ex){
            System.err.println("serviceDeliverList error" + ex );
        }
        finally {
            db.close();
        }
        return service;
    }

    @Override
    public DefaultTableModel serviceDeliverTable() {
        List<Service> service = new ArrayList<>();
        DefaultTableModel md = new DefaultTableModel();
        //add column
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Title");
        md.addColumn("Date");
        md.addColumn("Status");

        service = serviceDeliverList();

        for (Service item : service) {
            Object[] row = {item.getC().getName(),item.getC().getSurname(), item.getC().getEmail(),item.getC().getPhone(), item.getTitle(), item.getDate(), item.getStatus()};
            md.addRow(row);
        }

        return md;
    }

    @Override
    public List<Service> serviceNewList() {
        List<Service> service = new ArrayList<>();
        try {
            String sql = "SELECT customer.cid,sid,name,surname,email,phone,title,date,status FROM service INNER JOIN customer ON service.cid = customer.cid where status =0";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();//satir ve sutun haline getirir. Iterator gibi calisir, tuketilince tekrar olusturmak gerekir.
            while (rs.next()){//son elemana kadar true doner, ilgili satir
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int sid = rs.getInt("sid");
                String title = rs.getString("title");
                String date = rs.getString("date");
                int status = Integer.parseInt(rs.getString("status"));
                Customer c = new Customer(cid,name,surname,email,phone);
                Service s = new Service(sid,cid,title,date,status,c);
                service.add(s);
            }
        }catch (Exception ex){
            System.err.println("serviceNewList error" + ex );
        }
        finally {
            db.close();
        }
        return service;
    }

    @Override
    public DefaultTableModel serviceNewTable() {
        List<Service> service = new ArrayList<>();
        DefaultTableModel md = new DefaultTableModel();
        //add column
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Title");
        md.addColumn("Date");
        md.addColumn("Status");

        service = serviceNewList();

        for (Service item : service) {
            Object[] row = {item.getC().getName(),item.getC().getSurname(), item.getC().getEmail(),item.getC().getPhone(), item.getTitle(), item.getDate(), item.getStatus()};
            md.addRow(row);
        }

        return md;
    }
}
