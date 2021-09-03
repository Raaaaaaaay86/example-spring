package com.example.plan;

import com.example.plan.util.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class PlanApplicationTests {
	private final Calculator calculator = new Calculator();

	@BeforeAll
	public static void beforeAll() {
		System.out.println("[BEFORE ALL]");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("[TEST START]");
	}

	@AfterEach
	public void afterEach() {
		System.out.println("[TEST END]");
	}

	@AfterAll
	private static void afterAll() {
		System.out.println("[AFTER ALL]");
	}

	@Test
	public void Addition() {
		Assertions.assertEquals(2, calculator.add(1, 1));
	}

	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	public void palindromes(String candidate) {
		System.out.println(candidate);
	}

	@RepeatedTest(10)
	public void repeatedTest(RepetitionInfo repetitionInfo) {
		System.out.println(repetitionInfo.getCurrentRepetition());
	}

	@TestFactory
	@DisplayName("Create 3 tests")
	public Collection<DynamicTest> dynamicTestsWithCollection() {
		var dynamicTestList = new ArrayList<DynamicTest>();

		for (int i = 0; i < 3; i++) {
			var num = i;
			var addTest = DynamicTest.dynamicTest(
					"ADD TEST",
					() -> Assertions.assertEquals(num * 2, Math.addExact(num, num))
			);
			System.out.println("i: " + num);
			System.out.println("expected: " + num * 2);
			dynamicTestList.add(addTest);
		}

		return dynamicTestList;
	}

	// @Nested https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested


}
