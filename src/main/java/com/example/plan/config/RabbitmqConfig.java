package com.example.plan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public FanoutExchange fanoutExchanger() {
		return new FanoutExchange("fanoutExchanger");
	}

	@Bean
	Binding bingExchangerA() {
		return BindingBuilder.bind(this.queueA()).to(this.fanoutExchanger());
	}

	@Bean
	Binding bindExchangerB() {
		return BindingBuilder.bind(this.queueB()).to(this.fanoutExchanger());
	}
}
