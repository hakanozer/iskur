# SPRING SECURITY IN DEPTH

Bir önceki dökümanda spring security'e kısa bir giriş yapmıştık.
Spring security default gelen özelliklerinin yanında bir çok özelleştirebilme seçeneği sağlamaktadır. 

### FORM LOGIN

Spring security'nin **form login** için bazı default ayarları şu şekildedir.

- Kütüphane içinde gelen bir login sayfayı vardır. Kullanıcının authenticationı yoksa(daha önce login olmamış ise) kullanıcı **/login** şeklinde default login formu içeren bir sayfaya yönlendirilir.
- Bu sayfadaki formda **username** ve **password** iki adet field vardır. Ve singIn butonuna tıklanıldığında **POST** methodu ile yine **/login** URL'ine istek gönderilir.
- Spring security'nin **UsernamePasswordAuthenticationFilter** filteri **/login'e** gelen **POST** isteklerinde çalışmak üzere hazır haldedir zaten. İstek gelince burası kullanıcıyı login yapmaya çalışır.
- Spring security'e hiç bir ek özelleştirme yapmadan uygulamanızı çalıştırdığınızda console bir tane auto generated bir şifre görmektesiniz arkadaşlar.  **Using generated security password: 377bd23c-715e-4d2d-bb15-079e665d73be**. Spring user yaratma gibi işlemlerle uğraşmadan hızlıca süreci anlamamız adına bize başta bir kullanıcı tanımlar. Bu kullanıcın kullanıcı adı da "**user**" oluyor default'ta. Yani biz login sayfasında bu bilgilerle giriş yapabilmekteyiz.
- Filterimiz isteği alır userı bulmaya çalışır, user'ı bulursa bir session başlatır. Ve cevapta kullanıcıya anahtarı(**session id**'de denir buna) gönderir. Burda anahtarın gönderildiği cookie'nin default ismi **JSESSIONID**'dir, developer console'u açıp sizde görebilirsiniz.
- Burda kullanıcıya geri cevap dönerken kullanıcıyı login sayfasına gelmeden önce istek attığı sayfaya yönledirir spring. Eğer direk login sayfasına gelindi ise yönlendirme **/** şeklinde ana sayfaya yapılır.
- Sonraki isteklerde bu anahtar ile gelen kullanıcının anahtarı bir filtre aracılığıyla kontrol edilir ve oturum devam ediyor mu etmiyor mu bakılır.

Arkadaşlar burada anlatılan tüm süreçlere tüm detayları ile müdahale edebiliyoruz. Spring security'de güvenlik adımlarını özelleştirmek için öncelikle bir **@Configuration** sınıfı yazmalıyız.

```
    @Configuration
    public class SecurityConfiguration {
    
    }
```
Springin bu sınıfta yapılan güvenlik ayarlarını dikkate alması için bu sınıfı birde **@EnableWebSecurity** annotationu ile işaretlememiz gerekmektedir.

```
    @Configuration
    @EnableWebSecurity
    public class SecurityConfiguration {
    
    }
```
Spring security bize kolaylık olsun diye security'i konfigüre edeceğimiz sınıfları extend edebileceğimiz bir sınıf sunmaktadır. **WebSecurityConfigurerAdapter**, bizde kendi **@Configuration** sınıfımızı bu sınıftan **extend** ediyoruz. (Bu sınıfın üstünü çizecektir IDE korkmayın, depreceated(zamanı geçmiş, ilerde tamamen kaldırılabilir) olan bir sınıftır, ancak olayın temeli bu sınıf üstünden daha iyi anlaşılmaktadır)

```
    @Configuration
    @EnableWebSecurity
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    }
```

İşte bu an'dan sonra belli methodları **override** ederek güvenlik işlemlerini tamamen konfigüre edebiliriz. Buradaki temel konfigürasyon methodumuz **protected void configure(HttpSecurity http)** imzasına sahip **configure** methodudur.

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		
	}
```

Burada fluent(.(nokta) koya koya method çağırımları yaparak) şekilde güvenliği tamamen konfigüre edebilmekteyiz. Mesela form logine dair neler yapabildiğimize bakacak olursak

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.antMatchers("/customLogin").permitAll();
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.usernameParameter("email") // override form username field name
		.passwordParameter("password") // override password field name
		.loginPage("/customLogin") // don't use default login page, use this
		.loginProcessingUrl("/customLoginProcessing") // custom login processing URL
		.successForwardUrl("/profile") //after succesfully login redirect user to profile page
		.successHandler(new CustomSuccessHandler()) // if auth success run some codes on this listener(handler), spring will call this
		.failureForwardUrl("/loginFailure") // if auth fails, then redirect user to this URL
		.failureHandler(new CustomFailureHandler()); //when auth fails, run some code 

	}
```

şeklinde özelleştirmeler yapabilmekteyiz. Burada

- İlk başta spring security'e istekler **authentication** ve **authorization** yapmak sitediğimiz belirtiyoruz. **authorizeHttpRequests**
- Daha sonra sisteme gelen tüm isteklerin mutlaka **authenticated** olması gerektiğini belirtiyoruz. ** .anyRequest().authenticated()**
- Daha sonra **.and().formLogin()** diyerek formLogin konfigürasyon kısmını geçiyoruz. Gördüğünüz gibi burada farklı methodlar çağırarak tüm süreci konfigüre edebiliyorum.(Yorum satırlarını okuyun lütfen)
- Bu adımlardan sonra mesela login gerektiren bir yere istek attığımda (**/hello** mesela) kodun artık **/login'e** değil **/customLogin** şeklinde benim yazdığım **URL'e** gittiğini sizde görebilirsiniz.

