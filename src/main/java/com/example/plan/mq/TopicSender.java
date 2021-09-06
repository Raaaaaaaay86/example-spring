package com.example.plan.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void send(String route) {
		System.out.println("Send to route : " + route);
		rabbitTemplate.convertAndSend("topicExchanger", route, route);
	}
}
