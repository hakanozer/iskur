package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private final String driver = "org.sqlite.JDBC"; //JDBC path
    private String url = "jdbc:sqlite:db/"; //database path
    private final String dbName ="proje.db";
    //Connection Class
    //java ile sql database bağlantı kurarken Connection sinifından yararlanır
    private Connection conn = null;
    //conn nesnesini her bağlantı kuracağımızdda ya da kapatacağımızda kullandığımız
    //için globalde tanımladık



    public DB(){
        //db sınıfını her çağırdığımda database pathını alıp bağlantı kurabilmesi
        //için constructor una tam database pathını tanımlarız
        this.url = this.url + dbName;
        //bu adım tanımladığımız database pathi ile name'ini birleştirip
        //tam path yolunu döndürmeyi sağlar
    }
    public DB(String dbName){
        this.url = this.url + dbName;
        //başka bir veri tabanına bağlanmamız gerekebilir. bu sebepten
        //parametreli bir constructor oluşturup name'i verdim
    }

    public Connection connect(){
        //dışarı ile bir işlem gerçekleştireceksem try catch kullanırım
        //bu olası bir hata durumunda hatanın nedenini görmemi sağlar
        try {
            Class.forName(driver);
            //Class.forName derlenmiş class uzantısının tetiklenmesini sağlar
            conn = DriverManager.getConnection( url );
            //daha önce connection sınıfında tanımladığımız conn nesnesinin içine
            //bağlantıyı sağlamak için url'yi yazdık
            System.out.println( "Connection Success" );
        }catch ( Exception ex ){
            System.out.println( "Connection Error : " + ex );
        }
        return conn;
    }
    public void close(){
        // veri tabanı ile kurduğumuz bağlantıyı gerçekleştirdiğimiz işlemler
        //sonunda kapatmamız gerekir. bunun için close methodu oluşturduk
        try {
            if( !conn.isClosed() ){
                conn.close();
            }
        }catch ( Exception ex ){
            System.out.println("close error : " + ex );
        }
    }
    /* buraya kadar veri tabanına bağlantı kurmak için gerekli işlemleri gerçekleştirdik
    şimdi bir Generator adında bir sınıf daha oluşturup bağlantısını kurduğumuz veri
    tabanında sql komutlarını koşturabileceğimiz bir kurgu gerçekleştireceğiz
     */

}
