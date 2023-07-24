package com.test.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.demo.config.User;

@SpringBootApplication
public class KafkaConsumerApplication {
	
	List<String> message = new ArrayList<>();
	
	User user = new User();
	
	@KafkaListener(groupId="javatechie-1", topics="javatechie", containerFactory="kafkaListenerContainerFacotry")
	public List<String> getMsgFromTopic(String data) {
		
		message.add(data);
		return message;		
	}
	
	
	@KafkaListener(groupId="javatechie-2", topics="javatechie", containerFactory="userKafkaListenerContainerFacotry")
	public User getJsonMsgFromTopic(User data) {
		
		this.user = data;
		return user;		
	}
	
	
	
	
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMessage() {
		return message;
	}
	
	
	@GetMapping("/consumeJsoMessage")
	public User consumeJsonMessage() {
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
