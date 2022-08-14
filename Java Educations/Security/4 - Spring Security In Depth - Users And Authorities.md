# SPRING SECURITY AUTHORITIES AND USERS

Arkadaşlar bu ana kadar springin bize sağladığı default userı kullandık. Ancak gerçek bir sistemde userlar başka bir katmandan(database mesela) alınarak login işlemi yapılmalıdır. Şimdi önce bu katmana geçmeden spring'in içinde gelen inMemoryAuthentication konusuna bir ufak bakalım.

**inMemoryAuthentication** güvenlik konseptini biraz daha anlayabilmek için memory'de tutulmak üzere bizim açıkca bir kaç user yarattığımız yöntemdir. **Configuration** sınıfımızda imzası **protected void configure(AuthenticationManagerBuilder auth)** bu şekilde olan **configure()** methodunu kullanarak

```
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("abdullah").password("123456").authorities("CREATE_CAMPAIGN")
		.and()
		.withUser("murat").password("12345").authorities("DELETE_CAMPAIGN", "READ_CAMPAIGN");
		
	}
```	
şeklinde iki adet user yaratıyorum. Bunların kullanıcı adı ve şifrelerini belirliyorum. Ve bunlara bazı yetkiler tanımlıyorum.

Arkadaşlar yetki dediğimiz şey kabaca basit bir string'dir. CREATE_ACCOUNT, DELETE_ACCOUNT vb.. şeklinde. Burada bir kullanıcıya bu yetkiyi verebiliriz. Daha sonra security konfigürasyonunda url bazlı bir şekilde buraya sadece şu yetkisi olanlar istek atsın diyebiliriz. Gerçek bir uygulama çok kapsamlı bir şekilde geliştirilmektedir bu kısımlar. (Role-Based, Group-Based, Permissions vb...)

Spring için sisteme giriş yapıp sistemi kullanabilecek User demek

- Kullanıcı adı
- Passwordu
- Yetkileri

tanımlanmış user demektir.

Burada artık default olarak verilen user yoktur. Sistemin userları; eklediğimiz bu userlardır. Ve bunlar spring çalıştığı sürece memory'de tutulmaktadır.

Burada bir de **configuration** sınıfımıza

```
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
```

bunu geçici bir süreliğine eklemenizi istiyorum. Artık bu aşamada login sayfasından veya basic authentication ile bu userların bilgileriyle giriş yapılabilecektir.

Hatırlarsanız **URL** bazlı konfigürasyon yaparken 

```
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http
    		.authorizeHttpRequests()
    		.antMatchers("/hakkımızda").permitAll()
    		.antMatchers("/createNewCampaign").hasAuthority("CREATE_CAMPAING")
    		.anyRequest()
    		.authenticated()
    		...
    		...
            
       }
	}
```	

şeklinde .antMatchers("/createNewCampaign/**").hasAuthority("CREATE_CAMPAIGN") diyerek belirttiğimiz URL'e sadece yetkisi olan kullanıcıların istek atabilmesini sağlıyorduk. Yani buraya sadece abdullah userımız istek atabilir yukarıdaki konfigürasyona göre. Buna **Authorization** diyoruz işte. Haklara dair stringler tanımlayıp userlara bunları vermek veya vermemek.

Burada yetkisi olmayan bir kişi buraya istek attığında **403 Forbidden** şeklinde cevap dönecektir.

### CUSTOM USER SERVICE

Gerçek bir sistemde userlar **persistent(kalıcı)** olarak saklandığı bir yerden çekilmektedir.(**database, file, webservice vb...**) Bu noktada bizde spring security'e kullanıcı login olmaya çalıştıgında userları git şurdan çek şeklinde bir bilgi verebilmekteyiz. Biz gelin bir **MYSQL database'inden** **JDBC** aracılığıyla user'ı getirebilen bir yapı hazırlayalım.

Bunun için öncelikle database'imizde bir users tablosu oluşturuyoruz.

```
    {
        id int(11) AUTO_INREMENT,
        username varchar(255),
        password varchar(20),
        isEnabled boolean,
        PRIMARY KEY(id)
    }
```

Daha sonra buraya
```
    ("abdullah", "123456", true)
    ("murat", "12345", true)
```
şeklinde iki kayıt ekleyelim.

Daha sonra spring'de bulunan UserDetailsService interface'ini implement eden bir sınıf yazıyoruz.

```
    public class DatabaseUserDetailsService implements UserDetailsService{
    	@Override
    	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		// TODO Auto-generated method stub
    		return null;
    	}
    }
```

Bu sınıftaki **loadUserByUsername()** methodu ile username'e göre user'ın detayını getirme işinin kodunu yazıyoruz. Mesela biz **JDBC** kullanarak bu işi yapıyor olalım burada methodumuz aşağıdaki gibi olacaktır.

```
   public class DatabaseUserDetailsService implements UserDetailsService{
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "john", "doe");
    			Statement st = c.createStatement();
    			String query = "SELECT * FROM users WHERE username = \"" + username + "\"";
    			ResultSet rs = st.executeQuery(query);
    			if(rs.next()) {
    				String userName = rs.getString("username");
    				String password = rs.getString("password");
    				boolean isEnabled = rs.getBoolean("isEnabled");
    			}else {
    				throw new UsernameNotFoundException(username + " Not Found");
    			}
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return null;
    	}
}
```

