## RELATIONSHIPS

Arkadaşlar database dünyasında 3 çeşit ilişki vardır temelde.

* One-To-One
* One-To-Many
* Many-To-Many

Yani aslında olaya baktığımızda birşey diğer birşeyden ya one(1) tane yada many(>1) taneye sahip olabilir.

MyBatis bir entity'nin ilişkilerini'de getirebilmekte ve bunlarıda doğru objelere mapleyebilmektedir.
MyBatis bunu yaparken mapper.xml'lerde **<resultMap>** tagleri içinde

* <association> (one tipi ilişkilerde)
* <collection> (many tipi ilişkilerde)

şeklinde iki tagi kullanmaktadır.

MyBatis'de ilişkileri getirirken iki yöntem vardır.
* JOIN query'si yazarak tek bir query'de ilişkininde getirilmesi
* İlişkisel verinin ana query'den sonra ekstra bir query ile getirilmesi

Şimdi bunlara tek tek bakalım birlikte.

##ASSOCIATION

**<association>** tagini **one-to-one** veya **one-to-many** ilişkisinin one tarafı için kullanabiliyoruz. Mesela course'ların bir instructorı vardır. Course **Has One** Instructor.

Bunun için kod tarafımızda **Course** sınıfına öncelikle(Instructorları User sınıfında gösteriyoruz) **User** tipinde bir property ekliyoruz.

```
    public class Course {
    	private int id;
    	private String name;
    	private int price;
    	private User user;
        //getters setters
    }
```
**User** sınıfıda şu şekilde olacaktır.
```
    public class User {
    	private int id;
    	private String name;
    	//getters setters
    }
```
1 - Join yaparak ilişkiyi getirme
Bu adımda kendimiz açıkça course detay query'sine JOIN ekleyerek user'ınında getirilmesini sağlayacağız.
```
    <mapper namespace = "CourseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    	    SELECT * FROM courses INNER JOIN users ON users.id = courses.user_id WHERE courses.id = #{id}
    	</select>
    </mapper>	
```

Öncelikle burada bazı problemler var onları çözelim. İki tabloda'da id kolonu var ve biz bir şekilde bunu çözmezsek(alias vermek) mapleme işi düzgünce yapılamaz. Bundan dolayı

```
    <mapper namespace = "CourseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    	    SELECT *, courses.id as course_id, users.id as user_id FROM courses INNER JOIN users ON users.id = courses.user_id WHERE courses.id = #{id}
    	</select>
    </mapper>	
```

şeklinde id kolonlarına alias ekliyoruz. İşte şimdi bir **resultMap** kullanarak association'umuzu mapleyebiliriz.

```
    <resultMap type="Course" id="courseDetailResultMap">
		<id property="id" column="course_id"/>
		<association property="user">
			<id property="id" column="user_id"/>
			<result property="name" column="name"/>
		</association>
	</resultMap>
```	

Gördüğünüz gibi id'lere alias verdiğimiz için önce id kolonlarını düzgünce mapliyoruz. 
Daha sonra **<association>** tagini kullanarak bu ilişkisel query'den gelen datanın(join) **Course** sınıfında bulunan **user** isimli property'e denk geldiğini söylüyoruz ve maplenmenin yapılmasını sağlıyoruz.

Dikkat edin **<association>**'da **User** için tüm kolonların maplenmesini açıkça yaptık. **<association> tag**inde auto mapping default olarak kapalı gelmektedir arkadaşlar. 

Ya hocam ne şimdi join yaptım diye **<association>** taginde ilişkiden gelen verinin tüm kolonları property ismi ile aynı olsa bile tek tek **<result>** olarak mapleme mi yapmalıyım diyorsunuzdur. Arkadaşlar burada **<association>** taginde **auto mappingi** aktifleştirip bu yükten kurtulabiliriz. Ancak dikkat edin **JOIN** yaptığımız tablolarda aynı isimli kolonlar varsa alias vermeyi unutmamak şartı ile.

Yani automappingi açtığımız durumda **resultMapimiz** şu şekildede olabilir.

```
    <resultMap type="Course" id="courseDetailResultMap" autoMapping="true">
		<id property="id" column="course_id"/>
		<association property="user" autoMapping = "true">
			<id property="id" column="user_id"/>
		</association>
	</resultMap>
```	

İşte bu aşamada java tarafında 

```
    Course c = session.selectOne("findById", 2);
	System.out.println(c.getUser().getName());
```
şeklinde **Course**'un instructorının adına ulaşabiliyoruz.

2 - Bu adımda ilişkisel verinin joinle değil sonradan atılan ayrı bir query ile getirilmesini sağlayacağız. Burada **courseMapper.xml**'deki detay query'sini tekrardan sadece course detayını getiren hale getiriyoruz.

