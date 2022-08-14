# KAFKA IN DEPTH

Evet arkadaşlar şimdi biraz daha derin konulardan bahsedeceğiz. **Biz üstünkörü mühendisler değil, detaya hakim, gerektiğinde detaylara müdahale edebilecek mühendisler olacağız!!**

Lütfen kafanızda senaryoları düzgünce oturtmak için gerekirse fazladan çaba sarfedin!

# PARTITION

Arkadaşlar kafka topicler var dedik. Producerlar buraya mesaj atabilir. Consumerlarda burdan okuyabilir dedik. Burada çok ciddi mesaj biriken bir sistem düşünün(bankacılık sistemi buna güzel bir örnektir.) Bunları tek bir consumerlar işlemeye çalışmak belki işin aylarca sürmesine sebep olabilir. Bunu kimse istemiyor. Bir şekilde mesajları paralelde birden fazla consumer ile işlememiz gerekmektedir arkadaşlar. 

Hocam tamam ne var bir tane daha consumer çalıştıralım ayrı bir process olarak; kafka consumerlara paralelde göndersin mesajları dediğinizi duyar gibiyim. Evet bu dediğiniz mesela **RabbitMQ** için doğru ancak Kafka bu işi farklı şekilde yapmaktadır arkadaşlar. Kafka aynı gruptaki iki consumerın aynı topic'ten mesaj okumasını izin vermemektedir. (Partition yoksa veya consumerlar farklı grupta değilse, buraya geleceğiz)

Kafka topic yaratma aşamasında bizden 
```
    NewTopic newTopic = new NewTopic("bootcamp", 2, (short)1);
```

ikinci parametre olarak partition bekliyordu bundan bahsetmiştik.

Topic mesajları tutarken kendi içinde tek bir parçada değil, birden fazla ayrı parçada(array gibi düşünün bazı mesajlar bir arrayde, diğerleri başka arrayde) tutabilmektedir. İşte bu parçalara partition demekteyiz arkadaşlar. **Topic yaratma aşamasında ikinci parametreye 2 gibi değer verirsek bu topice gelen mesajların iki ayrı parçada tutulacağı anlamına gelmektedir.**

İşte partition kafkanın mesajların paralelde işlenebilmesi için temel yöntemidir. Artık iki consumer yaratabiliriz. Bunların ikiside aynı topice subscribe olabilir. Kafka bunları subscribe aşamasında birer partitiona atayacaktır. Ve bu sayede paralel şekilde okuma yapılabilecektir. Burada ihtiyacımıza göre partitionu artırabiliriz. Consumerıda bu sayıya uygun artırırsak sistemimizin performansı ciddi anlamda hızlanır.

* Topic
* Partition 1 -> Consumer 1
* Partition 2 -> Consumer 2

**Kafka da topicler default olarak 1 partitiona sahiptir.**

NOT: Arkadaşlar partitionun mesajları sıralı işlenmesini iptal ettiğini görüyorsunuz.**Normalde topicin tek bir partitionu varsa mesajlar bir consumer tarafından sıralı bir şekilde işlenmekte idi**. Yani sıra korunuyordu. Birden fazla partition oldugu durumda mesajlar partitionlar arasında paylaşılmakta ve consumerlar diğer consumera bağlı olmadan mesajları işlemektedir. Burada genel sıralı çalışma mantığının kaybolduğunu görebilirsiniz. Tekil partition içinde hala sıralı evet ama tüm mesajlar genelinde sıralı çalışmayı kaybettik.
**Bu noktada eğer mesajların kesinlikle sıralı işlenmesi gerekiyor ise dikkatli olmanız gerekmektedir.**

Birden fazla partitionu olan topic'lerde producer direk olarak bir partitiona mesaj gönderebilir.(Çokta tavsiye etmiyoruz bunu).
```
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("courses", 1, null, "Odevleri kontrol et");  
```
Burara gördüğünüz ikinci parametre partition id'dir. Partition id'leri arrayler gibi 0'dan başlayarak artan şekilde otomatik üretilmektedir. Yani iki partition varsa id'leri 0 ve 1'dir.

