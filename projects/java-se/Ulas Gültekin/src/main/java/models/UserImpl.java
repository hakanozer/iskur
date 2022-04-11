package models;

import props.User;
import utils.DB;
import utils.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserImpl implements IUser{
    DB db=new DB();
    public static String name="";
    public static String surname="";
    public static int uid=0;

    @Override
    public int userUpdate(User user) {
        int status=0;
        try {
            String sql="update user set name=?,surname=?,email=?,password=? where uid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,user.getName());
            pre.setString(2,user.getSurname());
            pre.setString(3,user.getEmail());
            pre.setString(4,user.getPassword());
            pre.setInt(5,user.getUid());
            status= pre.executeUpdate();

        }catch (Exception ex){
            System.out.println("UserUpdate of Error");
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public boolean userLogin(String email, String password) {
        boolean status=false;
        try {String sql="select * from user where email=? and password=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,email);
            pre.setString(2, Util.MD5(password));
            ResultSet rs= pre.executeQuery();
            status= rs.next();
            if (status){
                name= rs.getString("name");
                surname=rs.getString("surname");
                uid=rs.getInt("uid");
            }

        }catch (Exception ex)
        {
            System.out.println("Error of userLogin");

        }finally {
            db.close();
        }
        return status;
    }
}
