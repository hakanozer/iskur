#JAVA DATABASE OPERATIONS INTRODUCTION
Arkadaşlar java'da herhangi bir türde database'e(**MySql, Oracle, PostgreSQL**) bağlanıp sorgularımızı işletebiliyoruz. 
Java'da database işlemlerine dair spesifikasyona **JDBC(Java Database Connectivity)** diyoruz.

Java'da herhangi bir tür database'e bağlanırken ona uygun **Driver** sınıfını bağımlılık olarak yüklememiz gerekmektedir. Mesela **MySQL** işlemleri yapacak isek ***pom.xml***'imize

```
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>
    
```

şeklinde bağımlılığımızı yüklüyoruz.

Java'da database işlemleri **Driver** mantığı üzerinden ilerlemektedir. Yüklediğimiz bağımlılık **MySQL**'e özel bağlantı, sorgulama gibi kodları içermektedir. Biz de ilk olarak bu driver'i aktifleştirmeliyiz.
```
    Class.forName("com.mysql.cj.jdbc.Driver");
```

şeklinde bir çağırım yaparak bu **Driver**'in yüklenmesini ve kendisini **DriverManager**'a kaydetmesinin sağlıyoruz. Burada kullandığımız isim **MySQL** driver'inin bulunduğu package ve sınıfı ismidir. Full isim vererek **JVM**'in bu sınıfı yuklemesini sağlıyoruz. Bu sınıfın içine baktığımızda şöyle bir kod blogu görüyoruz.
```
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    static {
        try {
            java.sql.DriverManager.registerDriver(new Driver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}
```

Buna **statik kod bloğu** diyoruz ve **bir sınıf JVM'e yüklendiğinde ilk olarak çalışan kısım statik kod bloklarıdır**. Burada **MySQL** driver'i kendini DriverManager'a register ediyor.

Bu aşamadan sonra database'e bağlantı açabilir, sorgularımızı çalıştırabiliriz.
Database'e bağlanmak için bize database sunucusunun protokolu hostu portu lazımdır. Ve bunu şu şekilde bir stringde(**connection string**) belirtiriz.
```
    /*  protocol://host:port/additionalParams(database ismi verilebilir burada)  */
    String url = "jdbc:mysql://localhost:3306/test";
```

Burada protokol kısmı kullandığımız driver'a göre **jdbc:mysql**, **jdbc:postgresql** vb.. farklı farklı olabilir.
Daha sonra **DriverManager**'in **getConnection()** methodu ile database ile aramızda bir bağlantı açıyoruz.
```
    Connection conn = DriverManager.getConnection(url, "nusd", "nusddev1234");
```
Burada ikinci ve üçüncü parametre database kullanıcı adı ve şifresidir. Kullanıcı adı ve şifreyi connection stringde **queryString** parametreleri (?'den sonra gelen, HTTP'dekinin aynısı) olarakta belirtebiliriz.
```
    //protocol://host:port/additionalParams(database ismi verilebilir burada)
    String url = "jdbc:mysql://localhost:3306/test?user=nusd&password=nusddev1234";
```

Arkadaşlar burada connection stringde dikkat edin /test şeklinde database isminide belirttik. Bu ilk bağlanacağımız database'dir. Belirtmek zorunda değiliz kolaylık için kullanıyoruz. Daha sonra bunu değiştirmek isteyebiliriz. Bu durumda
```
    conn.setCatalog("anotherDatabase");
```
şeklinde başka bir database'e geçebiliriz.

Artık bağlantımız var, query'imizi yazıp sorgumuzu çalıştırabiliriz. Database'e sorgu gönderebilmek için direk connection nesnesi değil **connection nesnesi üstünden yarattığımız Statement nesnesini kullanırız.**
```
	    String query = "SELECT * FROM courses";
		Statement st = conn.createStatement();
		st.execureQuery(query);
```

Gördüğünüz gibi yarattığımız statement'in **executeQuery(query)** methodu ile sorgumuzu database'e sunucusuna gönderip sonuçları alabiliriz.

