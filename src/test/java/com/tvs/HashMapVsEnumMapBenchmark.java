package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stsypanov on 06.04.2015.
 */
public class HashMapVsEnumMapBenchmark {
	public static final int ITERATIONS = 100000;

	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	private static int totalHashCode;

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 2)
	public void testHashMap() throws Exception {
		for (int i = 0; i < ITERATIONS; i++) {
			Map<TestEnum, String> map = new HashMap<>();
			Map<TestEnum, String> testEnumStringMap = fillTheMap(map);
			for (TestEnum e : TestEnum.values()) {
				String s = testEnumStringMap.get(e);
				totalHashCode += s.hashCode();
			}
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 2)
	public void testEnumMap() throws Exception {
		for (int i = 0; i < ITERATIONS; i++) {
			Map<TestEnum, String> map = new EnumMap<>(TestEnum.class);
			Map<TestEnum, String> testEnumStringMap = fillTheMap(map);
			for (TestEnum e : TestEnum.values()) {
				String s = testEnumStringMap.get(e);
				totalHashCode += s.hashCode();
			}
		}
	}

	public Map<TestEnum, String> fillTheMap(Map<TestEnum, String> map) {
		for (TestEnum e : TestEnum.values()) {
			map.put(e, e.name());
		}
		return map;
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println(totalHashCode);
	}
}
