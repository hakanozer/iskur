# MAVEN 

**Maven** bir **build automation tool**(Build işini otomatik hale getirme)'u olarak kullanılmaktadır.
Maven tüm konfigürasyonunu **pom.xml(project Object Model)** adını verdiğimiz bir dosyadan almaktadır. Temel olarak
- Kütüphane bağımlılıkların yönetilmesi
- Build aşamaları(compile, test, generate documentations, packaging, deploy vb...) ' nı belirlenmesi ve özelleştirilmesi

imkanlarını sağlamaktadır. Bir **java** uygulamasının kodunu yazdıktan sonra **doğru yerde doğru şekilde çalışabilir hale** getirmek için birçok adım gerekebilmektedir. İşte bu adımları maven kullanarak **pom.xml'de** çoğunlukla **deklaratif** bir şekilde ayarlayabilmekteyiz.

## KURULUM

Maven cli'den çalıştırılmak üzere bilgisayara yüklenebilen bir programdır. **Maven kullanmak için herhangi bir IDE'ye ihtiyacımız yoktur, IDE'ler bu işlemleri daha kolay bir şekilde yapmamızı sağlar sadece!**

Öncelikle [Download Maven](https://maven.apache.org/download.cgi) sayfasından işletim sisteminize uygun şekilde maven programı indirilir ve bir klasöre çıkarılır. Daha sonra maven'i cli'dan bir command olarak kullanabilmek için extract edilen folder ve bu folder'daki **/bin** klasörü ortam değişkenlerine eklenir. Mesela windows kurulumu için [Maven Windows](https://phoenixnap.com/kb/install-maven-windows) linkinden adımlara bakabilirsiniz.

Yüklemenin başarılı olup olmadığını command prompt'ta(***cli, terminal***) **mvn -version** komutunu çalıştırarak kontrol edebiliriz.

```	
    mvn -version
    Apache Maven 3.6.3
    ....
    ....
```	

### PROJE YARATMA

Günümüzde IDE'ler developer'a kolaylık sağlamak adına maven entegrasyonu ile birlikte gelmektedir. Ve arayüzden maven'a dair işlemleri kolayca yapabilmemizi sağlamaktadırlar. Ancak bu işin arka planında yine maven uygulaması kullanılmaktadır. Sadece bize yansıtılmamaktadır bir çok konu. Alt düzeyde bu olayların nasıl işlediğini bakacak olursak;

Maven'i command line'dan **mvn** komutu şeklinde kullanabilmekteyiz.
Maven destekli bir proje yaratmak için **mvn archetype:generate** şeklinde komutumuzu çalıştırabiliriz. Bu komut interaktif bir şekilde proje yaratmamızı sağlamaktadır.

Maven bu komutu çalıştırdığımızda bizden sırasıyla
- Kullanmak istediğimiz archetype
- GroupId
- ArtifactId
- Version

gibi proje bilgilerini sorup bizden aldığı bilgilerle proje yapısını ve projeye ait **pom.xml** dosyasını oluşturacaktır. Burada kullanmak istediğimiz **archetype** dediğimiz şey ***öntanımlı proje yapısıdır.*** Mesela web uygulaması geliştiriyorsak buna uygun bir proje yapısı olmalıdır.(**WEB-INF klaösür olan, web.xml dosyası olan vb...**)

Bu adımları tamamladığımızda bu komutu hangi folderın içinde çalıştırdıysak o folderda maven destekli bir java projesi yaratılmış olmaktadır.

Burada birde interactive olmayan bir şekilde
```	
    mvn archetype:generate 
    	-DgroupId={project-packaging}
    	-DartifactId={project-name}
    	-DarchetypeArtifactId={maven-template} 
    	-DinteractiveMode=false
```		
şeklinde bilgileri konsol parametresi olarak direk verip projemizi oluşturabiliriz. 

### ARCHETYPE, GROUP ID, ARTIFACT ID, VERSION

**Archetype'a** öntanımlı proje yapısı(Project template) diyebiliriz. Uzun yıllardır java'da geliştirilen uygulamaların yapısından en iyileri ve en düzgünleri veya bir proje yapısına özel olanlar bulunup bunların proje yapısı Maven'in içine alınmıştır. Bunlara **archetype** diyoruz aslında. Bir proje oluştururken bizde uygulamızın tipine göre(web uygulaması, plugin geliştirme, basit bir uygulama) uygun archetype'i seçebiliriz ve seçmeliyizde. 

**groupId, artifactId ve version** maven'ın projemizi **unique(tekil)** bir şekilde ayırt edebilmesi için kullanılan parçalardır. Ve aslında javanın package isimlendirme mantığına benzer bir mantık işler burada da.

**GroupId** kütüphaneyi geliştiren şirketi, kurumu, kişiyi gösteren kısımdır ana olarak ve genelde domain'in tersten yazılmışı(unique'lik adına domain kullanmak mantıklı) şeklinde belirlenir. Mesela ***org.apache, com.google,  com.mysql vb....***

