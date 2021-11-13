package dev.jack;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.jack.module.Person;
import dev.jack.module.Student;
import dev.jack.module.StudentSerializer;
import dev.jack.util.LoadConfig;

@SpringBootApplication
public class KafkaProducerApplication {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("please passing <topic> <configfile>");
			System.exit(1);
		}
		SpringApplication.run(KafkaProducerApplication.class, args);
		String topicName = args[0].toString();
		String configFile = args[1].toString();
		/*
		Properties props = new Properties();
	    //props.put("BOOTSTRAP_SERVERS_CONFIG","BOOTSTRAP_SERVERS");
		props.put("bootstrap.servers", "localhost:2181");
		//Set acknowledgements for producer requests.   
		props.put("acks", "all");
	    //If the request fails, the producer can automatically retry,
	    props.put("retries", 0);      
	    //Specify buffer size in config
	    props.put("batch.size", 16384);
	    //Reduce the no of requests less than 0   
	    props.put("linger.ms", 1);   
	    //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	    props.put("buffer.memory", 33554432);
	    props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");   
	    props.put("value.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	    */

		Properties props = LoadConfig.loadConfig(configFile);
		// customize serializer with Person
	    //props.put("value.serializer",  Person.class.getName());
		/*
	    props.put("key.serializer", 
		         "org.apache.kafka.common.serialization.StringSerializer"); 
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Person.class.getName());
	    */
	    //Producer<String, String> producer = new KafkaProducer<String, String>(props);
	    KafkaProducer<String, Student> producer = new KafkaProducer<String, Student>(props);

	    for(int i = 0; i < 10; i++) {
	    	producer.send(new ProducerRecord<String, Student>(topicName, 
	    			Integer.toString(i), new Student(i, "n "+i, new Person(i, "p "+i))));
	    }
        System.out.println("Message sent successfully");
        producer.flush();
        producer.close();
	}

}
