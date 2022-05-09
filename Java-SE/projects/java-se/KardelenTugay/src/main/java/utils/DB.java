package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private final String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:db/";
    private final String dbName = "technic_service.db";

    //Connection class
    private Connection conn = null;

    public DB(){

        this.url = this.url + dbName;
    }

    public DB(String dbName){

        this.url = this.url + dbName;  //baska bir veri tabanina baglanirsam pathini verir
    }

    public Connection connect(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Success");
        }catch (Exception ex){
            System.err.println("Connection error: " + ex);
        }
        return conn;
    }

    //db close
    public void close(){
        try {
            if (!conn.isClosed()){
                conn.close();
            }
        }catch (Exception ex){
            System.err.println("close error: " + ex);
        }
    }

}
