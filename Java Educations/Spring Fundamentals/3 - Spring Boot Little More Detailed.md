# MVC mimarisi ve Spring Boot

**MVC** bir mimaridir arkadaşlar, kodumuzun **Model-View-Controller** adını verdiğimiz katmanlara bölerek daha sistematik, değiştirilmesi kolay kodlar yazabilmemizi sağlamaktadır. 

* Model katmanı veri katmanımızdır diyebiliriz. Verimiz farklı kaynaklardan gelebilir, dosya, veritabanı, dış bir servis vb.. ORM yazılımları(Hibernate, MyBatis) bu katmanda iş yapmaktadır.
* Controller katmanını gelen isteği karşılayıp uygun modelden veya servisten uygun datayı alıp, uygun view'a dataları gönderip cevabın oluşturulmasını sağlayan katmandır. Yani bir trafik polisi gibi işleri düzenleyen isteğin karşılandığı ilk kısımdır. Bu kısım iş yapma logici içermemeli, sadece uygun modelden ve servisten datayı alıp uygun dönüş tipiyle kullanıcıya cevap dönmelidir. Tekrar kullanılabilir bir katman değildir, bundan dolayı burada **İŞ** yapan bir kod olmamalıdır. İşi yapan yer tekrar kullanılabilir başka sınıflar olmalıdır.(Servis, repository, validator, view resolver vs....)
* View kullanıcıya dönecek verinin formatlanması katmanıdır arkadaşlar. Eğer browser'a bir cevap dönecek isek HTML, CSS, JSP; webservis geliştiriyor isek JSON, XML gibi formatların kullanıldığı katmandır. Webservis dünyasında frameworkler çoğu zaman otomatik olarak formatı ayarlamaktadır HTTP headerlarına göre, yani view katmanı webservis geliştirmede neredeyse gereksizdir diyebiliriz.

**MVC** temelde website(Çıktıdaki amacın insan olduğu, HTML,CSS vb.. dizaynlar yapılan) geliştirmesi için kullanılmaktadır. Spring MVC bu amaca yönelik **spring** kullanan web frameworkümüzdür.

Zamanla işler değişmeye başlayıp artık **SPA(Single Page Application)** ve **Mobile uygulama** dünyasının büyümeye başlaması dolayısıyla View katmanı meselesinde değişiklik olmuştur.

Mobil uygulamaların dizaynların **HTMl ve CSS** kullanmaması, **SPA'da** çok gelişmiş Frontend frameworklerinin çıkması ile artık cevaplarda HTML dönme ihtiyacı kalmamıştır ve her isteyene ortak bir format dönme adına MVC'deki V biraz havada kalmaya başlamıştır.

Webservis ve API'lerin gelişmesi ile birliktede artık **BACKEND RENDERED** dediğimiz(Backend tarafında HTML ve CSS'in ayarlanması) konusu geride kalmıştır.

İşte bu noktada **Spring BOOT** frameworku biraz daha bu amaca yönelik geliştirilmiş bir frameworktur. Amaç **HTML, CSS** ile web sayfası üretmekten çok, **JSON veya XML** ile cevaplar dönüp isteyenin istediği şekilde veriyi alıp şekillendireceği bir yapı oluşturmak.

# SPRING BOOT
**Spring veya Spring BOOT'un** temelde weble alakası yoktur arkadaşlar ancak yoğun olarak bu alanda kullanılmaktadır. Bizde eğitimizde web tarafını işleyeceğiz çoğunlukla.

***Spring initializer***'den proje kurulumu yaparken eğer spring bootu WEB uygulaması geliştirmek için kullanacak isek **Spring web** modülünü dependency olarak ekliyoruz. (Biz hep **WEB** geliştirmesi yapacağımız için bu bağımlılığı hep ekleyeceğiz).

Burdanda aslında **Springin ve Spring BOOT** web'e özel şeyler olmadığını anlayabilmekteyiz.

Arkadaşlar spring web mimarisinde gelen web isteklerine cevap vermek için Controller adını verdiğimiz sınıfları oluşturuz öncelikle. BU sınıflar **@Controller** annotationuna sahip olurlar.
```	
    @Controller
    public class MovieController(){
        
    }
```	

**@Controller** annotationu özünde **@Component** annotationudur, yani **Spring BOOT** ayağa kalktığında bu annotationa sahip sınıfları **bean** olarak kaydetmektedir. Yani controllerlarımız bean'dir ve yaratılması, bağımlılıklarının verilmesi spring tarafından kontrol edilmektedir.(**Dispatcher servlet**'i hatırlayın gelen tüm istekleri spring'e devrediyorduk)

