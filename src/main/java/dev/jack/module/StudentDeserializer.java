package dev.jack.module;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StudentDeserializer implements Deserializer<Student> {
	@Override
	public void configure(Map configs, boolean isKey) {
		Deserializer.super.configure(configs, isKey);
	}
	@Override
	public Student deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Student student = null;
		try {
			student = mapper.readValue(data, Student.class);
		} catch (Exception e){
			e.printStackTrace();
		}
		return student;
		//return (data == null) ? null : (Student) SerializationUtils.deserialize(data);
	}
//	@Override
//	public Student deserialize(String topic, Headers headers, byte[] data) {
//		return Deserializer.super.deserialize(topic, headers, data);
//	}
	@Override
	public void close() {
		Deserializer.super.close();
	}
//	public StudentDeserializer() {
//		this(null);
//	}
//	public StudentDeserializer(Class<?> vc) {
//		//super(vc);
//	}

//	public StudentDeserializer(JavaType valueType) {
//		super(valueType);
//	}
//
//	public StudentDeserializer(StdDeserializer<?> src) {
//		super(src);
//	}

//	public Student deserialize(JsonParser jp, DeserializationContext ctx) 
//		throws IOException, JsonProcessingException {
//		JsonNode node = jp.getCodec().readTree(jp);
//		int id = (Integer) node.get("id").numberValue();
//		String course = node.get("course").asText();
//		int personId = (Integer) node.get("createdBy").numberValue();
//		
//		return new Student(id, course, new Person(personId, null));
//	}
}
