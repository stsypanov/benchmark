package com.tvs;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by stsypanov on 06.04.2015.
 */

public class RegExpBenchmark {
	private static Pattern PATTERN;
	private static Pattern MORE_PATTERN;

	private static final String splitString = "[<aaaaaaa, bbbbbbbbbbb<aaaa, bbbbb<aaa, bbbbb<aa]";

	@BeforeClass
	public static void setUpClass() throws Exception {
		PATTERN = Pattern.compile("<[^>]+>");
		MORE_PATTERN = Pattern.compile(">", Pattern.LITERAL);
	}

	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	@Test
	@SuppressWarnings("all")
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testDynamicRegExp() throws Exception {
		List<String> messages = getMessages();
		for (int i = 0; i < messages.size(); i++) {
			messages.set(i, messages.get(i).replaceAll("<[^>]+>", ""));
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	public void testPrecompiledRegExp() throws Exception {
		List<String> messages = getMessages();
		for (int i = 0; i < messages.size(); i++) {
			messages.set(i, PATTERN.matcher(messages.get(i)).replaceAll(""));
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 100, warmupRounds = 5)
	public void testDynamicSplit(){
		List<String> messages = getMessages();
		for(String s : messages) {
			String toString = Arrays.toString(s.split(">"));
			Assert.assertEquals(toString, splitString);
		}
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 100, warmupRounds = 5)
	public void testPrecompiledSplit(){
		List<String> messages = getMessages();
		for(String s : messages) {
			String toString = Arrays.toString(MORE_PATTERN.split(s));
			Assert.assertEquals(toString, splitString);
		}
	}

	public List<String> getMessages() {
		int capacity = 10000;
		List<String> result = new ArrayList<>(capacity);
		for (int i = 0; i < capacity; i++) {
			result.add("<aaaaaaa>bbbbbbbbbbb<aaaa>bbbbb<aaa>bbbbb<aa>");
		}
		return result;
	}

}
