package logout
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class logout {
	@And("User tap LOGOUT sub menu")
	def Tap_Logout() {
		
		Mobile.tap(findTestObject('Object Repository/Header/BurgerMenu/LOGOUTSubMenu'), 10)
		
		println 'Successfully tap logout'
	}
	
	@Then("User returns to the login page")
	def Returns_to_the_Login_Page() {
		Mobile.verifyElementExist(findTestObject('Object Repository/LoginPage/ApplicationIcon'), 10)
		
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/UsernameInputField'), 10), 'Username', false)
		
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/PasswordInputField'), 10), 'Password', false)
		
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/LOGINButton'), 10), 'LOGIN', false)
		
		Mobile.verifyElementExist(findTestObject('Object Repository/LoginPage/LoginIllustration'), 10)
		
		println 'Successfully returns to the login page'
		
	}
}