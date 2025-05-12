package org.cucumbertaf.testlib;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CTAFAssert {

	private final ExtentTest logger;

	public CTAFAssert(ExtentTest logger) {
		this.logger = logger;
	}

	public void log(String message) {
		logger.log(Status.PASS, message);
	}

	public void fail_log(String message) {
		logger.log(Status.FAIL, message);
	}
	
	public void skip_log(String message) {
		logger.log(Status.SKIP, message);
	}

	public void assert_equals(String act, String exp, String success_message, String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals(String act, String exp, String success_message, String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equals(boolean act, boolean exp, String success_message, String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals(boolean act, boolean exp, String success_message, String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_contains(String act, String exp, String success_message, String failure_message) {
		Assert.assertTrue(act.contains(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}
	
	public void assert_partialcontains(String act, String exp, String success_message, String failure_message) {
		Assert.assertTrue(act.contains(exp), failure_message);
		logger.log(Status.SKIP,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_contains(List<String> act, String exp, String success_message, String failure_message) {
		Assert.assertTrue(act.contains(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_contains(String exp, List<String> act, String success_message, String failure_message) {
		Assert.assertTrue(act.contains(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_contains(String act, String exp, String success_message, String failure_message) {
		Assert.assertFalse(act.contains(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_contains(List<String> act, String exp, String success_message, String failure_message) {
		Assert.assertFalse(act.contains(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_starts_with(String act, String exp, String success_message, String failure_message) {
		Assert.assertTrue(act.startsWith(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_starts_with(String act, String exp, String success_message, String failure_message) {
		Assert.assertFalse(act.startsWith(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equals(int act, int exp, String success_message, String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals(int act, int exp, String success_message, String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equals(int[] act, int[] exp, String success_message, String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS, success_message + " : " + "actual value = " + Arrays.toString(act) + " and"
				+ " expected value = " + Arrays.toString(exp));
	}

	public void assert_not_equals(int[] act, int[] exp, String success_message, String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS, success_message + " : " + "actual value = " + Arrays.toString(act) + " and"
				+ " expected value = " + Arrays.toString(exp));
	}

	public void assert_contains(Integer[] act, Integer exp, String success_message, String failure_message) {
		Assert.assertTrue(Arrays.asList(act).contains(exp), failure_message);
		logger.log(Status.PASS, success_message + " : " + "actual value = " + Arrays.toString(act) + " and"
				+ " expected value = " + exp);
	}

	public void assert_not_contains(Integer[] act, Integer exp, String success_message, String failure_message) {
		Assert.assertFalse(Arrays.asList(act).contains(exp), failure_message);
		logger.log(Status.PASS, success_message + " : " + "actual value = " + Arrays.toString(act) + " and"
				+ " expected value = " + exp);
	}

	public void assert_equals(List<String> act, List<String> exp, String success_message, String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}
	
	public void assert_contains(List<String> act, List<String> exp, String success_message, String failure_message) {
		Assert.assertTrue(act.containsAll(exp), failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals(List<String> act, List<String> exp, String success_message, String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equals_integer(List<Integer> act, List<Integer> exp, String success_message,
			String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals_integer(List<Integer> act, List<Integer> exp, String success_message,
			String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equals(Map<String, String> act, Map<String, String> exp, String success_message,
			String failure_message) {
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_not_equals(Map<String, String> act, Map<String, String> exp, String success_message,
			String failure_message) {
		Assert.assertNotEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);
	}

	public void assert_equalschar(char act, char exp, String success_message, String failure_message) {
		// TODO Auto-generated method stub
		Assert.assertEquals(act, exp, failure_message);
		logger.log(Status.PASS,
				success_message + " : " + "actual value = " + act + " and" + " expected value = " + exp);

	}
}
