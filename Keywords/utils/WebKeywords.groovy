package utils
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import java.util.List

class WebKeywords {
	Random random = new Random()
	/*
	 * Check if user is logged-in
	 *  @return true if user is logged in
	 */
	@Keyword
	def isLoggedIn() {
		WebUI.verifyElementVisible(  findTestObject('Object Repository/SideMenu/a_Logout')  )
	}

	/**
	 * 	 * 
	 * @param testObject dropdown list object
	 * @param subText facility name
	 */
	@Keyword
	def selectDropdownitemByText(TestObject testObject, String subText) {
		WebElement element = WebUiCommonHelper.findWebElement(testObject, 30)
		Select dropdownList = new Select(element)

		for (WebElement option : dropdownList.getOptions()) {
			if (option.getText().toLowerCase().contains(subText.toLowerCase())) {
				dropdownList.selectByVisibleText(option.getText())
				break
			}
		}
	}

	/**
	 * Get a reference to radio button object
	 * @param label for radio button
	 * @return Radio button object with corresponding label
	 */
	@Keyword
	def getRadioButtonByLabel(String label) {
		TestObject radioButton = new TestObject("LabelForRadioButton")
		radioButton.addProperty("xpath", ConditionType.EQUALS, "//label[text()[contains(.,'${label}')]]")
		radioButton
	}

	/**
	 * @param label for check box
	 * @return check box object with corresponding label
	 */
	@Keyword
	def getCheckBoxByLabel(String label) {
		TestObject checkbox = new TestObject("LabelForCheckbox")
		checkbox.addProperty("xpath", ConditionType.EQUALS, "//label[text()[contains(.,'${label}')]]")
		checkbox
	}

	/**
	 * Select random day from the coming month 
	 */
	@Keyword
	def selectDay() {
		WebUI.click(findTestObject('Object Repository/BookingPage/button_calendar')  )
		WebUI.click(  findTestObject('Object Repository/BookingPage/link_next_month')  )
		List<WebElement> days = WebUiCommonHelper.findWebElements( findTestObject('Object Repository/BookingPage/link_day')  , 10)
		int randomDay = random.nextInt(days.size())
		days.get(randomDay).click()
	}
	
	/**
	 * Get reference to object with corresponding text
	 * @param info included in Test Object
	 * @return Test Object according to text
	 */
	@Keyword
	def getCellInfo(String info) {
		TestObject div = new TestObject('info')
		div.addProperty("xpath",ConditionType.EQUALS,"//div[@class='col-xs-8' and contains(.,'${info}')]")
	}


	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}