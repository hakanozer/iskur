package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private final String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:db/";
    private final String dbName ="proje.db";
    private Connection conn = null;

    public DB(){
        this.url = this.url + dbName;
    }
    public DB(String dbName){
        this.url = this.url + dbName;
    }

    public Connection connect(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection( url );
            System.out.println( "Connection Success" );
        }catch ( Exception ex ){
            System.out.println( "Connection Error : " + ex );
        }
        return conn;
    }
    public void close(){
        try {
            if( !conn.isClosed() ){
                conn.close();
            }
        }catch ( Exception ex ){
            System.out.println("close error : " + ex );
        }
    }

}
