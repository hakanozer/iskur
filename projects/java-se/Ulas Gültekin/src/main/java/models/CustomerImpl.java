package models;

import props.Customer;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomer{
    DB db=new DB();
    private List<Customer> customerList=new ArrayList<>();
    Customer customer=new Customer();
    @Override
    public int customerInsert(Customer customer) {
        int status=0;
        try {
            String sql="insert into customer values(null,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status= pre.executeUpdate();

        }catch (Exception ex){
            System.out.println("CustomerInsert Error"+ex);
            ex.printStackTrace();
            if (ex.toString().contains("UNIQUE")){
                status=-1;


            }
        }finally {
            db.close();
        }

        return 0;
    }

    @Override
    public int customerDelete(int cid) {
        int status=0;
        try{
            String sql="delete from customer where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);

            status= pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("customerDelete Error: "+ex); //err kýrmýzý gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açýk olan
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        int status=0;

        try {
            String sql = "UPDATE customer SET name=?,surname=?,email=?,phone=?,address=? where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());  //string ise setstring int ise setýnteger.
            pre.setString(2,customer.getSurname());  //1 den baþlar.soru iþareti sýrasý.
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone()); //md5 e çevir hashcode a
            pre.setString(5,customer.getAddress());
            pre.setInt(6,customer.getCid());


            //soru iþaretlerine gönderilecek olan datanýn gönderim yönteminin bir adýda bind yöntemi olarak geçer.
            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("customerUpdate Error: "+ex); //err kýrmýzý gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açýk olan
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        try {
            String sql="select * from customer order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();

            customerList.clear();
            while(rs.next()){
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                String surname= rs.getString("surname");
                String email=rs.getString("email");
                String phone=rs.getString("phone");
                String address=rs.getString("address");
                Customer customer=new Customer(cid,name,surname,email,phone,address);
                customerList.add(customer);

            }

        }catch (Exception ex){
            System.out.println("customerList Error"+ex);
            ex.printStackTrace();


        }finally {
            db.close();
        }


        return customerList;
    }

    @Override
    public List<Customer> customerSearch(String data) {
        return null;
    }
    public DefaultTableModel customerModel(){  //Model oluþturduk datatablemodel ile sütýn ve sayýrlar oluþturduk
        customerList();

        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("cid");  //kolon ekledik
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Address");

        for(Customer item:customerList){ //car türünde bir nesne getiriyor.
            Object[] row={item.getCid(),item.getName(),item.getSurname(),item.getEmail(),item.getPhone(),item.getAddress()};//item.getCid(),

            tableModel.addRow(row);
        }

        return  tableModel;
    }
}
