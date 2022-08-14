# Revisit

Spring'in büyüyen projelerimizde yaşadığımız bazı sorunlara dair çözümler getiren bir kütüphane oldugundan bahsettik.

Bu sorunlar

* Sınıfların birbiri ile çok iç-içe geçmesi(**Tight Coupling**)
* İlerde değişim ihtiyacı oldugunda çok fazla yere dokunulması gerektiği(**Refactoring, Code Changes**)
* Sınıflara fazla sorumluluk yüklenmesi(**Single Responsibility**)
* Kodumuzun test edilebilirliğinin zorlaşması(**Testing**)
* Nesnelerin hayat döngüsünü yönetmenin zorlaşması(Yaratılma anı, ölüm anı, nesneden hayatta kaç tane bulunması gerektiği vb...)

**Spring** bu sorunların çözümü için; nesnelerin hayat döngüsünü yönetilebilecek, sınıfların bağımlı olduğu diğer sınıfları **DI(Dependency Injection)** mentalitesi ile dışardan alabilmesini sağlayacak yeteneklere sahiptir.

Şimdi öncelikle bir örnek sınıf üstünden sorunları ve çözümleri anlamaya çalışalım.

```
    class Photographer(){
        private SonyCamera camera;
        public Photographer(){
            this.camera = new SonyCamera();
       }
    }
```

Örnekteki sınıfımız yukarıda bahsettiğimiz tüm sorunları bize gösteren bir sınıftır. 

Photographer sınıfı 
- Cameranın nasıl yaratılacağını bilmek zorunda bırakılmıştır(**new SonyCamera()**), yani gereksiz sorumlulukları vardır.
- Photographer sınıfı sadece belirli bir camera(SonyCamera) sınıfıyla çalışabilir haldedir.(**Tight Coupling**)
- Bu sınıftaki koda dokunmadan Photographer'ın başka bir camera ile çalışması mümkün değildir. Halbuki bir photographer istediği anda başka kamera kullanabilmeli, hatta telefonunu kamera olarak kullanabilmelidir.
- Photographer sınıfını test etmek istediğimde kesinlikle gerçek bir kamera nesnesi dünyaya gelmelidir. (**new SonyCamera()**)

Bunların hemen hemen hepsini hergün yapıyoruz bizde. Ancak işler biraz büyüdüğünde bu bize ciddi sorun olmaya başlıyor. Arkadaşlar kod her zaman büyür, değişir ve gelişir!

Bu sorunların hepsine çok basit bir çözümümüz mevcuttur. **Bağımlıkların dışarıdan verilmesi!!** Yani **Inversion Of Control ve Dependency Injection**. Yani bir şekilde photographer kamera bağımlılığı dışarıdan alırsa tüm sorunlar bir anda buharlaşmaktadır. Şimdi bu sınıfı şu hale getirirsek eğer

```
    class Photographer(){
        private Camera camera;
        // get camera via constructor
        public Photographer(Camera c){
           this.camera = c;
       }
       //get camera via setter
      public void setCamera(Camera c){
          this.camera = c;
      }
    }
```

Buradada gördüğünüz photographer sınıfı bağımlı olduğu diğer bir nesneyi ya **constructor** ile yaratım aşamasında yada yaratımdan sonra **setter** ile alabilir. Yani başka türlü nasıl alabilir ki zaten! Sınıfımızı bu hale getirdiğimizde otomatik olarak;

* Artık sınıfımız Cameranın nasıl yaratıldığını bilmek zorunda değildir. Başka birisi yaratıp gönderecektir buraya.
* Photographer sınıfına Camera sınıfından extend etmek şartı ile farklı cameralar verebilirim. (hatırlayın çocuk sınıflar ata sınıf referansı olan yerlere gönderilebilirlerdi!)
* Camera sınıfından fake(mock) bir çocuk sınıf oluşturur test ederken onu kullanabilirim.

Artık kodun çok daha esnek ve değişime açık bir hale gelmiştir. Mesela şu an **SamsungCamera** kullanıyorum diyelim ve **SonyCamera** kullanmaya karar verdim diyelim, hemen bir **SonyCamera** sınıfı yazıp **Camera** sınıfından extend edersem artık photographer'im **SonyCamera** ilede çalışabilir. **Photographer** sınıfında bir satır kod değiştirmeden **Photographer** nesnesinin fotograf çekme stilini değiştirebildim yani! (Polymorphism dediğiniz şeyde aslında budur aslında, kafanızı karıştırmayın şimdilik ama)

Arkadaşlar spring var veya yok umrumuzda değil kodumuzu her zaman bu şekilde yazmalıyız. Bu yaptığımız işin springle hiçbir alakası yoktur!

