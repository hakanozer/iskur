package model;

import props.Customer;
import props.CustomerService;
import props.Service;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerServiceImpl implements ICustomerService{
    DB db = new DB();
    List<CustomerService> ls0 = new ArrayList<>();//ilk sonuclar
    List<CustomerService> lsSearch0 = new ArrayList<>();

    CustomerServiceImpl(){

    }
    public CustomerServiceImpl(int status){
        ls0 = customerServiceList(status);
        lsSearch0 = ls0;;
    }


    public List<CustomerService> customerServiceList(int inpStatus) {
        List<CustomerService> customerServiceList = new ArrayList<>();
        try
        {
            String sql = "select * from customer inner join service s on customer.cid = s.cid where s.status=? order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,inpStatus);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                int cid=rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer customer=new Customer(cid,name,surname,email,phone,address);
                int sid=rs.getInt("sid");
                String title=rs.getString("title");
                String info=rs.getString("info");
                int days=rs.getInt("days");
                String date=rs.getString("date");
                int status=rs.getInt("status");
                int price=rs.getInt("price");
                Service service=new Service(sid,cid,title,info,days,date,status,price);
                CustomerService customerService = new CustomerService(customer,service);
                customerServiceList.add(customerService);
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
        return customerServiceList;
    }

    public DefaultTableModel serviceCustomerTable(String data) {
        ls0=lsSearch0;
        //en başta bir column isimleri oluşturulması gerekiyor.
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("cid");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");
        md.addColumn("sid");
        md.addColumn("title");
        md.addColumn("info");
        md.addColumn("days");
        md.addColumn("date");
        md.addColumn("status");
        md.addColumn("price");

        if (data != null && !data.equals("")) {//arama sonuclarını gonder
            List<CustomerService> subLs = new ArrayList<>();
            for (CustomerService item : ls0) {
                if (item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getAddress().toLowerCase(Locale.ROOT).contains(data)
                        || item.getService().getTitle().toLowerCase(Locale.ROOT).contains(data)
                        || item.getService().getInfo().toLowerCase(Locale.ROOT).contains(data)
                ) {
                    subLs.add(item);
                }
            }
            ls0 = subLs;
        }
        for (CustomerService item : ls0) {
            Object[] row = {item.getCustomer().getCid(),
                    item.getCustomer().getName(),
                    item.getCustomer().getSurname(),
                    item.getCustomer().getEmail(),
                    item.getCustomer().getPhone(),
                    item.getCustomer().getAddress(),
                    item.getService().getSid(),
                    item.getService().getTitle(),
                    item.getService().getInfo(),
                    item.getService().getDays(),
                    item.getService().getDate(),
                    item.getService().getStatus(),
                    item.getService().getPrice()
            };
            md.addRow(row);
        }
        return md;
    }
}
