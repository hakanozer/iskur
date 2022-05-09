package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private final String driver="org.sqlite.JDBC";     //driver değişmeisne ve dışardan erişilmesine gerk yok
    private String url="jdbc:sqlite:db/"; //jdbc protokolü ile sqlite a bağlan jdbc sabit        //içindeki bilgiler dğeişmeyecek..
    private final String dbName="tecnic_service.db";     //

    //Connection Class
    private Connection conn= null;



    //2 constructora ihtiyac var
    //1 i default
    public DB(){
        this.url=this.url+dbName;  //finaldeki dbname bu
    }
    //2. parameteli dbname e göre verilen db e göre yapılmalı. bağlantı. örneğin user a bağlancaksak user ı vermeliyiz.
    public DB(String dbName){
        this.url=this.url+dbName; //başka dbismi burdaki parametredeki dbnamei aldı.
    }

    public Connection connect(){ //java dışarı ile bağlantı kuraacağı zaman trycach elzemdir.dosya işlemleri db için
        try {
            //class for name: derlenmiş class uzantısının kullanımını sağlar. .jar dosyalar kullanıma hazırdır.. .java uzantılı dosyalar kullanıma hazır değildir.
            Class.forName(driver);//driverı çalıştır tetikle.
            conn = DriverManager.getConnection(url);//sqlite e bağlanrt için gerekli
            System.out.println("Connection Success");

        }catch (Exception ex){
            System.err.println("Connection Error: "+ex); //connection hatası
        }
        return conn;  //brisi connect metodyuna başvurduğunda connecton nesnesi dönmeli
    }

    //db close
    public void close(){//method halinde daha iyi.
        try {
            if (!conn.isClosed()){//connection açıksa
                conn.close();
            }
        }catch (Exception ex){
            System.err.println("close Error: "+ex);
        }
    }



}
