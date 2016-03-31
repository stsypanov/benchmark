package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by stsypanov on 05.10.2015.
 */
public class RegExpVsIntegerParseIntBenchmark {
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
	public static final int SIZE = 1000000;

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testParseWithIntegerValueOf() {
		List<Integer> ints = new ArrayList<>(SIZE);
		List<String> messages = getNumberStrings();
		for (String s : messages) {
			Integer i = parseWithValueOf(s);
			ints.add(i);
		}
		assert !ints.isEmpty();
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testParseWithRegExp() {
		List<Integer> ints = new ArrayList<>(SIZE);
		List<String> messages = getNumberStrings();
		for (String s : messages) {
			Integer i = parseWithRegExp(s);
			ints.add(i);
		}
		assert !ints.isEmpty();
	}

	private int parseWithRegExp(String s) {
		return NUMBER_PATTERN.matcher(s).matches() ? Integer.valueOf(s) : -1;
	}

	private int parseWithValueOf(String s) {
		try {
			return Integer.valueOf(s);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private List<String> getNumberStrings() {
		Random random = new Random(System.nanoTime());

		List<String> numberStrings = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			numberStrings.add(String.valueOf(random.nextInt()));
		}

		return numberStrings;
	}
}
