package org.cucumbertaf.testbase;

import org.cucumbertaf.pageobjects.AccessRequestPage;
import org.cucumbertaf.pageobjects.ApplicationDefinitionPage;
import org.cucumbertaf.pageobjects.AttributesPage;
import org.cucumbertaf.pageobjects.GroupsPage;
import org.cucumbertaf.pageobjects.LoginPage;
import org.cucumbertaf.pageobjects.ManageUserAccessPage;
import org.cucumbertaf.pageobjects.ManagerCertificationPage;
import org.cucumbertaf.pageobjects.TaskPage;
import org.cucumbertaf.pageobjects.WorkItemPage;
import org.cucumbertaf.testlib.TestContext;
import org.cucumbertaf.utils.CryptoUtil;
import org.cucumbertaf.testlib.CTAFAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BaseStepDef {

	protected final WebDriver driver;
	//Modified by Aramis Babcock
	protected final Map<String, String> data;
	protected final CTAFAssert assertLogger;
	protected final TestContext context;

	protected LoginPage loginpage;
	protected ApplicationDefinitionPage applicationdefinitionpage;
	protected ManageUserAccessPage manageUserAccessPage;
	protected WorkItemPage workitempage;
	protected GroupsPage groupsPage;
	protected TaskPage taskpage;
	protected AttributesPage attributespage;
	protected ManagerCertificationPage managercertificationpage;
	protected AccessRequestPage accessRequestPage;

	protected JavascriptExecutor jsExecutor;

	protected String username, password;
	protected String eidvalue, fnameval, lnameval, comment, fullName,useremailaddress, alteidvalue, manager, securitymanager;
	protected String workitemvalue, descriptionval, desval, workitemmanagerval, workitemmanageraction, workitemworkgroupval,
			workitemworkgroupaction, accessrequest_action, accessrequest_type, accessval, accessRemoveVal, systemname, identitycubeURL, env;
	//Variables for System Attributes
	//AFM
	protected String contractExpirationDate,
	//AFPROMS
	mpfLevelID, managementLevelID, boardType,
	//ALMSS
	typeLabel, iaTraining,
	//CON-IT
	supervisorEmail, fpdsngID, grade,
	//EBIS
		//NA
	//EESOH-MIS	
	nationalGuardReserveFlag, contractNumber, contractorName, contractStartDate, contractEndDate,
	//FMSuite
	jobtitle, rank, base,
	//ILS-S
	
	//JOCASII-****
	
	//REMIS
	
	//RTS
	
	//Multiple Systems
	phonecomm;

	protected int numberOfApprovers;
	protected String ent0Man, ent1Man, ent0SecMan, ent1SecMan, ent0Third, ent1Third, appDenyAll,entAddApproveDeny, entRemoveApproveDeny;
	protected String[] accessvals, accessRemoveVals, workitem_action;
	protected String[][] entAddApproveDenyList, entRemoveApproveDenyList;
	protected String applicationtypeval, applicationNameval, applicationOwnerval;
	protected String idrefreshval, str_name, str_taskval, str_result, str_rowval, taskval;
	protected String manager_eid, manager_password, forwardto_user, workgroupuser_eid, workgroupuser_password;
	protected String certificationtype, certificationaccesslevel, certificationexclusionrule;

	public BaseStepDef(TestContext context) {
		this.driver = context.getDriver();
		this.data = context.getData();
		this.assertLogger = new CTAFAssert(context.getExtentTest());
		this.context = context;

		this.loginpage = new LoginPage(driver);
		this.applicationdefinitionpage = new ApplicationDefinitionPage(driver);
		this.taskpage = new TaskPage(driver);
		this.workitempage = new WorkItemPage(driver);
		this.manageUserAccessPage = new ManageUserAccessPage(driver);
		this.attributespage = new AttributesPage(driver);
		this.managercertificationpage = new ManagerCertificationPage(driver); 
		this.accessRequestPage = new AccessRequestPage(driver);
		this.groupsPage = new GroupsPage(driver);
		
		
		this.jsExecutor = (JavascriptExecutor) driver;
		//Modified by Aramis Babcock
		username = data.get("user_name");
		password = CryptoUtil.getDecryptedPassword(data.get("user_password"));
		
		manager_eid = data.get("manager_eid");
		manager_password = CryptoUtil.getDecryptedPassword(data.get("manager_password"));
		workgroupuser_eid = data.get("workgroupuser_eid");
		workgroupuser_password = CryptoUtil.getDecryptedPassword(data.get("workgroupuser_password"));

		eidvalue = data.get("eid");
		fnameval = data.get("fname");
		lnameval = data.get("lname");
		useremailaddress = data.get("useremail");
		systemname = data.get("system_name");
		identitycubeURL = data.get("identity_cube_url");
		env = data.get("environment (TDE or PRE-PROD)");
		
		accessrequest_type = data.get("access_type");
		accessrequest_action = data.get("accessrequest_action");
		accessval = data.get("access");
		accessRemoveVal = data.get("access remove");
		//String numberApp = data.get("Total Number of Approvers");
		//numberOfApprovers = Integer.parseInt(numberApp); 
		numberOfApprovers = 3; 
		
		entAddApproveDeny = data.get("entAdd approval list");
		entRemoveApproveDeny = data.get("entRemove approval list");
		//entAddApproveDenyList, entRemoveApproveDenyList
		comment = data.get("comment");
		//add more below
		//AFIPPS
			//NA
		//AFM
		contractExpirationDate = data.get("contractExpirationDate (MM/DD/YYYY) Ex. 11/22/2029 (AFM)");
		//AFPROMS
		mpfLevelID = data.get("MPF Level ID (AFPROMS)");
		managementLevelID = data.get("Management Level ID (AFPROMS)");
		boardType = data.get("Board Type (AFPROMS)");
		//ALMSS
		typeLabel = data.get("Type-Label (ALMSS)");
		iaTraining = data.get("IA Training (ALMSS)");
		//CON-IT
		supervisorEmail = data.get("supervisorEmail (CON-IT)");
		fpdsngID = data.get("FPDSNG ID (CON-IT)");
		grade = data.get("grade (CON-IT)");
		//EBIS
			//NA
		//EESOH-MIS
		nationalGuardReserveFlag = data.get("nationalGuardReserveFlag (EESOH-MIS)");
		contractNumber = data.get("contractNumber (EESOH-MIS)");
		contractorName = data.get("contractorName (EESOH-MIS)");
		contractStartDate = data.get("contractStartDate (MM/DD/YYYY) Ex. 11/22/2024 (EESOH-MIS)"); //(DD-MMM-YYYY) Ex. 22-Nov-2029
		contractEndDate = data.get("contractEndDate (MM/DD/YYYY) Ex. 11/22/2029 (EESOH-MIS)"); //(DD-MMM-YYYY) Ex. 22-Nov-2029
		//FMSuite
		jobtitle = data.get("jobtitle (FMSuite)");
		rank = data.get("rank (FMSuite)");
		base = data.get("base (FMSuite)");
		//ILS-S
			//Check Multiple Systems
		//JOCASII-****
			//NA
		//REMIS
			//NA
		//RTS
			//NA
		//Multiple Systems
		phonecomm = "2101234567";//data.get("phonecomm (CON-IT, EESOH-MIS, FMSuite, ILS-S)");//CON-IT, EESOH-MIS, FMSuite, ILS-S
		//add more above
		manager = data.get("manager");
		securitymanager = data.get("securitymanager");
		
		//reworking need to remove later
		ent0Man = data.get("ent0Man");
		ent1Man = data.get("ent1Man");
		ent0SecMan = data.get("ent0SecMan");
		ent1SecMan = data.get("ent1SecMan");
		ent0Third = data.get("ent0Third");
		ent1Third = data.get("ent1Third");
		appDenyAll = data.get("appDenyAll");
//////////////////
		workitemvalue = data.get("workitem");
		workitemmanagerval = data.get("workitem_manager");
		workitemmanageraction = data.get("workitem_manageraction");
		workitemworkgroupval = data.get("workitem_workgroup");
		workitemworkgroupaction = data.get("workitem_workgroupaction");
		workitem_action = data.get("workitem_action") == null ? null : data.get("workitem_action").split(",");
		forwardto_user = data.get("forwardto_user");

		applicationNameval = data.get("application_name");
		applicationtypeval = data.get("application_type");
		applicationOwnerval = data.get("application_owner");

		taskval = data.get("Task");
		
		certificationtype = data.get("certification_type");
		certificationaccesslevel = data.get("certification_accesslevel");
		certificationexclusionrule = data.get("certification_exclusionrule");
		
	}

}
