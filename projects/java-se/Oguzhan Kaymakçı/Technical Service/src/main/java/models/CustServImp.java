package models;

import props.Customer;
import props.ServiceCustomer;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustServImp implements ICustomerService{
    DB db = new DB();
    List<ServiceCustomer> lsx = new ArrayList<>();
    List<ServiceCustomer> lsSearchx = new ArrayList<>();
    @Override
    public List<ServiceCustomer> customerServiceList(int statuss ) throws SQLException {
        try {


        String sql = "select * from join Service s join Customer c on s.cid=c.cid cid desc" ;
        PreparedStatement pre = db.connect().prepareStatement(sql);
        ResultSet rs = pre.executeQuery();

        while (rs.next()){

           int sid= Integer.parseInt(rs.getString("sid"));
            int cid= Integer.parseInt(rs.getString("cid"));
           String title= rs.getString("title");
            String info= rs.getString("info");
            int days= Integer.parseInt(rs.getString("days"));
            String date = rs.getString("date");
            int status = Integer.parseInt(rs.getString("status"));
            String name =rs.getString("name");
            String surname= rs.getString("surname");
            String email= rs.getString("email");
            String phone= rs.getString("phone");
            String address= rs.getString("address");


            ServiceCustomer serviceCustomer= new ServiceCustomer( sid, cid, title, info,  days, date, status, name, surname, email,phone,address);
            customerServiceList(statuss).add(serviceCustomer);
        }
        }catch (Exception ex){
            System.out.println("error"+ ex);
        }finally {
            db.close();

        }

        return null;
    }

    @Override
    public DefaultTableModel servCusTable(String data) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("sid");
        model.addColumn("cid");
        model.addColumn("name");
        model.addColumn("surname");
        model.addColumn("email");
        model.addColumn("phone");
        model.addColumn("address");
        model.addColumn("title");
        model.addColumn("info");
        model.addColumn("days");
        model.addColumn("date");
        model.addColumn("status");

        if ( data != null && !data.equals("") ) {//int veya stringe
            data = data.toLowerCase();
            // arama sonuçlarını gönder
            List<ServiceCustomer> subLsx =  new ArrayList<>();
            for (ServiceCustomer item:lsx){
                if (//item.getCustomer().getCid().toLowerCase(Locale.ROOT).contains(data)
                        item.getCustomer().getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getSurname().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getEmail().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getPhone().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCustomer().getAddress().toLowerCase(Locale.ROOT).contains(data)
                        || item.getService().getTitle().toLowerCase(Locale.ROOT).contains(data)
                        || item.getService().getInfo().toLowerCase(Locale.ROOT).contains(data)
                        || item.getService().getDate().toLowerCase(Locale.ROOT).contains(data)

 )
                {
                    subLsx.add(item);
                }
            }
            lsx = subLsx;
        }        for ( ServiceCustomer item : lsx ) {
            Object[] row = { item.getCustomer().getName(), item.getCustomer().getSurname(), item.getCustomer().getEmail(), item.getCustomer().getPhone(), item.getCustomer().getAddress(), item.getService().getTitle(),item.getService().getInfo(),item.getService().getDate() };
            model.addRow(row);
        }

        return model;
    }
}
