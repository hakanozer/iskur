# Kafka Revisit

Arkadaşlar programlama dünyasında **message-broker** adını verdiğimiz yazılımlar vardır. Bunların amacı temel olarak farklı processler arasında veri(mesaj) akışına imkan sağlamaktır. Örnek olarak **Apache KAFKA, RabbitMQ, ActiveMQ** vb.. (Bunlar genel olarak **AMQP** adını verdiğimiz bir protokol kullanılar. Protokol neydi hatırlayın!)

Bu yazılımlar temelde aynı işi detayda bazı farklılıklarla yerine getirmektedir. 

Kafka çok performanslı bir mesajlaşma aracıdır arkadaşlar temelde.(***Kafkanın kendiside JAVA ile geliştirilmiştir arkadaşlar, JAVA'nın ne kadar performanslı bir dil olduğunu burdan anlayabiliriz!!***) Mesajlaşmadan kasıt chat gibi bir mesajlaşma değil, farklı processler arasında haberleşmedir diyebiliriz. 

Temel mantığı **publish subscribe** mantığıdır yani birileri bir mesajı publish(yayınlamak)eder, bu mesajdan haberdar olmak isteyen subscriber(abone) ler publish sonrası mesajı alıp ne yapmaları gerekiyor ise onu yerine getirirler.

Kafka ile çalışabilmek için kafka ve zookepeer adında iki yazılımın sistemimize yüklenmesi lazımdır. (Zookeeper bazı işlerde kafka tarafından kullanılmaktadır, son sürümlerde zorunlu olmaığı söyleniyor.)

Örnek olarak kafka'yı windows'a yükleyip çalıştırmak için [Kafka Windows Installation](https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/) linkine bakabilrsiniz.

Kafka sistemimize yüklendikten sonra çalıştırılır ve bir portta mesaj publishlenmesi bekler, publish olayı gerçekleştiğinde subscriberlara mesajları gönderir.

Peki gerçek hayatta ne gibi senaryolarda kullanılmaktadır dersek

* Uzun sürebilecek işlemlerin web isteği yapan kullanıcıyı bekletmemesi adına arka planda işlenmesi adına yapılacak işe dair mesajların yayınlandıgı bir yer olarak. Mesela sisteminizi birisi kaydolduğunda herkese mail atıyorsunuz diyelim, burda kayıt olan kullanıcının isteğini bekletmemek için mail gönderme işini arka planda yapılmak üzer kafkaya gönderebiliriz. Daha sonra subscriberlara mesaj ulaştırılır ve subscriber mail gönderme işini yerine getirir.
* Yoğun sistemlerde sistemin salığı açısından, yük altında sistemin hata yaşamasının önüne geçilmesi için yapılacak işlemlerin geçiçi olarak depolandığı yer olarak kullanılabilir. Mesela banka sistemleri yoğun sistemlerdir ve her işlem hemen sonuç vermek durumunda değildir. Birine eft yapıyorsanız hemen o an olmasına gerek yoktur. İşte bu gibi durumlarda işlem kafkaya atılır. Müsait subscriber olunca sizin işleminiz gerçekleştirilir. 
* Farklı processler arasında asenkron bir şekilde iletişim sağlanması. Özellikle microservis mimarisini gelişmesi ile birlikte farklı servisler arasında haberleşme ihtiyacı artmıştır. Burada senkron(direk birbiriyle konuşması) bir haberleşme durumunda bir servisin o an cevap veremiyor olması genel olarak isteğin başarısız olmasına sebep olabilir. İşte bu gibi durumnlarda araya kafkayı sokarak haberleşme için mesaj kafkaya gömnderilmekte, haberleşmenin diğer tarafı ne zaman uygun olursa subscribe olup mesajları işleyebilmektedir. Bu sayede senkron haberleşme sıkıntısı ciddi anlamada bertaraf edilmektedir.
* Buyuk veri(Big data) dunyasında yaşıyoruz arkadaşlar. Bir web sayfasında yaptığımız her mouse hareketi bile kayıt altına alınıyor çoğu zaman. Çok ciddi veri akışı var ama bunun analizinin hemen yapılması gerek yok. Daha sonrada yapılabilir. İşte bunun gibi çok ciddi veri akışın oldu yerlerde(Big data, loglama işleri vs..) kafka olaylara dair mesajların atıldığı ilk yer olmaktadır. Daha sonra subscriber'lar bunları rahat rahat işlemektedir.

***Not : Senkron haberleşme mesela senin belli bir portta çalışan bir programa direk olarak istek atmandır diyebiliriz. Mesela siz spring bootla uygulama yaptınız postmandan direk olarak spring bootun sunucusu tomcate istek attınız. Burda spring boot uygulaması ayakta değilse isteğiniz başarısız olacaktır, eğer ayakta ise direk cevanı alacaksınızdır. Asenkron dediğimizde ise ben yine spring boot projesine istek atmak istiyorum ama bunu direk ona giderek değil kafka'ya bir mesaj ekleyerek yapıyorum. Şu an kapalı olan spring boot projesi daha sonra ayağa kalktığında kafka'da bana mesaj var mı diye bakıp işi yerine getirebilir. Yani sizin isteğiniz geçte olsa başarılı olur. İşte buna asenkron iletişim diyoruz kabaca.***

Senkron asenkron kafanızda oturmadı ise şöyle düşünün. Birisini aradınız telefonunuzdan telefonu açtı konuşuyorsunuz. Burda herşey o an gerçekleşiyor. Buna senkron diyebilirsiniz. Aynı kişiye mesaj attığınızı düşünün. Burada alıcı istediği zaman mesaja bakıp cevabı bir şekilde dönebilir.(Sizi arayabilir, mesaj atabilir size). BUna asenkron diyebilirsiniz.


Peki kafka bu bahsedilen işleri nasıl yapmaktadır?

# KAFKA SÖZLÜĞÜ

Arkadaşlar kafka'nın çalışma temelde dört ana kısımdan oluşmaktadır.
* Producer(Mesaj üreten bir process)
* Topic(Mesaj grupları, bir mesaj her zaman bir topic'e aittir)
* Message broker - Kafkanın kendisi (Mesajları alıcıları ulaştırmak üzere saklayan kafka yazılımımız)
* Consumer (Mesajları okumak üzere beklemede olan alıcı processler)

* Producer belirli bir olay sonucu kafkaya mesaj yollayan herhangi bir yazılımdır. Mesela bankaya eft'ye dair bir istek geldi, backend kodu gider bunu kafkaya mesaj olarak ekler. İşte backend kodu burada producer olmuştur.
* Topic bir producer'in mesaj yayınladığı zaman bu mesajın ait olduğu gruptur. Mesela mail göndermeye dair bir mesaj yayınlanacak o zaman "mails" isminde bir topic yaratılıp mail gonderme işlemlerinin bu topice gönderilmesi mantıklı olandır. Keza havale eft gibi işlemler içinde ayrı ayrı topicler yaratılabilir. Bir producer mesaj yayınlarken topic belirtmek ZORUNDADIR.
* Kafka topic bilgilerini, topiclerdeki mesaj bilgilerini tutan yazılımızdır. Producerlar kafkaya bağlanarak topiclere mesajlarını yayınlarlar. Consumerlarda kafkaya bağlanarak ilgilendikleri topice dair mesajları okuyabilirler.
* Consumer bir veya daha fazla topice kaydolarak buraya gelen mesajları kafkadan alabilen processlerdir. Mesela bir "mails" topicine kaydolabilir, buraya mesaj geldiğinde mesajları kafkadan alabilir ve mail gönderme işini yerine getirebilir. Bir consumer sadece bir topicle ilgilenmek zorunda değildir. İstediği kadar topicden gelen mesaja kaydolabilir.
* Consumer konusunda önemli olan şey şudur arkadaşlar, consumerlar bir consumer grubu içine olmak zorundadır. Grupsuz consumer olamaz. Bu gruplama ilerde bazı performans işlerinde bize kolaylık sağlayacaktır.

Yani akış
Producer -> Send Message to Kafka Topic -> Consumer Read Message From Kafka Topic

Peki topicler nasıl yaratılıyor diye sorarsak bunu indirdiğimiz kafka programının bize sundugu **cli(command line, terminal)** toolları ile yapabilmekteyiz veya direk olarak JAVA, python, PHP vb.. gibi programlama dilleri ilede yapabilmekteyiz. Kafka default olarak bize düzgün bir UI sağlamamaktadır bunun için. Bu konuda **RabbitMQ** ciddi anlamda ileridedir. 

# KAFKA IN JAVA

Java dilini kullanarak kafkada topic yaratabilir, topic silebilir, producer yaratabilir ve topice mesaj gonderebilir, consumer yaratıp topicten mesajları okuyabiliriz arkadaşlar.

Java da kafka ile çalışmak için(spring frameworku yok ortada, direk normal java ile) bir maven projesi yaratıp pom.xml'e

    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>3.1.0</version>
    </dependency>

şeklinde kafka kütüphanesini eklioruz.

# TOPIC EKLEME

Kafka da herhangi bir şey yapmak için öncelikle kafka'ya bağlanmamız gerekmektedir. Kafka yüklenip çalıştırıldıktan sonra default olarak localhost:9092 adresinde gelecek istekleri beklemektedir.
```
    Properties properties = new Properties();
	properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    AdminClient adminClient = KafkaAdminClient.create(props);
```    
    
şeklinde bağlanacağımz adresi properties nesnesi içinde vererek bir adet **AdminClient** nesnesi oluşturuyoruz. Topic oluşturma bir admin işidir, bundan dolayı **AdminClient** nesnesi üstünden ilerliyoruz.

Daha sonra

```
    NewTopic newTopic = new NewTopic("topicName", 1, (short)1);
    List<NewTopic> newTopics = new ArrayList<NewTopic>();
    newTopics.add(newTopic);
    adminClient.createTopics(newTopics);
```    

kafka ya eklemek üzere yeni bir **NewTopic** nesnesi yaratıyoruz arkadaşlar. **NewTopic** constructorına baktığımızda üç adet parametre aldığını görüyoruz. Bu parametreler

* topic adı
* partition sayısı
* replication factor

Bunlarda son ikisinin ne olduğu ilerde açıklanacaktır. Şimdilik 1 verebilirsiniz ikisinede.

Yarattığımız **NewTopic** nesnesini bir listeye ekliyoruz(**createTopics()** methodu liste bekliyor, tek bir tane topic oluşturmak istesek bile bu şekilde yapmak durumunda kalıyoruz, listeye birden fazla **NewTopic** ekleyerek birden fazla topicte yaratabiliriz tek bir istekte)

Daha sonra **AdminClient**'ın createTopics() methoduna listeyi vererek topiclerin yaratılmasını sağlıyoruz.
İşte bu aşamadan sonra artık producer ile mesaj üretmeye ve consumer ile bu mesajları işlemeye başlayabilmekteyiz arkadaşlar. Devamını diğer dökümanda bulabilirsiniz.

**NOT : Kafka'ya restart atmak topicleri ve topiclerdeki mesajları silmez arkadaşlar. Rahat rahat restart atabilirsiniz.**


    