#### ÇOK ÖNEMLİ NOT

Spring'e tüm istekleri authenticate et dediğimizden dolayı spring login sayfası URL'imiz olan **/customLogine** gelen isteklerinde login olmuş userı olması gerektiğini düşünür ve custom logine gelen isteği bile custom logine tekrardan yönledirir login yoktur diye, böylece elimizde **nur topu gibi bir sonsuz yönlendirme olur**. Burada şimlik ne olduğunu bilmesenizde **/customLogin** sayfasına herkesin gelebilmesi ve bu sayfaya gelenlere login kontrolü yapılmaması için **authorizeHttpRequests** çağrısından hemen sonra

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.antMatchers("/customLogin").permitAll()
		.anyRequest()
		.authenticated()
	    ....
	    ....

	}
```

şeklinde **.antMatchers("/customLogin").permitAll()** kısmını ekliyoruz.

## SESSION CONFIGURATION

Arkadaşlar session auth'da sunucu tarafında session açılır demiştik. Bu sessionuna dair bazı konfigürasyonları yine bu method aracılığıyla yapabilmekteyiz.
```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement()
		.sessionFixation().migrateSession()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.invalidSessionUrl("/inValidSession");

	}
```
- **sessionManagement()** diyerek session konfigürasyonuna başlatıyoruz. 
- **sessionFixation().migrateSession()** diyerek session fixation olarak bildiğimiz bir saldırı çeşidi için(detayları kafa karıştırabilir girmiyorum şimdilik) güvenlik önlemi ekleniyor.
- **sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)** diyerek session'ı her istekte değilde gerektiği zaman üretilmesi gibi bir ayar yapıyoruz.
- **invalidSessionUrl("/inValidSession")** geçersiz bir session id(anahtar) ile istek yapıldığında kullanıcnın yönlendirileceği **URL** belirleniyor.

Önemli konulardan biriside sessionların süresinin olduğuydu. Bu default olarak 30 dakikadır. Bunuda konfigüre edebiliyoruz ancak buradan değil!

- Ya properties dosyanıza **server.servlet.session.timeout= 40m** şeklinde bir entry ekleyerek(40 dakika yapar session süresini, 30s dersem mesela 30 saniye yapar)
- Yada hatırlarsanız **AuthenticationSuccessHandler** vardı formLogin ayarlarında orda geliştirdiğimiz **CustomSuccessHandler()** sınıfının içinde bu ayarı yapabiliyoruz.

```

    public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    	@Override
    	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    			Authentication authentication) throws IOException, ServletException {
    		// parameter type is seconds
    		// SET Session timeout as 40 minute
    		request.getSession(false).setMaxInactiveInterval(40 * 60);
    	}
    }
