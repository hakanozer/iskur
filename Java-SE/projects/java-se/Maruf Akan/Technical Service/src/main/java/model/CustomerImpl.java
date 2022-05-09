package model;

import props.Customer;
import props.User;
import utils.DB;
import utils.Util;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    ServiceImpl service=new ServiceImpl();
    List<Customer> ls = new ArrayList<>();//ilk sonuclar
    List<Customer> lsSearch = new ArrayList<>();
    public CustomerImpl(){
        ls = service.serviceCustomerList();
        lsSearch = ls;
    }
    @Override
    public int customerInsert(Customer customer) {
        ls=lsSearch;
        int status=0;
        try{
            //kaç parametre varsa o kadr soru işareti kullanılacak
            String sql="INSERT INTO customer values(null,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);//pre üzerinden veriler eşitlnmesi için kullanılrı.
            //prepared ayrıştırma hazılama vb anlama gelir.
            //int cid, String name, String surname, String email, String phone, String address
            pre.setString(1,customer.getName());//veritabanına gondereceğimiz değer bir string ise setString kullanmamı gerekir
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status = pre.executeUpdate();//sql sorgusunu güvenli bir şekilde kullanılmasını sağlar
            //silme update delete içiin executeUpdate kullanılır.
        }catch (Exception e){
            System.out.println("customerUpdate Error : "+e);
            if(e.toString().contains("UNIQUE") && e.toString().contains("email"))
                status = -1;
            else if (e.toString().contains("UNIQUE") && e.toString().contains("phone"))
                status = -2;
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        ls=lsSearch;
        int status = 0;
        try{
            //int cid, String name, String surname, String email, String phone, String address
            //soru işatlerine gonderim yontemi bind yontemi
            String sql=" UPDATE customer SET name= ?,surname = ?,email = ?, phone =?, address =? where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            pre.setInt(6,customer.getCid());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("customerUpdate Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
        //gelişmiş db implmentasyonnda update işlemninde etkilenen hernahgi bir işlem yoksa update işlemi false döner
    }

    @Override
    public int customerDelete(int cid) {
        int status=0;
        try{
            String sql="DELETE FROM customer WHERE cid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("customerDelete Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        List<Customer> ls0=new ArrayList<>();
        try{
            String sql="SELECT * FROM customer";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            //select varsa executeUpdate kullanılmaz --> executeQuery <-- kullanılır
            ResultSet rs= pre.executeQuery(); //select varsa executeQuery bu kullanılır
            //ResultSet bir excel tablosu gibidir. Hem satır hem sutun gibi bir yapıya sahiptir.
            while (rs.next()){//rs.next() son elemana kadar bakar.
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");
                Customer c=new Customer(cid,name,surname,email,phone,address);
                ls0.add(c);
            }
            //rs yi bu local alanda bir kere tüketebilir.
        }catch (Exception e){
            System.out.println("customerList Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return ls0;
    }

    @Override
    public List<Customer> customerSearch(String data) {
        List<Customer> ls=new ArrayList<>();
        String searchData="%"+data+"%";
        try{
            String sql="SELECT * FROM customer WHERE name LIKE ? or surname LIKE ? or email LIKE ? or phone LIKE ? or address LIKE ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,searchData);
            pre.setString(2,searchData);
            pre.setString(3,searchData);
            pre.setString(4,searchData);
            pre.setString(5,searchData);
            //select varsa executeUpdate kullanılmaz --> executeQuery <-- kullanılır
            ResultSet rs= pre.executeQuery(); //select varsa executeQuery bu kullanılır
            //ResultSet bir excel tablosu gibidir. Hem satır hem sutun gibi bir yapıya sahiptir.
            while (rs.next()){//rs.next() son elemana kadar bakar.
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");
                Customer c=new Customer(cid,name,surname,email,phone,address);
                ls.add(c);
            }
            if(ls.size()>0)
                return ls;
        }
        catch (Exception e){
            System.out.println("customerList Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return null;
    }

    @Override
    public DefaultTableModel customerTable() {
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
        /*if (data != null && !data.equals("")) {//arama sonuclarını gonder
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
        }*/
        for (Customer item : ls) {
            Object[] row = {item.getCid(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            md.addRow(row);
        }
        return md;
    }
}
