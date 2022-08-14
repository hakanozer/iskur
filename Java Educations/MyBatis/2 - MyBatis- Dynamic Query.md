## Dynamic Query

Arkadaşlar çoğu zaman filtreleme gibi işlemlerde bize dinamik bir şekilde generate olan query lazım olmaktadadır. Yani mesela bir web uygulaması yaptık courseları 

* İsmine göre
* Fiyatına göre
* Hocasına göre

arama yapılabilecek imkanı da verdik. 

Şimdi burada bir kullanıcı isme göre ararsa
```
    SELECT * FROM courses WHERE name = [gelen name değeri]
```    
şeklinde bir query olmalıdır. 

Kullanıcı fiyata göre arama yaparsa
```
    SELECT * FROM courses WHERE price = [gelen price değeri]
```    
şeklinde bir query olmalıdır. 

Kullanıcı isme ve fiyata göre arama yaparsa
```
    SELECT * FROM courses WHERE price = [gelen price değeri] AND name = [gelen name değeri]
```    
şeklinde bir query olacaktır.

Burada aslında tek bir query yazarak gelen filtrede olan değerlere göre bu query'imizi dynamic olarak generate edebiliriz. Bunun için **mapper.xml**'lerde **if, foreach, choose, when, otherwise** gibi yapıları kullanabilmekteyiz.

Mesela yukarıdaki senaryo için

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="search" resultType="Course" parameterType="map">
    		SELECT * FROM courses
    		<where>
                <if test="name != null">
                     name = #{name}
                </if>
                <if test="price != null">
                    AND price = #{price}
                </if>
                <if test="instructorname != null">
                    AND instructorname = #{instructorname}
                </if>
            </where>
    	</select>
    </mapper>
```

şeklinde **where ve if** tagleri ile şartlarımız ekleyebiliriz. Bu şartlar bu query'i çağırırken buraya gönderilen parametrelerin içindeki değerlere göre query'imizi dinamik bir şekilde oluşturacaktır. Mesela kodumuzda

```
        Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("name", "Java");
		searchParams.put("price", 100);
        List<Course> courses = session.selectList("getAll", searchParams);
```

şeklinde **name ve price** parametrelerini gönderirsek oluşan query

```
    SELECT * FROM courses WHERE price = 100 AND name = "Java"
```    
şeklinde olacaktır.

Bu şekilde 

```
        Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("name", "Java");
        List<Course> courses = session.selectList("getAll", searchParams);
```

sadece **name** parametresi gönderirsek oluşan query
```
    SELECT * FROM courses WHERE name = "Java"
``` 
şeklinde olacaktır.
 
### Choose, When, Otherwise
 
 Arkadaşlar bu yapı programlama dillerindeki **switch-case** benzeri bir yapıdır. Belli şartlarda query'e eklenecek parçayı bulup eklememizi sağlar. Sadece tek bir kısım query'e eklenecektir! Mesela şöyle bir query yazarsak
 
 ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="search" resultType="Course" parameterType="map">
    		SELECT * FROM courses
    		<choose>
                <when test="name != null">
                  AND name = #{name}
                </when>
                <when test="price != null">
                  AND price = #{price}
                </when>
                <otherwise>
                  AND authorname = #{authorname}
                </otherwise>
          </choose>
    	</select>
    </mapper>
```
Burada her zaman tekil bir kısım query'e eklenecektir. Yani parametrelerde **name** varsa query
```
    SELECT * FROM courses WHERE name = #{name}
```
şeklinde olacaktır. **Diğerlerin varlığı veya yokluğu önemli değildir!!**

Eğer parametrelerde name yoksa ve price varsa query
```
    SELECT * FROM courses WHERE price = #{price}
```
olacaktır.

Eğer name ve price yoksa query otherwise kısmına girecek ve
```
    SELECT * FROM courses WHERE authorname = #{authorname}
```
şeklinde olacaktır.

Bunun aynısını <set> adındaki tag ilede yapabilmektesiniz. Buradada tek bir if kısmı if'lerin sırasına ve gönderdiğiniz parametrelere göre query'e eklenecektir.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="search" resultType="Course" parameterType="map">
    		SELECT * FROM courses
    		<set>
              <if test="name != null">name=#{name},</if>
              <if test="price != null">price=#{price},</if>
              <if test="authorname != null">authorname=#{authorname},</if>
            </set>
    	</select>
    </mapper>
```

### FOREACH

Arkadaşlar IN querylerini dinamik generate etmek istediğimizde <foreach> kullanabilmekteyiz. Mesela id'si 1,2 veya 3 olan courseları getirmek istediğimde, 1,2 ve 3 ü bir collection ile query'e gönderebilirim ve query'inin **IN** kısmını **<foreach>** ile belirleyebilirim.

```
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace = "courseMapper">
    	<select id="searchInIds" resultType="Course">
    		SELECT * FROM courses
    		<where>
    	   		<foreach item="item" index="index" collection="list" open="id IN(" separator="," close=")">
    	               #{item}
    	         </foreach>
            </where>
    	</select>
    </mapper>
```

Bu query'inin çıktısı
```
    SELECT * FROM courses WHERE id IN(1,2,3) olacaktır.
```

Artık burada dinamik olarak elimdeki id listesini buraya gönderebilir ve sonucumu alabilirim.

 
 








