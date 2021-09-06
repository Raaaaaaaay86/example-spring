package com.example.plan.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "userQueue")
public class QueueReceiverA {
	@RabbitHandler
	public void process(Map<String, Object> user) {
		System.out.println("QueueReceiver received user data: " + user);
	}
}
