package com.example.plan.util;

import java.util.Arrays;

public class Calculator {

	public long add(long... numbers) {
		return Arrays.stream(numbers).reduce(Long::sum).orElse(0);
	}
}
