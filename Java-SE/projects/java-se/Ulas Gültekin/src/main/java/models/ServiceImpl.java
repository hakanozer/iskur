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

public class ServiceImpl implements IService{

    DB db=new DB();
    Service service=new Service();
    Customer customer=new Customer();
    List<Customer> ls=new ArrayList<>();
    List<Customer> lsSearch=new ArrayList<>();
    List<Service> serviceList=new ArrayList<>();
    public ServiceImpl(){
        ls=serviceCustomerList();
        lsSearch=ls;

    }
    @Override
    public List<Customer> serviceCustomerList() {
        List<Customer> customerList=new ArrayList<>();
        try {
            String sql="select * from customer order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            while(rs.next()){
                int cid=rs.getInt("cid");
                String name =rs.getString("name");
                String surname =rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");
                Customer customer=new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);


            }


        }catch (Exception ex){
            System.out.println("Error in serviceCustomer"+ex);

        }finally {
            db.close();
        }

        return customerList;
    }

    @Override
    public DefaultTableModel servicesCustomerTable(String data) {
        ls=lsSearch;
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("Cid");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("E-mail");
        model.addColumn("Phone");
        model.addColumn("Address");

        if (data!=null && !data.equals("")){
            data=data.toLowerCase(Locale.ROOT);
            List<Customer> subls=new ArrayList<>();
            for (Customer item: ls){
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data) )
                {
                    subls.add(item);
                }
            }
            ls = subls;
        }

        for ( Customer item : ls ) {
            Object[] row = { item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress() };
            model.addRow(row);
        }

        return model;



            }






    @Override
    public List<Service> serviceList() {
        try
        {
            String sql = "select * from service order by sid desc ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();

            serviceList.clear();
            while(rs.next())
            {
                int sid=rs.getInt("sid");
                int cid=rs.getInt("cid");
                String title = rs.getString("title");
                String info = rs.getString("info");
                String days = rs.getString("days");
                String date = rs.getString("date");
                int price = rs.getInt("price");
                int status = rs.getInt("status");

                Service service = new Service(sid,cid,title,info,days,date,price,status);
                serviceList().add(service);


            }
        }
        catch (Exception ex)
        {
            System.err.println("serviceList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }



        return serviceList;
    }

    @Override
    public int serviceInsert(Service service) {
        int status=0;
        try {
            String sql="insert into service values(null,?,?,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setString(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getPrice());
            pre.setInt(7,service.getStatus());

            status= pre.executeUpdate();


        }catch (Exception ex){
            System.out.println("Error in ServiceInsert"+ex);

        }finally {
            db.close();
        }


        return status;
    }

    @Override
    public int serviceDelete(int cid) {
        int status=0;
        try {
            String sql="delete * from service where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
           pre.setInt(1,cid);
           status= pre.executeUpdate();


        }catch (Exception ex){
            System.out.println("Error in ServiceDelete"+ex);
        }finally {
            db.close();
        }


        return status;





    }

    @Override
    public int serviceUpdate(Service service) {
        int status=0;
        try {
            String sql="update service set cid=?,title=?,info=?,days=?,date=?,price=?,status=? where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setString(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getPrice());
            pre.setInt(7,service.getStatus());
            pre.setInt(8,service.getSid());
            status= pre.executeUpdate();


        }catch (Exception ex){
            System.out.println("Error in ServiceUpdate"+ex);
        }finally {
            db.close();
        }


        return status;
    }
}
