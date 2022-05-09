package models;

import props.Customer;
import props.Service;
import utils.DB;
import views.Base;
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
            data=data.toLowerCase(Locale.ROOT);
            // arama sonuçlarını gönder
            List<Customer> subLs = new ArrayList<>();
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
            Object[] row = { item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(),
                    item.getAddress() };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public DefaultTableModel serviceTable() {
        List<Service> list= new ArrayList<>();
        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Service No");
        tableModel.addColumn("Customer No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Title");
        tableModel.addColumn("Info");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Status");
        tableModel.addColumn("Price");
        list=serviceList();
        for (Service item: list){
            String state="";
            if (item.getStatus()==0){
                state= "Product Just Arrived";
            }else if (item.getStatus()==1){
                state="Product In Repair";
            }else if (item.getStatus()==2){
                state="Product Has Been Repaired";
            }else {
                state="Product Delivered";
            }

            Object[] row={item.getSid(),item.getCid(),item.getCustomer().getName(),item.getCustomer().getSurname(),
            item.getTitle(),item.getInfo(),item.getDays(),item.getDate(Base.Date()),state,item.getPrice()};
            tableModel.addRow(row);
        }

        return tableModel;
    }


    @Override
    public List<Service> serviceList() {
        List<Service> servicesList=new ArrayList<>();
        try {
            String sql="SELECT sid,s.cid,name,surname,title,info,days,date,status,price from service s "+
                    "JOIN customer c on s.cid=c.cid ORDER BY sid DESC";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while (rs.next()){
                int sid=rs.getInt("sid");
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String title=rs.getString("title");
                String info= rs.getString("info");
                int days=rs.getInt("days");
                String date=rs.getString("date");
                int status=rs.getInt("status");
                double price=rs.getInt("price");
                Customer customerS=new Customer(cid,name,surname);
                Service serviceS=new Service(sid,cid,title,info,days,Base.Date(),status,price,customerS);

                servicesList.add(serviceS);


            }

        }catch (Exception ex){
            System.err.println("serviceList Error:"+ex);
        }finally {
            db.close();
        }
        return servicesList;
    }

    @Override
    public int serviceInsert(Service service) {
        int status=0;
        try {
            String sql="insert into service values(null,?,?,?,?,?,0,?) ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate(Base.Date()));
            pre.setDouble(6,service.getPrice());
            status= pre.executeUpdate();



        }catch (Exception ex){
            System.err.println("serviceInsert Error:"+ex);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceDelete(int sid) {
        int status=0;
        try {
            String sql="delete from service where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,sid);
            status=pre.executeUpdate();
        }catch (Exception ex){
            System.err.println("serviceDelete Error:"+ex);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status=0;
        try {
            String sql="update service set title=?,info=?,days=?,date=?,status=?,price=? where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,service.getTitle());
            pre.setString(2,service.getInfo());
            pre.setInt(3,service.getDays());
            pre.setString(4,service.getDate(Base.Date()));
            pre.setInt(5,service.getStatus());
            pre.setDouble(6,service.getPrice());
            pre.setInt(7,service.getSid());
            status=pre.executeUpdate();

        }catch (Exception ex){
            System.err.println("serviceUpdate Error:"+ex);
        }
        return status;
    }


}
