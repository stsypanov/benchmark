package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stsypanov on 06.04.2015.
 */
public class CollectionToArrayBenchmark {
	public static final int ITERATION_COUNT = 10000;
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	public static final int STRING_LIST_SIZE = 50000;

	@Test
	@SuppressWarnings("all")
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testToEmptyArray() throws Exception {
		List<String> messages = getMessages();
		for (int i = 0; i < ITERATION_COUNT; i++) {
			String[] strings = messages.toArray(new String[]{});
			assert strings.length == STRING_LIST_SIZE;
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testToArrayOfCorrectSize() throws Exception {
		List<String> messages = getMessages();
		for (int i = 0; i < ITERATION_COUNT; i++) {
			String[] strings = messages.toArray(new String[messages.size()]);
			assert strings.length == STRING_LIST_SIZE;
		}
	}

	public List<String> getMessages() {
		List<String> result = new ArrayList<>(STRING_LIST_SIZE);
		for (int i = 0; i < STRING_LIST_SIZE; i++) {
			result.add("<aaaaaaa>bbbbbbbbbbb<aaaa>bbbbb<aaa>bbbbb<aa>");
		}
		return result;
	}
}
