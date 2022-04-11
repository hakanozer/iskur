package model;

import props.Customer;
import props.User;
import utils.DB;
import utils.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    @Override
    public int customerInsert(Customer customer) {
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
        List<Customer> ls=new ArrayList<>();
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
                ls.add(c);
            }
            //rs yi bu local alanda bir kere tüketebilir.
        }catch (Exception e){
            System.out.println("customerList Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return ls;
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
}
