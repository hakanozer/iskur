# Spring MVC
Arkadaşlar **Spring MVC** altyapısında spring kullanan güncel bir web frameworküdür. Eskiden kullandığımız servlet yapısına göre çok daha düzgün kod geliştirme imkanı sunmaktadır. 

Bir önceki dökümanda springe dair öğrendiğimiz tüm yetenekleri burada kullanabilmekteyiz.

Öncelikle bir maven projesi yaratıyoruz arkadaşlar. Bu maven projesinin archetype'i **webapp** olacaktır arkadaşlar. Sonra **pom.xml'e**

```
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.22</version>
    </dependency>
```

şeklinde **spring-webmvc** kütüphanesinin en güncel versiyonunu ekliyoruz. Bu spring'in diğer kütüphanelerini'de ekleyecektir.(spring-context, spring-beans vs....)

Tomcat sunucumuzda çalıştıracağız uygulamamızı, bunun için gerekli indirmeyi yapmanız gerekmektedir.

Bunu yaptıktan sonra birkaç yöntemle spring destekli web uygulamasını ayağa kaldırabilmekteyiz.

#### Eski usül web.xml ve beans.xml kullanarak
Tomcatte web uygulaması geliştirilirken belli bir yapı vardır şuna benzer;

```
tomcatfolder
    webapps
        yourwebapp
            WEB-INF
                web.xml
                classes
```   

Burada **web.xml** tomcate servletlerimizi tanıttığımız yerdir. classes folderı derlenmiş kodlarımızı içeren yerdir. (Yani tomcatin classpathlerinden biridir.)

Spring tomcatle çalışırken çok ince bir dokunuşla web uygulamasını spring destekli hale getirir. Bunuda gelen tüm istekleri kendi geliştirdiği bir servlete yönlendirerek yerine getirir. Buna **DispatcherServlet** diyoruz. Bizde burada gelen tüm istekleri bu servlete yönlendiren konfigürasyona **web.xml** dosyanıza ekliyoruz.

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

Burada gördüğünüz url-pattern'de / diyerek tüm isteklere refere ettik. Ve bu isteklerin **dispatcher** adındaki servlete gitmesi gerektiği belirttik. Yukarıdada zaten dispatcher adındaki servletin
**org.springframework.web.servlet.DispatcherServlet** olduğunu belirttik. Bu şekilde uygulamamız çalışınca tomcat tüm isteklerde bu servlet'i ayağa kaldırıp uygun methodunu çağıracaktır.

İşte tam bu noktada bu servlet kendi içinde spring'i ayağa kaldırır. Ve bean tanımlarını sizin yukarda 

```
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/beans.xml</param-value>
    </init-param>
```      
kısmında **contextConfigLocation** parametre ismiyle belirttiğiniz yerden okur. Ve spring ayağa kalkmış olur.

### Daha gğncel @ComponentScan ve @Bean Yöntemi

Burada öncekine göre değişen tek şey arkadaşlar beanleri beans.xml'den değil java kodu ile **@Bean** ve **@Component** annotationları ile belirtmektir. Bunu yaparken değişen tek yer 

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

buradaki **init-param** kısmı olacaktır. Burada **contextClass** init-param'i ile xml'den değil **@Configuration** sınıfından beanleri okuyabilen sınıfın adını veriyoruz. Daha sonra geliştirdiğim **@Configuration** annotationuna sahip sınıfı full package ismi ile birlikte **contextConfigLocation** init-param'ına yazıyorum. Ve bu sınıfta istersem bean tanımlarım istersem **component-scanning** yaptırabilirim.

``` 
    @Configuration
    @ComponentScan(basePackages = "com.apo")
    public class BeansConfiguration {
    	@Bean
    	public ICamera camera(){
    	    return new SonyCamera();
    	}
    }
```

Burada **SonyCamera** bir bean olarak kaydolacaktır. Ve bir de **com.apo** package'inin altında **@Component** annotationuna sahip tüm sınıflar bean olarak kaydolacaktır. Ve spring ayağa kalkmış olacaktır.

### En güncel yöntem **WebInitializer**

Arkadaşlar tomcat bir yerden sonra web.xml'den tamamen kurtulabilmemiz adına servletlerin ayarlarının kodla yapılabilmesini sağlamıştır. Bir sınıf yazıp bunu **WebInitializer** interface'inden türetip **onStartUp()** methodunu override ederek tamamen java kodu ile **DispatcherServlet** ayağa kaldırılabilmektedir.

