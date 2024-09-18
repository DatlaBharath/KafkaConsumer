package com.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.PropertyConfigurator;
public class TestKafkaConsumer {
	public static void main(String[] args) {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Properties props = new Properties();
		props.put("bootstrap.servers","localhost:9092");
		props.put("group.id","test-group");
		props.put("enable.auto.commit","true");
		

		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
		consumer.subscribe(Arrays.asList("source-topic")); // we can consume multiple topics related to java
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> record1 : records) {
				System.out.printf( "offset= %d, key=%s, value=%s \n",record1.offset(),record1.key(),record1.value());
			}				
		}
	}
}
