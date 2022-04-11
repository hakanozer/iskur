package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private final String driver="org.sqlite.JDBC";
    private String url="jdbc:sqlite:db/";
    private final String dbName="Technical_Service.db";

    //Connection Class
    private Connection connection=null;

    public DB(){
        this.url=this.url+dbName;
    }

    public DB(String dbName){
        this.url=this.url+dbName;
    }

    public Connection connect(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url);
            System.out.println("Connection Success");
        }catch (Exception e){
            System.out.println("Connection Error: "+e);
        }
        return connection;
    }

    //db close
    public void close(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            System.err.println("Close Error: "+e);
        }
    }
}
