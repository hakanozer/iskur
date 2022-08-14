# MyBatis CRUD

Arkadaşlar **MyBatis** **JDBC** üzerine kurulmuş lightweight(hafif) bir ORM kütüphanesidir. **Query sonuçlarını objelere maplemek, ilişkileri maplemek, dynamic query oluşturma, lazy ve eager load gibi konularda bize yardımcı olmaktadır**. Biraz kendine özgü durumları vardır, diğer **ORM**'leri biraz kafanızda arka plana atmanız lazım bunu öğrenirken.

**MyBatis** projesi oluşturmak için maven projesi yaratıp(simple archetype) öncelikle bağımlılıklarımızı yüklememiz lazımdır. MyBatis ve mybatisin gerçekte çalışacağı database için driver bağımlılığı yeterlidir. Biz MySQL ile çalışacağız.

```
    	<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.5.9</version>
		</dependency>
		
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.29</version>
		</dependency>
```

Bunları ekledikten sonra **MyBatis**'i konfigüre etmemiz gerekmektedir. Hangi db'ye bağlanacak, hangi sql driveri kullanıyor, kullanıcı adı ve şifre ne vb...(JDBC'deki gibi)

**MyBatis** en baştan beri yoğun bir şekilde xml ile çalışmaktadır. **Bizde konfigürasyonu xml dosyası ile yapacağız**. Öncelikle maven projemizde src/main klasörünün altına /resources adında bir klasör açıyoruz. Daha sonra içine **myBatis_conf.xml** şeklinde bir dosya yaratıyoruz. Ve bu dosyanın içine 

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
       <environments default = "development">
          <environment id = "development">
             <transactionManager type = "JDBC"/>  
                <dataSource type = "POOLED">
                   <property name = "driver" value = "com.mysql.cj.jdbc.Driver"/>
                   <property name = "url" value = "jdbc:mysql://localhost:3306/test"/>
                   <property name = "username" value = "john"/>
                   <property name = "password" value = "doe"/>
                </dataSource>        
          </environment>
       </environments>
    </configuration>
```

şeklinde en temel bağlantı bilgileri için konfigürasyonu ekliyoruz. Burada farklı environmentlerde(development, production vb...) farklı bağlantı bilgileri için <environments> tagi altında ayrı ayrı tanımlar yapabiliriz. Burada biz development adında bir environment tanımlayıp bunu default olarak ayarlıyoruz ve sonra konfigürasyon bilgilerini giriyoruz.

Gördüğünüz gibi aynı JDBC'deki gibi database'e bağlanmak için gerekli bilgileri veriyoruz aslında. Burada ekstradan
* datasource type = "POOLED" kısmı paralelde bir çok query çalışırken her query için bağlantı açıp kapatma; belli bir bağlantı kümesinin açık tutup gerektiğinde boşta olanı kullan gibi bir özellik sağlıyor. Buna **Connection Pooling(Bağlantı Havuzu)** denmektedir. Bu havuzda kaç tane bağlantının aktif şekilde tutulacağı da konfigüre edilebilmektedir.
* transactionManager type = "JDBC" demek ise transactionları açıkca bizim yöneteceğimizi söylüyor yani işlemler bitince açıkça commit veya rollback çağrısı yapmak bizim işimizdir. 

Bu adımdan sonra artık querylerimiz ile sınıflarımız arasında mapleme yapabilmekteyiz. Bunun için öncelikle courses adında bir tablomuz olduğunu düşünelim.

```
    courses{
        id int(11) auto_increment PRIMARY KEY
        name varchar(255)
        price int(11)
    }
```

Buna karşılık Java sınıfımızı oluşturuyoruz. (Getter setterları unutmuyoruz, MyBatis yoğun bir şekilde getter setter kullanır!)

```
    public class Course {
    	
    	private int id;
    	private String name;
    	private int price;
    	
    	//getters setters
    }
```

Bu işlemden sonra artık queryleri mapleme işine gelmiş bulunmaktayız. Arkadaşlar **MyBatis bizi query yazmaktan kurtarmaz diğer ORM'ler gibi amacı bu değildir**. Query'leri yine yazmak zorundayız. MyBatis query sonuçları ile objeler arası mapleme işini yapar aslında temel olarak.

Bunun için yine bir resources folderı altına courserMapper.xml(adı değişebilir zorunlu değil belli bir format) şeklinde bir xml dosyası açıyoruz.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	    
    </mapper>
```

Daha sonra bunun içine query tipimize göre uygun taglerle(<select>, <insert>, <update>, <delete>) querylerimizi ekliyoruz.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="getAll" resultType="com.apo.batiscourse.Course">
    		SELECT * FROM courses
    	</select>
    </mapper>
```

Burada yeni bir select query'si ekledim tüm courseları getiren. Bu query'e bir **id** verdim bunu ilerde bu query'i çağırırken kodumuzda kullancağız. Birde **resultType** vererek queryden dönen her bir satırın **Course** nesnesine çevirilmesi gerektiğini söyledim.(Package ismi ile birlikte full sınıfı ismi veriyoruz dikkat!)


Bu aşamada bu **courseMapper.xml** dosyasını **myBatis_conf.xml** konfigürasyon dosyamıza tanıtmamız lazımdır. Bunu <mappers> tagi ile

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
       <environments default = "development">
          <environment id = "development">
             <transactionManager type = "JDBC"/>  
                <dataSource type = "POOLED">
                   <property name = "driver" value = "com.mysql.cj.jdbc.Driver"/>
                   <property name = "url" value = "jdbc:mysql://localhost:3306/test"/>
                   <property name = "username" value = "john"/>
                   <property name = "password" value = "doe"/>
                </dataSource>        
          </environment>
       </environments>
       mappers>
     	<mapper resource = "courseMapper.xml"/>
      </mappers>
    </configuration>
```

şeklinde tanımlıyoruz. **Unutmayın her tanımladığınız mapper.xml dosyasını(her bir table için ayrı mapper dosyası olur genelde) konfigürasyon dosyasına eklemeniz zorunludur.**


Bu aşamadan sonra artık Java tarafına geçip MyBatis'i hazırladığımız konfigürasyon ile ayağa kaldırmalı, daha sonra **courseMapper.xml**'de belirlediğimiz herhangi bir query'i çağırmalıyız.

```
        Reader reader;
		try {
		    //read configuration and create session factory
			reader = Resources.getResourceAsReader("myBatis_conf.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			// open session, session is connection between database and us(like JDBC connection)
			SqlSession session = sqlSessionFactory.openSession();
			// call query from mapper.xml files with defined id
			List<Course> courses = session.selectList("getAll");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
```	
Gördüğünüz gibi courseMapper.xml'de tanımladığımız query'i id'si ile session nesnesi üstünden çağırdım ve sonuç bir collection olacağı için List<Course> şeklinde sonucu alabildim. **Sonuç liste döneceği için session'ın selectList() methodunu çağırdım bu arada, farklı işlemler için session nesnesinin farklı methodlarını çağıracağım.**

mapper.xml'lerde ne kadar query'iniz varsa hepsini tanımlayabilirsiniz. Mesela kurs detayını id'sine göre getirmek istediğimizde hemen **courseMapper.xml**'e gidiyoruz ve yeni bir <select> query'si ekliyoruz. 

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="getAll" resultType="com.apo.batiscourse.Course">
    		SELECT * FROM courses
    	</select>
    	<select id="findById" resultType="com.apo.batiscourse.Course">
		    SELECT * FROM courses WHERE id = #{id}
	    </select>
    </mapper>
```

Gördüğünüz gibi query'e ***WHERE id = # {id}*** şeklinde parametre ekledim. Aynı **JDBC prepared statementi** gibi parametrik query parçaları buradada **#{id}** şeklinde gösterilmektedir. (Gerçekten alt levelde zaten prepared stetament çalışıyor her zaman diyebiliriz.)

Daha sonra Java kodumda bu query'i şu şekilde çağırabilirim. 

```
	Course course = session.selectOne("findById", 1);
```

Gördüğünüz gibi tekil bir sonuç beklediğim için **selectOne()** methodunu çağırdım. Ve id parametresine gidecek değeri buradan selectOne() methodunun ikinci parametresi olarak gönderdim. Yani bu çağrım yapıldığında çalışacak query

```
	SELECT * FROM courses WHERE id = 1
```

şeklinde olacaktır.

Arkadaşlar dikkatinizi çekmiştir courseMapper.xml'de **resultType="com.apo.batiscourse.Course"** şeklinde sonucun Course nesnesine çevrilmesi gerektiğini söyledim. Burada sürekli her query'de full package ismi ile birlikte sınıf adını yazmak yorucu olabilmektedir. MyBatis'de **sınıf isimlerine daha kısa bir şekilde alias(kısa isim) verebiliyoruz**. Bunu myBatis_conf.xml dosyamızda 

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
     
     <typeAliases>
    	<typeAlias type="com.apo.batiscourse.Course" alias="Course"/>
    </typeAliases>
    
    //another configurations
    <configuration>
    </configuration>
```

Gördüğünüz gibi **typeAliases** altında **com.apo.batiscourse.Course** sınıfına **Course** şeklinde alias verdim. Artık mapper dosyalarımda maplemeyi yaparken full paket ismi yerine sadece **resultType="Course"** şeklinde yazabilirim.


## UPDATE OPERATION

Arkadaşlar update operasyonu için yine mapper dosyamıza <update> tagi ile query'imizi tanımlıyoruz. 
```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
        //other queries....
        //update query
	    <update id="updateCourseName" parameterType="map">
		    UPDATE courses SET name = #{name} WHERE id = #{id}
	    </update>
    </mapper>
```

Gördüğünüz gibi query'imize yine **id** verdik birde **parameterType** diye bir attribute ekledik. Update yapılırken buraya **name ve id** değerlerini göndermem gerekiyor bende bunu bir Map(HashMap vs...) şeklinde göndereceğimi söyledim aslında MyBatis'e.

Bu aşamadan sonra Java kodumda 

```
    SqlSession session = sqlSessionFactory.openSession();
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("name", "updatedName");
	params.put("id", 1);
	session.update("updateCourseName", params);
```		

şeklinde session'un **update()** methodu query'min id'si ve parametreleri gönderdiğim map ile çağırıyorum. **Dikkat edin map'e koyduğum parametre isimleri query'deki isimlerle aynı olmalıdır!**

Arkadaşlar parametre gönderirken Map göndermek zorunda değiliz. Direk bir **Course** objeside gonderebilriz.

```
        //fetch first
        Course c = session.selectOne("mapper.findById", 1);
        // update object name
		c.setName("updateName");
		//send object as parameter
		session.update("updateCourseName", c);
```

Bu durumda update query'imizde **parameterType="Course"** şekline değişiklik yapıyoruz.

```
     <update id="updateCourseName" parameterType="Course">
	    UPDATE courses SET name = #{name} WHERE id = #{id}
    </update>
```

Update methodları update işleminde etkilenen record sayısını return etmektedir. (JDBC'deki **executeUpdate()** gibi)

## INSERT OPERATION

Arkadaşlar insert için yine bir query yazıyoruz. Bu sefer tag olarak <insert> tagini kullanıyoruz.
```
    <insert id="create" parameterType="Course">
		INSERT INTO courses(name, price) VALUES(#{name}, #{price})
	</insert>
```
Java tarafındada burda belirlenen **parameterType'a** göre(Yine map'te olabilir objede olabilir) verileri gönderip insert işlemi yapabiliyoruz.

```
        
      SqlSession session = sqlSessionFactory.openSession();
	  Course c = new Course();
	  c.setName("Python Course");
	  c.setPrice(122);
	  session.insert("create", c);
```

Buradada seeion'in **insert()** methodunu çağırdık arkadaşlar. Burada önemli bir nokta record create edildikten sonra database tarafında set edilen recordun id'sine ihtiyaç duyabileceğimizdir. Bu id database tarafında auto increment şekilde create ediliyor, bende yok yani. MyBatis'e aşağıdaki gibi; bu recordu insert ettikten sonra DB'den generate olan id'sini al parametre olarak gönderdiğim objenin id'si olarak set et diyebiliriz arkadaşlar. Bunu mapper dosyamızda 

```
    <insert id="create" parameterType="Course" useGeneratedKeys="true">
		INSERT INTO courses(name, price) VALUES(#{name}, #{price})
	</insert>
```

şeklinde **useGeneratedKeys** attribute'una true vererek söylüyoruz. Bu aşamada parametre olarak gönderdiğimiz **Course** nesnesinin id'si operasyondan sonra generate olan id olarak set olmuştur.

```
        SqlSession session = sqlSessionFactory.openSession();
	    Course c = new Course();
	    c.setName("Python Course");
	    c.setPrice(122);
	    session.insert("create", c);
        //this will print auto generated id
        System.out.println(c.getId());
```

Bu methodda JDBC'deki gibi işlemden etkilenen(create olan) record sayısını döner.

##DELETE OPERATION

Arkadaşlar delete operationu içinde <delete> tagini kullanıp query'imizi yazıyoruz.

```
    <delete id="deleteById">
		DELETE FROM courses WHERE id = #{id}
	</delete>
```

Daha sonra java kodunda session'ın delete() methodu ile bu query'i çağırabiliyoruz. Bu method aynı JDBC'deki gibi işlemden etkilenen record sayısını döner.

```
    SqlSession session = sqlSessionFactory.openSession();
	session.delete("deleteById", 1);
```

## MULTIPLE PARAMETER

Arkadaşlar herhangi bir query birden fazla parametre bekleyebilir. Burada doğru şekilde parametreyi geçirdikten sonra problem yoktur. Mesela name veya price'a göre SELECT yaptığım bir query'i

```
    <select id="findByNameOrPrice" resultType="Course">
		SELECT * FROM courses WHERE name = #{name} OR price = #{price} 
	</select>
```

şeklinde yazabilirm. Burada sonuç 0 veya daha fazla olabileceği için session'in **selectList()** methodunu çağırırım. Ve çağırırken parametreleri bir map ile gönderebilirm. (Obje olarakta gönderilebilir bir kısıt yoktur.)

Yani Java kodumuz

```
    SqlSession session = sqlSessionFactory.openSession();
	Map<String, Object> searchParams = new HashMap<String, Object>();
	searchParams.put("name", "Java");
	searchParams.put("price", 100);
	List<Course> courses = session.selectList("findByNameOrPrice", searchParams);
```

şeklinde olacaktır.

Konfigürasyon dosyamızın son haline bakacak olursak 

```

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
    	<typeAlias type="com.apo.batiscourse.Course" alias="Course"/>
    </typeAliases>
   <environments default = "app">
      <environment id = "app">
         <transactionManager type = "JDBC"/>  
            <dataSource type = "POOLED">
               <property name = "driver" value = "com.mysql.cj.jdbc.Driver"/>
               <property name = "url" value = "jdbc:mysql://localhost:3306/test"/>
               <property name = "username" value = "john"/>
               <property name = "password" value = "doe"/>
            </dataSource>        
      </environment>
   </environments>
   <mappers>
     	<mapper resource = "coursemapper.xml"/>
   </mappers>
</configuration>

```

## ÇOK ONEMLİ NOT

Arkadaşlar MyBatis **Update, Create, Delete** gibi operasyonlar'da **commit veya rollbacki** açıkca çağırmanızı bekler. Yani bu işlemlerden sonra **session.commit()** veya** session.rollback()** diyerek transaction'ı sizin bitirmeniz gerekmektedir. Bu çağrımı yapmazsanız; değişiklikler database'e yansımayacaktır.

##ÖNEMLI NOT

Arkadaşlar mapper dosyalarındaki <mapper> taginin

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="getAll" resultType="com.apo.batiscourse.Course">
    		SELECT * FROM courses
    	</select>
    </mapper>
```

gördüğünüz gibi bir **namespace** attribute'si vardır.

<mapper> taginde ki namespace attributesi birden fazla mapperımız olduğunda query'lere verdiğimiz id'lerde çakışması olması durumunda ayrımın sağlanabilmesi için mapper'a özel bir isimdir aslında. 

Yani mesela **courseMapper.xml'de** ***getAll*** id'li bir query'm var bir de **userMapper.xml'de** aynı id'li query var. Bunları session nesnesi üstünden çağırırken direk **getAll** dersem; session nesnesi hangisi çağıracağını bilemez.

```
    //hangi getAll userdaki mi, courserdaki mi?
	session.selectList("getAll");
```

Bu gibi durumlarda bu çakışmayı; hangi mapperdaki **getAll** çağırmak istiyorsak o mapper'a verdiğimiz namespace'i id'nin önüne ekleyerek sorunu çözebiliriz.

```
    //couserMapper.xml'deki getAll çağırılacak
	session.selectList("courseMapper.getAll");
```













