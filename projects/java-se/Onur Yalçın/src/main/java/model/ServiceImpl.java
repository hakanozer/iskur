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
    List<Customer> ls = new ArrayList<>();//ilk sonuclar
    List<Customer> lsSearch = new ArrayList<>();
    public ServiceImpl(){
        ls = serviceCustomerList();
        lsSearch = ls;
    }
    @Override
    public int serviceInsert(Service service) {
        int status=0;
        try{
            //public Service(int sid, int cid, String info, int days, String date, int status,int price) {
            //kaç parametre varsa o kadr soru işareti kullanılacak
            //public Service(int sid, int cid, String info, int days, String date, int status,int price) {
            String sql="INSERT INTO service values(null,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);//pre üzerinden veriler eşitlnmesi için kullanılrı.
            //prepared ayrıştırma hazılama vb anlama gelir.
            //int cid, String name, String surname, String email, String phone, String address
            pre.setInt(1,service.getSid());
            pre.setInt(2,service.getCid());
            pre.setString(3,service.getTitle());
            pre.setString(4,service.getInfo());//veritabanına gondereceğimiz değer bir string ise setString kullanmamı gerekir
            pre.setInt(5,service.getDays());
            pre.setString(6,service.getDate());
            pre.setInt(7,service.getStatus());
            pre.setInt(8,service.getPrice());
            status = pre.executeUpdate();//sql sorgusunu güvenli bir şekilde kullanılmasını sağlar
            //silme update delete içiin executeUpdate kullanılır.
        }catch (Exception e){
            System.out.println("serviceInsert Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
    }

    @Override
    public int serviceUpdate(Service service) {
        int status = 0;
        try{
            //public Service(int sid, int cid, String info, int info, String date, int status,int price)
            String sql=" UPDATE customer SET cid= ?,title=?, info = ?,days =?, date =?, status =?, price =? where sid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,service.getCid());
            pre.setString(2,service.getTitle());
            pre.setString(3,service.getInfo());//veritabanına gondereceğimiz değer bir string ise setString kullanmamı gerekir
            pre.setInt(4,service.getDays());
            pre.setString(5,service.getDate());
            pre.setInt(6,service.getStatus());
            pre.setInt(7,service.getPrice());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("serviceUpdate Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
        //gelişmiş db implmentasyonnda update işlemninde etkilenen hernahgi bir işlem yoksa update
    }

    @Override
    public int serviceDelete(int cid) {
        return 0;
    }

    @Override
    public List<Service> serviceList() {
        List<Service> ls=new ArrayList<>();
        try{
            String sql="SELECT * FROM service";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            //select varsa executeUpdate kullanılmaz --> executeQuery <-- kullanılır
            ResultSet rs= pre.executeQuery(); //select varsa executeQuery bu kullanılır
            //ResultSet bir excel tablosu gibidir. Hem satır hem sutun gibi bir yapıya sahiptir.
            //(int sid, int cid, String info, int days, String date, int status, int price)
            while (rs.next()){//rs.next() son elemana kadar bakar.
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
            //rs yi bu local alanda bir kere tüketebilir.
        }catch (Exception e){
            System.out.println("serviceList Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
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

    //tabloda dinamik şekilde arama yapbailmek ve tabloya entegre etmek için kullanılacak
    @Override
    public DefaultTableModel serviceCustomerTable(String data) {
        ls=lsSearch;
        //en başta bir column isimleri oluşturulması gerekiyor.
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("cid");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");

        //1.ilkönce ne istendiğine gore konum al
        //List<Customer> ls = serviceCustomerList(); //burasını constructora atalım conn azalatmaka için
        if (data != null && !data.equals("")) {//arama sonuclarını gonder
            List<Customer> subLs = new ArrayList<>();
            for (Customer item : ls) {
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getAddress().toLowerCase(Locale.ROOT).contains(data)) {
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
}
