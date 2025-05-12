package org.cucumbertaf.testlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;

public class TestContext {

	private WebDriver driver;
	private String featureName;
	private String scenarioName;
	// Modified by Kuldeep
	private Map<String, String> data;
	private Map<String, Object> exl_write_data_map;
	private Map<String, List<String>> exl_list_write_data_map = new HashMap<>();
	private Map<String, String> moreData = new HashMap<>();
	private Scenario logger;
	private String str_value;
	private ArrayList<String> test_list = new ArrayList<>();
	private List<String> common_list = new ArrayList<>();
	private ExtentTest extentTest;

	public ExtentTest getExtentTest() {
		return extentTest;
	}

	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	// Modified by Kuldeep
	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public void setMoreData(Map<String, String> data) {
		this.moreData = data;
	}

	public void setLogger(Scenario logger) {
		this.logger = logger;
	}

	public void setStringvalue(String str_value) {
		this.str_value = str_value;
	}

	// Modified by Kuldeep
	public void setListvalue(String str_value) {
		this.test_list.add(str_value);
	}

	public void setcommonlist(List<String> common_list) {
		this.common_list = common_list;
	}

	public List<String> getcommonlist() {
		return common_list;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getFeatureName() {
		return featureName;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	// Modified by Kuldeep
	public Map<String, String> getData() {
		return data;
	}

	public Map<String, String> getMoreData() {
		return moreData;
	}

	public Scenario getLogger() {
		return logger;
	}

	public String getStringvalue() {
		return str_value;
	}

	// Modified by Kuldeep
	public ArrayList<String> getListvalue() {
		return test_list;
	}

	public Map<String, Object> getExl_write_data_map() {
		return exl_write_data_map;
	}

	public void setExl_write_data_map(Map<String, Object> exl_write_data_map) {
		this.exl_write_data_map = exl_write_data_map;
	}

	public Map<String, List<String>> getExl_list_write_data_map() {
		return exl_list_write_data_map;
	}

	public void setExl_list_write_data_map(Map<String, List<String>> exl_list_write_data_map) {
		this.exl_list_write_data_map = exl_list_write_data_map;
	}

}