Aslında buraya kadar konsepti tamamladık. Ancak ufak bir ekle daha da güzel bir hale getirebiliriz. Burada kullanılabilecek kameraları **Camera** sınıfından extend etmeye zorlamak çokta düzgün bir yapı değildir. Mesela benim elimde akıllı telefon var, bu arkadaşta kamera olarak kullanılabilir, ancak Smartphone sınıfının **Camera** sınıfından extend etmesi hiçte mantıklı değildir. Kalıtım mantığına terstir. Yani biz **SmartPhone is a Camera** demiyoruz, **SmartPhone can be used as camera veya SmartPhone is capable of using as camera!** diyoruz. İşte bundan dolayı sistemde birşeyin kamera olarak kullanılabilmesi için gerekli şartları(Interface dediğimiz şey buydu zaten değil mi!!) içeren interface'imi tanımlarım.

```
    interface ICamera(){
        public Photo takePhoto();
        public Video takeVideo();
        ....
        ....
    }
```  

Artık herhangi bir sınıf bu interface'i gerçekleştirirse Photographer tarafından kamera olarak kullanılabilecektir. (**DI için bir şart değildir, DI bağımlılığı dışardan verip vermemenle alakalıdır, interface kullanmanla değil!**)

```
    Class Photographer(){
        private ICamera camera;
         // get camera via constructor
        public Photographer(ICamera c){
           this.camera = c;
       }
        //get camera via setter
        public void setCamera(ICamera c){
           this.camera = c;
       }
       // bu kodunda gerçekte nasıl çalışacağı buraya gönderilen camera nesnesinin gerçek tipine bağlıdır değil mi!
       // işte polymorphism budur!!
       public Photo takeSinglePhoto(){
           return this.camera.takePhoto();
       }
    }
```   

Artık bu sınıfa camera nesnesi yollamak için göndereceğin sınıfın Camera sınıfını extend etme şartı yoktur, **ICamera** interface'ini implement eden her sınıf buraya gönderilebilir! (**SmartPhone** sınıfına **ICamerayı** implement ettir, kamera olarak telefonu kullan!)

Şimdi bu aşamada böyle kodlar geliştirdiğimizde bir framework ortamında sorunlar çıkmaya başlıyor; peki ne gibi sorunlar bunlar?

Framework dediğimiz aslında bizim kodlarımızı yöneten, yaygın mimarileri güzelce uygulanan yapılar oluyor. Mesela bir web uygulamasında framework bize kardeşim sen route'larını şuraya yaz, model mi yapmak istiyorsun şuraya yaz ve o sınıfa şöyle bir kaç property koy, controller mı yazacaksın onuda şuraya yaz, request nesnesine ihtiyacın varsa şöyle iste gibi. Buradaki tüm süreçte düzgünce controller nesnesinin yaratılması; route göre uygun controller methodunun çağırılması gibi konuları framework bizim yerimize sağlıyor.

İşte tam burada framework; bu bahsedilen işleri yapabilmek için controllerı nasıl yaratacağını ve bağımlılıklarını nasıl vereceğini, methodu çağırırken onun parametrelerinde bağımlılık varsa bunları nasıl vereceğini vb.. bilmek zorunda kalıyor. Hah işte tüm olay bu. Burada artık bu işleri yapan bir kütüphaneye ihtiyaç duyuyoruz. **Spring kütüphanesi** ve **Spring frameworku** konuya burada dahil oluyor.

Spring kütüphanesinin framework olma hikayeside burada başlıyor.

Spring bize bu konularda yardımcı olan(yardımcı olmak zorunda olan aslında, framework olarak kullanılmak istiyorsa!!) kütüphanemizdir. Ona belli yöntemler ile hayat döngüsünü, bağımlılıklarını yönetmesini istediğimiz sınıfları(Spring bunlara **bean** diyor) veriririz. O da bize bu sınıflardan nesneler lazım oldugunda güzelce bu işi yerine getirir.

Spring çok yeteneklidir, nesnenin doğumunda ölümüme, nasıl doğacağına, doğduktan sonra birşey yapılacak ise bunu yapmaya, ölmeden önce defnetmeye, bir nesnenin bağımlı olduğu diğer nesnenin yaratılıp bağımlılıkların yönetilmesi gibi konuların hepsine çözümleri vardır.(**scope, autowire, lazy-init, init-method, destroy-method**)

Tabi bunların olması için spring'e gerekli bilgileri vermem gerekmektedir. Gelin bunları spring'e nasıl öğretebileceğimize bakalım.

