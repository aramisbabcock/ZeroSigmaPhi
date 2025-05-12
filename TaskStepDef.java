package org.cucumbertaf.stepdefs;

import java.util.List;
import java.util.Map;

import org.cucumbertaf.pageobjects.TaskPage;
import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.testlib.CTAFAssert;
import org.cucumbertaf.utils.PropertyUtil;
import org.cucumbertaf.utils.WebTable;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TaskStepDef extends BaseStepDef{
	

	public TaskStepDef(TestContext context) {
		super(context);
		

	}

	@Then("click on setup link")
	public void click_on_setup_link() throws InterruptedException {
		try {
			taskpage.clicksetup();
			assertLogger.log("Clicked on setup dropdown");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@Then("select tasks link from dropdown")
	public void select_tasks_link() throws InterruptedException {
		try {
			taskpage.clicktasks(PropertyUtil.getProperty("setuptype"));
			assertLogger.log("Clicked on task link");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@And("validate task page is displayed")
	public void validate_task_page_is_displayed() throws InterruptedException {
		String tasktitleval = taskpage.gettasktitle();
		assertLogger.assert_equals(tasktitleval, PropertyUtil.getProperty("setuptype"),
				"Task page is displayed as expected", "Task page is not displayed as expected");
	}

	@Then("search task in tasks tab")
	public void search_task() throws InterruptedException {
		try {
			taskpage.searchtask(taskval);
			taskpage.clicksearchbutton();
			assertLogger.log("Searched for the task < " + taskval + " >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate task appeared in search table")
	public void validate_task_in_search_results() throws InterruptedException {
		String tasknameString = taskpage.getNameFromSearchTable();
		assertLogger.assert_equals(taskval, tasknameString, "Task Search Results - Task is in search result as expected",
				"Task not found in search result");
	}

	@Then("click on task link")
	public void click_on_task_link() throws InterruptedException {
		try {
			taskpage.clicktasktype();
			assertLogger.log("Clicked on searched task < " + taskval + " >");
		} catch (InterruptedException e) {

			assertLogger.log(e.getMessage());
		}
	}

	@And("validate edit task is displayed")
	public void validate_edit_task_title() throws InterruptedException {
		String tasktitleval = taskpage.getedittasktitle();
		assertLogger.assert_equals(tasktitleval, PropertyUtil.getProperty("edittask"),
				"Edit task page is displayed as expected", "Edit task page is not displayed as expected");
	}

	@Then("execute task and validate task message is displayed")
	public void execute_task() throws InterruptedException {
		taskpage.clicksaveexecute();
		String popupval = taskpage.getmessagetext();
		assertLogger.assert_equals(popupval, "''" + taskval + "'' has been executed in the background...",
				"Task message is displayed as expected", "Task message is not displayed as expected");
	}

	@Then("click on ok button")
	public void click_on_ok_button() {
		try {
			taskpage.clickok();
			assertLogger.log("Passed:Clicked on ok button >");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}

	}
	


	@Then("click on task results tab")
	public void click_task_results_tab() throws InterruptedException {
		try {
			taskpage.clicktaskresults();
			assertLogger.log("Clicked on task result tab");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

	@Then("search the task in task results tab")
	public void search_the_task_in_task_results_tab() throws InterruptedException {

		taskpage.settaskresult(taskval);
		taskpage.clicktaskresulticon();
		do {
			taskpage.clickrefresh();
			str_rowval = taskpage.getcelldata(2, 3);
		} while (str_rowval.equals(" "));
	}

	@And("validate task is successful")
	public void validate_task_is_successful() {
		str_rowval = taskpage.getcelldata(2, 1);
		if (str_rowval.contains(taskval)) {
			str_taskval = taskpage.getcelldata(2, 3);
		}
		assertLogger.assert_equals(str_taskval, PropertyUtil.getProperty("taskmsg"), "Task is successful as expected",
				"Task is not successful as expected");
	}

	@Then("click on task tab")
	public void click_task_tab() throws InterruptedException {
		try {
			taskpage.clicktasktab();
			assertLogger.log("Clicked on task tab");
		} catch (InterruptedException e) {
			assertLogger.log(e.getMessage());
		}
	}

}
