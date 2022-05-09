package models;

import Utils.DB;
import Utils.Util;
import props.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {
    DB db= new DB();
    public static String name="";
    public static int uid=0;


    @Override
    public int userUpdate(User user) {
        int status=0;

        try {
            String sql = "UPDATE user SET name=?,surname=?,email=?,password=? where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,user.getName());  //string ise setstring int ise setınteger.
            pre.setString(2,user.getSurname());  //1 den başlar.soru işareti sırası.
            pre.setString(3,user.getEmail());
            pre.setString(4,Util.MD5(user.getPassword())); //md5 e çevir hashcode a
            pre.setInt(5,user.getUid());
            //soru işaretlerine gönderilecek olan datanın gönderim yönteminin bir adıda bind yöntemi olarak geçer.
            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("userUpdate Error: "+ex); //err kırmızı gösteriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //açık olan
        }
        return status;
    }

    @Override
    public boolean userLogin(String email, String password) {
        boolean status=false;
        //try cach finally elzem
        try {
            String sql="select * from user where email=? and password=?";
            PreparedStatement pre= db.connect().prepareStatement(sql);
            pre.setString(1,email); //parametredeki içindeki email passwordu atıtk
            pre.setString(2,Util.MD5(password)); //şifreyi dönüştür.

            ResultSet rs=pre.executeQuery();    //rs e attık aldığımız verileri.
                                                //tekil data bekliyoruz.  //yukardaki sorgudan veri gelirse true olacak statu rs nextten veri gelmezse false döncek
            status=rs.next();                //sonraki satır var mı diye kontrol edicez varsa true dicez veri varmı diye kontrol ediyoz.

            if (status){//burdaki isim ve soyismi alıp name e atıcaz true ise status
               name = rs.getString("name")+" "+rs.getString("surname"); //sütun ismiyle değerine ulaşmış olduk.
               uid=rs.getInt("uid");
            }




        }catch (Exception ex){
            System.err.println("userLogin Error: "+ex);
            ex.printStackTrace();
        }finally {
            db.close();
        }


        return status;  //false olursa hata oolduğunu anlıcaz
    }



}