Controller sınıflarımıza yazdığımız her bir method(public olma şartı ile) bir web isteğine cevap vermek üzere kodlanır diyebiliriz. Burada **@RequestMapping** annotationunu kullanarak bu methodun nasıl bir URL'e istek yapaldığında çalışması gerektiğini springe söyleyebiliriz.

```	
    @Controller
    public class MovieController(){
        @RequestMapping("/movies/{id}")
        public Movie detail(){
            
        }
    }
```	    

**Spring normalde controllerı yazdığımız methodlardan return ettiğimiz şeyler için view dosyası arar arkadaşlar.** Default davranışı budur, ancak biz **HTMl, CSS** gibi bir çıktı değil, **JSON XML** gibi bir çıktı üretmek istediğimizden dolayı springe return ettiğim şeyi direk cevap olarak dön; view arama demek durumunda kalırız. Bunuda methodun üstüne koyduğumuz **@ResponseBody** annotationu ile springe söyleyebilmekteyiz.
```	
    @Controller
    public class MovieController(){
        @RequestMapping("/movies/{id}")
        @ResponseBody
        public Movie detail(){
            
        }
    }
```	
İşte bu noktada biz her zaman **JSON veya XML** dönecek isek sürekli **@ResponseBody** yazmak yorucu olabilir. Spring buna çözüm olarak bize yeni bir annotation sunmaktadır. **@RestController** annotationunu **@Controller** yerine kullanabiliriz bu sayede spring otomatik olarak view aramaz tüm methodlardan dönen cevabı kullanıcıya gönderir. Yani **@RestContoller = @Controller + @ResponseBody**   

```	
    @RestContoller
    public class MovieController(){
        @RequestMapping("/movies/{id}")
        public Movie detail(){
            
        }
    }
```

**@RequestMapping** annotationunda controllerdaki methodumuzun hangi **HTTP** methoduna(GET, POST, PUT, PATCH vb...) cevap vereceği ayarlayabilmekteyiz arkadaşlar.

    @RequestMapping(path = "/movies/{id}", method = HttpMethod.GET)
    
**Burada artık URL'i annotationun path attribute'una yazmak durumundayız.**

Spring frameworku burada developerlara kolalık olması adına **@GetMapping("/movies/{id}")** şeklinde direk HTTP methodunu belirtebileceğiz annotationalarada sahiptir.(**@PostMapping, PutMapping vs..**). Bu bir kolaylıktır, yapılan iş **@RequestMappingle** aynıdır.

Peki **HTTP** isteğinde gelen parametreleri nasıl okuyoruz derseniz

