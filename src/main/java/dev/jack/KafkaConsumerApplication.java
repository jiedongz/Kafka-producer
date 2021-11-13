package dev.jack;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;

import dev.jack.module.Student;
import dev.jack.module.StudentDeserializer;
import dev.jack.util.LoadConfig;

public class KafkaConsumerApplication {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("please passing <topic> <configfile>");
			System.exit(1);
		}
		SpringApplication.run(KafkaConsumerApplication.class, args);
		String topicName = args[0].toString();
		String configFile = args[1].toString();
		Properties props = LoadConfig.loadConfig(configFile);

//	    props.put("key.serializer", 
//		         "org.apache.kafka.common.serialization.StringSerializer"); 
//		props.put("value.serializer",  Person.class.getName());

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(topicName));
		System.out.println("Subscriped to topic: " + topicName);
		int i = 0;
		while (true) {
			if (i++ > 1000) {
				System.out.println("count > " + i + ". exiting");
				System.exit(2);
			}
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s\n",
						record.offset(), record.key(), record.value());
//				if (record.key().equals("exit"))
//					System.exit(2);
			}
		}
	}

}