**ArtifactId** geliştirilen projenin ismidir aslıdır. Mesela google android'e dair bir media player geliştirdiğinde artifactId'ye ***mediaplayer** diyebilir.

İşte bu ikisi bir projeyi unique olarak belirtebilmektedir. Mesela google'ın geliştirdiği mediaplayer için **com.google.mediaplayer** projeyi unique olarak belirtebilir. Benim geliştirdiğimde **com.abdullah.mediaplayer** olabilir.

Neden unique olmasını bu kadar önemsiyoruz dersek; eğer unique olarak bir kütüphaneyi belirtemezsek, bu kütüphaneyi kullanmak isteyenler nasıl doğru kütüphaneyi bulabilir ki?

Peki hocam biz bir maven projesi geliştirirken neden **groupId, artifactId ve versiyon** veriyoruz ki dışardan kimse kullanmayacak ki diyorsanız; siz belki burda geliştrdiğiniz projeyi bir kütüphane olarak başka projelerinizde kullanacaksınızdır, bu durumda yine kendi projenizi yeni projenize **groupId, artifactId ve versiyon** ile ekleyebilmektesiniz pom.xml'i kullanarak. **Veya çok güzel bir kütüphane geliştrdiniz ve open source olarak kütüphanenizi başkalarıda kullanabilsin diye repository'e yükleyeceksinizdir, küçük düşünmeyin!**

Version'da bildiğimiz versiyon. Projenin o anki sürüm bilgisi yani. 
Sonuçta biz bir kütüphaneyi kullanmak istediğimizde tam olarak **groupId:artifactId:version** şeklinde üç bilgiyi vermek durumundayız.

### POM.XML

**Pom(Project Object Model)** maven'in kalbi olan, tüm sürecin xml formatında ayarlanabildiği ana dosyamızdır. Örnek bir **pom.xml** şu şekilde olmaktadır.

```	
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.apo</groupId>
  <artifactId>documentation</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>
  <name>unitcourse</name>
  <description>A simple unitcourse.</description>
  <url>http://www.example.com</url>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>
   <repositories>
    <repository>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
    </repository>
  </repositories>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>
	<dependency>
	    <groupId>org.mockito</groupId>
	    <artifactId>mockito-core</artifactId>
	    <version>4.6.1</version>
	    <scope>test</scope>
	</dependency>
  </dependencies>
  <build>
    <plugins>
        <plugin>
            <artifactId>maven-clean-plugin</artifactId>
            <version>2.5</version>
            <executions>
              <execution>
                <id>default-clean</id>
                <phase>clean</phase>
                <goals>
                  <goal>clean</goal>
                </goals>
              </execution>
            </executions>
      </plugin>
    </plugins>
  </build>
</project>
```	

Burdaki taglere kısaca bakacak olursak;

