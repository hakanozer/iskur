package models;

import props.User;
import utils.DB;
import utils.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {

    DB db = new DB();

    @Override
    public int userInsert(User user) {
        int status = 0;
        try {
            String sql = " insert into user values ( null, ?, ?, ?, ? ); ";
            // insert into user values ( null, '' or 1 = 1; delete from user' )
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getSurname());
            pre.setString(3, user.getEmail());
            pre.setString(4, Util.MD5(user.getPassword()) );
            status = pre.executeUpdate();
        }catch (Exception ex) {
            System.err.println("userInsert Error: " + ex );
        }finally {
            db.close(); // açık olan db'yi kapat
        }
        return status;
    }

    @Override
    public int userUpdate(User user) {
        int status = 0;
        try {
            String sql = "update user set name = ?, surname = ?, email = ?, password = ? where uid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getSurname());
            pre.setString(3, user.getEmail());
            pre.setString(4, Util.MD5(user.getPassword()));
            pre.setInt(5, user.getUid());
            status = pre.executeUpdate();
        }catch (Exception ex) {
            System.err.println("userUpdate Error : " + ex);
        }
        return status;
    }

    @Override
    public int userDelete(int uid) {
        int status=0;
        try{
            String sql="DELETE FROM user WHERE uid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,uid);
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("userDelete Error : "+e);
        }
        finally {
            db.close();//ne olursa olsun try catch'den sonra çalışacak olan kısımdır.
        }
        return status;
    }

    @Override
    public List<User> userList() {
        List<User> ls = new ArrayList<>();
        try {
            String sql = "select * from user";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while ( rs.next() ) {
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User u = new User(uid,name,surname,email,password);
                ls.add(u);
            }
        }catch (Exception ex) {
            System.err.println("userList Error : " + ex);
        }finally {
            db.close();
        }
        return ls;
    }

    @Override
    public boolean userLogin(String email, String password) {
        boolean status = false;
        try {
            String sql = "select * from user where email = ? and password = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, Util.MD5(password) );
            ResultSet rs = pre.executeQuery();
            status = rs.next();
        }catch (Exception ex) {
            System.err.println("userLogin Error : " + ex);
        }finally {
            db.close();
        }
        return status;
    }

    /**
     *
     * <img src="https://www.mediaclick.com.tr/uploads/2018/08/java.png" width="200" />
     * @param uid
     * @return User | null -> kullanıcı olmadığında geriye <b><i>null</i></b> döner
     */
    @Override
    public User userSingle(int uid) {
        try {
            String sql = "select * from user where uid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            if ( rs.next() ) {
                int uidx = rs.getInt("uid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User u = new User(uidx,name,surname,email,password);
                return u;
            }
        }catch (Exception ex) {
            System.err.println("userSingle Error : " + ex);
        }finally {
            db.close();
        }
        return null;
    }

}
