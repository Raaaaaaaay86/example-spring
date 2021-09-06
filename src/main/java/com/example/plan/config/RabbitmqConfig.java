package com.example.plan.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.Topic;

@Configuration
public class RabbitmqConfig {
	@Bean
	public Queue queueA() {
		return new Queue("fanout.a");
	}

	@Bean
	public Queue queueB() {
		return new Queue("fanout.b");
	}

	@Bean
	public Queue queueUser() {
		// 純 Queue 不需要添加 Exchanger，直接指派 queue routing key 即可
		return new Queue("userQueue");
	}

	@Bean
	public Queue queueTopicA() {
		return new Queue("topic.a");
	}

	@Bean
	public Queue queueTopicB() {
		return new Queue("topic.b");
	}

	@Bean
	public FanoutExchange fanoutExchanger() {
		return new FanoutExchange("fanoutExchanger");
	}

	@Bean
	public TopicExchange topicExchanger() {
		return new TopicExchange("topicExchanger");
	}

	@Bean
	Binding bingExchangerA() {
		return BindingBuilder.bind(this.queueA()).to(this.fanoutExchanger());
	}

	@Bean
	Binding bindExchangerB() {
		return BindingBuilder.bind(this.queueB()).to(this.fanoutExchanger());
	}

	@Bean
	Binding bindingTopicExchangerA() {
		return BindingBuilder.bind(this.queueTopicA()).to(this.topicExchanger()).with("topic.a");
	}

	@Bean
	Binding bindingTopicExchangerB() {
		// topic.a, topic.b, topic.c ...... 皆會在此生效
		return BindingBuilder.bind(this.queueTopicB()).to(this.topicExchanger()).with("topic.#");
	}
}
