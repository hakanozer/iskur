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
    List<Service>lsService=new ArrayList<>();
    List<Service>lsServiceSearch=new ArrayList<>();
    List<Service>lsSCompleted=new ArrayList<>();
    List<Service>lsSSearchCompleted=new ArrayList<>();
    List<Service>lstDelivered=new ArrayList<>();
    List<Service>lstSearchDelivered=new ArrayList<>();




    public ServiceImpl() {
        ls = serviceCustomerList();
        lsSearch = ls;
        lsService=serviceList(1);
        lsServiceSearch=lsService;
        lsSCompleted=serviceList(2);
        lsSSearchCompleted=lsSCompleted;
        lstDelivered=serviceList(3);
        lstSearchDelivered=lstDelivered;

    }

    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try {
            String sql = "select * from customer order by cid desc";
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
            System.err.println("customerList Error: " + ex.toString());
            ex.printStackTrace();
        } finally {
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

        if (data != null && !data.equals("")) {
            data=data.toLowerCase();
            // arama sonuçlarını gönder
            List<Customer> subLs = new ArrayList<>();
            for (Customer item : ls) {
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data))
                {
                    subLs.add(item);
                }
            }
            ls = subLs;
        }

        for (Customer item : ls) {
            Object[] row = {item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            model.addRow(row);
        }

        return model;
    }

    @Override
    public List<Service> serviceList(int status1) {
         String sql="";
         ResultSet rs=null;
        List<Service> servicesList = new ArrayList<>();
        try { if(status1==-1){
              sql = "Select sid,s.cid,name,surname,phone,title,info,days,date,status,price,orderdate from services s " +
                    "join customer c on s.cid = c.cid order by sid desc";
            PreparedStatement pre = db.connect().prepareStatement(sql);
             rs = pre.executeQuery();
           }else{
            sql="Select sid,s.cid,name,surname,phone,title,info,days,date,status,price,orderdate from services s " +
                    "join customer c on s.cid = c.cid where status=? order by date desc";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,status1);
             rs = pre.executeQuery();
        }
               while (rs.next()) {
                   String orderdate=rs.getString("orderdate");
                int sid = rs.getInt("sid");
                int cid = rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String phone=rs.getString("phone");
                String title = rs.getString("title");
                String info = rs.getString("info");
                int days= rs.getInt("days");
                String date = rs.getString("date");
                int status= rs.getInt("status");
                double price= rs.getDouble("price");

                Customer csm=new Customer(cid,name,surname,phone);
                Service s = new Service(sid,cid,title,info,days,date,status,price,csm,orderdate);
                servicesList.add(s);

            }
        } catch (Exception ex) {
            System.out.println("customerList Error:" + ex);
        } finally {
            db.close();
        }
    return servicesList;
    }