- **groupId, artifactId, version** gibi tag'lerle projenin ana bilgileri yer almaktadır.
- **packaging** tagi ile projenin build işlemi sonrasında jar formatında çıkarılması gerektiğini söylüyoruz. Burası **jar, war, ear, pom vb...** farklı değerler alabilmektedir.
- **properties** tagi ile pom.xml'de tekrar kullanılmak üzere değişkenler tanımlanmaktadır.
- **repositories** tagi ile bağımlı olduğumuz kütüphanelerin indirilebileceği adresleri belirliyoruz(Apple store, google play market gibi düşünün, bunların java kütüphanaleri için olan hali). Maven'dan kütüphanelerin yüklendiği yerlere **repository** diyoruz. Daha sonra başkaları bu repository'lerin adresleri üzerinden kütüphaneleri indirebilmektedir. (pom.xml'de kütüphaneyi belirterek)
- **dependencies** tagi bağımlı olduğumuz kütüphaneleri belirttiğimiz kısımdır. Burada kütüphanenin **groupId, artifactId ve versiyounu** vererek kütüphanenin kodlarının cihazımıza indirilmesi ve kodumuzda kullanılabilmesi sağlanmaktadır maven tarafından.
- **build** tagi ve içindeki **plugins** tagi ile build aşamalarını tamamen özelleştirebiliyoruz. Burada **plugin** dediğimiz; build aşamalarında çalıştırılmak üzere geliştirilmiş kütüphanelerdir.

### SUPER POM, EFFECTIVE POM, PARENT POM

Java'da nasıl ki tüm sınıflarımız **biz görmesek bile Object sınıfından kalıtıyor ise**, bir maven projesi yarattığımızda da pom.xml'imiz default bir pom'dan kalıtmaktadır bazı şeyleri. Buna **Super Pom** denmektedir. **Super Pom her projede olması beklenen (repository ayarları, bazı build adımlarında yapılacak işler) gibi işlemlerin default olarak tanımlandığı pom'dur ve her pom.xml'de bundan kalıtmaktadır.**

Java'da projemizin pom'unu bizim geliştirdiğimiz bir pom.xml'den de kalıtabiliriz. Bu durumda **kalıttığımız pom.xml Parent pom olacaktır**. Bizim pom.xml'imize de bu durumda **child pom** denilmektedir. Bazen yaptığımız tüm projelerde ortak ayarlar ortak bağımlılık versiyonları gibi şeyleri bir yerde tanımlayıp tekrar tekrar kullanmak isteyebiliriz. İşte bu durumda bunu bir parent pom'da tanımlayabiliriz. Ve diğer projelerimizde (child pom)
```	
     <parent>
        <groupId>com.apo</groupId>
        <artifactId>configurations</artifactId>
        <version>0.0.1-SNAPSHOT</version>
     </parent>
```	  
  
şeklinde **parent** tagi ile parent pomu olarak belirtebiliriz. 
 
**Effective pom** bir projenin parent'tan gelen, super'den gelen ve kendi pom.xml'inde yazan bilgilerin birleşmiş halidir. **Yani projenin pomunun tam halidir.**

### DEPENDENCIES(BAĞIMLILIKLAR)

Maven bağımlıklıkları ve bağımlı olduğüumuz kütüphanenin bağımlılıklarınıda yüklemektedir.(**transitive dependecies**) Yani projenin çalışması için ipin sonuna kadar gitmektedir. 

pom.xml'de **dependencies** tagi ile bağımlı oldugumuz kütüphanleri belirtebiliriz.

```	
	<dependencies>	
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>3.14</version>
			<scope>compile</scope>	
		</dependency>
	</dependencies>	
```	

Burada her bir bağımlılık dependency tagi içinde groupId, artifactId ve versiyon olarak belirtilmek durumundadır. Bunu pom.xml'e eklediğimizde maven otomatik olarak kütüphaneyi bir yerlerden bizim bilgisayarımıza indirecektir ve bizde projemizde kullanmaya başlayabileceğiz.

Burada scope tagi kullandığımız kütüphaneye dair bir takım işleri(classpath'e olacak mı, packaging yaparken dahil olacak mı vs...) belirleyebilmektedir. Scope tagi
- compile
- provided
- runtime
- vb...

şeklinde değerler alabilmektedir. Burada mesela **compile** demek bu kütüphanenin kodlarının compile aşamasında lazım olduğunu, kütüphaneini kodlarının classpath'te olması gerektiğini kabaca belirtmektedir.

**Provided** bu kütüphanenin lazım olduğunu ancak packagingde dahil edilmesine gerek olmadığını bunun uygulamanın koyulacağı ortam tarafından sağlacanacağı gibi bir bilgi vermektedir maven'a. 

**Runtime** ise bu kütüphanenin compile işine dahil olması gerekmediğini, runtime'da dinamik olarak bağlanacağını söyler. Mesela **JDBC driver'ları** bize compile'da lazım değildir, kod çalışırken Driver dinamik olarak yüklenmektedir.

Maven'da bazen bağımlı olduğumuz kütüphanenin bağımlı olduğu kütüphaneyi yüklememek veya farklı bir versiyonda yüklemek isteyebiliriz. Bu gibi durumlarda **exclusion** tagini kullanılabilmekteyiz. 

```
    <dependency>
          <groupId>sample.ProjectB</groupId>
          <artifactId>Project-B</artifactId>
          <version>1.0-SNAPSHOT</version>
          <exclusions>
            <exclusion>
              <groupId>sample.ProjectE</groupId>
              <artifactId>Project-E</artifactId>
            </exclusion>
          </exclusions>
    </dependency>
```    

## REPOSITORIES

Maven'da bağımlılıklarımız default olarak **central repository** adını verdiğimiz bir repository'den bizim bilgisayarımıza indirilmektedir. Buna dair bilgi'de **super pom'da**

```
    <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>Central Repository</name>
          <url>https://repo.maven.apache.org/maven2</url>
        </repository>
  </repositories>
``` 

şeklinde belirtilmektedir. Biz de kendi **pom.xml'imize** başka repository'leri ekleyebiliriz.(Repository kütüphaneleri içeren bir yer, bir adres, javada bir çok repository vardır.)

Maven'da bir bağımlılık önce uzak repository'den bizim bilgisayarımıza indirilir. Bu indirildiği yere **local repository** denmektedir. Bu genelde **$userPath/.m2/repositories** şeklinde bir klasör olmaktadır. 
Daha sonra başka bir proje aynı kütüphaneye(versiyonuna kadar aynı olmalıdır) ihtiyac duyarsa kütüphane direk olarak bu local repository'den alınmaktadır.

Yani aslında maven'da farklı repository tipleri vardır. Maven'da tüm projelerin default olarak sahip olduğu **https://repo.maven.apache.org/maven2** linkinden kütüphaneleri indiren repository'e **central repository** diyoruz. 

Bunları kullanacağımız zaman maven bunları bilgisayarda bir yere indirmektedir. Bu yere **local repository** diyoruz.

Birde yine internet üzerinden central dışındaki herhangi bir repository'dende kütüphane indirebiliyoruz. Bunlara da **remote repository** denmektedir.

Maven **pom.xml'de** bir dependency gördüğünde sırasıyla **Local Repository -> Central Repository -> Other Remote Repositories** şeklinde bir arama gerçekleştirir.

Maven'da kendi geliştirdiğimiz projeleri'de remote repository'e veya local repository'e koyabiliriz(***maven install*** komutu local repository yükler default olarak). Bu sayede diğer projelerimizde kendi geliştirdiğimiz projeyi kodumuza bağımlılık olarak ekleyebilmekteyiz. (custom plugin developmentında geliştirdiğimiz plugini **maven install** diyerek local repomuza yükleyeceğiz, daha sonra başka projeden dependency olarak kullanacağız.)

## NOT
Arkadaşlar mesela bir uygulama yapıyorsunuz mysql kütüphanesine ihtiyaç duydunuz; bu durumda google direk **java mysql maven** şeklinde birşey yazabilirsiniz. Burada size çıkan ilk link **https://mvnrepository.com/** olacaktır. Bu sayfa bu bahsettiğimiz central repository'nin brwoserdan görünebilen halidir. Burası; kütüphanenin versiyonunu seçtikten sonra size direk olarak pom.xml'e koyabileceğiniz <dependency> tagini verecektir. 

```
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.30</version>
    </dependency>
```

## BUILD

Build'e; bir projenin kodları geliştirildikten sonra doğru yerde doğru şekilde çalışabilmesi için gerekli adımlardır denebilir.

Bir java uygulaması geliştirildikten sonra mesela;
- Kodun derlenmesi
- Dökümantasyon üretilmesi
- Testlerinin çalıştırılması
- Paketlenmesi (jar, war vb...)
- Kodun sunucuya alınması
- vb...

gibi işlemlerin yapılması gerekmektedir. İşte bu işlemleri **pom.xml'de** build tagi içinde pluginler kullanarak yerine getirebilmekteyiz.

Maven burada bir çok konuda öntanımlı ayarlarla gelmektedir. Burayı iyice anlamanız lazım kalbi burası.

Maven'da build işlemleri **lifecycle(hayat döngüsü)'larla** gerçekleştirilmektedir. 
Maven default olarak 3 tane lifecycle ile gelmektedir.

- Default
- Clean
- Site

Lifecycle'lar çok iyi tanımlanmış **fazlardan(Phases)** oluşmaktadır. Fazlara bir build lifecycle'ının belli kodları çalıştırdığı breakpointler diyebiliriz. Mesela default lifecycle'ı

- process-resources
- compile
- process-test-resources
- test-compile
- test
- package
- install
- deploy
- .....
- .....

Clean lifecycle'ı

- pre-clean
- clean
- post-clean

şeklinde fazlardan oluşmaktadır. [Full liste için bakınız](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

**Default lifecycle'i projeyi build etmek için, clean lifecycle'i önceki build dosyalarını silmek için kullanılmaktadır diyebiliriz kabaca.**

Bir uygulama geliştirildikten sonra build edileceği zaman aslında bir lifecycle konuya dahil olmalıdır. Ancak düşündüğünüz gibi değil!

Burada en önemli nokta bir lifecycle direk olarak başlatılamaz. Build aşamalarını tetiklemek istiyorsak önümüzde iki yol vardır.

- Bir lifecycle'in bir fazını kullanmak
- Direk olarak bir pluginin goalini çağırmak

İlkini bakacak olursak mesela terminale(cli) şu şekilde
```
    mvn test-compile
```

**test-compile** fazının adı verilerek build başlatılırsa maven öncelikle bu fazın hangi lifecycle'a ait olduğunu bulacaktır. Yani default lifecycle'a ait olduğunu anlayacaktır.(**Hocam peki başka lifecycle'da aynı isimle faz varsa ne olacak, birden fazla lifecycle'da aynı isimde faz olamaz!**)

**Ve daha sonra bu lifecycle'da, bu faza kadar ne kadar faz varsa(bu fazda dahil) hepsini çalıştıracaktır. Bu fazdan sonraki fazları çalıştırmayacaktır.**

Yani çalışacak fazlar

- process-resources
- compile
- process-test-resources
- test-compile

bunlar olacaktır. Peki hocam bir lifecycle'i baştan sona çalıştırmak istersem ne yapacağım. **E basit lifecycle'in son fazının adını vereceksin!**

mesela

```
    mvn clean
```

şeklinde bir komut yazdınız. Burada clean clean lifecycle'ında bir fazdır.(Aynı ismi vermeleri bence de kafa karıştırıcı haklısınız!). Yani buradada clean lifecycle'ında clean fazına kadar ne kadar faz varsa çalışacaktır.

- pre-clean
- clean

Arkadaş build işlemi gorduğunuz gibi mvn <phase-name> şeklinde başlatılabiliyor. Burada <phase-name> birden fazla olabilir. Yani

```
    mvn clean install
```

şeklinde clean fazi ve install fazini vererekte build alabilirsiniz. Burada önce clean fazına kadarki fazlar çalışacaktır sonrada default lifecycle'inin install fazına kadarki fazlar çalışacaktır. **Yani eski build'i sil yeni build al demektir!, hatırlayın clean lifecycle'ı eski builderi temizleme amacı ile kullanılıyordu çoğunluklu**

Peki phaselarda nasıl bir kod çalışmaktadır ve ne yapılmaktadır? 
**Aklınızda artık şu oluşmuş olmalı lifecycle fazlarında builde dair birşeyler yapılabilir ve bunları yapan pluginlerdir!**

Buraya biraz daha detaylı bakmaya çalışalım ama öncelikle şunu bilelim, super pom demiştik ya tüm pomların atası olarak. Burada hemen hemen tüm java uygulamalarının buildinde gerekli olan bazı şeyler zaten belirtilmiştir. Yani ciddi bir özelleştirme ihtiyacınız yoksa default yapı işinizi görecektir.

pom.xml'e de build tagine baktığımızda

```
<build>
    <plugins>
        <plugin>
            <artifactId>maven-clean-plugin</artifactId>
            <version>2.5</version>
            <executions>
              <execution>
                <id>default-clean</id>
                <phase>clean</phase>
                <goals>
                  <goal>clean</goal>
                </goals>
              </execution>
            </executions>
      </plugin>
      ......
      ......
      [other plugins]
    </plugins>
  </build>
```

şeklinde bir entry görüyoruz. İşte build aşaması tamamen build taginin içindeki bu ayarlamalara göre çalışmaktadır. Build tagi içinde **belli bir phase'da çalışmak üzere bir plugini kaydedebiliyoruz**. Ve biz maven'da build işlemini başlattığımızda burada belirtilen faza gelindiğinde bu pluginin kodları çalıştırılacaktır. Pluginde projeye dair ne yapması gerekiyorsa bunu yerine getirecektir. Burayı daha iyi anlamak adına plugins konusuna biraz bakalım.

## PLUGINS
Plugin başkaları(veya bizde custom plugin geliştirebiliriz) tarafından bir build fazında belli işlemler yerine getirmek üzere geliştirilmiş yine java projeleridir. **Pluginler goal'ler adını verdiğimiz build aşamalarındaki farklı işleri yerine getireceilecek sınıflara sahiptir**. Maven'da hemen hemen tüm işlemler için geliştirilmiş plugin bulabilmekteyiz. Super pomun(her pomun atası) build tagine bakacak olursak
```
<build>
    <plugins>
      <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>2.5</version>
        <executions>
          <execution>
            <id>default-clean</id>
            <phase>clean</phase>
            <goals>
              <goal>clean</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <version>2.6</version>
        <executions>
          <execution>
            <id>default-testResources</id>
            <phase>process-test-resources</phase>
            <goals>
              <goal>testResources</goal>
            </goals>
          </execution>
          <execution>
            <id>default-resources</id>
            <phase>process-resources</phase>
            <goals>
              <goal>resources</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.4</version>
        <executions>
          <execution>
            <id>default-jar</id>
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <executions>
          <execution>
            <id>default-compile</id>
            <phase>compile</phase>
            <goals>
              <goal>compile</goal>
            </goals>
          </execution>
          <execution>
            <id>default-testCompile</id>
            <phase>test-compile</phase>
            <goals>
              <goal>testCompile</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.12.4</version>
        <executions>
          <execution>
            <id>default-test</id>
            <phase>test</phase>
            <goals>
              <goal>test</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-install-plugin</artifactId>
        <version>2.4</version>
        <executions>
          <execution>
            <id>default-install</id>
            <phase>install</phase>
            <goals>
              <goal>install</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-deploy-plugin</artifactId>
        <version>2.7</version>
        <executions>
          <execution>
            <id>default-deploy</id>
            <phase>deploy</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <artifactId>maven-site-plugin</artifactId>
        <version>3.3</version>
        <executions>
          <execution>
            <id>default-site</id>
            <phase>site</phase>
            <goals>
              <goal>site</goal>
            </goals>
            <configuration>
              <outputDirectory>/home/abdullah/Desktop/spring-security/unitcourse/target/site</outputDirectory>
              <reportPlugins>
                <reportPlugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-project-info-reports-plugin</artifactId>
                </reportPlugin>
              </reportPlugins>
            </configuration>
          </execution>
          <execution>
            <id>default-deploy</id>
            <phase>site-deploy</phase>
            <goals>
              <goal>deploy</goal>
            </goals>
            <configuration>
              <outputDirectory>/home/abdullah/Desktop/spring-security/unitcourse/target/site</outputDirectory>
              <reportPlugins>
                <reportPlugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-project-info-reports-plugin</artifactId>
                </reportPlugin>
              </reportPlugins>
            </configuration>
          </execution>
        </executions>
        <configuration>
          <outputDirectory>/home/abdullah/Desktop/spring-security/unitcourse/target/site</outputDirectory>
          <reportPlugins>
            <reportPlugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-project-info-reports-plugin</artifactId>
            </reportPlugin>
          </reportPlugins>
        </configuration>
      </plugin>
    </plugins>
</build>
```

şeklinde uzunca hazırlanmış tagler görüyoruz. Burada maven geliştiricileri hemen hemen her java projesinde lazım olan belli build adımlarını konfigüre etmektedirler default olarak. Peki nasıl?

**Maven'da bir plugin bir goal'ünü ***pom.xml'deki*** plugins tagini kullanarak bir build phase'e de çalışmak üzere kaydetmektedir.** Bunu **execeutions** tagleri ile yapmaktayız. Burada pluginin **artifactId'si ve versiyonu verilmelidir minimum'da**, bir pluginin kullanılabilmesi için, maven diğer bağımlılıklarda olduğu gibi bu pluginleride local repository'e indirmek zorundadır. Burada normal bağımlılıkların aksine groupId bazı pluginler için opsiyonel olabilmektedir. Bunun sebebini araştırıp bulmanızı bekliyorum. 

```
	<plugin>
		<artifactId>maven-deploy-plugin</artifactId>
		<version>2.8.2</version>
		<executions>
		  <execution>
		    <id>default-deploy</id>
		    <phase>compile</phase>
		    <goals>
		      <goal>deploy</goal>
		    </goals>
		  </execution>
		</executions>
	</plugin>
```	
      	
Burada örnek olarak önce **maven-deploy-plugin'ini** artifact olarak belirtip indirilmesini sağlamaktayız. Daha sonra **executions** kısmında bu pluginde bulunan deploy adındaki goal'ü deploy fazında çalışmak üzere kaydımızı yapıyoruz.

Ve bu eklemeyi yaptığımızda build işlemi başlatırsak eper build işlemi burada belirtilen fazdan geçecek ise işte tam o aşamada bu pluginin deploy goal'i çalışacaktır.

Dikkat edin lifecycle ile ilgili bir bilgi yok. Maven phase isminden lifecycle'i bulabilmektedir. 

Yani mesela biz 
```
    mnv install
```
şeklinde komutumuz çalıştırdık diyelim. compile fazı fazı install fazından önce çalışmaktadır default lifecycle'ında. Maven sırayla fazları çalıştırır çalıştırır ve compile fazına gelince bizim kaydettiğimiz deploy-pluginin içinde geliştirilmiş deploy adlı goal'ü(java sınıfı kabacai ne olduğunu kafanızda büyütmeyin!) çalıştırır.

    
Build başlatmanın ikinci yöntemi direk olarak plugin ve goal ismi vermektir.

Burada mvn komutundan sonra **plugin:goal** şeklinde bir pluginin içindeki goal'ü direk çalıştırabiliriz. **Burada faz veya lifecycle yoktur yani sadece bu goal çalışır ve konu biter.**

```
   	<plugin>
		<artifactId>maven-deploy-plugin</artifactId>
		<version>2.8.2</version>
		<executions>
		  <execution>
		    <id>default-deploy</id>
		    <phase>deploy</phase>
		    <goals>
		      <goal>deploy</goal>
		    </goals>
		  </execution>
		</executions>
	</plugin>
```

Mesela bu plugini hiç **pom.xml'e** execution şeklinde eklemeden direk olarak'ta **deploy:deploy** şeklinde çalıştırabiliriz. İlk kısım(iki noktadan önceki deploy kısmı) pluginin artifactId'de maven ve plugin kısımları çıkarınca kalan plugin ismidir. Sağ taraftaki ikinci kısım ise goal ismidir. Bu şekilde sadece deploy pluginin deploy goalü çalıştırılacaktır.

Maven'da pluginler bazen dışardan parametreye ihtiyaç duyabilmektedir. Mesela deploy plugini nereye deploy yapacağına dair bilgilere parametre olarak ihtiyaç duymaktadır.

Maven'da plugin eğer parametre kabul ediyor ise <configuration> tagini kullanarak parametre geçirebilmekteyiz plugine.

```
	  <execution>
	    <id>default-deploy</id>
	    <phase>deploy</phase>
	    <goals>
	      <goal>deploy</goal>
	    </goals>
	    <configuration>
	    	<parameterName>parameterValue</parameterName>
	    </configuration>
	  </execution> 	
```

## PROPERTIES

Maven'da <properties> taginin içine propertyler tanımlayabilmekteyiz.
```
    <properties>
    	<mysql.version>3.14</mysql.version>
    </properties>	
```

Bu property'i **pom.xml'in** devamında **${mysql.version}** şeklinde $ işareti ve süslü parantezler ile refere ederek kullanabilmekteyiz. Property'ler amacı aslında pom.xml'in her tarafında geçerli olacak değişkenler tanımlamaktır.

### MAVEN IN ECLIPSE
Arkadaşlar eclipse entegre maven ile gelmektedir. Biraz inceleme ile doğru şeyleri rahatça bulabilirsiniz. Eclipse'de uygulama geliştirirken maven işlemleri için terminal ve cli'den uğraşmanız gerekmemektedir. 



	    
       	







