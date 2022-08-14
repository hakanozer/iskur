# JWT AUTHENTICATION
**JWT authentication** kullanıcıya verilen anahtarın belli bir formatta üretildiği, sunucu tarafında anahtara dair hiçbir şey tutmaya gerek olmayan, günümüzde yaygın olarak kullanılan bir authentication mekanizmasıdır. Buradaki **JWT(Json Web Token)** ismi üretilen anahtarın(token)'ın yapısının **JWT** olduğunu göstermektedir. Yoksa **Basic Authentication** veya gidip gelen **session_id** ile farkı yoktur diyebiliriz. En önemli özellikleri

- Token'in belli bir yapıda olması ve içinde anlamlı veri içermesi
- İstemci tarafında değiştirilememesi (İmza mekanizması)
- Sunucu tarafında anahtara dair ben bu anahtarı şuna verdim şeklinde bir kayıt mekanizmasına ihtiyaç duymaması, tokenin kime ait olduğu, kullancının yetkileri, tokenin ne zaman expire olacağı gibi tüm bilgiler tokenin kendi içinde saklanabilmektedir. (Mesela biz userları ayırt edebilmek için username'i token içine gömeceğiz, diğer isteklerde tokendan username'i alıp kullanıcıyı tanıyacağız.)

Spring security **token auth mekanizmasına default olarak destek vermememektedir**. Burada tüm akışı bizim geliştirmemiz gerekmektedir. Yani

- Token oluşturma, validate etme, imzalama işleri
- Kullanıcın kullanıcı adı ve şifresi ile login olabileceği bir action
- Kullanıcı her istek yaptığında JWT tokeni kontrol etmek için Filter sınıfı

gibi geliştirmelerin tamamından biz sorumluyuz.

İlk önce token oluşturma, validate etme, imzalama gibi işlemleri yapabilmek için 

```
    <dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt</artifactId>
		<version>0.9.1</version>
	</dependency>
```	

kütüphanesini **pom.xml'e** ekliyoruz. Ve bunu kullanabilmek için

```
    @Component
    public class JWTTokenUtil implements Serializable {
    	private static final long serialVersionUID = -2550185165626007488L;
    	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    	private String secret = "Abdullah";
    	public String getUsernameFromToken(String token) {
    		return getClaimFromToken(token, Claims::getSubject);
    	}
    	public Date getExpirationDateFromToken(String token) {
    		return getClaimFromToken(token, Claims::getExpiration);
    	}
    	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    		final Claims claims = getAllClaimsFromToken(token);
    		return claimsResolver.apply(claims);
    	}
    	private Claims getAllClaimsFromToken(String token) {
    		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    	}
    	private Boolean isTokenExpired(String token) {
    		final Date expiration = getExpirationDateFromToken(token);
    		return expiration.before(new Date());
    	}
    	public String generateToken(UserDetails userDetails) {
    		Map<String, Object> claims = new HashMap<>();
    		return doGenerateToken(claims, userDetails.getUsername());
    	}
    	private String doGenerateToken(Map<String, Object> claims, String subject) {
    
    		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
    				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
    				.signWith(SignatureAlgorithm.HS512, secret).compact();
    	}
    	public Boolean validateToken(String token, UserDetails userDetails) {
    		final String username = getUsernameFromToken(token);
    		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    	}
    }
```

şeklinde bir sınıf geliştiriyoruz. Bu sınıfı **@Autowire** edebilmek içinde **@Component** annotationu ile işaretliyoruz. Tokena dair tüm işlemleri bu sınıf üzerinden yapacağız. Burada **private String secret = "Abdullah";** şeklinde bir property görmektesiniz. Bu tokeni imzalama vs.. gibi işlemlerde kullanılan gizli değerdir. Bunun kodda olması yanlıştır aslında properties dosyasına alınması daha doğrudur.

**public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60** ise tokenin geçerlilik süresidir.(saniye cinsinden)