```
    <mapper namespace = "courseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    	    SELECT * FROM courses WHERE id = #{id}
    	</select>
    </mapper>
```

Daha sonra **resultMap** kısmında ise **<association>** taginde bazı değişiklikler yapıyoruz.
```
     <resultMap type="Course" id="courseDetailResultMap">
		<association property="user" column="user_id" select="getUserById"/>
	</resultMap>
```	

Burada association tagina baktığımızda yine ilişkisel datanın **Course** sınıfındaki user propertisine denk geldiğini söylüyoruz. 

**column** attribute'u ile user datası getirilirken **Course** nesnesindeki **user_id** kolonunun kullanılması gerektiğini söylüyoruz. Yani user detayı getirilirken atılacak **SELECT * FROM users WHERE id = #{id}** şeklindeki ikinci query'mize gönderilecek değeri ayarlıyoruz.

Ve daha sonra **select** attribute'una **getUserById** şeklinde aslında **user detayını getirmek için çalıştırılacak ikinci query'nin id'sini veriyoruz**.

Daha sonra herhangi bir mapper'a(**courseMapper.xml**'de olabilir, **userMapper.xml**'de olabilir, tekrar kullanılabilirlik adına **userMapper.xml**'e yazmak daha iyidir.) **getUserById** query'imizi yazalım.
```
    <select id="getUserById" resultType="User">
		SELECT * FROM users WHERE id= #{id}
	</select>
```

Hah işte artık **MyBatis** bu şekilde course'ın user'ınıda ikinci bir query atarak getirebilmektedir.

* Course'ın detayını getir
* Daha sonra getUserById query'sine course'daki user_id parametresini göndererek User detayını getir
* Bu getirilen user'ı course nesnesinin user'ı olarak set et
* Ve ilişkisel data ayrı bir query ile bu şekilde getirilmiş oldu.

Yani önce
```
    SELECT * FROM courses WHERE id= 2 
```
şeklinde course detayı istenir daha sonra bunun user_id kolonunun değeri alınır ve ikinci query ile 
```
    SELECT * FROM users WHERE id = 3
```
şeklinde user datası getirilir. **JOIN'e göre query sayısı bir arttı.!!**

Burada java kodunda herhangi bir değişim yoktur, ilişkinin joinden mi geldiği sonradan ayrı bir query'den mi geldiği java tarafını etkilemeyecektir.

```
    Course c = session.selectOne("findById", 2);
	System.out.println(c.getUser().getName());
```

##COLLECTION

**<collection>** **many-to-many** veya **one-to-many** ilişkilerinin many tarafını maplemek için kullanılmaktadır. **<association>**'a çok benzerdir arkadaşlar **<collection>** tagi. Şimdi yukarıdaki **Course-User** ilişkisine birde ters taraftan bakalım. Ters taraftan bakınca ilişki many-to-one'dır yani bir user'ın birden fazla course'u olabilir. **User Has Many Courses**

MyBatis'ten user'ı istediğimizde onun courselarının da gelmesini istediğimizde bunu **<collection>** tagi ile yapabilmekteyiz. Yine bunun da istersek **JOIN** ile, istersek daha sonra ayrı bir query ile getirilmesini sağlayabiliriz.

Öncelikle **User** sınıfımıza **List<Course> courses** şeklinde userın courselarını tutabilecek listeyi ekliyoruz.

```
    public class User {
    	private int id;
    	private String username;
    	private List<Course> courses;
        //getters setters
    }
```

İlk olarak join ile courseları getirme kısmını yapalım. **userMapper.xml**'e bu işlem için query'imizi şu şekilde ekliyoruz.
```
    <select id="getUsersWithCourses" resultMap="userWithCourses">
		SELECT *, courses.id as course_id, users.id as user_id FROM users INNER JOIN courses ON courses.user_id = users.id
	</select>
```
Gördüğünüz gibi yine isimleri çakışan kolonlara alias ile farklı isimler verdik. Daha sonra bu query sonucuna özel **resultMapimizi**
```
    <resultMap type="User" id="userWithCourses" autoMapping="true">
		<id column="user_id" property="id"/>
		<collection property="courses" ofType="Course">
			<id column = "course_id" property = "id"/>
			<result property="name" column="name"/>
		</collection>
	</resultMap>
```	
bu şekilde belirtebiliriz.

Buradaki dikkat çeken noktalara bir bakarsak

