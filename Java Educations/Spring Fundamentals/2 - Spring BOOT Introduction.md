# Nedir bu Spring Boot?

Arkadaşlar spring frameworkü web'de kullanılmaya başladığında bir çok şey güzel hale geldi demiştik. Ancak insanın olduğu yerde ilerleme durmak bilmiyor!

Bir süre **Spring MVC'de** proje geliştirdiğimizde birçoğumuz şunu farkediyoruz, aynı şeyleri tekrar tekrar yapıyorum ben!!

* web.xml'e git dispatcher servlet için gerekli maplemeyi ekle
* Beanleri springe kaydetmek için component scanning yap
* Kendi kullanacağım beanler için(Mesela data katmanı için hibernate, view katmanı için view resolver, JSON ile çalışabilmek için JSON mapper, Security için spring security) bağımlılıkları pom.xml'e ekle. Daha sonra bunları bir configuration sınıfında **@Bean** olarak veya **@Component** olarak configure et
* Büyüyen projelerde başka ihtiyaçlar için bağımlılıkları yükle konfigürasyonu yap.

**Ve bunları yaparken değişen tek şeyin aslında bazı parametreler olduğunu görüyorum! Mesela database için sadece database server adresi veya kullanıcı adı, şifre değişiyor, Security için field isimleri değişiyor, view resolver için viewların oldugu folder veya file uzantıları değişiyor. Yani aslında bu değişimler dışında ben sürekli aynı şeyi yapıyorum!!**

İşte bu noktada spring bootun dünyaya gelme fikri filizleniyor. Springle uzun süreler uğraşan bazı insanlar; ya hepimiz aynı şeyi yapıyoruz hemen hemen, bunları tekrar tekrar yapmadan kolay bir hale getirsek diyorlar ve gerçekten sadece Spring MVC'nin üstüne bazı şeyleri otomatik yapan bir katman geliyor, başka birşey değil!! *Spring* aynı spring *DI* aynı *DI*, birşey değişmedi arkadaşlar, sadece birşeyler *biraz daha kolay hale geldi!!*

Spring Bootun mentalitesi -> **Convention over configuration**, yani elimizden geldiğince birşeyleri otomatize edelim, insanlar kolayca kullansın.

Ve aslında yaptıkları şu oldu
* Yüklenen bir uygulamada bağımlıkları kontrol et ve bunlara uygun şekilde bazı beanleri otomatik olarak kaydet, component scanningi otomatik olarak yap, dispatcher servleti otomatik olarak tüm istekleri karşılayacak şekilde konfigüre et. Aslında dersimizde yaptığımız spring MVC'deki şeyleri kendisi hallediyor bir şartla ama, ona dair bağımlılık yüklenmiş olmalıdır.
* Mesela spring-web-mvc bağımlılığı varsa dispatcheri konfigüre et
* Mesela spring-data-jpa bağımlılığı varsa data katmanını konfigüre et(Hibernate vs...)
* Mesela spring-xml gibi bir bağımlılık varsa XMl ve JSON operasyonları için gerekli beanleri konfigüre et.

Bunları yaparken **AutoConfiguration** dediğimiz sınıfları kullanmaktadır, burada da bir sihir yoktur.
Mesela **DispatcherServletAutoConfiguration** bir sınıf vardır, dispatcher servletin konfigüre edilmesine bu karar vermektedir ve akış şu şekilde işlemektedir.

* Spring boot ayağa kalkarken bağımlılıklara ve içerdikleri sınıflara dair bir liste çıkarmaktadır.
* Daha sonra AutoConfiguration sınıflarını(bu sınıfları nerden buldugu biraz karışık kafanızı karışltırmamak için girmiyorum) çalıştırıyor. 
* Auto configuration sınıflarıda belli bir mantıkta eğer gerçekten gerekiyor ise bean kaydı ve konfigürasyonu yerine getiriyor.

AutoConfiguration sınıfları **@ConditionalOnMissingBean, @ConditionalOnClass, @ConditionalOnProperty** gibi annotationları kullanarak eklenen kütüphane için bean kaydı yapıp yapmayacağına, konfigürasyon parametrelerine karar vermektedir. (Aslında bean kaydı yapmak için gerekli bağımlılık var mı yok mu kabaca bunun kontrolu)

Mesela **DispatcherServletAutoCOnfiguration** kabaca şu şekilde çalışmaktadır.
```
@ConditionalOnClass(DispatcherServlet.class)
    class DispatcherServletAutoConfiguration{
        @Bean(name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
		public DispatcherServlet dispatcherServlet(WebMvcProperties webMvcProperties) {
			DispatcherServlet dispatcherServlet = new DispatcherServlet();
			dispatcherServlet.setDispatchOptionsRequest(webMvcProperties.isDispatchOptionsRequest());
			dispatcherServlet.setDispatchTraceRequest(webMvcProperties.isDispatchTraceRequest());
			dispatcherServlet.setThrowExceptionIfNoHandlerFound(webMvcProperties.isThrowExceptionIfNoHandlerFound());
			dispatcherServlet.setPublishEvents(webMvcProperties.isPublishRequestHandledEvents());
			dispatcherServlet.setEnableLoggingRequestDetails(webMvcProperties.isLogRequestDetails());
			return dispatcherServlet;
		}
    }
```

Bu sınıfın tam hali için [DispatcherServlet Auto Configurer](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/web/servlet/DispatcherServletAutoConfiguration.java) linkine bakabilirsiniz arkadaşlar.

Arkadaşlar spring boot bir MVC projesi geliştirme amacıyla değil, **daha çok webservis veya API geliştirmek üzerine düşünülüp tasarlanmış bir frameworktur.(MVC halinede getirebilirsiniz, bu da mümkün).**

Yani çıktı olarak insan yönelik HTML sayfası üreteyim vs.. değil, internet üstündeki cihazları ortak standartlar(**HTTP, JSON, XML**) üzerinden haberleştireyim derdi vardır. Makina-insan değil makina-makina haberleşmesinde kullanılması(buna webservis demiyor muyuz zaten) tercih edilmektedir.

**Ödevimizde bizde JSON çıktılar kullanarak, webservis mimarisine hazırlık yapacağız. Hem geliştirmesi hemde başkasının webservisini kullanma olarak.**

# NOT

Spring boot uygulaması geliştirirken en kolay yöntemlerden birisi [Spring initializer](https://start.spring.io/) sitesinden yapmak arkadaşlar. Burada bağımlıklarınızı, versiyonu, proje ismini kolaylıkla ayarlayabiliyorsunuz. Burdan projeyi konfigüre edip indirdikten sonra **import->existing maven project** deyip projeyi eclipse'e import edebilirsiniz.
