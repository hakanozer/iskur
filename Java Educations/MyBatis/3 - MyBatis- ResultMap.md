# RESULT MAP

MyBatis'te query sonuçları nesnelere maplerken query'imize resultType şeklinde sınıfımızı veriyorduk.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="getAll" resultType="com.apo.batiscourse.Course">
    		SELECT * FROM courses
    	</select>
    </mapper>
```

Burada myBatis otomatik olarak database'de dönen kolonları, kolonlarla aynı isimli property'lere mapler. (Auto-Mapping)

Burada bir de çoğu zaman database'de snake_case olarak bulunan kolon isimlerini kodda camelCase'e çevirmemiz gerekmektedir. MyBatis bunun çok fazla kullanıldığı görüp bunları otomatik maplemek için bize bir ayar sunmaktadır. Bunu MyBatis konfigürasyonuna eklediğimizde artık MyBatis otomatik olarak mesela start_date kolonunu startDate property'sine mapleyebilmektedir.

```
    <configuration>
    	<settings>
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>
    </configuration>
```

Bunlara rağmen bazen kolon isimlerini ile property isimlerinin çok farklı olması, query'de kolonlara alias(SELECT name as user_name vb...) vermemiz ve bazende başka sebeplerle database'den gelen query sonuçlarının objelere maplenmesine müdahale etme gereksinimi doğabilmektedir.

İşte **<resultMap>** query sonuçlarının objelere maplenmesi yapılırken araya girip manuel işlemlerin yapılmasını sağlayan temel yapımızdır.

Daha önce kullandığımız **courses** tablosuna **course_start_date** diye bir kolon eklediğimizi düşünelim. Bunu Java tarafındaki sınıfta **startDate** property'sinde tutmak istesem işte bu noktada manuel müdahale etme gerekliliğim doğacaktır.

**resultMap**'ler **mapper.xml** dosyalarımızda **<resultMap>** tagi ile tanımlanmaktadır. Mesela yeni kolonla birlikte maplemeyi düzgünce yapmak için courseMapper.xml'e şu şekilde bir **resultMap** ekleyebiliriz.
```
    <resultMap type="Course" id="courseDetailResultMap">
		<result column="course_start_date" property="startDate"/>
    </resultMap>
```	

**<resultMap>** içinde **<result>** tagleri ile kolonları propertylere manuel bir şekilde mapledik.

Burada **resultMap**'e bir id veriyoruz daha sonra mapper'daki querylerimizden bu id ile kullanılması gereken resultSet'e işaret edeceğiz. 

Burada **auto mapping** devam etmektedir. Yani açıkça mapleme vermediğimiz kolonlarda property ismi ile kolon ismi tutuyorsa maplenme devam edecektir. Burda sadece problem olacak kolona müdahale ettik.

**resultMap**'e verdiğimiz type ise bu **resultMap**'in çevrim yapacağı objeyi göstermektedir. Yani sonuçta **Course** nesnesi oluşacaktır.

Burada id kolonunun mapping'i için özel bir kullanım vardır; direk olarak **<result>** tagi değil **<id>** tagini kullanabiliriz. Id kolonunun myBatis tarafından bilinmesi myBatis'de yapılan bazı karşılaştırma işlemleri için önemlidir.

```
    <resultMap type="Course" id="courseDetailResultMap">
		<id property="id" column="id"/>
		<result column="course_start_date" property="startDate"/>
	</resultMap>
```	
Bunu yapmak zorunda değiliz ama oldu ki id kolonunun manuel maplenmesi gerekti o zaman **<id>** tagini kullanmak daha doğrudur.

Bir **resultMap**'te otomatik mappingi tamamen kapatıp manuele geçebiliriz. bunu **<resultMap>** taginin **autoMapping** attribute'una false değeri vererek yapabiliyoruz.

```
    <resultMap type="Course" id="courseDetailResultMap" autoMapping="false">
		<id property="id" column="id"/>
		<result column="course_start_date" property="startDate"/>
	</resultMap>
