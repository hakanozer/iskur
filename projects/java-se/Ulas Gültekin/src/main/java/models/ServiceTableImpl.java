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

public class ServiceTableImpl implements IServiceTable{
    DB db=new DB();
    List<Service> sList=new ArrayList<>();
    List<Service> sListCopy=new ArrayList<>();
    public ServiceTableImpl(){
        sList=serviceList();
        sListCopy=sList;
    }
    @Override
    public DefaultTableModel servicesTable(String data) {
        sList=sListCopy;
        DefaultTableModel tableModel=new DefaultTableModel();

        tableModel.addColumn("Sid");
        tableModel.addColumn("Cid");
        tableModel.addColumn("Title");
        tableModel.addColumn("Info");
        tableModel.addColumn("Days");
        tableModel.addColumn("Date");
        tableModel.addColumn("Price");
        tableModel.addColumn("Status");
        if (data!=null && !data.equals("") ){
            data=data.toLowerCase(Locale.ROOT);
            List<Service> searchedServiceList=new ArrayList<>();
            for (Service item:sList){

                if (item.getTitle().toLowerCase(Locale.ROOT).contains(data)||item.getInfo().toLowerCase(Locale.ROOT).contains(data)
                ||item.getDays().toLowerCase(Locale.ROOT).contains(data)||item.getDate().toLowerCase(Locale.ROOT).contains(data)||
                        item.getDate().toLowerCase(Locale.ROOT).contains(data)){
                    searchedServiceList.add(item);


                }
                sList=searchedServiceList;

            }



            }
        Customer customer=new Customer();
        for (Service item:sList){

            Object[] row={item.getSid(),item.getCid(),item.getTitle(),item.getInfo(),item.getDays(),item.getDate(),item.getPrice(),
                    item.getStatus()};
            tableModel.addRow(row);

        }



        return tableModel;
    }

    @Override
    public List<Service> serviceList() {
        List<Service> serviceList=new ArrayList<>();
        try {
            String sql="select * from service order by sid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int sid= rs.getInt("sid");
                int cid= rs.getInt("cid");
                String title=rs.getString("title");
                String info=rs.getString("info");
                String days=rs.getString("days");
                String date=rs.getString("date");
                int price= rs.getInt("price");
                int status=rs.getInt("status");
                Service service=new Service(sid,cid,title,info,days,date,price,status);
                serviceList.add(service);


            }

        }catch (Exception ex){
            System.out.println("Error in serviceList"+ex);

        }finally {
            db.close();
        }


        return serviceList;
    }

    @Override
    public int serviceDelete(int sid) {
        int status=0;
        try{
            String sql="delete from service where sid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,sid);

            status= pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("serviceTableDelete Error: "+ex); //err kýrmýzý gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açýk olan
        }
        return status;

    }

}