* Eğer parametremiz path parametresi ise(URL'de / kullanılarak eklenen parametreler mesela movies/1) bunu methodunuza @PathVariable annotationu kullanarak parametre olarak alabilirsiniz.
* Eğer parametre query string parametresi ise(URL'de ? işaretinden sonra eklenen key=value değerleri mesela movies?name=Interstellar) bunu @RequestParam annotationu ile methoda parametre olarak alabiliriz.
* Eğer POST isteği ile HTTP body'sinde veri gelmiş ise bununa yine @RequestParam annotationu ile okuyabiliriz.
* Gelen tüm parametreleri yine @RequestParam annotationu ve Map<Object, Object> kullanarak tek seferde bir map'in içine alabiliriz.

```
    @RestContoller
    public class MovieController(){
        @GetMapping("/movies/{id}")
        //örnek istek http://localhost/movies/1
        public Movie detail(@PathVariable int id){
            //id'nin değeri bu istekte 1 olacak
        }
        @GetMapping("/movies/search")
        //ornek istek http://localhost/movies?name=Interstellar
        public List<Movie> searchWithName(@RequestParam String name){
            //name'in değeri bu istekte Interstellar olacak
        }
        @GetMapping("/movies")
        //tüm parametreler map'e alınmıştır.
        //ornek istek http://localhost/movies?name=Interstellar&year=55
        public List<Movie> allMovies(@RequestParam(required=false) Map<String,String> params){
            //params.get("name") Interstellar olacak, params.get("year") 55 olacak
        }
    }
```	    

Arkadaşlar hem **@PathVariable** hem'de **@RequestParam** annotationu name ve required şeklinde iki önemli attribute'a sahiptir.
* name attribute'i ile gelen parametrenin ismini belirtebiliriz. Normalde spring methoddaki parametre ismine bakar, gelen istekteki parametreyi buna göre belirler. Yani

**@RequestParam** String id demek gelen istekte id isimli parametreye refere eder. Eğer burada gelen istekteki isimden farklı method parametresi ismi belirlemek isterseniz şu şekilde

public Movide detail(@RequestParam(name = "movie_name") String name) şeklinde kullanarak spring'e gelen istekte movie_name isminde parametreyi String name method parametresine ata demiş oluruz.

* required attribute'side beklenen parametreinin zorunlu olup olmadığını belirlemek için kullanılır. Eğer required değeri true ise istekte bu parametre yok ise spring direk olarak hata dönecektir. 

```	
    @RequestParam(required = false)
```	    

# JSON cevap

Arkadaşlar **Spring BOOT**'da dediğimiz gibi amaç çıktı insan için değildir bundan dolayı **HTML, CSS** gibi dizayn dillerine ihtiyacımız yoktur verimizi daha evrensel standartlarda(**JSON, XML**) kullanıcıya açabiliriz.

Spring boot kendi içinde default olarak JSON cevaplara destek vermektedir arkadaşlar. Siz bir methodunuzdan bir objeyi return ederseniz **Spring BOOT** bunu **JSON**'a çevirip kullancıya dönecektir. Büyük rahatlık gerçekten.

İsterseniz siz HashMap dönün isterseniz direk olarak Movie nesnesi dönün **Spring BOOT**'a **JSON**'a çevirme işini halledecektir. Bununla uğraşmanıza gerek yok. Yani

```	
    @GetMapping("/movies/{id}")
    public Movie detail(@PathVariable int id){
        Movie m = this.movieService.findById(id);
        return m;
    }
```	    

gibi bir controller methodunuzda movie nesnesi **Spring BOOT** tarafından direk olarak bir JSON objesine maplenip kullanıcıya o şekilde dönecektir.

```	
    {
        "id" : 1,
        "name" : "Interstellar",
        "year" = 2021
    }
```	

Burada tabii ki başka formatlarda(XML vb...) dönüş yapabilirsiniz. Buda kolayca ayarlanabilmektedir. Default davranış JSON şeklindedir.

# ÖNEMLI BIR NOT

Arkadaşlar **Spring BOOT** sihirli değildir, otomatik konfigüre olabilen bir yapıdadır. Bizi tekrar eden bir çok işten kurtarır. Bİr spring boot uygulamasını initializerden ayarlayıp eclipse import ettiğinizde içinde main methodu olan bir sınıf bizi karşılamaktadır. İşte tüm sihri bu sınıftaki @SpringBootApplication annotationunda saklanmaktadır diyebiliriz.

```	
    @SpringBootApplication
    public class ApiApplication {
    	public static void main(String[] args) {
    		SpringApplication.run(ApiApplication.class, args);
    	}
    }
```

Bu annotationa biraz daha dikkatli baktıgımızda

```	
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
    public @interface SpringBootApplication {
        ............
    }
```	    
    
* @EnableAutoConfiguration ile beanlerin otomatik bağlanmasını (pom.xml'de belirtilen bağımlılıklara göre)
* @ComponentScan ile bu sınıftan bulunduğu package ve altta ne kadar package varsa oralarda bean(component) aranması
* @SpringBootConfiguration ile'de aslında kabaca @Bean annotationları ile bean kaydı yapılabilmesini(bu maddede tam olarak bu değil ancak böyle düşünmenizde sakınca yok) sağlanması.

Yani bakın lütfen aklınızda sihirli olduğuna dair ufak birşey varsa silin atın, bunların hepsini biz Spring MVC'de yaptık, bu bizim yerimize yapıyor.

# ÖNEMLİ NOT 2
*Spring BOOT* kendi içinde bir web sunucu ile gelmektedir. Harici bir websunucuya ihtiyacı yoktur. Direk main methodu olan biraz önce bahsettiğimiz sınıfı run as java application derseniz web uygulamanız ayağa kalkacaktır. Eğer sunucuyu çalıştırırken port çakışması yaşıyor iseniz Projenize
**src/main/resources/applciation.properties** dosyasına

```	
    server.port=6161
```	    

gibi bir satır ekleyerek **6161** gibi bir portta çalışmasını sağlayabilirsiniz.
Kolay gelsin arkadaşlar.
