#SPRING-DATA-JDBC

Arkadaşlar herşey iyi güzel, jdbc ile her işi yapabiliyoruz. Ancak yorucu ve birçok işi tekrar tekrar yazmak zorunda kalıyoruz. Bunu daha kolay yapsak fena olmazdı hani.

**OOP** dünyası başka bir dünya, burada classlari propertyler, methodlari kalıtım vb.. şeyler var
**RDBMS** dünyası ise başka birşey burada database, table, kolon, satır, foreign key gibi şeyler var. 

Biz **OOP** bir dille kod yazıyoruz, database'imiz **RDBMS** işte bu noktada bunlar arasındaki farklılıkları en aza indirgeyebilecek, sanki **OOP**'deki nesnelerler çalışıyormuşuzcasına bize kolaylık sağlayacak bazı yaklaşımlar vardır. Bunların geneline **ORM(Object Relation Mapping - Mapper)** diyoruz arkadaşlar. Araya adam sokuyoruz yani database'le konuşurken.

Bunlar veriyi database'den getirdikten sonra nesneye çevirme, nesne üstünden update işlemleri yapma, ilişkileri getirme vb.. birçok işi yerine getirebilmektedir.

Java dünyasında çok yaygın kullanılan **hibernate** bir ORM yazılımıdır mesela. 

Arkadaşlar Spring ile database işlemleri yaparken işlerimizin biraz daha kolay olması adına **ORM** yazılımları kullanırız. Spring dünyasında **spring-data-[package-name]** şeklinde spring-data ile başlayan kütüphaneler genel olarak data katmanlarına erişimi kolaylaştıran hepsine diyemesekte birçoğu ORM olan kütüphanelerdir.

Burada **spring-data-jpa**, **spring-data-jdbc**, **spring-data-redis** gibi kutuphanelerimiz vardır. Biz bu dökümanda **spring-data-jdbc**'yi incelemeye çalışacağız.

Öncelikle spring initializer'e girip uygun bir şekilde bağımlılıklarımızı belirliyoruz. Burda mysql ile çalışıp spring-data-jdbc ORM'unu kullanacağız bunun için

```
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jdbc</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
```

şeklinde bağımlılıklarımızı seçip **pom.xml'e** eklenmesini sağlıyoruz. Dikkat edin initializerde **JDBC API** diye bir bağımlılık daha var bizim kullanacağımız bu değil! Bizimki **Spring Data JDBC**.

Daha sonra bir database'e bağlanacağımız için database konfigürasyon bilgilerini bir yere eklememiz lazım. **Spring projelerinde resources folderının altındaki application.properties dosyası konfigurasyon parametreleri için default dosyamızdır.** Buraya aynı normal jdbc'deki connection string, username, password, driver gibi bilgileri eklemeliyiz.

```
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=john
    spring.datasource.password=doe
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Arkadaşlar daha sonra kodumuzda nesne olarak erişmek istediğimiz database table'larına dair sınıf tanımlaması yapıyoruz.(Entity diyebilirsiniz bu sınıflara) Mesela accounts şeklinde bir table'imiz olsun buna karşılık sınıf olarak Account adında bir sınıf tanımlayabiliriz ve table'daki kolonları sınıftaki propertyler olarak belirtebiliriz.

```
    @Table(name = "accounts")
    public class Account {
    	
    	@Id
    	private int id;
    	@Column("source")
    	private String name;
    	private String surname;
    	@Column("tc_number")
    	private int tc;
    	@Column("number")
    	private int accountNumber;
    	private int balance;
    	
    	//getters setters
    }
```
Burada sınıf tanımını yaparken annotationlar ile kullandığımız **ORM**'e bilgiler veriyoruz. **@Table** annotationu ile bu sınıfa karşılık gelen table'i belirtiyoruz. **@Column** annotationu ilede bu propertylere karşılık gelen kolon adlarını belirtiyoruz. Burada kolon adı ile property adı aynı ise **@Column** annotationuna gerek yoktur.

Bunu yaptıktan sonra bir adet Repository interface'i yaratıyoruz ve bu interface'i **ORM**'in içinde gelen bir interface'den(**CrudRepository**) extend ediyoruz. Bu sınıfı **@Repository** annotationu ile işaretlemeyi unutmuyoruz!
```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
    
    }
```

Burada dikkat edin **CrudRepository** generic bir sınıf ve generic parametrenin birincisi repository'nin işlem yaptıgı sınıfın adı(Account) diğeride bu işlem yapılan sınıfın id kolonunun veri tipi(Integer). Bunlar zorunludur bazı işlemler için.

Aslında bu kadar arkadaşlar! Daha sonra controllerımız'da bu repository'i **@Autowire** ediyoruz ve ön tanımlı gelen birçok hazır methodu(**save(Account), findAll(), findById() vb...**) tek satır sql yazmadan kullanabiliyoruz.

```
    @RestController
    public class AccountController {
    	
    	@Autowired
    	private AccountRepository repository;
    	
    	@GetMapping("/accounts")
    	public List<Account> all(@PathVariable int id) {
    		return this.repository.findAll();
    	}
    }