Şimdi şöyle bir senaryoya bakalım birlikte; iki partition var tek consumer var
* Topic
* partition-1
* partition-2
* Consumer-1

Bakın **bir partitiondaki mesajları aynı gruptaki birden fazla consumer işlemeyez**. Bir consumer birden fazla partitiondaki mesajı işleyebilir. Yani burada kafka consumera hem partition-1 hem partition-2 deki mesajları gönderecektir. **Sisteme yeni bir tane consumer gelince kafka hemen birinci partitionu consumer1'e ikinci partitionu consumer2'ye atayacaktır.**

** Peki üçüncü bir consumer gelince ne olacak? Boşta partition yok, e bir partitiona aynı gruptaki birden fazla consumer bakamaz demiştik, boşta kalacak arkadaşlar üçünü gelen hiçbir mesaj almayacak! **

# MESSAGE KEY

Arkadaşlar
```
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("bootcamp", 1, null, "Odevleri kontrol et");  
```
Burada gördüğümüz gibi bir mesajı gönderirken üçüncü parametrede null diye birşey gönderdik; bu parametre mesaj keyi için vardır. Yani biz mesaja bir key'de verebilmekteyiz. Peki neden? Bunu bir senaryo ile açıklamaya çalışalım.

Banka sistemi yapıyorsunuz mesela, eft ve havale işleri için kafka kullanmaya karar verdiniz. Bir tane para_transfer adında tek bir partitionu olan topic oluşturdunuz ve bir consumer burdaki mesajları işlemeye başladı.

Daha sonra tek consumerın yetmediğini düşünüp topic'in partition sayısını iki ye çıkarıp bir tane daha consumer ekleyip mesaj işleme hızını ~2 katına çıkardınız. 

```
    partition-1 - [eft|havale|eft|eft] - Consumer 1
    partition-1 - [havale|havale|eft|eft] - Consumer 2
```

Kafka mesajları partitionlara kendi belirlediği bir sisteme göre dağıtmaktadır. (partitionlardaki mesaj sayısını eşit tutmaya çalışmaktadır denebilir kabaca.)

Bu noktada şöyle bir şey düşündünüz, consumerlarımın ikiside iki işide yapmak zorunda olmasın. Birisi sadece havale işleri ile uğraşsın, birisi ise eft işleri ile uğraşsın. İşte bu noktada sanki partitionlara giden mesajları eft ve havale olmasına göre ayarlarsam; oraya bakan consumer sadece ya havale ya eft işi yapacaktır. İşte bu noktada bizim eft'lerin bir partitiona, havalelerin bir partitiona gitmesini sağlamamız gerekmektedir.

İşte bu noktada message keyini kullanabilmekteyiz. Mesajı produce ederken ona uygun bir key verip, kafkanın keye göre bunları partitionlara göndermesi sağlanabilmektedir.

```
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("bootcamp", null, "eft", "musteri:1 musteri:2 miktar:100"); 
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("bootcamp", null, "havale", "musteri:4 musteri:2 miktar:100");
```

Artık partition numarası vererek direk bir partitiona değil, key vererek keye göre uygun partitiona gönderilmesini sağlamaktayız. Burada kafka kesin olmamakla birlikte mesajları keylere göre partitionlara dağıtacaktır.

Hocam direk partitiona göndermekle aynı şey değil mi bu derseniz evet benzer, ama bu da bir yöntem bazen bunu da kullanmamız gerekebilmektedir. 

**NOT : Burada detayda farklı konular var, bazen farklı keye sahip mesajlar aynı partitiona gidebilir. Mesela üç tane keyim varsa iki partition varsa ne olacak, e kesinlikle mi, minimum iki key aynı partitiona gidecektir. Burda garanti olan şey aslında key aynı ise bu mesajların her zaman aynı partitionda olmasının garanti edilmesidir.**

