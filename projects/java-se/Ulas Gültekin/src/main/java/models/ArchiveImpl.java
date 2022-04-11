package models;

import props.Service;
import utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArchiveImpl implements IArchive{
    DB db=new DB();
    Service service=new Service();

    @Override
    public int archiveDelete(int sid) {
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

    @Override
    public int archiveUpdate(int sid) {
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
