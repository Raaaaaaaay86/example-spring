package com.example.plan.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void send() {
		String context = "Fanout";
		System.out.println("Sender: " + context);
		this.rabbitTemplate.convertAndSend("fanoutExchanger", "", context);
	}

}
