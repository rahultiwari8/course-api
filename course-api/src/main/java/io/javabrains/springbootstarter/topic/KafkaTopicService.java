package io.javabrains.springbootstarter.topic;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;

@Service
public class KafkaTopicService {
	
	public String  putDataKafkaTopic(String data) 
	{
		String topicName = "testsh";
		String errorTopicName = "errorTopic";
		  String key = "Key1";
		  
		  ProducerRecord<String, String> record;
		  JSONObject jObj = null ;
		/*
		 * String value = "{\n" + "  \"name\": \"John\",\n" + "  \"age\": 31,\n" +
		 * "  \"city\": \"New York\"\n" + "}";
		 */
		  
		  String value = data;
		  
		JSONParser parser = new JSONParser();
		try
		{
		 jObj =(JSONObject) parser.parse(data);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		String id = (String)jObj.get("id");
		
		boolean bool=id.length()<20 && id.length()!=0;
		
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
	      if(bool==true)
		
		  {
	    	   record = new ProducerRecord<>(topicName,key,value);
		  }
	      else
	      {
	    	   record = new ProducerRecord<>(errorTopicName,key,value);
		  }
		  
		  try
		  {producer.send(record);	       
	      producer.close();
	      return "sent";
		  }
		  catch (Exception e) {
			// TODO: handle exception
			  
			  return e.getMessage();
		}
	}

}