Bu bilgileri spring'e verirken temelde 3 yöntemimiz vardır;

* xml dosyası kullanarak bean'lerimizi ve bağımlılıklarını tanımlamak
```
<beans xmlns = "http://www.springframework.org/schema/beans"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <bean id = "photographer" class = "com.apo.course.Photographer" scope="singleton" init-method="init" destroy-method="destroy" autowire = "constructor">
       <constructor-arg ref = "camera"/>
   </bean>
    <bean id = "camera" class = "com.apo.course.SonyCamera"></bean>
</beans>
```

Gördüğünüz gibi burada **SonyCamera** ve **Photographer** sınıfımı bir **bean** olarak kaydediyorum. Burada xml'deki elementlere ufakça bir bakacak olursak;

* **scope** nesnenin yaratılma süreci ile alakalıdır. İki değer(daha fazlası var ama şimdilik önemli değil) alabilmektedir; prototype ve singleton. 
    * Singleton bu sınıfa dair nesnenin springden talep edilmesi durumunda sadece bir nesnenin yaratılmasını sağlar. Yani bu nesneden uygulama süresi boyunca bir tane olacaktır sadece. Birisi ilk istedğinde yaratılacak daha sonra diğer isteklerde yaratılmış nesne verilecektir. 
    * Prototype bu sınıftan her nesne talep edildiğinde yeni nesne yaratılacak demek oluyor. 
* **init-method** bir nesne dünyaya geldikten sonra bazı logicler işletmek istiyor olabilrisiniz. Spring'e **init-method="x"** şeklinde init methodu verirseniz, spring bu nesneyi yarattıktan sonra init methodunuda çağıracaktır.
* **destroy-method** spring yönettiği bir nesne'nin bellekten silinmeden hemen önce yapılması gereken işleri belirtebildiğimiz yöntemdir. **destroy-method="y"** dediğimizde spring bu nesneyi memory'den silmeden önce belirtilen methoduda çağıracaktır.
* **autowire** dependency injection için kullandığımız attribute'dur. Biz **autowire** diyerek spring'e bu sınıftan bir nesne dünyaya gelirken sınıfın constructorını bak orda bir bağımlılık varsa onuda inject etmeye çalış diyoruz. Burada autowire değer olarak byName, byType ve constructor şeklinde 3 değer alabilmektedir.
    * byName sınıftaki propertylerin ismine bakar, bu isimlerle kayıtlı bean varsa setter methodları aracılığıyla bunları inject etmeye çalışır.
    * byType sınıftaki propertylerin ismine değil type'ına bakar ve bu typelara uygun bean varsa bunları inject eder.
    * constructor direk constructora bakar ve autowire(bağımlılığı yaratıp sınıfa vermek) işlemi yapmaya çalışır.
*  <constructor-arg ref = "camera"/> diyerek constructordan bağımlılıkların inject edilmesini otomatik halde yapabilirsiniz. Burada photographer yaratılırken constructorına camera id'sine sahip bir beanden'de yaratılıp gönderilmesi gerektiğğini belirtiyoruz. Bunu setter'lada yapabiliriz; <property name = "camera" ref = "camera"/> diyerek photographer yaratıldıktan camera id'sine sahip bean'den bir tane yaratılıp setCamera methodu ile cameranın setlenmesi gerektiğini söylemiş olduk.

*  **@Configuration** ve **@Bean** annotationları ile java kodu ile tanımlamak
```
    @Configuration
    public class BeansConfiguration {
    	@Bean(name = "camera", destroyMethod = "destroy", initMethod = "closeFilesWhenDestroy")
    	@Scope("singleton")
    	public ICamera camera() {
    		return new SonyCamera();
    	}
    	@Bean
    	public Photographer photoGrapher() {
    		return new Photographer(this.camera());
    	}
    }
```

* Sınıfı **@Configuration** annotationu ile işaretliyoruz.
* Bean tanımlamak için **@Bean** annotationunu kullanıyoruz. Bu sınıfa yazacağımız üstünde **@Bean** annotationu olan her method bir bean kaydı yapmaktadır spring'e.
* **@Bean** annotationu ile **init-methodu**, **destroy-methodu** ve **name** denen attribute ile bean id'sini set edebilirsiniz. Burada eğer name attribute'una değer vermezseniz bean id'si methodun ismi olacaktır.
* **@Scope** annotationu ile bean'in scope belirtilebilmekkedir.