``` 
    public class MyWebAppInitializer implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext container) {
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setConfigLocation("/WEB-INF/beans.xml");
    
            ServletRegistration.Dynamic dispatcher = container
              .addServlet("dispatcher", new DispatcherServlet(context));
    
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
        }
    }
``` 

şeklinde dispatcher servlet ve spring için beans.xml ayarlaması yapılabilmektedir. Tomcat ayağa kalkarken bu sınıfı bulup **onStartUp()** methodunu çağıracaktır.

Buradada **beans.xml** ile çalışmak zorunda değiliz. Yine **@Bean** ve **@ComponentScanning** annotationları ile **@Configuration** sınıfı yaratabiliriz. 

``` 
    public class MyWebAppInitializer implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext container) {
        
            AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.register(BeansConfiguration.class);
    
            ServletRegistration.Dynamic dispatcher = container
              .addServlet("dispatcher", new DispatcherServlet(context));
    
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
        }
    }
``` 

Gördüğünüz gibi **XmlWebApplicationContext** sınıfını **AnnotationConfigWebApplicationContext** şeklinde değiştirip **@Configuration** sınıfımı register ettim.

Bu aşamadan sonra spring destekli bir web uygulaması ayağa kalkmış oluyor arkadaşlar. Artık MVC mimarisine uygun controller'larımı, viewlarımı, modellerimi düzgünce geliştirip **@Autowire** yöntemi ile bağımlılıklarını set edebilirim.

## CONTROLLER

**MVC** mimarisinde isteğin ilk geldiği yer controller kısmıdır. Spring'de geliştirdiğimiz bir sınıfı **@Controller** annotationu ile controller olarak belirtebiliriz. **@Controller** annotationu bir **@Component** annotationudur aynı zamanda. Yani controller sınıfımın yaratılması bağımlılıklarının verilmesi tamamen spring yöntemindedir. Controller sınıfı bir bean'dir yani!

``` 
    @Controller
    public class HelloController{
        public String hello(){
            
        }
    }
``` 

Burada spring'in **@Controller** sınıflarını görebilmesi için controller sınıfının **@ComponentScan**'e verdiğiniz package içinde olmasına dikkat edin lütfen. Yoksa bulamaz spring controller'ı.

Controller sınıflarında public yazdığımız her bir method dışardan yapılan bir isteğe cevap verme adayı methodlardır. Bu methodların hangi URL'e yapılan isteklere cevap vereceğini **@RequestMapping** adını verdiğimiz annotation ile belirleyebilmekteyiz.


``` 
    @Controller
    public class HelloController{
        @RequestMapping("/hello")
        public String hello(){
            
        }
    }
``` 

Gördüğünüz gibi birisi **http://host:port/hello** şeklinde bir istek yapınca spring bu methodu çalıştırıp sonuç dönecektir. Burada isteğin HTTP methodunu ayarlayabilmekteyiz **@RequestMapping** annotationu ile.


``` 
    @Controller
    public class HelloController{
        @RequestMapping(path = "/hello", method = RequestMethod.GET)
        public String hello(){
            
        }
    }
``` 

**Spring MVC** doğası gereği bir **HTML** view dönmemizi bekler cevap olarak. **Yani methodda return ettiğimiz değerin bir view ismi olduğunu düşünür ve o isimde bir view dosyası arar.** Bu davranışı elimine etmek için, yani spring'e return ettiğim şey view ismi değildir, direk kullanıcıya dönmen gereken cevaptır demek için **@ResponseBody** annotationunu kullanabiliriz.

``` 
    @Controller
    public class HelloController{
        @RequestMapping(path = "/hello", method = RequestMethod.GET)
        @ResponseBody
        public String hello(){
            return "Hello World";
        }
    }
``` 
Artık buraya bir istek yapılırsa cevap olarak "Hello World" texti dönecektir. 

Peki bir **HTML** view dönmek istersek ne olacak derseniz. Burada öncelikle **ViewResolver** tipinde bir bean'i springe kaydetmemiz lazım. Bunu da **BeansConfiguration** adını verdiğimiz sınıfta yapabiliriz.

``` 
    @Configuration
    @ComponentScan(basePackages = "com.apo")
    public class BeansConfiguration
        @Bean
        public ViewResolver internalResourceViewResolver() {
            InternalResourceViewResolver bean = new InternalResourceViewResolver();
            bean.setPrefix("/WEB-INF/views/");
            bean.setSuffix(".jsp");
            return bean;
        }
    }
``` 

