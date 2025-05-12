package org.cucumbertaf.utils;

import org.cucumbertaf.basepageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WebTable  {

	WebElement element;
	protected WebDriver driver;
	protected WebDriverWait wait;

	public WebTable(WebElement element) {
		this.element = element;

	}
	
	public WebTable(WebElement element,WebDriver driver) {
		this.driver = driver;
		this.element = element;

	}

	

	public int getNumberOfRows() {
		// for sub-element searching ./ is required
		// without '.' searching is done in whole page
		return element.findElements(By.xpath("//tbody//tr")).size();
	}
	
	public int getidentityRows() {
		// for sub-element searching ./ is required
		// without '.' searching is done in whole page
		return element.findElements(By.xpath("tbody/tr")).size();
	}

	public int getNumberOfColumns() {
		int size = element.findElements(By.xpath("//thead//th")).size();
		if (size == 0) {
			size = element.findElements(By.xpath("//tr[1]//td")).size();
		}
		return size;
	}

	public String getCellValueAt(int row, int col) throws NoSuchElementException {

		return element.findElement(By.xpath("tbody/tr[" + row + "]/td[" + col + "]")).getText();

	}

	public int checkelement(String obj) {

		return element.findElements(By.xpath(".//tr/td[normalize-space()=" + "'" + obj + "'" + "]")).size();
	}

	public void clickAt(int row, int col) {
		element.findElement(By.xpath(".//tr[" + row + "]//td[" + col + "]/div")).click();
	}
	
	public void clickAtattributesrowcol(int row, int col) {
		scrollToElement(element.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]")));
		element.findElement(By.xpath(".//tr[" + row + "]//td[" + col + "]/a")).click();
	}


	public WebElement getTableElement(int row, int col) {
		return element.findElement(By.xpath("tbody/tr[" + row + "]//td[" + col + "]"));
	}

	public void selectAt(int row, int col, String textToSelect) {
		WebElement dropdown = element.findElement(By.xpath(".//tr[" + row + "]//td[" + col + "]//select"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(textToSelect);
	}

	public void enterAt(int row, int col, String valueToEnter) {
		WebElement input = element.findElement(By.xpath(".//tr[" + row + "]//td[" + col + "]//input[@type='text']"));
		input.sendKeys(valueToEnter);
	}

	public List<String> getColumnNames() {
		List<String> lst = element.findElements(By.xpath("//thead//th")).stream().map(WebElement::getText)
				.map(String::trim).collect(Collectors.toList());
		if (lst.size() == 0) {
			lst = element.findElements(By.xpath("tbody/tr[1]//th")).stream().map(WebElement::getText).map(String::trim)
					.collect(Collectors.toList());
		}
		return lst;
	}

	public int getColumnIndexOf(String colName) {
		List<String> collect = getColumnNames();
		for (int i = 0; i < collect.size(); i++) {
			if (collect.get(i).trim().equals(colName)) {
				return i + 1;
			} else if (collect.get(i).trim().contains(colName)) {
				return i + 1;
			}
		}
		return -1;
	}

	public int getRowNumberHavingValueAtColumn(String value, String colName) {
		int columnIndexOf = getColumnIndexOf(colName);
		int noOfRows = getNumberOfRows();
		for (int i = 2; i <= noOfRows; i++) {
			String val = getCellValueAt(i, columnIndexOf).trim();
			if (value.equals(val)) {
				return i;
			}
		}
		return -1;
	}

	public String getCellValueAtRowAndCol(String value, int col1, int col2) {
		int noOfRows = getNumberOfRows();
		for (int i = 1; i <= noOfRows; i++) {
			//element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col1 + "]")).click();
			String val = getCellValueAt(i, col1).trim();
			if (value.equals(val)) {
				element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col1 + "]")).click();
				
				String val2 = element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]")).getText();
				return val2;
			}
		}

		return null;
	}

	public void scrollToElement(WebElement element) throws NoSuchElementException{

		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String getElementTextAtRowAndCol(String value, int col1, int col2) throws NoSuchElementException {

		int noOfRows = getNumberOfRows();
		for (int i = 1; i <= noOfRows; i++) {
			// element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col1 +
			// "]")).click();
			String val = getCellValueAt(i, col1).trim();
			if (value.equals(val)) {
				//element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]/table/tbody/tr/td/span/span"))
						//.click();
				scrollToElement(element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]/table/tbody/tr/td/span/span")));
                
				String str = element
						.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]/table/tbody/tr/td/span/span"))
						.getText();
				return str;
			}
		}
		return null;

	}

	public String getElementTextAtMultiColumn(String value, int col1, int col2) throws NoSuchElementException {
		String stri = null;
		int noOfRows = getNumberOfRows();
		for (int i = 1; i <= noOfRows; i++) {

			String val = getCellValueAt(i, col1).trim();
			if (value.equals(val)) {

				scrollToElement(element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]/table/tbody")));
				stri = element.findElement(By.xpath("tbody/tr[" + i + "]/td[" + col2 + "]/table/tbody")).getText();

				return stri;

			}
		}
		return null;

	}

}
