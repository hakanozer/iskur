package models;

import props.User;
import utils.DB;
import utils.Util;

import java.sql.PreparedStatement;
import java.util.List;

public class UserImpl implements IUser {

    @Override
    public int userInsert(User user) {
        int status = 0;
        DB db = new DB();
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
        }
        return status;
    }

    @Override
    public int userUpdate(User user) {
        return 0;
    }

    @Override
    public int userDelete(int uid) {
        return 0;
    }

    @Override
    public List<User> userList() {
        return null;
    }
}