Burada spring'e controller'ın return ettiği isme göre view dosyalarını ararken **/WEB-INF/views** klasörü altında uzantısı **.jsp** olan dosyalara bak demiş oluyoruz. **JSP java tarafında HTML, CSS, Javascript ve Java kodlarının aynı anda yazılabildiği dosya yapısıdır arkadaşlar.**

Bu aşamadan sonra controller'dan **@ResponseBody** annotationunu siliyoruz ve return olarak "hello" dönüyoruz.

``` 
    @Controller
    public class HelloController{
        @RequestMapping(path = "/hello", method = RequestMethod.GET)
        public String hello(){
            return "hello";
        }
    }
``` 

Burada spring **ViewResolver** beanine verdiğimiz değerlere göre **/WEB-INF/views/** klasörünün altında **hello.jsp** adında bir dosya arıyor ve kullanıcıya cevap olarak bu dosyayı dönüyor.

### AUTOWIRING

Peki şimdi şöyle düşünelim sisteme kayıtlı tüm kullanıcıları veren bir istek geliştirmek istiyorum. Burada userlarımı database'den alacağım diyelim. Ve bunun için **UserRepository** adında bir sınıf geliştirdim. Controller'ımıda şu şekilde yazabilirim.

``` 
    @Controller
    public class UserController{
        @RequestMapping(path = "/users", method = RequestMethod.GET)
        public String all(){
            UserRepository ur = new UserRepository();
            List<User> allUsers = ur.getAll(); 
            return "burda userları html'e çeviren bir view ismi donulur";
        }
    }
``` 

Şimdi bu kodu böyle geliştirdiğimde taa spring'in en başında bahsettiğimiz tüm sorunlarla yüzleşmiş oluyoruz.

* Bu sınıf **UserRepository'nin** nasıl yaratılması gerektiğini biliyor
* Bu methoda test etmek istediğimde fake bir repository veremem ben buraya açıkça new'le ben yarattığım için
* Userlarımı farklı bir kaynaktan(dosyadan) mesela çekmek istediğimde bu koda dokunmadan bu değişikliği yapamıyorum.

Biz spring'i zaten bunları çözmek için kullanmıyor muyduk? İşte burada spring'in bağımlılıkları enjekte edebilme yeteneğinden yararlanabiliriz. UserRepository sınıfını bir bean olarak spring'e kaydederiz önce.


``` 
    @Component
    public class UserRepository{
        public List<User> getAll(){
            //codes
        }
    }
``` 

Daha sonra controller'da **@Autowire** ile

``` 
    @Controller
    public class UserController{
        private UserRepository ur;
        @AutoWired
        public UserController(UserRepository ur){
            this.ur = ur;
        }
        @RequestMapping(path = "/users", method = RequestMethod.GET)
        public String all(){
            UserRepository ur = new UserRepository();
            List<User> allUsers = ur.getAll(); 
            return "hello";
        }
    }
``` 

diyerek **UserRepository** bağımlılığının buraya spring tarafından enjekte edilmesini sağlarız. Şu an kodumuz bahsettiğimiz tüm sorunlardan arınmış oldu küçük bir hamle ile.

Birde **UserRepository'lere** bir interface tanımlarsam yarın yeni bir repository geliştirirken millet interface'e göre tanımlar geçer. Mis gerçekten!

``` 
    public interface IUserRepository{
        public List<User> getAll();
    }

    @Component
    public class UserRepository implements IUserRepository{
        public List<User> getAll(){
            //codes
        }
    }
``` 

Ve controller artık interface tipinde repository bekler;

``` 
    @Controller
    public class UserController{
        private IUserRepository ur;
        @AutoWired
        public UserController(IUserRepository ur){
            this.ur = ur;
        }
        @RequestMapping(path = "/users", method = RequestMethod.GET)
        public String all(){
            UserRepository ur = new UserRepository();
            List<User> allUsers = ur.getAll(); 
            return "hello";
        }
    }
``` 

Arkadaşlar kodunuzu şu hale getirin, ilerisi için hayatınızda çalışma sürenizi yarı yarıya azaltacağını size garanti ediyorum.

Burdan sonrasını sizin emeklerinize ve araştırmalarınıza bırakıyorum arkadaşlar. Mesela **Spring MVC'de** gerçekten database'den veri çeken **UserRepository** nasıl yapılır?




    
    
    
    
    
