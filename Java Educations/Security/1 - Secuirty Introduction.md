# SECURITY

### NOT
Arkadaşlar security çok kapsamlı bir konudur. Web özelinde Certificate, HTTPS, CSRF, DDOS, Session fixation, SQL injection, XSS, Hashing, Signing vb... bir çok kavrama sahiptir diyebiliriz. Burda amacımız daha özet bir şekilde sistemem giren kişiyi tanıma ve yetkilerini ayarlama olacaktır.

Bir uygulama yaptığımız zaman bu uygulamanın nasıl kullanılacağından da sorumlu hale geliyoruz otomatik olarak. Bu durum uygulama çeşidine göre değişebilir.

- Mesela bir haber sitesi geliştirdiniz burada kimsenin giriş yapmasına aslında gerek yoktur, İsteyen girer istediği haberi okur ve çıkar.
- Mesela bir bankacılık uygulaması geliştirdiniz. Burada kullanıcılarınız olmalı bunlar giriş yapmalı ve sadece yetkileri içinde(kendi hesaplarına dair birşey yapmak mesela) birşeyler yapabilmelidir.
- Aynı bankacılık uygulamasına yönetici işlemleri için de birisi girebilir, bu kişi mesela birinin hesabını kapatabilir, yeni kampanyalar ekleyebilir. Burada ben normal bir kullanıcı olarak bunları yapamam ama o yapabilir.

İşte bu senaryolara bakınca geldiğimiz nokta, kullanıcıların bir şekilde giriş yapabildiği, kullanıcıların yetkilerinin farklı olduğu bir sistem oluşmuş oluyor. Burada uygulamayı geliştiren kişi

- Kullanıcıların giriş yapabileceği
- Kullanıcıların çıkış yapabileceği
- Kullanıcıların tanınabileceği
- Yetkilerinin takip edilebileceği

gibi konuları tüm yönleriyle sağlamak durumunda kalmaktadır. İşte burdan konu güvenliğe bağlanmaktadır.

Güvenlik temelde iki ana başlık üzerinde şekillenmektedir.

- Authentication; authentication temelde kullanıcıyı tanımamız diyebiliriz.(Who?) Mesela tarayıcıdan erişilen bir uygulamanızda form'dan login olarak bir web uygulamasını kendinizi tanıtırsınız diyebiliriz kabaca. Diğer isteklerde size gönderilen session key(bir anahtar) ile kendinizi tanıtırsınız. Arkadaşlar HTTP gibi mimarilerde(Stateless) iki istek arasında tanınabilmeniz için mutlaka her istekte kendinizi tanıtıcı bir bilgi olmalıdır.
- Authorization ise tanınan kullanıcının yapabileceği şeylerdir diyebiliriz.(Access rights, permissions, authorities vs..). Mesela bir kullanıcı sisteme haber ekleyebilir, yeni kullanıcı ekleyebilir, diğer bir kullanıcı sadece eklenen haberleri okuyabilir.

İşte temel olarak bunları gerçekleştirdiğimiz katmana güvenlik katmanı diyoruz.(Security), Uygulama geliştiricileri olarak bu konuları çözmemiz lazım.

Bu işlemleri yaparken yaygın olarak kullanılan mekanizmalar aşağıdaki gibidir;

- Form Login (Session Authentication)
- Basic Authentication
- JWT Authentication
- API KEY AUTHENTICATION
- OAuth
- Permission and Authority Management

Arkadaşlar biz zaten hayatımızda bunları yaygın olarak kullanıyoruz zaten herhangi bir sistem kullanırken. Adını belki koymuyorduk bugüne kadar.

### FORM LOGIN

Arkadaşlar tarayıcı ile eriştiğimiz herhangi bir websayfasında giriş yaparken form login'i yapmış oluyoruz aslında. Buradaki akış şu şekilde olmaktadır.

