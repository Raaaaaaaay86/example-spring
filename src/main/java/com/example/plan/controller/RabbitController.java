package com.example.plan.controller;

import com.example.plan.mq.FanoutSender;
import com.example.plan.mq.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

	@Autowired
	FanoutSender fanoutSender;

	@Autowired
	QueueSender queueSender;

	@GetMapping("/fanoutSend")
	public void sendFanout() {
		fanoutSender.send();
	}

	@GetMapping("/userQueueSend")
	public void userQueue() {
		queueSender.sendQueue("userQueue");
	}
}