**executeQuery(query)** methodu bize bir **ResultSet(dönen recordları içeren bir collection benzeri yapı)** döner. Bizde bunun üstünden iterate ederek(dolaşarak) tek tek satırları görebiliriz.
```
        String query = "SELECT * FROM courses";
		Statement st = conn.createStatement();
		ResultSet rs = s.executeQuery(query);
		while(rs.next()) {
		    System.out.println(rs.getString([columnName or index(index 1'den başlar)]));
		}
```
**ResultSet** ilk geldiğinde içinde kayıtlar vardır ve ResultSet'in imleçi ilk kaydın bir gerisinde durmaktadır. Her bir next() çağrısı resultSet imlecini bir sonraki kayda ilerletir, yani sonraki record'a geçmemizi sağlar. While içinde her bir iterasyonda sıraki kaydı erişiriz.

Ve döngünün içinde **rs.getString(), rs.getInt(), rs.getBoolean() vb..** gibi methodları kullanarak o an üzerinde bulunulan recordun kolonlarının değerlerini okuyabiliriz. Burada kolonun veri tipi ne ise ona uygun methodu kullanmamız gerekir. Yani varchar bir kolon için rs.getString() methodunuz kullanmamız gerekmektedir. Burada bu methodlar hem index hemde kolon adı kabul edebilir. Kolon adı ile erişim daha efektiftir.

Database'den birşey getirdiğimiz(**SELECT**) isteklerde Statement'in **executeQuery()** mehodunu kullanırız. Database'de değişime sebep olan query'lerde(**Insert, Delete, Update, Create Table, Create Database, Alter Table vb...**) statement'in **executeUpdate(query)** methodunu kullanırız.
```
    String updateQuery = "UPDATE courses SET name = \"Updated\" WHERE id = 1";
	Statement st = conn.createStatement();
	int affectedRecord = st.executeUpdate(queryTwo);
```

Bu bize yaptığımız işlemden kaç satır etkilendi ise onun sayısını döner, **resultset dönmez!**

### DATABASE METADATA
Arkadaşlar metadata; data hakkında data demektir. Mesela bir resmin kendi görüntüsel hali datasıdır, nerde, ne zaman ve hangi ışık koşullarında çekildiği metadatadır. **JDBC** ile sadece basitçe query çalıştırma değil metadata işlemleride yapabiliriz. Yani database'de ki tüm table'ları getir, tableların kolonlarının bilgilerini getir vb...

Mesela aşağıdaki kod ile **test** isimli database'de ne kadar table varsa isimlerini listeleyebilirim.
```
    DatabaseMetaData databaseMetaData = conn.getMetaData();
    ResultSet resultSet = databaseMetaData.getTables("test", null, null, new String[]{"TABLE"})
    while(resultSet.next()) { 
        String tableName = resultSet.getString("TABLE_NAME"); 
        System.out.println(tableName);
  }
	
```

Aynı şekilde bir table'daki kolonların detaylı bilgilerini isteyebilirim.

```
      ResultSet columns = databaseMetaData.getColumns("test", null, "courses", null)
	  while(columns.next()) {
	    String columnName = columns.getString("COLUMN_NAME");
	    String columnSize = columns.getString("COLUMN_SIZE");
	    String datatype = columns.getString("DATA_TYPE");
	    String isNullable = columns.getString("IS_NULLABLE");
	    String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
	    System.out.print(columnName + " ---- ");
	    System.out.print(columnSize + " -------- ");
	    System.out.print(datatype + " --------- ");
	    System.out.print(isNullable + " ---------- ");
	    System.out.println(isAutoIncrement);
	 }
```

### DDL & DML
Arkadaşlar **DML** data manipulation language **DDL** ise data definition language demektedir. **DDL** dediğimiz database'in yapısına dair query'lerdir çoğu zaman. Mesela **Create Table, Alter Table, Create Database, Rename vb...**

**DML** ise aslında database'de herhangi bir table'da bulunan datalar üzerinde yaptığımız işlemlere dair query'lerimizdir. **Insert into, update, delete, select vb...**

**JDBC**'de her türlü query işletebiliriz arkadaşlar. **DDL veya DML** farketmez. Sıfırdan bir database'i yaratıp, içine tableları create edip, sonra table'ın yapısını değiştirip, sonra içine veri ekleme, getirme ve veri çıkarma gibi tüm işlemler...

```
    String query = "CREATE TABLE bootcamp_students(id int(11) auto_increment, name varchar(255), PRIMARY KEY(id))";
	Statement st = conn.createStatement();
	st.execureUpdate(query);
```

gibi...