* **resultMap**'te açık bir şekilde **autoMapping'i** true olarak belirttim bu sorgudaki ana entitiy olan **User** maplemesinin otomatik yapılması için zorunludur. (Arkadaşlar bug'da olabilir emin değilim normalde **resultMap**'ler default olarak **auto mapping** yapıyor, belki de **<collection>** taginden dolayı auto mapping bu senaryoya özel olarak kapatılıyor, bizden **auto mappingi** açmamız bekleniyor emin değilim, biz her ihtimale karşın açalım)
* daha sonra **<collection>** tagi ile userın courseları maplenmeye çalışılmıştır. Yine maplemenin **User** sınıfındaki **courses** adındanki property'e yapılacağı söylenir.
* **<collection>** taginde **ofType="Course"** diyerek oluşacak **courses** listesinin içinde **Course** nesneleri olacağı belirtiliyor.(Otomatik olarak çözümlemesini beklerdim, ancak bunu yazmazsak saçma sapan bir hata yaşıyoruz arkadaşlar)
* Daha sonra course'lar için **<id>** ve **<result>** tagi kullanarak kolon maplemelerini yapıyoruz.

Burada **<collection>** taginde aynı **<association>** taginde olduğu olduğu **auto mapping** kapalıdır. Yani **<collection>** içinde neyi mapledi iseniz sadece o kolonlar olacaktır **Course** nesnesinde. Burada yine collection taginde **autoMapping="true"** diyerek **Course**'a dair kolonlarında otomatik maplenmesini açabilirsiniz.

```
    <resultMap type="User" id="userWithCourses" autoMapping="true">
		<id column="user_id" property="id"/>
		<collection property="courses" ofType="Course" autoMapping="true">
			<id column = "course_id" property = "id"/>
		</collection>
	</resultMap>
```	

Java kodunuzdada direk olarak user nesnesi üstünden **getCourses()** methodunu çağırarak direk olarak user'ın course'larına erişilebilmekteyiz.

```
    List<User> users = session.selectList("getUsersWithCourses");
	for(User u : users) {
		System.out.println(u.getName());
		for(Course c : u.getCourses()) {
			System.out.print(c.getName() + " --- ");
		}
		System.out.println();
	}
```

Burada ilişkiyi yine **<association>**'da olduğu gibi **JOIN** yapmadan ayrı bir query olarak getirebilmekteyiz. Burada **<collection>** tagine yine **select** attribute'u ekliyoruz. Ve bu attribute'ın değerine ilişkisel datanın getirilmesi için gerekli ikinci query'inin id'sini yazıyoruz.

Ana query'imiz yine sadece user'ın detayını getirecek hale gelecektir.

```
    <select id="getUsers" resultMap="userWithCourses">
		SELECT * FROM users
	</select
```
Daha sonra **user_id** sine göre courseları getiren yeni bir query yazacağız.
```
    <select id="getUserCoursesWithUserId" resultType="Course">
		SELECT * FROM courses WHERE user_id = #{id}
	</select>
```

Daha sonra **userWithCourses** id'sine sahip **resultMapimizi** şu şekilde
```
    <resultMap type="User" id="userWithCourses">
		<collection property="courses" column = "id" ofType="Course" autoMapping="true" select="getUserCoursesWithUserId">
		</collection>
	</resultMap>
```

tanımlıyoruz ve bu şekilde ilişkisel datanın ikinci bir query ile getirilmesini sağlayabiliriz. Java kodumuzda bir değişiklik olmayacaktır. **Java ayrı query ile mi geldi, joinle mi geldi bunu dikkate almaz.**

```
    List<User> users = session.selectList("getUsersWithCourses");
	for(User u : users) {
		System.out.println(u.getName());
		for(Course c : u.getCourses()) {
			System.out.print(c.getName() + " --- ");
		}
		System.out.println();
	}
```


## LAZY-EAGER LOADING

Arkadaşlar son yaptığımız; ilişkiyi ayrı query ile getirme konusunda **MyBatis** bize ekstradan **lazy** ve **eager** load adında iki yöntem sunmaktadır.

* **Lazy(Tembel, gecikmiş)** load ilişkisel query'nin ilişkiye dair method çağırılmadan işletilmemesini sağlar. Yani mesela userları aldınız **getCourses()** methodunu hiçbir zaman çağırmadınız, bu durumda courseları getiren ikinci query hiç atılmayacaktır. Bu bize bazı noktalarda database'e daha az sorgu atılması faydasını sağlamaktadır.
* **Eager(Açgözlü)** load'da user nesnesinin **getCourses()** methodunu çağırmasak bile ikinci query her zaman çalışıp courseları getirecektir.

Bir ilişkiye dair query'nin lazy mi eager mi olduğunu **<association>** ve **<collection>** taginde **fetchType** attribute'una **lazy** veya **eager** değerinden birini vererek belirleyebilmekteyiz.

```
    <resultMap type="User" id="userWithCourses">
		<collection property="courses" column = "id" ofType="Course" autoMapping="true" select="getUserCoursesWithUserId" fetchType="lazy" veya fetchType="eager">
		</collection>
	</resultMap>
```









