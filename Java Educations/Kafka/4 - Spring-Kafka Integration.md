# Spring Boot Kafka Integration

Daha önce normal java ile kafka'yı kullanmayı öğrenmiştik. Şimdi bir'de **Spring boot** ile bunu nasıl yapabiliriz görelim.

**Spring boot** ile bir paketi entegre ederken çoğu zaman ***spring-boot-starter-{libraryname}*** şeklinde bağımlılığımızı yükleriz. Bu paket auto-configurationu yerine getirir. **Kafka**'da ise entegrasyon manuel bazı adımlar gerektirmektedir.

Öncelikle

```
        <dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>
```

şeklinde kafka bağımlılığımızı **pom.xml'e** ekliyoruz.
Daha sonra topicimizi yaratma işini'de spring'den yapmak istiyorsak(istemeyebilirsiniz, daha önce yaratılan bir topici kullanmak isteyebilirsiniz, o zaman bu adımı atlayabilirsiniz.)

```
    @Configuration
    public class KafkaTopicConfig {
        @Bean
        public KafkaAdmin kafkaAdmin() {
            Map<String, Object> configs = new HashMap<>();
            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
            return new KafkaAdmin(configs);
        }
        @Bean
        public NewTopic transferTopic() {
             return new NewTopic("transfer", 1, (short) 1);
        }
    }
```

şeklinde ismini sizin belirleyecebileceğiniz bir configuration sınıfı oluşturup Kafka'da admin işlemleri yapabilmek için gerekli **@Bean** tanımlarını yapıyoruz. Sınıfı **@Configuration** annotationu(**Hatırlayın spring @Component, xml veya @Configuration annotationu içindeki methodlar ile bean kabul ediyordu!, Spring bootta farklı birşey yok, istediğiniz kadar @Configuration ile annotated edilmiş sınıf yazabilirsiniz**) ile işaretleyerek springe burdaki beanleri kaydetmesini sağlıyoruz.

Burada spring akıllı bir şekilde **NewTopic** return eden ve **@Bean** annotationu ile işaretlenen methodların hepsini; ayağa kalktıktan sonra çalıştırır ve topicleri verdiğiniz bilgilere göre yaratır.

##PRODUCER

Producer kafkaya mesaj gönderen taraftı. Springte producer yaratabilmek için yine bazı beanleri kaydetmemiz gerekmektedir. Bunu yine yeni bir **@Configuration** sınıfında yapabiliriz.(Bir önceki configuration sınıfını'da kullanabiliriz sorun yok bunda, ancak ayrı olması daha okunabilir olacaktır.)
```
    @Configuration
    public class KafkaProducerConfig {
        @Bean
        public ProducerFactory<String, String> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(
              ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
              "127.0.0.1:9092");
            configProps.put(
              ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
              StringSerializer.class);
            configProps.put(
              ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
              StringSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }
    
        @Bean
        public KafkaTemplate<String, String> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }
```

Burada'da producerin konfigurasyonu için bir bean(**ProducerFactory**), bir de kafkaya bağlanıp mesaj gönderebilecek bir başka bean tanımlıyoruz. Biz mesaj göndereceğimiz zaman herhangi bir sınıfta(controllerda mesela) **@Autowired** ile **KafkaTemplate** beanini alacağız ve mesajımızı onun üstünden göndereceğiz.
```

    public class BankController{
        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;
        @PostMapping("/transfer")
        public ResponseEntity<?> transferMoney(String msg) {
            //transfer operations
            String topicName = "transfer";
            kafkaTemplate.send(topicName, "Transfer message");
        }
    }
```

şeklinde topic ismi ile mesajı kafkaya iletebilmekteyiz.

##Consumer

Consumer producerlar tarafından topiclere üretilen mesajları okumak üzere kafkaya bağlanan, mesajla ilgili belirli bir işi yerine getiren kısımdı.

Springte consumer yaratmak için yine bazı beanleri kaydetmemiz gerekmektedir.
```
    @EnableKafka
    @Configuration
    public class KafkaConsumerConfig {
        @Bean
        public ConsumerFactory<String, String> consumerFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(
              ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
              "127.0.0.1:9092");
            props.put(
              ConsumerConfig.GROUP_ID_CONFIG, 
              "consumer_group_one");
            props.put(
              ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
              StringDeserializer.class);
            props.put(
              ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
              StringDeserializer.class);
            return new DefaultKafkaConsumerFactory<>(props);
        }
    
        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, String> 
          kafkaListenerContainerFactory() {
       
            ConcurrentKafkaListenerContainerFactory<String, String> factory =
              new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }
    }
```

Buradada bir **@Configuration** sınıfı ile consumer yönetimi için gerekli iki bean kaydı yaptık. Ve dikkat ederseniz sınıfın isminin üstünde **@EnableKafka** şeklinde bir annotation kullandık. Bu springin consumer'lara dair bazı işlemleri çalıştırması için gereklidir.

Buradan sonra herhangi bir bean'de yazdığımız bir methodu **@KafkaListener** annotationu ile işaretleyerek consumer yaratabiliriz. Ben genelde **Consumer** adında bir sınıf açıp onun içine method ekliyorum.

```
    @Component
    public class Consumer {

    	@KafkaListener(topics = "transfer", groupId = "transfer_consumer_group")
    	public void listenTransfer(@Payload String message, 
    			  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
    			  @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key
    	) {
    	    System.out.println("Message : " + message + ", Partition : " + partition + ", Key : " + key);
    	}
    }
```

**@KafkaListener** annotationu ile customerın dinlediği topici(birden fazla da olabilir, {"a", "b"} şeklinde array verilip) ve group id'si belirlenebilir. Burad seçilen group id **@Configuration** sınıfında belirleneni ezecektir. Ve işte bu aşamadan sonra uygulamanız ayağa kalktığında consumerınızda hazır olacaktır. Bir producer mesaj eklediğinde consumer'ın ***listenTransfer()*** methodu çalıştıracaktır.

**@Payload** annotationu ile mesajın içeriği okunmakta, **@Header** annotationu ile mesajın ait oldugu partition ve mesajın keyi(varsa) okunabilmektedir.

Arkadaşlar daha detayları var, bundan daha fazla yeteneğe sahip aslında **Spring-Kafka** entegrasyonu. Bunun için daha detaylı bakmak isterseniz [Spring Kafka Baeldung](https://www.baeldung.com/spring-kafka) linkini inceleyebilirsiniz.

Gördüğünüz gibi aslında normal java ile yaptığımız kısmın neredeyse tamamen aynısı, ekstradan spring için beanler tanımladık.




