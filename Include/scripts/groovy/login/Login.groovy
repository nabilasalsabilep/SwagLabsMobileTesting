package login
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

import randomdata.*

class Login {
	@Given("User opens swag labs application via mobile device")
	def Opens_Swag_Labs_Mobile_Apps() {

		Mobile.startApplication(System.getProperty('user.dir') + '/Data Files/SauceLabs.apk', false)

		Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/ApplicationIcon'), 30)

		Mobile.verifyElementExist(findTestObject('Object Repository/LoginPage/ApplicationIcon'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/UsernameInputField'), 10), 'Username', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/PasswordInputField'), 10), 'Password', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/LOGINButton'), 10), 'LOGIN', false)

		Mobile.verifyElementExist(findTestObject('Object Repository/LoginPage/LoginIllustration'), 10)

		println 'Successfully launch the apps'
	}

	@When("User input valid username")
	def Input_Valid_Username() {

		Mobile.sendKeys(findTestObject('Object Repository/LoginPage/UsernameInputField'), GlobalVariable.username)

		println 'Successfully entered valid username data into the field'
	}

	@When("User input invalid username")
	def Input_Invalid_Username() {

		RandomData randusername = new RandomData()

		Mobile.sendKeys(findTestObject('Object Repository/LoginPage/UsernameInputField'), randusername.generateRandomUsername())

		println 'Successfully entered invalid username data into the field'
	}

	@And("User input valid password")
	def Input_Valid_Password() {

		Mobile.sendKeys(findTestObject('Object Repository/LoginPage/PasswordInputField'), GlobalVariable.password)

		println 'Successfully entered valid password data into the field'
	}

	@And("User input invalid password")
	def Input_Invalid_Password() {

		RandomData randpass = new RandomData()

		Mobile.sendKeys(findTestObject('Object Repository/LoginPage/PasswordInputField'), randpass.generateRandomPassword())

		println 'Successfully entered invalid password data into the field'
	}

	@And("User tap Login button")
	def Tap_Login() {

		Mobile.tap(findTestObject('Object Repository/LoginPage/LOGINButton'), 10)

		println 'successful tapping the login button'
	}

	@Then("The system displays the products page")
	def Displays_Products_Page() {

		Mobile.waitForElementPresent(findTestObject('Object Repository/Header/ApplicationIcon'), 30)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenuButton'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/ApplicationIcon'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/CartButton'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/ProductsPage/PRODUCTSPageTitle'), 10), 'PRODUCTS', false)

		Mobile.verifyElementExist(findTestObject('Object Repository/ProductsPage/ViewGroupButton'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/ProductsPage/SortButton'), 10)

		println 'Successfully displays the dashboard page'
	}

	@Then("The system will display a failed login error message")
	def Verify_Error_Message() {

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/LoginPage/ErrorMessage'), 10), 'Username and password do not match any user in this service.', false)

		println 'Successfully display error message on login page'
	}
}