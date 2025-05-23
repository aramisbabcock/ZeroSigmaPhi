package org.cucumbertaf.basepageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//fix
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }
    
    public void clickElement(WebElement element) {
    	if (element.isDisplayed()) {
            	element.click();
        }
    }
    
    public void setInputBoxValue(WebElement element, String value) {
    	if (element.isDisplayed()) {
    		element.clear();
    		element.sendKeys(value);
    	}
    }

    public void selectByTextFromDropdown(WebElement element, String optionToSelect) {
        new Select(element).selectByVisibleText(optionToSelect);
    }

    public void selectByIndexFromDropdown(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public void selectByValueFromDropdown(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public List<String> getAllDropdownValues(WebElement element) {
        Select select = new Select(element);
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    public void waitForPage(int value) throws InterruptedException {
    	Thread.sleep(value);
    	
    }

    public void waitForElement(WebElement element) throws InterruptedException {
        wait.until(visibilityOf(element));
        Thread.sleep(2000);
    }

    public void waitForAllElements(List<WebElement> element) {
        wait.until(visibilityOfAllElements(element));
    }
    
    public void waitForElementToHide(WebElement element) {
        wait.until(invisibilityOf(element));
    }
    
    public void waitForAllElementsToHide(List<WebElement> element) {
        wait.until(invisibilityOfAllElements(element));
    }

    public List<String> getListOfValues(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    public void selectElementFromDropDown(List<WebElement> elements, String elename) {
    	for(int i = 0; i<=elements.size()-1; i++) {
             if(elements.get(i).getText().contains(elename)) {
            	 elements.get(i).click();
                break;
             }
        }
    }
    
    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getInputBoxValue(WebElement element) {
        return element.getAttribute("value");
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public void selectCheckBox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unSelectCheckBox(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void selectRadioButton(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unSelectRadioButton(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void switchToFrameWithIndex(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrameWithNameOrId(String nameOrid) {
        driver.switchTo().frame(nameOrid);
    }

    public void switchToFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToFrameWithIndexWithWait(int index) {
        wait.until(frameToBeAvailableAndSwitchToIt(index));
        switchToFrameWithIndex(index);
    }

    public void switchToFrameWithNameOrIdWithWait(String nameOrid) {
        wait.until(frameToBeAvailableAndSwitchToIt(nameOrid));
        switchToFrameWithNameOrId(nameOrid);
    }

    public void switchToFrameByElementWithWait(WebElement element) {
        wait.until(frameToBeAvailableAndSwitchToIt(element));
        switchToFrameByElement(element);
    }
    
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("arguments[0].scrollIntoView(true);", element);
    }
    
    //Modified by Kuldeep
    public void clickmultipleelements(List<WebElement> elements) {
		for (WebElement element : elements) {
			scrollToElement(element);

			element.click();
		}
	}

    public void switchToWindowByTitle(String windowTitle) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getTitle().equals(windowTitle)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with title " + windowTitle + " not found");
        }
    }

    public void switchToWindowByPartialTitle(String windowTitle) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getTitle().contains(windowTitle)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with title " + windowTitle + " not found");
        }
    }

    public void switchToWindowByUrl(String url) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getCurrentUrl().equals(url)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with url " + url + " not found");
        }
    }

    public void switchToWindowByPartialUrl(String url) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getCurrentUrl().contains(url)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with url " + url + " not found");
        }
    }

    public String getCssProperty(WebElement element, String propertyName) {
        return element.getCssValue(propertyName);
    }

    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public boolean hasAttribute(WebElement element, String attributeName) {
        try {
            element.getAttribute(attributeName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String> createadaliasname(String fname , String lname) {
		List<String> obj1 = new ArrayList<String>();
		int rowval = fname.length();
		for(int i =1 ; i<=rowval;i++) {
			String strval = fname.substring(0, i).toLowerCase()+lname.toLowerCase();
			if(strval.length()<=8) {
				String strval1 =strval;
				obj1.add(strval1);
			}else {
				String strval1 =strval.substring(0, 8);
				obj1.add(strval1);
			}
		}
		
		return obj1;
	}
    
   
    public ArrayList<String> createemail(String fname, String lname , String emaildomain){
          ArrayList<String> arr = new ArrayList<String>();
          arr.add(fname + '.' + lname+"@" + emaildomain);
          for(int i=1; i<=10; i++) {
                arr.add(fname + '.' + lname + i+ "@" + emaildomain);
          }
             return arr;
    }
    
    public List<String> createusername(String fname, String lname , String emaildomain){
    	List<String> obj1 = new ArrayList<String>();
		int rowval = fname.length();
		for(int i =1 ; i<=rowval;i++) {
			String strval = fname.substring(0, i).toLowerCase()+lname.toLowerCase();
			if(strval.length()<=8) {
				String strval1 =strval+"@" + emaildomain;
				obj1.add(strval1);
			}else {
				String strval1 =strval.substring(0, 8)+"@" + emaildomain;
				obj1.add(strval1);
			}
		}
		
		return obj1;
    }
    
    public List<String> createdistinguishname(String fname, String lname , String distinguishname){
    	List<String> obj1 = new ArrayList<String>();
		int rowval = fname.length();
		for(int i =1 ; i<=rowval;i++) {
			String strval = fname.substring(0, i).toLowerCase()+lname.toLowerCase();
			if(strval.length()<=8) {
				String strval1 ="CN="+strval+","+distinguishname;
				obj1.add(strval1);
			}else {
				String strval1 ="CN="+strval.substring(0, 8)+","+distinguishname;
				obj1.add(strval1);
			}
		}
		
		return obj1;
    }
    
    public List<String> createmsds(String fname, String lname , String msds){
    	List<String> obj1 = new ArrayList<String>();
		int rowval = fname.length();
		for(int i =1 ; i<=rowval;i++) {
			String strval = fname.substring(0, i).toLowerCase()+lname.toLowerCase();
			if(strval.length()<=8) {
				String strval1 =msds+strval;
				obj1.add(strval1);
			}else {
				String strval1 =msds+strval.substring(0, 8);
				obj1.add(strval1);
			}
		}
		
		return obj1;
    }
    
    public String currentdate() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
  }

  public String sapcurrentdate() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
  }

    
}
