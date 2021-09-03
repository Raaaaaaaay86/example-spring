package com.example.plan.entity;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public class Info {
	@CreatedDate
	private Date createdTime;

	@Override
	public String toString () {
		return "Info{" + "createdTime=" + createdTime + '}';
	}
}
