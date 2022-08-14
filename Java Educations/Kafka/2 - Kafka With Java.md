# JAVA KAFKA PRODUCER - CONSUMER

Arkadaşlar kafkada isterseniz produceri python ile yazın consumerı java ile yazın, isterseniz PHP veya node.js ile yazın iki tarafıda; burada herhangi bir sınır yok. (***Producer ve publisherin mesaj paylaşmak dışında herhangi ortak bir noktası yoktur!***)

Biz hem producerı hemde consumer'ı **JAVA** ile geliştireceğiz.

# PRODUCER

Producer'ın kafka da bir topic'e mesaj gonderebilen processler(java kodumuz) olduğunu söylemiştik. courses adında bir topicimiz olduğunu düşünüp ona bir tane mesaj gönderelim.

Oncelikle pom.xml'e kafka kütüphanesini ekliyoruz arkadaşlar.
```
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>3.1.0</version>
    </dependency>
```  
Daha sonra bir tane **KafkaProducer** nesnesi yaratıyoruz arkadaşlar. Bu nesneyi yaratırken bazı config bilgilerini vermemiz gerekmektir.    

```
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    Producer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
```    
Konfigurasyona bakarsak önce tabii ki producerin bağlacağı kafka sunucusunun adresini veriyoruz. Daha sonra bu producer'in üreteceği mesajların key'inin ve değerinin normal stringler olacağını belirtiyoruz. İlerde **JSON** mesaj gönderme yaptığımızda buradaki config **JSON**'a özel şekilde ayarlanacaktır.

Hocam burda key ne diyor iseniz, biraz daha sabır açıklayacağım arkadaşlar.
Daha sonra **ProducerRecord** adını verdiğimiz nesneden bir tane yaratarak mesajımızı hazırlıyoruz.
```   
    ProducerRecord producerRecord = new ProducerRecord<Integer, String>("courses", "3.hafta bitti, ödev hazırla");
```
Burada birinci parametremiz topic adı ikinci parametre göndermek istediğimiz mesaj olmakta. Ve bu işlemden sonra

```
     producer.send(producerRecord);
```  
diyerek mesajımızı kafkaya iletiyoruz.

**Bu aşamada kafka da consumer varsa mesaj consumera iletilecektir. Eğer consumer yoksa mesaj kafka'da yaşamaya devam edecek; herhangi bir consumer aktifleştiğinde mesaj ona iletilecektir.**

# CONSUMER

Consumerın kafkadaki bir veya daha fazla topic'e subscribe olup buradaki mesajları işleyebilen bir process(bizim yazacağımız java kodu yani) oldugundan bahsetmiştik.

Öncelikle

```
    String bootstrapServers = "localhost:9092";
	String groupId = "courses_consumer";

	Properties properties = new Properties();
	properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
```

şeklinde config bilgileri ayarlıyoruz. Burada 
* Consumer'ın kafka sunucusuna bağlanabilmesi için kafkanın adresini veriyoruz.
* Burada da messageların string oldugunu belirten serializer sınıflarını konfigüre ediyoruz.
* Kafka her bir consumer kesinlikle bir grupta olmalıdır. Bundan dolayı kendi oluşturdugumuz bir grup isminide set ediyoruz. Consumer grupları ilerde çok işimize yarayacak arkadaşlar göreceğiz onuda.
* Auto commitin ne olduğu açıklanacaktır.

Daha sonra bu konfigürasyon ile bir adet **KafkaConsumer** nesnesi yaratıyoruz.

```
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
```

E hocam hangi topic'teki mesajları okuyacak bu diyorsanız değil mi, Consumer yaratıldıktan sonra topiclere subscribe oluyor arkadaşlar aşağıdaki gibi.

```
    String[] topic = {"courses"};
    consumer.subscribe(Arrays.asList(topic));
```    

Gördüğünüz gibi array kabul ediyor subscribe methodu, yani bir consumer birden fazla topicteki mesaj ile ilgilenebilir.
Subscribe işleminde sonra Consumer bir sonsuz döngüde (**while(true)**) kafkadan mesajların gelmesini beklemektedir.

```
    while (true) {
		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	}
```

Burada **poll()** methodu ile **Consumer** kafka'dan mesajları istemektedir. Eğer kafkada mesaj varsa(***bir veya daha fazla, o an ne varsa hepsi***) direk olarak mesajları alacaktır. Eğer o anda kafka mesaj yoksa **Consumer** poll() methoduna verilen timeout parametresi süresince mesaj gelmesini bekleyecek(**kod bloklanacak**) süre geçtiğinde ise kod devam edecek, while(true)'a tekrardan gelecek aynı süreç tekrarlanacaktır.

Kafka'dan gelen mesajlar **ConsumerRecords<String, String>** şeklindedir gördüğünüz gibi. Bu bir veya daha fazla mesajı tutabilen, configte verdiğimiz gibi key ve value değerlerinin string olduğu mesajları tutan bir nesnedir.

Daha sonra **ConsumerRecords** üstünde for ile dönerek tekil **ConsumerRecord** nesnelerini alarak mesajlarımıza erişebiliriz. (Birden fazla mesajı tutan **ConsumerRecords**, tekil recordlar **ConsumerRecord** buna dikkat!) arkadaşlar.

```
    for (ConsumerRecord<String, String> record : records) {
        //Operate on ConsumerRecord<String, String> record
	}
```	

**ConsumerRecord** nesnesi bir mesaja dair tüm bilgileri içeren çok önemli nesnemizdir. Bu nesne üstünden mesajın içeriğine, bulundugu topic'e, hangi partitionda olduğu(geleceğiz bu konuya), hangi keye sahip olduğu(buna da geleceğiz) gibi tüm bilgilere erişebilmekteyiz.

```
    for (ConsumerRecord<String, String> record : records) {
        //Operate on ConsumerRecord<String, String> record
        System.out.println("Topic : " + record.topic());
        System.out.println("Key : " + record.key());
        System.out.println("Message : " + record.value()); //message içeriği value() ile okunmaktadır;
		System.out.println("Partition: " + record.partition());
	}
```	

Artık bu aşamdan sonra mesajla ne yapacak iseniz o noktaya gelmiş durumdasınız. Mesela producer'da gönderdiğimiz mesaj 3.haftanın bittiğini, ödev hazırlamam gerektiğini söylüyordu, kodun devamında ödev hazırlayabilirim!!

**Gördüğünüz gibi asenkron iletişim başarılı bir şekilde sağlanmış oldu.**