Hatırlayın consumerlarda gelen mesajın keyini okuyabilmekte idi.
```
    System.out.println("Key : " + record.key());
```

# CONSUMER OFFSET

Peki arkadaşlar mesajların işlenildiğini takibi nasıl yapılmaktadır dersek? Sonuçta amaç tüm mesajların işlenmesidir, bir consumer bir mesajı işlerken hata yaşanırsa nasıl bir süreç işlemektedir?

Arkadaşlar mesajların başarılı işlenip işlenmediğinin kafkaya bildirilmesi consumerın görevidir. Bir consumer bir mesajı başarılı olarak işlediğinde kafkaya ben bunu başarılı şekilde işledim benim mesajlarımda kaldığım yeri güncelle şeklinde bir bilgiyi kafkaya göndermektedir. Şöyle gösterecek olursak

```
    partition - 1 [eft|havale|eft|eft|eft|havele] - Consumer 1
```

Partitionu bir array gibi düşünebiliriz. Mesajlar 0, 1, 2 gibi adreslere sahiptir(buna kafka **offset** demektedir). Bir consumer mesajları partitiondan sıralı bir şekilde alır. **Mesela 1.ci mesajı aldı diyelim. Bunu başarılı işlerse kafkaya ben bunu işledim benim kaldığım mesaj offsetini 2 olarak güncelle diye bilgi verir.** Bundan sonra consumer bir daha mesaj istediğinde 2'den itibaren mesajlar gelecektir bu consumer'a. Bu şekilde mesajların tekrar tekrar işlenmesinin önüne geçilmektedir.

Hatırlarsanız consumer yaratırken 
```
    properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
```
şeklinde bir konfigurasyon veriyorduk. İşte bu konfigurasyon yukarıda bahsettiğimiz işi otomatik olarak yapmaktadır. Bunu biz manuelde yapabiliriz.

```
    properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
```
şeklinde konfigurasyonu false yaparak eğer işlem başarılı ise ben açıkca başarılı diyeceğim kafkaya demiş oluyoruz. bu durumda mesajları başarılı şekilde işledikten sonra
```
    consumer.commitSync();
```
çağrısı yaparak manuel bir şekilde offsetin güncellenmesini sağlayabilmekteyiz. **Manuel modda iken, eğer bunu çağırmazsak offset güncellenmeyecek ve aynı mesajları tekrar tekrar işleyeceğiz, buna dikkat etmemiz lazım!**

Bu arada kafanızı karıştırmadan şu bilgiyide vereyim, kafka da consumer bu offseti ileri geri oynatabilmektedir. Yani işlenen mesaj tekrar işlenebilir, bir mesaj işlenmeden geçilebilir. Bu bazı durumlarda ciddi avantajlar sağlamaktadır bize.

# REPLICATION FACTOR

Arkadaşlar kafka distributed ve clustered bir sistemdir. Ne demek peki bu derseniz, yani sadece bir server çalışmak zorunda değildir, birden fazla kafka serveri çalışabilir. Topiclerin bazısı bir server'da, bazısı başka diğer bir server'da tutlabilir, serverların birinin sıkıntı yaşaması durumunda veri kaybolmaması ve işlerin aksamaması adına veri kopyaları birden fazla sunucuda aynı anda tutulabilir. 

Bu sayede hem performans hem güvenlik artışı sağlanabilir.

**İşte bu noktada mesajlarımızın kaç farklı kafka sunucundan yedekleneceği ayarına REPLICATION FACTOR diyoruz. Eğer iki kafka serverimiz varsa kopyaları ikisindede tutmak için replication factor = 2 diyoruz. Bu sayıyı server sayısından fazla vermenin bir anlamı yoktur!**

