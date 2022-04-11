package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    //normal diğer sql lerde kullanıcı adı ve şifre de ister
    private final String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:db/";
    private final String dbName ="proje.db";

    //Connection class
    //java.sql.Connection; buradan olması gerekiyor.
    private Connection conn = null;

    //2 constructor a ihtiyac var
    public DB(){
        this.url=this.url+dbName;
    }
    public DB(String dbName){
        this.url=this.url+dbName;
    }
    //javada veritabanına bağlantı için connction sınıfı kullanılır.

    public Connection connect(){
        //dışarı ile bağlantı yapılacaksa try catch mutlaka kullanılırç
        try {
            //Clas.forName ? static oldugundan newlenemez
            Class.forName(driver);
            //connection ı beslememiz gerekiyor
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Success");
        }catch (Exception ex){
            System.out.println("Connection Error : "+ex);
        }
        return conn;
    }
    //baglantı açık olma durumuna gore kapatmak istersek onun için close methdounu olusturuyoruz
    public void close(){
        try {
            //baglantı açıksa burası çalışsın
            if(!conn.isClosed()){
                conn.close();
            }
        }catch (Exception e){
            System.out.println("close error : "+e);
        }
    }

}
