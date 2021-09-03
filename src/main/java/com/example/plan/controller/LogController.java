package com.example.plan.controller;

import com.example.plan.entity.Student;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/log")
public class LogController {

	private Logger logger = Logger.getLogger("stdout1");

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@GetMapping("/")
	public Object printHello (ModelMap model) {
		logger.debug("Hello /log");
		var student = new Student();
		student.setName("王王王");
		student.setId(99);
		student.setSex("male");
		student.setBirthday(876182400000L);

		redisTemplate.opsForValue().set("student::99", student);
		return "re";
	}

	@GetMapping("/aa")
	public Object test () {
		var re = redisTemplate.opsForValue().get("student::54");
		logger.debug(re);
		return "ss";
	}
}
