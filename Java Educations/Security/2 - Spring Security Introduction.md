## SPRING SECURITY INTRODUCTION

Arkadaşlar bir önceki döküman'da bahsettiğimiz güvenlik işlemleri için backend kodlarımız gerekli önlemleri almalıdır. Bunun için kendimizde kod yazabiliriz bu da bir yöntem ve çok geliştirici olabilir; ancak önce halihazırda bu işi yapabilen kütüphanelere bakmak fayda sağlayacaktır.

Spring tabanlı uygulamarımızda güvenlik işlerini çok güzel şekilde halleden bir kütüphanemiz vardır : **Spring security**.

Bu kütüphane default olarak gelen birçok özellik ve özelleştirebilme yeteneklerine sahiptir.

Temel olarak backend tarafında lazım olan bazı güvenlik gereksinimlere bakacak olursak

- Session yaratma
- Session anahtarını cookie olarak istemciye gönderme
- Daha sonra gelen session anahtarına göre kullanıcıyı tanıyabilme
- Eğer kullanıcı oturum açmadan oturum gereken bir yere istek attı ise kullanıcıya uygun cevabı dönmek
- Sessionların süresini belirleme
- Bazı saldırıları önlem alma(session fixation, CSRF)
- Authorization headeri ile authentication işlemi gerçekleştirme
- Kullanıcıların yetkisini yönetebilme
- Login olmaya çalışan kullanıcıların verilerini nerden çekeceğimize dair(In Memory, Database veya Bir API) yöntemler
- URL bazlı authentication ve authorization yapma imkanı(Yani mesela /admin isteğini sadece admin rolüne sahip kullanıcılar yapabilsin benzeri)
- Ve birçok yetenek

Spring bootta spring security'i kullanabilmek için pom.xml'imize

```
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
```

şeklinde spring security bağımlılığını eklemek yetmektedir.	Bu kütüphane default olarak **basic authentication** ve **form login'i** nerdeyse sıfır konfigürasyon ile çalıştırabilmemizi sağlamaktadır.

Şimdi kodumuzu bir controller ve action ekleyelim.

```

    @RestController
    public class HelloController {
    	
    	@GetMapping("/hello")
    	public String hello() {
    		return "Hello World";
    	}
    }
```    
    
Başka hiçbir ekleme yapmadan uygulamamızı başlatıp tarayıcı ile buraya **/hello** şeklinde istek attığımızda spring'in bizi **/login** şeklinde bir URL'e redirect ettiğini göreceğiz. Yani artık spring **login yapmadan(kullancıyı tanımadan)** herhangi bir URL'e istek atılmasına izin vermemektedir ve bizi login sayfasına yönlendirmektedir. İşte security!

Aynı isteği **POSTMAN** gibi bir tooldan yaptığınızda da size **401 Unauthorized** cevabı döndüğünü göreceksiniz. Bu cevapta basic authentication'dan gelmektedir. Yani basic authentication'da default olarak hazır halde gelmektedir.

Peki burda ne dönmektedir derseniz;

Hatırlarsanız java'da web'in temelinde **servlet'ler** vardı arkadaşlar. **Spring MVC** dediğimiz yapı bile temelde gelen tüm isteklerin yönlendirildiği **DispatcherServlet** adındaki servletle çalışan bir mimariydi.

Ve servlet dünyasında **filter** adını verdiğimiz; gelen isteklerin doğru handler'a(**servlet veya controller-action**) ulaşmadan önce veya kodumuz işledikten sonra kullanıcıya cevap gönderilmeden hemen önce çalıştırabileceğimiz bir mekanizma mevcuttu.

```
@WebFilter(urlPatterns = "/customer/*")
public class LogFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Log request for future use
		
		chain.doFilter();
	}
}
```

Bu şekilde **javax.servlet.Filter** interface'ini implement eden bir sınıf yazıp **@WebFilter** annotationu ile bu sınıfı bir filter olarak java'ya(web container'e aslında mesela tomcat)'e tanıtmış oluyorduk. Burada **@WebFilter'da** ***urlPatterns** keyi ile bu filterın hangi URL'lere gelen isteklerde çalışacağını belirtebiliyoruz. Biz burda **/customer** ile başlayan, devamı herhangi birşey olan tüm isteklerde bu filterın çalışması gerektiğini söyledik. 
Yani mesela customer/detail, customer/list vb.. gibi URL'Lere istek gelince bu filter çalışacaktır.

Arkadaşlar **spring security kütüphanesi gelen istekleri authentice etmek, session başlatmak, basic auth'u yerine getirmek için ve aslında hemen hemen tüm güvenlik işlemleri için filterları kullanmaktadır**. Spring security içinde güvenlik işlemleri için aşağıdaki filterlar bulunmaktadır.

- BasicUserApprovalFilter
- SecurityContextPersistenceFilter
- LogoutFilter
- UsernamePasswordAuthenticationFilter
- BasicAuthenticationFilter
- RequestCacheAwareFilter
- SecurityContextHolderAwareRequestFilter
- RememberMeAuthenticationFilter
- ForgotPasswordAuthenticationFilter
- AnonymousAuthenticationFilter
- SessionManagementFilter
- ExceptionTranslationFilter
- OAuth2ExceptionHandlerFilter
- VerificationCodeFilter
- OAuth2AuthorizationFilter
- OAuth2ProtectedResourceFilter
- FilterSecurityInterceptor

Mesela **UsernamePasswordAuthenticationFilter** **/login'e** gelen login isteklerinde kullanıcı adı ve şifreyi alıp kullanıcıyı login etmeye çalışan filterdır ve sadece **/login** URL'inde çalışır.

**BasicAuthenticationFilter** gelen istekten **Authorization** headerini okuyup burdaki bilgiler ile kullanıcıyı authenticate etmeye çalışan filter'dır. Koduna bakınca sizde anlayabilirsiniz aslında yaptığı şeyi! Mesela bu filterın gelen **AUTHORIZATION** headerini okuma kısmının kodu şu şekildedir.

```
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null) {
			return null;
		}
		header = header.trim();
		if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
			return null;
		}
		if (header.equalsIgnoreCase(AUTHENTICATION_SCHEME_BASIC)) {
			throw new BadCredentialsException("Empty basic authentication token");
		}
		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		byte[] decoded = decode(base64Token);
		String token = new String(decoded, getCredentialsCharset(request));
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken
				.unauthenticated(token.substring(0, delim), token.substring(delim + 1));
		result.setDetails(this.authenticationDetailsSource.buildDetails(request));
		return result;
	}
```

Gördüğünüz gibi özetle **AUTHORIZATION** headerini oku headerin okunan değerinde **BASIC** kısmını sil, kalan kısmı **base64 decode** yap. Eldeki veriyi **:** karakterine göre parçala ve kullanıcı adı ve şifreyi bul. Yani bir öndeki dökümanda anlattığımız logic!

Arkadaşlar sihir yok yazılmış kod var!





