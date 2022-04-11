package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    //madde bir driver baðlantýsý private olmalý

    private final  String driver="org.sqlite.JDBC";
    private  String url="jdbc:sqlite:db/";//jdbc bütün sql ile kullanýlýr
    private final String dbName="proje.db";

    //Connection Clas

    private Connection conn=null;


    public DB(){
        this.url=this.url+dbName;



    }
    public DB(String dbName){
        this.url=this.url+dbName;

    }

    public Connection connect(){

        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url);
            System.out.println("Connection Success");
        }
        catch(Exception ex){
            System.out.println("Connection Error"+ex);


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
            System.err.println("Close Error: "+ex);

        }
    }



}