```

Bundan sonra artık yeni model sınıfları ve repositoryler ekleyerek kodumuzu geliştiriyoruz.

### CUSTOM REPOSITORY METHODS

Arkadaşlar sadece CrudRepository'den gelen ön tanımlı methodları kullanmak zorunda değiliz. Burda önümüzde iki seçenek vardır.
* Derived Query Methods
* @Query annotation

**Derived Query method** tanımladığımız repository interface'i içine belli şartlara uymak şartı ile method tanımı yaptığımızda **ORM**'in bunları gerçek query haline getirip çalıştırabilmesidir. Mesela interface'e

```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
        public List<Accont> findByName(String name);
    }
```

şu şekilde findBy ile başlayan isimde bir method eklediğimizde **ORM** bunu otomatik olarak 

```
    SELECT * FROM account WHERE name = [parametreden gelen değer]; şeklinde bir query olarak çalıştırır.
```

Methodun isminde findBy'dan sonra gelen kısım database'deki kolon ismi değil sınıftaki property ismi olmalıdır unutmayın! Propertyden kolona çevrimi zaten Account sınıfına bakarak **ORM** yapabilir. **@Column** annotationa varsa mesela ona göre çevrim yapacaktır.

Burda epey yeteneklidir **ORM** mesela şu şekilde methodlarda ekleyebiliriz ve düzgünce çalışacaktır.

```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
        public List<Accont> findByName(String name);
        public List<Accont> findByNameStartingWith(String prefix);
        public List<Accont> findByNameContaining(String infix);
        public List<Accont> findByBalanceLessThan(Integer age);
        public List<Accont> findByBalanceIn(Collection<Integer> balances);
        public List<Accont> findByUpdatedDateAfter(ZonedDateTime birthDate);
        public List<Accont> findByNameOrderByName(String name);
        public List<Accont> findByNameOrSurname(String name, ZonedDateTime birthDate);
    }
```

Diğer bir yöntem **@Query** annotationudur arkadaşlar. Bu annotation ile interface'e bir method yazıp bu method çağırılırsa çalışacak query budur şeklinde bilgi verebiliriz ORM'e.

```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
        @Query("SELECT * FROM accounts WHERE name = :name and tc_number= :tc_number and number = :accountNumber")
        public List<Accont> search(@Param("name") String name,@Param("tc_number") int tc, @Param("accountNumber") int accountNumber);
    }
```

Buradaki query'imizi prepared statemente benzer bir şekilde ? yerine **named parameter** dediğimiz şekilde parametrik olarka tanımladık. Methodumuzda da parametlerin **@Param **annotationu ile query'de hangi : ile başlayan parametreye denk geldiğini belirttik bu saydede bu method çağırılınca yazdığımız query düzgünce çalışacaktır.

### RELATIONS

Arkadaşlar bu **ORM** aslında birçok konuda hibernate gibi bir **ORM'in** çok gerisindedir. İlişkiler konusundada yeteneği sınırlıdır ancak bazı şeyleri de yapabilmektedir. Mesela sistemde userlarım olsun bunların birden fazla accountu olabiliyor olsun. ***accounts*** tablosuna ekstradan bir **user_id** diye kolon koymuş olalım. Bu durumda userı her istediğimde accountlarıda gelsin istiyor isem **User** modelini şu şekilde gerçekleştirebilirim.


```
    @Table(name = "users")
    public class User {
    	
    	@Id
    	private int id;
    	private String username;
    	@MappedCollection(keyColumn = "id", idColumn = "user_id")
    	private Set<Account> courses;
   
        //getters setter
        
    }

```

**@MappedCollection** annotationda **idColumn** ile foreign key kolonunu, **key** ile user'daki id kolonunu belirterek user getirildiğinde accountlarının da getirilmesini sağlayabilirim.

### TRANSACTION

Arkadaşlar farketmişsinizdir burda transaction gormedik hiç. Spring içinde **ORM**'ler transactionları otomatik olarak yönetebilmektedir arkadaşlar. Ancak gerekli yerlerde bu declarative bir şekilde bizde yönetebilmekteyiz.

Normalde CrudRepository'den gelen herbir ön tanımlı methodu çağırdığımızda zaten bir transaction başlatılır çalışma bitince commitlenir. Kendi yazdıgımız methodların transactional olması istiyorsak isek çok kolay bir şekilde methodun ustune


```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
        @Transactional
        @Query("SELECT * FROM accounts WHERE name = :name and tc_number= :tc_number and number = :accountNumber")
        public List<Accont> search(@Param("name") String name,@Param("tc_number") int tc, @Param("accountNumber") int accountNumber);
    }
```

şeklinde **@Transcactional** annotationunu koyabiliriz. Bu autocommiti sıfıra çekme, iş bitince commitleme veya hata olursa rollback yapma gibi işlemleri otomatik olarak yapacaktır.

**@Transactional** annotationu epey yeteneklidir. İçindeki propertyler ile hangi exceptionlarda rollback yapacağına, isolation leveline vs karar verebiliyoruz.

```
    @Repository
    public interface AccountRepository extends CrudRepository<Account, Integer>{
        @Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.READ_COMMITTED)
        @Query("SELECT * FROM accounts WHERE name = :name and tc_number= :tc_number and number = :accountNumber")
        public List<Accont> search(@Param("name") String name,@Param("tc_number") int tc, @Param("accountNumber") int accountNumber);
    }
```

şeklinde.




























