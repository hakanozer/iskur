package models;

import props.Customer;
import props.Service;
import utils.DB;
import views.Dashboard;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArchiveImpl implements IArchive{
    DB db = new DB();
    private List<Service> serviceList =new ArrayList<>();
    @Override
    public List<Service> archiveList() {
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
                int price=rs.getInt("price");
                Customer customerS=new Customer(cid,name,surname);
                Service serviceS=new Service(sid,cid,title,info,days,date,status,price,customerS);

                serviceList.add(serviceS);

            }

        }catch (Exception ex){
            System.err.println("serviceList Error:"+ex);
        }finally {
            db.close();
        }
        return serviceList;
    }

    @Override
    public DefaultTableModel archiveTable() {
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
        list=archiveList();
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
                    item.getTitle(),item.getInfo(),item.getDays(),item.getDate(),state,item.getPrice()};
            tableModel.addRow(row);
        }

        return tableModel;
    }
}
