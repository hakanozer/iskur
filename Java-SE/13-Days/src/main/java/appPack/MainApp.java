package appPack;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainApp {
    public static void main(String[] args) {

        try {
            String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            String url = "jdbc:sqlite:db/proje.db";
            Connection con = DriverManager.getConnection(url);
            System.out.println("Connection Success");
        }catch (Exception ex) {
            System.err.println("Connection Error : " + ex);
        }



    }
}