```	

Bunu yaptıktan sonra **sadece resultMap içinde manuel bir şekilde maplediğimiz alanlar çevrilen objede yer alacaktır!!**. Çokta mantıklı birşey değildir, gereksiz yere ekstra manuel maplemeye sebep olabilir bu.

Bu aşamada **resultMapimiz** hazır olunca artık herhangi bir query'de query sonucunun maplenmesi esnasında bu **resultMap**'in kullanılması gerektiğini söyleyebiliriz.

```
    <select id="findById" resultMap="courseDetailResultMap">
		SELECT * FROM courses WHERE id = #{id}
	</select>
	<resultMap type="Course" id="courseDetailResultMap">
		<id property="id" column="id"/>
		<result column="course_start_date" property="startDate"/>
	</resultMap>
```	

Gördüğünüz gibi daha önce **<select>** taginde **resultType="Course"** dediğimiz yere artık **resultMap="courseDetailResultMap"** diyerek biraz önce **courseDetailResultMap** id'si ile oluşturdugumuz **resultMap**'in kullanılması gerektiğini belirttik.

Bunlar kodumuzda hiçbir değişikliğe sebep olmayacaktır. Kod yine query'i çağıracak sonuç olarak **Course** nesnesi alacaktır. 

```
    SqlSession session = sqlSessionFactory.openSession();
    Course c = session.selectOne("findById", 1);
```	

## Interface kullanımı

Arkadaşlar MyBatis geliştiricileri bir zaman sonra spring gibi frameworklere entegre olabilmek için **mapper.xml** dosyalarına karşılık gelen Interface'ler tanımlatarak daha güncel bir kod stiline geçmiştir.

Burada query'leri yine xml'de yazacağımız ancak bunları interfaceler aracılığıyla koda bağlayacağız.

Burada adım adım

1 - Önce bir tane interface tanımlanır. Bu interface'de method tanımları yapılır, **burda önemli olan mapper xml'lerde query'lere verdiğimiz id'ler ile burdaki methodlar aynı isme ve parametrelere sahip olmalıdır**.

Yani **mapper.xml**'deki bu query'e

```
    <select id="findById" resultType="Course">
		SELECT * FROM courses WHERE id = #{id}
	</select>
```	

karşılık interface'de

```
    package com.apo.batiscourse;
    @Mapper
    public interface CourseMapper {
    	public Course findById(int id);
    }
```
böyle bir methodumuz olacak. Ve interface mapper annotationuna sahip olacaktır.

2 - **mapper.xml** dosyalarını hatırlayın orada **namespace** adında bir şey vardı.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    		SELECT * FROM courses WHERE id = #{id}
    	</select>
    </mapper>	
```	
Buradaki namespace'i interface'in full package'ıyla birlikte sınıf ismi olarak güncelliyoruz. Yani

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "com.apo.batiscourse.CourseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    		SELECT * FROM courses WHERE id = #{id}
    	</select>
    </mapper>	
```

İşte bu aşamadan sonra kodumuzda artık session nesnesi üstünden query id'leri ile değil interfaceden generate olan(**MyBatis** generate ediyor) sınıfın methodlarını kullanabiliyoruz.

Session nenesinin **getMapper()** methoduna **CourseMapper.class** şeklinde parametreyi verip interfaceden xml'de yazan query'lere göre bir sınıf üretilmesini sağlıyoruz. Ve daha sonra mapper nesnesi üstünen direk olarak interfacedeki methodu çağırabiliyoruz. **MyBatis** burada **courseMapper.xml**'deki **findById** id'sine sahip query'yi işletecektir.

```
    Session session = sqlSessionFactory.openSession();
    CourseMapper mapper = session.getMapper(CourseMapper.class);
	Course c = mapper.findById(2);
	System.out.println(c.getName());
```	

Çalışan query buradaki findById query'sidir.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "com.apo.batiscourse.CourseMapper">
    	<select id="findById" resultMap="courseDetailResultMap" >
    		SELECT * FROM courses WHERE id = #{id}
    	</select>
    </mapper>	
```





