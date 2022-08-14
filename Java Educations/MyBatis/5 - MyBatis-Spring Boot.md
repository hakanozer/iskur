## SPRING BOOT MyBatis

Arkadaşlar MyBatisi öğrendikten sonra **Spring Boot** ile bunu kullanmayı öğreneceğiz ve daha önce anlatılanları öğrendik ise burda olaylar kolay gerçekten.

Öncelikle spring initializer'e girip gerekli bağımlılıkları yüklüyoruz. Lazım olan bağımlılık iki tanedir burada

* mybatis-spring-boot-starter
* MySQL ile çalışmak için **mysql-connector-java**


```
	    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.2.2</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```

Daha sonra spring'deki konfigürasyon dosyamıza(**application.properties**) bağlanılacak database için **JDBC**'de olduğu gibi gerekli bilgileri veriyoruz.

```
    spring.datasource.url = jdbc:mysql://localhost:3306/test?useSSL=false
    spring.datasource.username = john
    spring.datasource.password = doe
    spring.datasource.driver-class-name: com.mysql.cj.jdbc.Driver
```

Ekstradan MyBatis'in mapper dosyalarının spring tarafından bulunabilmesi için mapper dosyalarını nereye koydu isek onun pathini **mybatis.mapper-locations** keyi ile konfigürasyon dosyasına ekliyoruz.

```
    mybatis.mapper-locations = classpath:mapper/*.xml
```

Gördüğünüz gibi biz path olarak **classpath:mapper/*.xml** değerini verdik. Buradaki **classpath** resources folderına denk gelmektedir**. Daha sonra resources folderının **mapper** diye bir folder olacak ve onun altında herhangi bir isimle başlayan ve uzatısı xml olan dosyalar bizim mapperlarımız olacak.

Yani mesela Course objeleri için **mapper-course.xml** adında bir dosya oluştururuz. Ve bunu
**src/main/resources/mappers/mapper-course.xml** şeklinde doğru pathe koyduğumuzda MyBatis otomatik olarak mapper.xml'imizi bulabilecektir. Daha önce hatıralyın mapperd dosyalarını myBatis_config.xml dediğimiz konfigürasyon dosyasına tek tek ekliyorduk.


Hatırlarsınız mapperlarda **resultType=""** şeklinde query'den dönen sonucun maplenmesi gereken nesnenin sınıfını yazıyorduk. Burada sınıfa **alias** vermezsek paket ismi ile birlikte full sınıf ismini vermemiz gerekiyordu. **Bu noktada spring'de de alias'ları belirleyebiliyoruz**. Bunun için öncelikle Spring'e benim model sınıflarım(tablelara karşılık oluşturduğum sınıflar) şu package'ın altındadır git o paketin altına ordaki sınıfları al sınıf isimlerinin tamamen küçük yazılmış hallerini alias olarak kaydet diyoruz. Bunu properties dosyasına
```
    mybatis.type-aliases-package = com.example.bootis.models
```
konfigürasyon satırını ekleyerek yapıyoruz.

Gördüğünüz gibi ben modellerimin(tablelara karşılık oluşturduğum sınıflar) **com.example.bootis.models** package'i altında olduğunu söyledim. Yani mesela burada **Course** adında bir sınıf varsa otomatik olarak alias'i **course** olacaktır. Ve bizde **resultMap** veya **resultType**'larımızda artık sadece **course** yazarak maplemeyi yapabiliriz.

Burada default olarak alınan sınıf ismi alias'indan başka birşey kullanmak isterseniz geliştirdiğiniz model sınıf isminin üstünde **@Alias** annotationu ile istediğiniz değeri verebilrsiniz.
```
    @Alias("Course")
    public class Course {
    	private int id;
    	private String name;
    	private int price;
    
    }
```

Daha sonra önceden de yaptığımız gibi  **mapper-course.xml** şeklinde mapper dosyamızı oluşturuyoruz. Ve buraya id'ye göre course'un detayını getiren query'i ekliyoruz.
```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="findById" resultType="Course" >
    		SELECT * FROM courses WHERE id= #{id}
    	</select>
    </mapper>	
```	

Bu aşamada spring'in session vs yaratıp queryleri otomatik kullanabilmesi için daha önce anlattığım gibi **mapper.xml'lere** ait interface'imizi tanımlıyoruz.

Interface'de mapper xml'de olan query'lere karşılık methodlar bulundurmaktadır.  Bu sınıfı **@Mapper** annotationu ile işaretliyoruz ki Spring bunu **bean** olarak ve bir **mapper** olarak kaydetsin. Yani mapperımız şu şekilde olmalıdır.

```
    package com.example.bootis.mapper;
    import com.example.bootis.models.Course;
    @Mapper
    public interface CourseMapper{
    	public Course findById(int id);
    }
```

Arkadaşlar buradaki önemli noktayı hatırlayın **Interface ile mapper.xml'ler arasında ilişkiyi kurabilmek için mapper.xml'deki namespace değerine Mapper interface'inin paket ismi ile birlikte full ismini veriyorduk.**
```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "com.example.bootis.mapper.CourseMapper">
    	<select id="findById" resultType="Course" >
    		SELECT * FROM courses WHERE id= #{id}
    	</select>
    </mapper>	
```	

Bunu yaptığımızda interface'imiz ile mapper.xml dosyalarımız arasında ilişki tam olarak kurulmuş oluyor.

Buradan sonra klasik spring arkadaşlar. Mapper interface'lerini controller'da **autowire ediyor** ve kullanmaya başlıyoruz.

```
    @RestController
    public class CourseController {
    	@Autowired
    	private CourseMapper courseMapper;
    	@GetMapping("/courses/{id}")
    	public Course detail(@PathVariable int id) {
    		return this.courseMapper.findById(id);
    	}
    }
```

İşte bu kadar arkadaşlar. Burdan sonrası tamamen Mybatis ile alakalı **JOIN** yapmak, **<association>** getirmek, **<collection>** getirmek, **resultMap** veya lazy eager load yöntemlerini kullanmak vb... Daha önce öğrendiğimiz gibi artık istediğiniz gibi query yazıp controller'da kullanabilirsiniz.





