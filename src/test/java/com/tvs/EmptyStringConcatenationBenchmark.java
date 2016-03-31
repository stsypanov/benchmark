package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.Random;

/**
 * Created by stsypanov on 06.04.2015.
 */
public class EmptyStringConcatenationBenchmark {
	private static final int ITERATIONS = 100000;
	private static final Random rnd = new Random();

	private static int totalHashCode;

	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 2)
	public void testConcatenationWithEmptyString() throws Exception {
		for (int i = 0; i < ITERATIONS; i++) {
			int j = rnd.nextInt(99);
			String stringValue = "" + j;
			totalHashCode += stringValue.hashCode();
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 2)
	public void testUseValueOf() throws Exception {
		for (int i = 0; i < ITERATIONS; i++) {
			int j = rnd.nextInt(99);
			String stringValue = String.valueOf(j);
			totalHashCode += stringValue.hashCode();
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println(totalHashCode);
	}
}
