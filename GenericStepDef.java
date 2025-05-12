package org.cucumbertaf.stepdefs;

import java.util.concurrent.TimeUnit;

import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.PropertyUtil;

import com.google.common.util.concurrent.Uninterruptibles;

import io.cucumber.java.en.Given;
//fix
public class GenericStepDef extends BaseStepDef{

    public GenericStepDef(TestContext context) {
        super(context);
    }

    @Given("launch application")
    public void launch_application() {
        driver.get(PropertyUtil.getProperty("appUrl"));
        //driver.get("https://9b71-49-205-34-227.ngrok-free.app/identityiq/login.jsf?prompt=true");
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        System.out.println("-------user_launches_application");
    }

    //@When("user perform web table testing")
    //public void user_webtable_testing() {
       // OrderPage orderPage = new OrderPage(driver);
      //  WebTable table = new WebTable(orderPage.getTable());
      //  System.out.println(table.getNumberOfRows());
       // System.out.println(table.getNumberOfColumns());
      //  System.out.println(table.getColumnNames());
       // System.out.println(table.getCellValueAt(3, 3));
       // System.out.println(table.getColumnIndexOf("Designation"));
        //int rowNum=table.getRowNumberHavingValueAtColumn("S148106", "Employee ID");
       // System.out.println(rowNum);
        //table.clickAt(rowNum, 1);
        //table.selectAt(rowNum, 8,"Pending");
        //Uninterruptibles.sleepUninterruptibly(15, TimeUnit.SECONDS);
    //}

}
