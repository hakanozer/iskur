package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private final String driver="org.sqlite.JDBC";     //driver deðiþmeisne ve dýþardan eriþilmesine gerk yok
    private String url="jdbc:sqlite:db/"; //jdbc protokolü ile sqlite a baðlan jdbc sabit        //içindeki bilgiler dðeiþmeyecek..
    private final String dbName="tecnic_service.db";     //

    //Connection Class
    private Connection conn= null;



    //2 constructora ihtiyac var
    //1 i default
    public DB(){
        this.url=this.url+dbName;  //finaldeki dbname bu
    }
    //2. parameteli dbname e göre verilen db e göre yapýlmalý. baðlantý. örneðin user a baðlancaksak user ý vermeliyiz.
    public DB(String dbName){
        this.url=this.url+dbName; //baþka dbismi burdaki parametredeki dbnamei aldý.
    }

    public Connection connect(){ //java dýþarý ile baðlantý kuraacaðý zaman trycach elzemdir.dosya iþlemleri db için
        try {
            //class for name: derlenmiþ class uzantýsýnýn kullanýmýný saðlar. .jar dosyalar kullanýma hazýrdýr.. .java uzantýlý dosyalar kullanýma hazýr deðildir.
            Class.forName(driver);//driverý çalýþtýr tetikle.
            conn = DriverManager.getConnection(url);//sqlite e baðlanrt için gerekli
            System.out.println("Connection Success");

        }catch (Exception ex){
            System.err.println("Connection Error: "+ex); //connection hatasý
        }
        return conn;  //brisi connect metodyuna baþvurduðunda connecton nesnesi dönmeli
    }

    //db close
    public void close(){//method halinde daha iyi.
        try {
            if (!conn.isClosed()){//connection açýksa
                conn.close();
            }
        }catch (Exception ex){
            System.err.println("close Error: "+ex);
        }
    }



}
