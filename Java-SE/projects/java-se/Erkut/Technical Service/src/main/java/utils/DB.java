package utils;

import java.awt.dnd.DragGestureEvent;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private final String driver = "org.sqlite.JDBC" ; //solda mavenin altında sqlite-jdbcnin altında org. sınıfımda
    private String url = "jdbc:sqlite:db/" ;  //içindeki bilgiler değişmeyecek, sabit olacak
    private final String dbName = "technical_service.db";

    //Connection Class
    private Connection conn = null;

    public DB() {
        this.url = this.url + dbName;
    }

    public DB(String dbName) {   //parametreli gelen projeyi kendisine alıp yeni url'yi yaratttı.
        this.url = this.url + dbName;
    }


    public Connection connect() {
        try {
            Class.forName(driver) ;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Success");
        } catch (Exception ex) {
            System.err.println("Connection Error : " + ex);
        }
        return conn;
    }

    //db close
    public void close() {
        try{
            if (!conn.isClosed()) {
                conn.close();
            }
        }catch (Exception ex) {
            System.err.println("Close Error : " + ex );
        }
    }


}
