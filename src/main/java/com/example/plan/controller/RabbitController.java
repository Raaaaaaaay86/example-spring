package com.example.plan.controller;

import com.example.plan.mq.FanoutSender;
import com.example.plan.mq.QueueSender;
import com.example.plan.mq.TopicSender;
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

	@Autowired
	TopicSender topicSender;

	@GetMapping("/fanoutSend")
	public void sendFanout() {
		fanoutSender.send();
	}

	@GetMapping("/userQueueSend")
	public void userQueue() {
		queueSender.sendQueue("userQueue");
	}

	@GetMapping("/topicA")
	public void topicA() {
		topicSender.send("topic.a");
	}

	@GetMapping("/topicB")
	public void topicB() {
		topicSender.send("topic.1");
		topicSender.send("topic.c");
	}
}
