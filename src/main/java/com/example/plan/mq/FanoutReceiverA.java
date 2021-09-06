package com.example.plan.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.a")
public class FanoutReceiverA {
	@RabbitHandler
	public void process(String message) {
		System.out.println("FanoutReceiverA got message: " + message);
	}
}
