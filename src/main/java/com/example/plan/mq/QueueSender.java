package com.example.plan.mq;

import com.example.plan.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QueueSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void sendQueue(String route) {
		Map<String, Object> user = new HashMap<>();
		user.put("name", "Kim Kim Chi");
		user.put("age", 22);
		user.put("sex", "female");

		System.out.println("QueueSender send data to route: " + route);
		this.rabbitTemplate.convertAndSend(route, user);
	}
}
