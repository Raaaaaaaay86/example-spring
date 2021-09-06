package com.example.plan.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.a")
public class TopicReceiverA {
	@RabbitHandler
	public void process(String message) {
		System.out.println("TopicReceiverA receive message: " + message);
	}
}