- Kullanıcı form aracılığı ile kullanıcı adı ve şifresini girer, sunucuya bir POST isteği gider.
- Sunucu isteği alır, bu bilgilerle user'ı tanımaya çalışır. Userları database'de tutuyor ise mesela database'e sorgu atar; eğer bu bilgilerle user varsa bu user'ın verileri database'den koda gelir.
- Sunucu bazı ekstra kontroller yapar(Hesap lock'lımı, password doğrumu vb..)
- Eğer bunlarda sıkıntı yoksa kullanıcıya(tarayıcıya) ileriki isteklerinde kullanması üzerine bir anahtar(session key)'i cevapta gönderilir(cookie aracılığıyla). Bu random öylesine bir anahtardır. Kendi içinde anlamı yoktur.
- Sunucu bu anahtarı gönderirken kendi içinde de bu anahtar şu kişiye aittir şeklinde bir veri alanı yaratır. Aslında kabaca buna da session(oturum) diyoruz.
- Oturumlar ve burada kullanılan anhtarlar belli bir süre geçerlidir(genelde 25-30 dakika)
- Daha sonra tarayıcıdan aynı adrese yapılan tüm isteklerde tarayıcı bu anahtarı istekte bir header olarak sunucuya gönderir. Sunucuda bu anahtar üstünden kullanıcıyı bulur ve kullanıcıyı tanımış olur.
- Hocam ne gerek vardı anahtara diyebilirsiniz; bu anahtar her istekte kullanıcı adı ve şifresinin gitmesinin önüne geçerek güvenliği artırır. Ve süreli oturum mimariside bu şekilde mümkün olur zaten. Yoksa her defasında kullanıcı adı şifre gönderirsem oturum diye bir kavram da olmayacaktır doğal olarak.
- Onemli ama aklınızı karıştırmaması gereken bir konu bazen oturum açmadanda yani kullanıcıyı tanımadan da session başlatılabilir(anahtar üretilebilir.) Bazı sistemlerde(mesela login olmadan sepete ekleme yaptığımız sistemler) bu şekilde gerçekleştirilebilmektedir.

Gördüğünüz gibi her istekte bu sayede kullanıcı tanınmış olur.

### BASIC AUTHENTICATION

Arkadaşlar basic authentication uzun süre webservislerde kullanılan bir authentication mekanizmasıdır ve kabaca şu şekilde çalışır.

- Kullanıcı kullanıcı adı ve şifresini **"kullanıcıadı:şifre"** şeklinde : ile birleştirir.
- Daha sonra **base64 encode** adını verdiğimiz yöntemle bu veri encode edilir. **base64 encode** çok basit bir encode yöntemidir, herhangi bir güvenlik anlamı yoktur.(Geri decode edilip veri okunabilir)
- Daha sonra kullanıcı yapacağı istekte **AUTHORIZATION** headerine **"AUTHORIZATION : Basic [base64 encoded value]"** değerini verir ve istek yapılır.
- Sunucu gelen istekteki **AUTHORIZATION** headerinin değerini okur. **base4 decode** işlemi yapar. Ve eline **kullanıcıadı:şifre** verisi olmuş olur. Buradan sonra zaten kullanıcı ve şifreye erişen sunucu adamı tanıyabilir ve isteğe cevap verir.
- Arkadaşlar dikkat burada login gibi bir mekanizma yoktur. Her istekte kullanıcı adı ve şifre tekrar tekrar gönderilmiş olur. **Session veya cookie olayı yoktur burada!!**
- Basic authentication; sunucunun destek verip vermemeye kendi karar vereceği birşeydir. Günümüzde çok güvenli bulunmadığından yavaş yavaş terk edilmektedir.

###JWT AUTHENTICATION

Arkadaşlar **JWT** authentication yukarıdakilere benzer ancak ufak noktalarda avantajlı bir authentication mekanizmasıdır. Öncelikle JWT nedir bir bakalım.

**JWT(Json Web Token)** bir anahtar(token) yapısıdır. Hatırlayın form loginde kullanıcıya **random bir anahtar**(session key) gönderiliyordu. Bu anahtarın kendi içinde bir anlamsal durumu yoktu. JWT'te aslında bu şekilde bir anahtar yapısı ancak kendi içinde belli bir yapıya ve anlama sahiptir. JWT

- Header
- Payload
- Signature

şeklinde üç parçadan oluşan **JSON** formatında bir token yapısıdır.

Bu parçalardan **header** genelde imzalama algoritmasına dair bilgi içerir. **İmzalama; kullanıcıya gönderdiğimiz anahtarın değiştirilmesinin önüne geçen ve sunucu tarafında validate edilmesini sağlayan bir yöntemdir.**

```
    {
      "alg": "HS256",
    }
```    
**Payload** kısmı genelde tokenı isteyen userla ilgili veya tokenla ilgili bazı bilgiler(**user_id, role, authorities, expire time, creation time vs...**) içerir.    
```
    {
      "user_id": "1",
      "role": "admin",
      "exp":1658591666
    }
```  

Burada tokeni kimin için üretti isem onun id'si ve rolünü tuttum gördüğünüz gibi. İstediğimiz başka bilgileride bu kısımda tutabiliriz herhangi bir kısıt yoktur. Bu arada yaygın bir davranış vardır. Userı unique olarak tanımamızı sağlayan değeri(mesela id) **sub:[id value]** şeklinde **sub** isminde bir **JSON** propertisi olarak belirtmek, görünce şaşırmayın lütfen. Sub subject'in kısaltmasıdır.

İmza kısmı ise aslında header ve payload kısmının belli bir algoritma ile(**encoding, HMAC**) işlenmiş halidir. **İmza sunucu için; token istekte kendine geldiğinde bu tokenin kendi tarafından gönderildiğini ve içindeki verinin değiştirilmediğini garanti eder**(Untampered). İçindeki veri değişebilse ciddi sıkıntı olmaz mıydı sizce de? Düşünsenize token'a girdim normal bir kullanıcı olmama rağmen role kısmını admin olarak değiştirdim!

Son olarakta bu kısımlar **header.payload.signature** şeklinde arada nokta olacak şekilde birleştirilir ve **base64 encode**(base64urlencode aslında, urldede kullanılabilir bir çıktı almak için) yapılarak kullanıcıya gönderilir. Örnek bir JWT tokeni şu şekildedir.

```
    eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXJhdCIsImV4cCI6MTY1ODU5Mjk5MSwiaWF0IjoxNjU4NTc0OTkxfQ.GmPpUHX_aWl_MRl3foak-MjVxrIuO57obI0_gZTJsDeVlDn4R93_IZExM9_AKQF7grVHBNb9T6a2oYE46Sy2XQ
```

Sunucu açısından akışa bakacak olursak;

- Sunucuya kullanıcı adı ve şifresi gelir. Sunucu kullanıcı adı ve şifreye bakar. Eğer valid bir kullanıcı ise JWT oluşturma adımlarına geçer.
- User'i belirten keyi payloada koyar. Bir imzalama algoritması belirler. Daha sonra bu iki veriden(header ve payload) imza oluşturur. Bunları birleştirir ve base64 encode yaparak kullanıcıya cevap döner.
- Önemli nokta session gibi sunucu tarafında birşey tutma ihtiyacı yoktur. Sunucu tokeni istemciye gönderir ve unutur.
- Kullanıcı bundan sonra yapacağı her istekte bu tokeni **AUTHORIZATION : Bearer [token değeri]** şeklinde **HTTP** headeri olarak sunucuya gönderir. 
- Sunucuyu requestten gelen authorization headerindan tokeni okur. **base64 decode** yapar. İmza kısmına bakar değişiklik olamdığını garanti etmek için. Daha sonra tokenin payload kısmından kullanıcıyı belirten anahtarı okur ve user'ı tanır.
- User'ı tanıdıktan sonra userın yapmak istediği işlem için süreci devam ettirir.
- Burada token içinde user'ın yetkileride gömülmüş olabilir, sunucu yapılan işleme dair kullanıcın etkisi olup olmadığına bakabilir bu veriyi kullanarak.
- Gördüğünüz gibi aslında JWT form login benzeri kullanıcı adı ve şifre alır bir anahtar döner. Ancak farkı anahtara dair sunucu tarafında hiçbir veri tutmamasıdır.(**no session!**) **JWT token** kendi içinde zaten gerekli tüm bilgilere sahiptir. Bir dahaki istekte userın kim olduğu **TOKEN'in içindeki veriden anlaşılacaktır**.
- JWT authentication'daki JWT bir formattır arkadaşlar, olayı sunucu tarafında anahtarı kime verdim ben meselesini sunucuya hiçbir yük yaşatmadan halletmesidir. Bu scaling(sistemin daha fazla kullanıcıya hizmet verebilmesini sağlama) gibi konularda sunucu tarafında çok büyük bir kolaylıktır aslında.
- Şart olmamakla birlikte **JWT'de genelde webservisler ile kullanılır** ve hatta günümüzde çok yaygındır.

### API KEY AUTHENTICATION

Arkadaşlar **API key authentication** genelde şu şekilde işlemektedir.
- Kullanıcı mesela browserdan sizin sisteme kayıt olur. 
- Daha sonra bu kullanıcı sizin sisteme giriş yapıp kendine özel bir anahtar(ramdon sayılar ve karakterler) üretir. Bunu baya tarayıcıdan bir UI aracılığıyla yapar. (Farklı yöntemler var, kağıda yazıp getireni bile gördü bu gözler, hatta bende yaptım benzer birşey zamanında!) 
- Kullanıcı bu anahtarı alır bir yere kaydeder. 
- Daha sonra anahtarı aldığı bu sisteme **AUTHORIZATION : {Basic Or Bearer} [Aldığı anahtar]** şeklinde istek atabilir. 
- Basic auth'a benzemektedir ancak burada sürekli kullanıcı adı ve şifre gitmez, bunun yerine bir anahtar gönderilir. 
- Bu anahtarlar genelde çok uzun süre geçerli olacak şekildedir.
- Sizin **collectapi**'de yaptığınız şey işte tam olarak budur.
- Bu sayede collectapi'de belli servislere sadece api keyi olan kullanıcılar istek atabilir. Hatta orda hangi servislere subscribe olduğunuzda collectapi tarafında tutulmaktadır. Bu sayede authorization'da yapılmış olur.

### OAUTH

Oauth biraz daha third party auth dediğimiz başka bir sistemi kullanarak bir yere giriş yapabilmeyi sağlamaktadır.(Tam olarak bunla alaklı değildir, kullanım alanı olarak bu şekilde daha yaygındır) Mesela **Login With Facebook** benzeri gördüğünüz şeyler bu kapsama girmektedir. Buranın çalışması biraz karışık çok detayına girmiyorum.


### HTTP STATUS CODES

Arkadaşlar HTTP status kodlarını burada da düzgünce kullanmak önemlidir. Mesela kullanıcı login olmadan bir yere istek atıyor ise çoğu zaman kullanıcıyı login sayfasına yönlendiriz veya webservis isteği ise **401** hata kodunu döneriz. Kullanıcının yetkisi yoksa genelde ya başka bir sayfaya yönlendirilir yada **403** hata kodu döner.





