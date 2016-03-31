package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * Created by stsypanov on 06.04.2015.
 */
public class StringEmptinessBenchmark {
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	public static final String EMPTY_STRING = "";
	public static final String NOT_EMPTY_STRING = "323t43tds";

	public static final long ITERATIONS = 1000000000l;

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5, callgc = false)
	public void testWithEquals() throws Exception {
		for (long i = 0; i < ITERATIONS; i++) {
			boolean equals = "".equals(EMPTY_STRING);
			assert equals;
			equals = "".equals(NOT_EMPTY_STRING);
			Assert.assertFalse(equals);
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5, callgc = false)
	public void testWithIsEmpty() throws Exception {
		for (long i = 0; i < ITERATIONS; i++) {
			boolean equals = isEmpty(EMPTY_STRING);
			assert equals;
			equals = isEmpty(NOT_EMPTY_STRING);
			Assert.assertFalse(equals);
		}
	}

	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
}