Bu adımdan sonra her istekte **JWT** token'i kontrol edecek **filter** sınıfımızı geliştiriyoruz.

```
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private DatabaseUserDetailsService userService;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
			} catch (ExpiredJwtException e) {
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		if (username != null) {
			UserDetails userDetails = this.userService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
```

Burada gördüğünüz gibi token alınıp decode ediliyor. Daha sonra **JwtTokenUtil** sınıfı ile **JWT** token'dan **username** bilgisini alınıyor. Ve daha sonra **userDetails** servis ile(bizim yazdığımız JDBC ile çalışan) user'ın detayı getiriliyor. Ve bulunan usera kodun farklı yerlerinden erişebilmek adına user global olarak bir yere set ediliyor.

Bu adımdan sonra bir **Configuration** sınıfı ile artık spring'e **JWT** için yaptığım konfigürasyonları kullan diyebilmekteyim.

```
    @Configuration
    @EnableWebSecurity
    public class JWTSecurityConfig extends WebSecurityConfigurerAdapter{
    	@Autowired
    	private JwtRequestFilter jwtRequestFilter;	
    	@Override
    	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
    		.userDetailsService(new JdbUserDetailsService())
    		.passwordEncoder(passwordEncoder());
    	}
    	@Bean
        public PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }
    	@Bean
    	@Override
    	public AuthenticationManager authenticationManagerBean() throws Exception {
    		return super.authenticationManagerBean();
    	}
    	@Override
    	protected void configure(HttpSecurity httpSecurity) throws Exception {
    		httpSecurity
    		.authorizeRequests()
    		...
    		...
    		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    	}
    }
```
Burada **userDetailServis** bizim **JDBC** servisimiz olarak ayarlanıyor. Daha sonra yazdığımız filterı spring'e her istekte çalıştırması üzerine filter olarak ekliyoruz.

Ve son olarakta kullanıcının login olabileceği bir action geliştiriyoruz. (**LoginRequest** sınıfını yazmıyorum artık oralarda master durumdasınız)

```
@RestController
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	@Autowired
	private JdbUserDetailsService userDetailsService;
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Bad credentials");
		} catch (DisabledException e) {
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(request.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponse resp = new LoginResponse();
		resp.setStatus("success");
		resp.setToken(token);
        return ResponseEntity.ok().body(resp);
	}
}
```

Burada **AuthenticationManager** ile kullanıcı authentication akışından geçiriliyor(user'ı getir, passwordu kontrol et gibisinden). Daha sonra sıkıntı yok ise kullanıcı detayı **user servisten alınıp** o kullanıcıya özel token üretiliyor ve kullanıcıya cevap olarak dönüyor. 

Bu aşamadan sonra kullanıcı bir istek yaparken request'e **AUTHORIZATION : Bearer [aldığı token]** şeklinde authorization headerine'da eklerse kullanıcı tanıma işlemleri başarılı bir şekilde yapılabilmektedir.

Son olarak **/login** isteğine herkesin token'sız yani authenticate olmadan gelebilmesi için(**zaten authenticate olmak için buraya geliyorum burda authenticate kontrolu yapmamalıdır spring**) security configurationunda şu şekilde
```
    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		...
		...
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
```    	

**permitAll()**'ı ekliyoruz **/login** URL'i için.

### NOT

Arkadaşlar kodun herhangi bir yerinde o an işlemi yapan; yani authenticate olmuş user bilgisine ihtiyacımız olabilir. Mesela kullanıcının hesaplarını getirmek için kullanıcının id'sine ihtiyacımız vardır. Bu gibi durumlarda istediğiniz herhangi bir yerde(Tüm authentication typeları içni aynıdır bu)

```
    User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
```
şeklinde çağırım yaparak **org.springframework.security.core.userdetails.User** sınıfı tipinden authenticated userınızı alabilirsiniz.




