import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

WebUI.verifyElementVisible(  findTestObject('Object Repository/Log-in Page/header_Login')  )
WebUI.sendKeys(   findTestObject('Object Repository/Log-in Page/input_Username') , GlobalVariable.UserName)
WebUI.sendKeys(  findTestObject('Object Repository/Log-in Page/input_Password')  , GlobalVariable.Password)
WebUI.click(  findTestObject('Object Repository/Log-in Page/button_Login')  )

WebUI.waitForElementPresent(  findTestObject('Object Repository/BookingPage/h2_Make Appointment') , 15)
WebUI.click(  findTestObject('Object Repository/SideMenu/a_menu-toggle') )
WebUI.verifyEqual( CustomKeywords.'utils.WebKeywords.isLoggedIn'() , true)
WebUI.click(  findTestObject('Object Repository/SideMenu/a_menu-toggle') )