```

### BASIC AUTHENTICATION

Basic authentication'da zaten çok değişim gerektiren bir durum yoktur. Burada genel olarak sadece eğer gelen bilgilerle kullanıcı authenticate olamazsa kullancıya dönen cevabı customize etme işlemi yapılmaktadır.

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(new CustomAuthEntryPoint());

	}
```

**httpBasic()** çağrısı yaparak basic authentication'u etkinleştiriyoruz ve daha sonra **authenticationEntryPoint()** methoduna bizim kendi geliştirdiğimiz bir nesneyi veriyoruz. **AuthenticationEntryPoint** her ne kadar isminden biraz yanlış anlaşılsa'da aslında kullanıcıya; başarısız authentication denemesi sonrası dönen cevaptır diyebiliriz. Bu sınıfa baktığımızda 

```
    public class CustomAuthEntryPoint implements AuthenticationEntryPoint{
    	@Override
    	public void commence(HttpServletRequest request, HttpServletResponse response,
    			AuthenticationException authException) throws IOException, ServletException {
    		// TODO Auto-generated method stub
    		
    	}
    }
```

şeklinde **AuthenticationEntryPoint** interface'ini implement ettiğini görmekteyiz. Ve burdan gelen **commence** methodunu override etmektedir. İşte bu method'da biz **fail authenticationlarda ne yapılması gerektiğini kodlayabiliriz**. (Defaultta **401** koduyla **Unauthorized** cevabı dönmektedir, yani default'u da yeterlidir aslında!)


### URL BAZLI AUTHENTICATION VE AUTHORIZATION

Arkadaşlar biraz önceki kodda **.anyRequest().authenticated()** diyerek sisteme gelen tüm isteklerin login olmuş kullanıcı gerektirdiğini söylemiş oluyoruz. Ancak bazen burda özelleştirme yapmamız gerekebilmektedir. Mesela

- /hakkımızda sayfasına login olmasa bile kullanıcılar istek atabilsin(Mesela Hepsiburada anasayfasını login olmadan'da görebilmektesiniz.)
- /specialCampaigns şeklinde bir URL'e sadece login olan kullanıcılar gelebilsin
- /createNewCampaign gibi bir URL'e sadece login olmuş ve CREATE_CAMPAING adında bir yetkiye sahip kullanıcılar gelebilsin

İşte bu gibi senaryolarda spring security bize yine bunları tanımlama imkanı sunmaktadır.

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.antMatchers("/hakkımızda").permitAll()
		.antMatchers("/createNewCampaign").hasAuthority("CREATE_CAMPAING")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(new CustomAuthEntryPoint());

	}
```

Gördüğünüz gibi **permitAll()** diyerek **/hakkımızda** sayfasına herkesin gelebilmesini sağladık. 
**/createNewCampaign** sayfasını görebilmek için içinde kullanıcının login olmuş olması gerektiğini ve **CREATE_CAMPAING** adında bir yetkiye sahip olması gerektiğini söyledik. Bunun dışında kalan tüm istekler'de login olma şartı dışında herhangi bir şart yoktur.

Burada **antMatchers()** methoduna detayına girmeden ufak bir dokunalım. **antMatchers()** patternMatching dediğimiz işi yerine getiren dediğimiz bir methoddur. Pattern matching bilgisayar dünyasında çok kullanılan belli karakterlerle belli bir yapı(pattern) tanımlamamızı sağlayan yöntemin genel adıdır. Mesela bu methoda **/admin/** gibi bir pattern verirsek demek istediğimiz aslında /admin/ ile başlayan devamında herhangi birşey olabilen tüm **URL'ler** oluyor. Yani 
```
    /admin/createCampaign
    /admin/addUser
    /admin/deleteAccount 
```

gibi **URL'ler** bu şartı sağlamaktadır. Bizde böyle kümesel **URL'lere** topluca konfigürasyon yapmak istiyorsak bu şekilde pattern verebiliriz bu methoda.










