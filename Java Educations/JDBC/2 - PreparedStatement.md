# PREPARED STATEMENT
Arkadaşlar bizim dünyamızda **SQL injection** diye bir saldırı vardır. Genelde dışardan aldığımız parametreleri direk olarak **SQL** stringimize birleştirdiğimizde bu saldırıya açık hale geliriz. Mesela kullanıcıdan yaşını input girmesini istedik veya web sayfamızdadan gelen bir yaş inputu var bunu alıp sorguma direk olarak

```
    String parameter = "12";
    String query = "SELECT * FROM users WHERE age = " + parameter;
```
şeklinde birleştirirsem; burada kötü niyetli bir kullanıcı parametre değerine zarar verme ihtimali olan bir input girebilir. Mesela **1=1; DROP TABLE users;** şeklinde. Bu durumda query'imiz şu hale gelmektedir.

```
    String query = "SELECT * FROM users WHERE age = 1=1;DROP TABLE users";
```

Bu query eğer database sunucumuzda önlem alınmadı ise users table'ının tamamen silinmesine sebep olabilir. (**DROP table [tablename]** bir table'in silinmesini sağlar)

İşte bu gibi durumlarda parametreleri direk olarak query'e eklemememiz lazımdır. Ya biz uğraşıp gelen parametreyi iyice temizleyeceğiz ya da **PreparedStatement** dediğimiz daha güvenli bir yöntemi kullanacağız.

**Prepared Statement(adı üstünde)** daha sonra kullanılmak üzere önden hazırlanmış query'lerdir. Önden genel yapısı hazırlanan; parametrik kısımları ? işareti ile belirlenmiş querylerdir. Mesela yukarıdaki query'i **PreparedStatement** ile
```
    String query = "SELECT * FROM users WHERE age = ?";
    PreparedStatement ps = conn.prepareStatement(query);
```

şeklinde **conn.prepareStatement(query)** diyerek database'e gönderip bizim için hazır edilmesini sağlayabiliriz. Dikkat edin parametrik kısımları ? işareti ile belirttik. Bunların değeri sonradan ayrı bir şekilde database'e gönderilip sonuç alınacaktır.

Daha sonra dışardan aldığımız parametreyi 

```
    String query = "SELECT * FROM users WHERE age = ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, parameter);
```
şeklinde aynı **ResultSet**'teki gibi veri tipine göre uygun methodu çağırarak(integer için mesela **setInt()**, string için **setString()** vb....) prepared statement'e parametremizi geçirebiliriz. Daha sonra **PreparedStatement**'in uygun methodunu(select için **executeQuery**, update, delete vb için **executeUpdate()**) çağırarak query'inin çalışmasını sağlarız. 

burada **setInt()** methodundaki(ve diğer set methodlarındaki) ilk parametre query'de kaçıncı sıradaki ?(soru işareti)'ne değer atadığımızı belirten index kısmıdır. Ve bu index 1'den başlar.

Burada **gelen parametre query'e direk birleşmez**, database sunucusu tarafından direk olarak **parametre olarak değer görülür**. İşte bundan dolayı **SQL injection** mümkün olmaz. Yani aynı senaryoda işleyen tek query bizim select query'imizdir ve **age'i 1=1;DROP TABLE users değerine eşit olan user getirilmeye çalışılır!!**

```
    ResultSet rs = ps.executeQuery();
```

Arkadaşlar **PreparedStatement**'da parametrik birçok yeri ? işareti ile belirtebiliriz, sayısındada sınır yoktur diyebiliriz kabaca. Yani 

```
    String query = "SELECT * FROM users WHERE age = ? AND name = ? OR course IN(?,?,?)";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, 15);
    ps.setString(2, "Abdullah");
    ps.setString(3, "Java");
    ps.setString(4, "C#");
    ps.setString(5, "Go");
```
şeklinde bir query yazabiliriz.

İşte burada önemli nokta herşeyi ? işareti ile belirtemeyeceğimizdir. Mesela ORDER BY yazmak istediğimizde buradaki field ismini ve order yönünü ? işareti ile belirtemeyiz. 

```
    //Error
    String query = "SELECT * FROM users WHERE age = ? ORDER BY ? DESC
    PreparedStatement ps = conn.prepareStatement(query);
```

Veya SELECT * demek yerine hocam kolon isimlerini de ? işareti ile yazayım sonra veririm değerini diyemiyoruz. Buralar query yazım aşamasında belli olmalıdır.

```
    //Error
    String query = "SELECT ?,?,? FROM users WHERE age = ?
    PreparedStatement ps = conn.prepareStatement(query);
```

Aslında böyle olması mantıklıdır çünkü zaten buralar saldırıya açık yerler değildir çokta.

### SERVER SIDE PREPARED STATEMENT - CLIENT SIDE PREPARED STATEMENT

Arkadaşlar normalde **ParepatedStatementlar** şu şekilde çalışmaktadır. Önce prepare edilecek query database'e gönderilir.

```
    Prepare	UPDATE courses SET name = "Abd" WHERE id = ?
```

Daha sonra execute methodlarından biri çalıştığında parametreler gönderilir ve query'ler işletilir. 

```
    Execute with Param 1
```

Yani dikkat edin burda database'e iki kere gidildi. 

* Prepare
* Execute

Bazen bazı durumlarda bu iki kere gitmek bir yük olarak görülmekte ve bunu başka bir şekilde yapabilir miyiz sorusunu doğurmaktadır. 

Arkadaşlar **JDBC** tarafında driverlar tercihen prepared statement işini **sunucuya gitmeden kendi içindeki kodlarla yapmak isteyebiliyor**. Buna **client-side prepared statement** diyoruz.(Öncekine **server-side prepared statement** diyoruz).

Yani burada prepare etme işi de, parametreyi gönderip query çalıştırmada ve işin güvenliğide tamamen sizin java kodunuzda oluyor. Bunun getirisi database'e bir kere gitmek oluyor; **ancak günceliğini çok iyice sağlamak lazım yoksa ciddi probleme sebep olabilir.** 

Bizim **MySQL** driveri'da default olarak **client-side prepared statement** kullanıyor. Bu ayarı connection string'de verdiğimiz bir queryString parametresi ile değiştirebiliyoruz.

```
    String url = "jdbc:mysql://localhost:3306/test?useServerPrepStmts=true";
```

Buradaki **useServerPrepStmts** parametresi ile PreparedStatement'in sunucu tarafından yönetilmesini istiyoruz. Yani yukardaki ilk senaryo gerçekleşiyor; query database'e prepare olmak üzere gönderiliyor daha sonra parametre gönderilip query çalışıtırılıyor.