Burada dikkat ederseniz bu method **UserDetails** tipinde bir return değerine sahiptir. Bu spring'in sistemi kullanabilecek userlara dair tanımladığı bir interface'dir ve bizde user'ı database'den aldıktan sonra bu interface'i yerine getiren bir nesne dönmeliyiz. Bu interface'in içine baktığımızda

```
    public interface UserDetails extends Serializable {
    	Collection<? extends GrantedAuthority> getAuthorities();
    	String getPassword();
    	String getUsername();
    	boolean isAccountNonExpired();
    	boolean isAccountNonLocked();
    	boolean isCredentialsNonExpired();
    	boolean isEnabled();
	}
}
```

şeklinde 7 tane methoda sahip olduğunu görüyoruz. Burdaki methodları eğer lazımsa sistemimizde kullanıyoruz. Mesela bizde account locklama diye birşey yoksa **isAccountNonLocked()** methoduna direk return false diyerek devam edebiliriz.

Burada bu interface'i implement eden custom sınıfta yazabiliriz veya zaten Spring kendi içinde bunu implement denen **org.springframework.security.core.userdetails.User** adında bir sınıfla gelmektedir; bunu da kullanabilmekteyiz.

Yani bizde db'den aldığımız verilerle bu userdan yaratıp return edebiliriz aşağıdaki gibi.
```
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "dbUsername", "dbUserPassword");
			Statement st = c.createStatement();
			String query = "SELECT * FROm users WHERE username = \"" + username + "\"";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				String userName = rs.getString("username");
				String password = rs.getString("password");
				boolean isEnabled = rs.getBoolean("isEnabled");
			    return User
						.builder()
						.username(userName)
						.password(password)
						.disabled(!isEnabled)
						.accountExpired(false)
						.accountLocked(false)
						.credentialsExpired(false)
						.build();
			}else {
				throw new UsernameNotFoundException(username + " Not Found");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
```	

Burada son olarak authorities meselesi vardır arkadaşlar user'ı getirme katmanını db'ye aldığımızda. **Spring için sisteme giriş yapabilecek ve sistemi kullanacak userlar boşta olsa bir authorities collectionuna sahip olmalıdır.** Bizde bir şekilde artık authoritiesleride database'e alıp user bulma aşamasında bunlarıda getirmeli ve user'a eklemeliyiz. Burada iki yöntem olabilir;

- Ayrı bir tabloda authoritiesleri tutabiliriz, doğru yöntem budur.
- Veya user tablosuna authorities diye bir kolon ekleyip orada virgüllerle ayrılmış şekilde authorities'ler tutulabiliriz. (Kolay yöntem budur, ciddi bir uygulamada bunu tercih etmememiz lazımdır.)

İkinci yöntem için users tablosuna authorities diye bir kolon ekleriz ve yetkileri virgüllerle ayrılmış şekilde burada tutarız.

Bizde kendi yazdığımız custom user servisinde bu kolondaki authoritiesleri virgül ile parçalayıp User nesnesine ekleriz.

```
    public class DatabaseUserDetailsService implements UserDetailsService{
    	@Override
    	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "dbUsername", "dbUserPassword");
    			Statement st = c.createStatement();
    			String query = "SELECT * FROm users WHERE username = \"" + username + "\"";
    			ResultSet rs = st.executeQuery(query);
    			if(rs.next()) {
    				String userName = rs.getString("username");
    				String password = rs.getString("password");
    				boolean isEnabled = rs.getBoolean("isEnabled");
    				// mesela READ_CAMPAING,DELETE_CAMPAING geldi
    				String authorities = rs.getString("authorities");
    				// virgüle göre parçaladık elimizde [READ_CAMPAING, DELETE_CAMPAING] vardır.
    				String[] auths = authorities.split(",");
    				// daha sonra bunları spring authorityler için tanımladığı bir sınıftan nesne yaratarak listeye ekliyoruz
    				List<GrantedAuthority> grantedAuhorities = new ArrayList<GrantedAuthority>();
    				for(String authority : auths) {
    					grantedAuhorities.add(new SimpleGrantedAuthority(authority));
    				}
    				//ve burada .authorities(grantedAuhorities) diyerek user'a ekliyoruz
    				return User
    						.builder()
    						.username(userName)
    						.password(password)
    						.disabled(!isEnabled)
    						.accountExpired(false)
    						.accountLocked(false)
    						.credentialsExpired(false)
    						.authorities(grantedAuhorities)
    						.build();
    			}else {
    				throw new UsernameNotFoundException(username + " Not Found");
    			}
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return null;
    	}
    }
```

Ve son olarak **protected void configure(AuthenticationManagerBuilder auth)** methodu ile spring'e bizim user servisimizi kullanması gerektiğini söylüyoruz.

```
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new DatabaseUserDetailsService());
	}
```	

Artık bu noktadan sonra user ve authoritiesler database'den çekilecektir.

### NOT
Arkadaşlar spring security kendi içinde çok fazla sınıfa ve mekanizmaya sahiptir. Tüm bu işlemler yapılırken altyapıda

- AuthenticationManager
- UserProvider
- UserService
- Filters

gibi birçok mekanizma çalışmaktadır. Aklınızda hocam **username'e göre database'e istek attık bunun password kontrolü nerde yapılıyor** gibi bir soru geliyor ise kabaca **UserProvider veya AuthenticationManager** database'de gelen user'ın passwordu ile istekte gelen passwordu karşılaştırma işini yerine getirmektedir.










