package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private final String driver="org.sqlite.JDBC";     //driver de�i�meisne ve d��ardan eri�ilmesine gerk yok
    private String url="jdbc:sqlite:db/"; //jdbc protokol� ile sqlite a ba�lan jdbc sabit        //i�indeki bilgiler d�ei�meyecek..
    private final String dbName="tecnic_service.db";     //

    //Connection Class
    private Connection conn= null;



    //2 constructora ihtiyac var
    //1 i default
    public DB(){
        this.url=this.url+dbName;  //finaldeki dbname bu
    }
    //2. parameteli dbname e g�re verilen db e g�re yap�lmal�. ba�lant�. �rne�in user a ba�lancaksak user � vermeliyiz.
    public DB(String dbName){
        this.url=this.url+dbName; //ba�ka dbismi burdaki parametredeki dbnamei ald�.
    }

    public Connection connect(){ //java d��ar� ile ba�lant� kuraaca�� zaman trycach elzemdir.dosya i�lemleri db i�in
        try {
            //class for name: derlenmi� class uzant�s�n�n kullan�m�n� sa�lar. .jar dosyalar kullan�ma haz�rd�r.. .java uzant�l� dosyalar kullan�ma haz�r de�ildir.
            Class.forName(driver);//driver� �al��t�r tetikle.
            conn = DriverManager.getConnection(url);//sqlite e ba�lanrt i�in gerekli
            System.out.println("Connection Success");

        }catch (Exception ex){
            System.err.println("Connection Error: "+ex); //connection hatas�
        }
        return conn;  //brisi connect metodyuna ba�vurdu�unda connecton nesnesi d�nmeli
    }

    //db close
    public void close(){//method halinde daha iyi.
        try {
            if (!conn.isClosed()){//connection a��ksa
                conn.close();
            }
        }catch (Exception ex){
            System.err.println("close Error: "+ex);
        }
    }



}