XML'de yapabildiğiz şeyleri birebir java koduylada yapabiliyoruz bu şekilde arkadaşlar. Bu koda yukardaki xml'in birebir karşılığı diyebilirsiniz. Burada **autowire** gibi birşey yoktur, yani bağımlılıkları açıkça yaratıp uygun yere set eden biz oluyoruz.(Photographer yaratırken camera yaratıp constructor'a gönderen biziz).

* **Component Scanning** kullanarak **@Component** annotationu olan sınıfları bean olarak kaydetmek. **@Autowired** annotationu ilede bağımlılıkların otomatik inject edilmesini sağlamak.

```
    @Configuration
    @ComponentScan(basePackages = "com.apo")
    public class BeansConfiguration {
    	
    }
```
Burada spring'e **com.apo** package'inin altında ne kadar sınıf varsa(en alt levele kadar in, yani başka package varsa bu package'n içinde onun içinede gir..) git bu sınıflara bak. Bu sınıflarda **@Component** (**@Service, @Controller, @Repository** 'de olabilir, bunlar aynı zaman **@Component** annotationudur zaten!) annotationlarından herhangi birine sahip olanları **bean** olarak kaydet diyoruz. Ve tek tek uğraşmadan pratik bir şekilde beanler kayıt ediliyor.

Bu durumda sınıfların bağımlılıklarını nasıl ayarlayacak spring derseniz, burada **@AutoWire** annotationu ile autowiring yapılacaktır. 

```
    @Component
    public class Photographer {
    	    private ICamera camera;
    	    @Autowired
    	    public Photographer(ICamera camera){
    	        this.camera = camera
    	    }
    }
```

```
    @Component
    public class Camera implements ICamera {
    	  //default constructor
    }
```

Bu şekilde **Photographer** sınıfının constructor'ının üstüne **@Autowired** diyerek spring'e Photographer nesnesi istenirse senden git ICamera interface'inin implement eden bir bean bul, o beanden nesne oluştur ve Photographer'ın constructorını onu ver demiş oluyoruz.

**@Autowired** annotationu;
* property üstünde
* constructor üstünde
* herhangi bir methodun parametresinden önce
* setter methodu üstünde

kullanabilirsiniz. Hepside amaç bağımlılıkların bir şekilde inject edilmesidir. Bir bağımlılık için bunlardan uygun olanı seçip(size kalmış) oraya **@Autowired'i** koyabilirsiniz.

```
    @Component
    public class Photographer {
            @Autowired //field injection denir buna, kullanımını tavsiye etmiyoruz belli sebepler dolayısıyla
    	    private ICamera camera;
    	    @Autowired //constructor injection
    	    public Photographer(@AutoWired ICamera camera){ //direk parametrenin yanına da yazılabilir
    	        this.camera = camera
    	    }
    	    @AutoWired //setter injection
    	    public void setCamera(Icamera camera){
    	        this.camera = camera;
    	    }
    }
```

Günümüzde yaygın olarak 2 ve 3 seçenekleri tercih edilmektedir. Ancak mix şekildede kullnılabilir. Yani tek bir projede hem **xml**, hem **java kodu** ile hemde **component-scanning** ile aynı anda bean kaydını yapabiliriz. 

Spring'i bir framework gibi değil bu şekilde kullanmak isterseniz(öğrenme adımlarında) maven projenizin pom.xml'ine
```
    <dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.3.21</version>
	</dependency>
```
şeklinde bağımlılığı ekleyebilir, daha sonra tercih ettiğimniz yönteme göre spring'i ayağa kaldırabilirsiniz.

```
        /**
    	 * Beanleri xml'den oku
    	 */
    	ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beans.xml");
    	/**
    	 * Bean tanımlarını sınıftan oku
    	 */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeansConfiguration.class);
        context.refresh();
```

Bu aşamadan sonra context nesnesini kullanarak sınıf ismi veya bean id'si ile spring'den nesne talep edebilirsiniz.

```
    Photographer h = (Photographer)context.getBean(Photographer.class);
```


Temelde bunları yapabilen bir kütüphane olarak geliştirilen spring, daha sonra JAVA ortamındaki bazı sorunları çözmek adına *Framework* olarak kullanılmaya başlamıştır.

**Spring'i framework olarak kullandığımızda bu context nesnesini yaratmak, bağımlılıkların enjekte edilmesi framework tarafından otomatik olarak yapılacaktır.**

Daha iyi anlaşılabilmesi için springsiz dönemdeki web uygulamalarından bir örneğe bakalım.

Java da bir WEB uygulaması geliştirirken temel olarak

* Tomcat web sunucusu(başka sunucularda var tomcat olması şart değil!)
* Servlet ve JSP

kullanılmaktadır. Bir servlet(Web isteğine cevap verebilen bir java sınıfı) geliştirilip Tomcat'e bunun cevap vereceği URL'e dair bilgi verilip web sayfamız hazır hale getirilebilmektedir. Örnek bir Servlet sınıfına baktığımızda

```
    @WebServlet("/hello")
    public class HelloServlet extends HttpServlet {
    	private static final long serialVersionUID = 1L;
        public HelloServlet() {
            super();
        }
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    response.getWriter().append("Hello World");
    	}
    }
```

şeklinde **HttpServlet** sınıfından extend eden, boş bir constructor'a sahip, GET isteklerini karşılamak için **doGet(request, response)** şeklinde methoda sahip bir sınıf.

**@WebServlet** annotationunda yazan URL'e bir istek yapılırsa Tomcat bu sınıftan default(parametre almayan) constructorı ile bir nesne yaratıp, request ve response nesnelerini hazırlayıp **doGet(request, response)** methodunu çağıracaktır. Herşey ne kadar güzel değil mi? Aksine bir çok sorun var bu mimari'de.

Benim bu sınıfta mesela kullanıcıları bir veri kaynağından okuma ihtiyacım oldugunu düşünün. Eğer test edilebilir, kolayca değiştirilebilir, tek bir sorumluluğa sahip bir sınıf yazmak istiyorsam bu veri kaynağında okuyan sınıfı buraya inject etmem gerekiyor. 

İşte sorun burda başlıyor, bu servlet sınıfını ben yönetmiyorum! Ne yaratılması ne setterlarının çağırılması, ne de **doGet(request, response)** methodunun çağırılması!

Bağımlılığı constructora parametre eklesem tomcatin böyle bir yeteneği yok, nesne yaratamaz. Tomcat servleti boş constructorı ile yaratmaktadır. Neyi nasıl yaratacağını bilemez ki zaten tomcat!

Setter eklesem tomcatin yine böyle bir iddiası ve yeteneği yok o methodu çağırmaz. Yani ben burda bağımlı olduğum sınıfı kendim new'lemek zorundayım, işte tam bu anda istemediğimiz bir noktaya geldik, bu kod ilerde başımıza bela olacaktır.

```
    public class HelloServlet extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    	private UserService userService;
        public HelloServlet() {
            super();
        }
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    this.userService = new UserService();
    	    userDetail = this.userService.detail();
    	    response.getWriter().append("Hello World");
    	}
    }
```    

İşte tam bu noktada spring geliyor ve diyor ki durun, bunu ben çözerim! Ve spring'in altın dönemi başlıyor. **Sınıflarımızı, bağımlılıklarımızı springin yönettiği yeni bir WEB uygulama geliştirmesi yapısı doğmuş oluyor.**

Peki nasıl?
Burada spring çok ince bir dokunuşla tüm web geliştirme altyapısını kendi yönettiği şekle getiriyor arkadaşlar.
Biz tomcate sadece, springin içinde bulunan bir servleti tüm istekleri karşılayacak şekilde kaydediyoruz. Buna **dispatcher servlet** diyoruz.
```
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/beans.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

Bu servlete istek geldiğinde ilk yaptığı şey; bir kere mahsus spring frameworkunu ayağa kaldırmak oluyor. Ve **init-param'da** verdiğimiz **beans.xml** dosyasından beanleri okuyacak şekilde spring ayağa kaldırılıyor. Burada xml kullanmak şart değil, **@ComponentScanning** veya **@Bean** annotationları ilede spring ayağa kaldırılabilir. Bunun için 

```
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.apo.config.ApplicationConfig</param-value>
        </init-param>
         <init-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

şeklinde beanleri **ApplicationConfig** sınıfından oku şekline belirtebiliyoruz. Bu sınıfta **@ComponentScan** diyerek **@Component** annotationları ile belirtilmiş beanleri de okuyabilmekteyiz.

Ve işte tam burda artık spring dünyasına geçiş yapıyoruz. Bundan sonra sınıflarımızı DI'a uygun geliştiriyoruz ve kodu spring yönettiği için **@Autowire** ile bağımlıklarımızın inject edilmesini sağlıyoruz. Bu sayede büyüyen projelerde değişimler, testler çok daha rahat yapılabilir hale geliyor.

Ve gelen tüm web istekleri **DispatcherServletten** geçtiği için kodun devamının yönetimi spring'e devredilebiliyor.

Spring kütüphanesinin framework olma hikayesi işte burda başlıyor arkadaşlar. Spring'e web'e dair(**Routing, MVC, Data katmanı, JPA**) gibi şeyler eklenerek adamakıllı bir framework haline geliyor.