# CONSUMER GROUP

Arkadaşlar consumer yaratırken her consumerın mutlaka bir grupta olması gerektiğinden bahsetmiştik. Şimdi bunun nedenlerine biraz bakmaya çalışalım.

Şimdi senaryomuz şöyle olsun;

Hepsiburada gibi bir sistem geliştiriyorsunuz. Siparişleri kafkaya koyup daha sonra consumerlar tarafından işlenmesini sağlamak istiyorsunuz. Burada sipariş sonrası iki iş yapılması gerekmektedir. 

* Fatura hazırlama
* Siparişi kargolama işleri

Bizde dedik ki iki consumer geliştirelim birisi fatura oluştursun biriside kargo işlerini halletsin.

topic siparişler -> [sipariş1|sipariş2|sipariş2] -> fatura ureten consumer, kargo işlerini halleden consumer

Dikkat edin senaryo hız değilde sanki iki consumerında aynı mesajı alıp farklı işler yapması üstüne. Normalde kafka bir mesajı kesinlikle bir consumer tarafından işletiyordu. Burada amacımız bir mesajın onu dinleyen tüm customerlara gönderilmesidir. (Abonelik gibi düşünebilirsiniz, mesela dizinin yeni bölümü çıkınca tüm herkese haber ver gibi...)

Şimdi burda daha önce şöyle demiştik; **tek partitionlu bir topicte aynı anda mesaj sadece bir consumer'a gönderilir**. Anaç herkese haber vermek değil, bir mesajın biri tarafından işlenmesini sağlamaktır burada.

İşte bunun için aynı mesajın topici dinleyen tüm consumerlara gönderilmesi için consumer gruplar devreye girmektedir.

Eğer aynı mesajın birden fazla consumer tarafından işlenmesi gibi bir senaryonuz varsa bu noktada kafka consumerların farklı grupta olması şartı ile buna imkan sağlamaktadır. 

Yani biz burada
```
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "fatura");
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kargo");
```

şeklinde consumerlarımızın grubunu ayrı ayrı belirleyerek, aynı topicteki aynı mesajın iki consumerada gelmesini sağlayabilmekteyiz. Bu sayede aynı mesajı alan consumerlardan birinin fatura kısmını diğerinin kargo kısmını halletmesini sağlıyoruz. **Bakın burda mesaj iki kere işlenmiyor mesaja dair bir iş kısmı bir consumer diğer bir iş kısmı diğer bir consumer tarafından yapılmaktadır.**

**Burda amaç aynı mesajın birden fazla consumer tarafından işlenebilmesi unutmayın bunu. Normalde kafka bir mesajın kesinlikle ve kesinlike tek bir consumer tarafından işlenmesini sağlar.**

**Consumer grup sistemini abonelik gibi düşünebilirsiniz. Mesela siz bir haber sitesine bana yeni haber olursa mail at dediniz. Bende aynı şekilde haber sitesine bu şekilde kaydoldum. Burada yeni haber hem sana hem bana gelmelidir. Eğer kafka kullanılıyor ise sistemde; burada sende bende bir consumerımız ve grup idlerimiz farklı. "news" topici yeni bir haber eklendiğinde ikimizede gelecek!**

NOT : Arkadaşlar kafkanın matematiği cidden biraz karışık. Ve sanki partitionda, direk partitona mesaj gondermede, mesaj keyi kullanmada, consumerlara grup vermede benzer şeyler gibi görunebiliyor. Ancak farklı senaryolarda farklı çözümler oluyor. Ana sebepleri anlamaya çalışırsanız ilerde karar verme aşamasından daha iyi karar verebilirsiniz.

**Sonuç itibariyle kafka bir message brokerdır diyebiliriz. Ve bize sunduğu farklı yöntemlerle çok performanslı, güvenilir, hızlı, failsafe mesaj produce ve consume imkanı sunmaktadır.**