//================================================================================================
    @Override
    public int serviceInsert(Service service) {
        int status=0;
         try{
             String sql="insert into services values(null,?,?,?,?,?,0,?,?)";
             PreparedStatement pre=db.connect().prepareStatement(sql);
             pre.setInt(1,service.getCid());
             pre.setString(2,service.getTitle());
             pre.setString(3,service.getInfo());
             pre.setInt(4,service.getDays());
             pre.setString(5,service.getDate());
             pre.setDouble(6,service.getPrice());
             pre.setString(7,service.getOrderDate());
             status=pre.executeUpdate();

         }catch (Exception ex){
             System.out.println("serviceInsert Error: "+ex);
         }finally {
             db.close();
         }


        return status;
    }

    @Override
    public DefaultTableModel tablemodelOlustur() {
        List<Service> lst=new ArrayList<>();
        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Service No");
        tableModel.addColumn("Customer No");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Title");
        tableModel.addColumn("Details");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");
        tableModel.addColumn("Order Date");

        lst=serviceList(-1);
        for(Service item:lst){
            String condition="";
            if(item.getStatus()==0){
                condition="0-Just received";
            }else if(item.getStatus()==1){
                condition="1-In repair";
            }else if(item.getStatus()==2 ){
                condition="2-Completed";
            }else {
                condition="3-Delivered";
            }

            Object[] row={item.getSid(),
                    item.getCid(),
                    item.getCustomer().getName(),
                    item.getCustomer().getSurname(),
                    item.getTitle(),
                    item.getInfo(),
                    item.getDays(),
                    item.getDate(),
                    condition,
                    item.getPrice(),
                    item.getOrderDate()};

            tableModel.addRow(row);
        }
        return tableModel;

    }

    @Override
    public int serviceDelete(int sid) {

        int status = 0;
        try {
            String sql = "Delete from services where sid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, sid);
            status = pre.executeUpdate();
        } catch (Exception ex) {
            System.out.println("customerDelete Error: " + ex);
        } finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status = 0;
        try {
            String sql = "Update services set title=?,info=?,days=?,date=?,status=? ,price=? where sid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, service.getTitle());
            pre.setString(2, service.getInfo());
            pre.setInt(3, service.getDays());
            pre.setString(4, service.getDate());
            pre.setInt(5, service.getStatus());
            pre.setDouble(6,service.getPrice());
            pre.setDouble(7,service.getSid());
            status= pre.executeUpdate();
        } catch (Exception ex) {
            System.out.println("serviceUpdate Error: " + ex);
        } finally {
            db.close();
        }
        return status;
    }



    @Override
    public DefaultTableModel serviceModelDelivered(String data) {
        lstDelivered=lstSearchDelivered;

        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Service No");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Title");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Price");
        tableModel.addColumn("Order Date");
        if (data != null && !data.equals("")) {
            data = data.toLowerCase();
            List<Service> subLs = new ArrayList<>();
            for (Service item : lstDelivered) {

                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getTitle().toLowerCase(Locale.ROOT).contains(data)) {

                    subLs.add(item);
                }
            }
            lstDelivered = subLs;
        }
        for(Service item:lstDelivered) {

            Object[] row = {item.getSid(),
                    item.getCustomer().getName(),
                    item.getCustomer().getSurname(),
                    item.getCustomer().getPhone(),
                    item.getTitle(),
                    item.getDays(),
                    item.getDate(),
                    item.getPrice(),
                    item.getOrderDate()};

            tableModel.addRow(row);}

        return tableModel;
    }

    @Override
    public DefaultTableModel serviceModelInrepair(String data) {
       lsService=lsServiceSearch;
        //List<Service> lst=new ArrayList<>();
        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Service No");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Title");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");
        tableModel.addColumn("Order Date");
        if (data != null && !data.equals("")) {
            data = data.toLowerCase();

            List<Service> subLs = new ArrayList<>();
            //lst=serviceList(1);
            for (Service item : lsService) {

                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getTitle().toLowerCase(Locale.ROOT).contains(data)) {

                    subLs.add(item);
                }
            }
            lsService = subLs;
        }
         for(Service item:lsService) {
             String condition = "In Repair";

             Object[] row = { item.getSid(),
                     item.getCustomer().getName(),
                     item.getCustomer().getSurname(),
                     item.getCustomer().getPhone(),
                     item.getTitle(),
                     condition,
                     item.getPrice(),
                     item.getOrderDate()};

            tableModel.addRow(row);
        }
        return tableModel;
    }

    @Override
    public DefaultTableModel serviceModelCompleted(String data) {
        lsSCompleted=lsSSearchCompleted;
        //List<Service> lst=new ArrayList<>();
        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Service No");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Title");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");
        tableModel.addColumn("Order Date");
        if (data != null && !data.equals("")) {
            data = data.toLowerCase();
            // arama sonuçlarını gönder
            List<Service> subLs = new ArrayList<>();
            //lst=serviceList(1);
            for (Service item : lsSCompleted) {

                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data) ||
                        item.getTitle().toLowerCase(Locale.ROOT).contains(data)) {

                    subLs.add(item);
                }
            }
            lsSCompleted = subLs;
        }
        for(Service item:lsSCompleted) {
            String condition = "2-Completed";

            Object[] row = { item.getSid(), item.getCustomer().getName(), item.getCustomer().getSurname(),
                    item.getCustomer().getPhone(),
                    item.getTitle(), condition, item.getPrice(),item.getOrderDate()};

            tableModel.addRow(row);
        }
        return tableModel;
    }

}
