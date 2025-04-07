package qrcodescanner
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

import io.appium.java_client.AppiumDriver
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



class QRCodeScanner {
	@And("User tap burger menu on the header")
	def Tap_Burger_Menu() {

		Mobile.tap(findTestObject('Object Repository/Header/BurgerMenuButton'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenu/CloseBurgerMenu'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/ALLITEMSSubMenu'), 10), 'ALL ITEMS', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/WEBVIEWSubMenu'), 10), 'WEBVIEW', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/QRCODESCANNERSubMenu'), 10), 'QR CODE SCANNER', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/GEOLOCATIONSubMenu'), 10), 'GEO LOCATION', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/DRAWINGSubMenu'), 10), 'DRAWING', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/ABOUTSubMenu'), 10), 'ABOUT', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/LOGOUTSubMenu'), 10), 'LOGOUT', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/RESETAPPSTATESubMenu'), 10), 'RESET APP STATE', false)

		println 'Successfully open the burger menu'
	}

	@When("User tap QR CODE SCANNER sub menu")
	def Tap_QR_Code_Scanner() {

		Mobile.tap(findTestObject('Object Repository/Header/BurgerMenu/QRCODESCANNERSubMenu'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/QRCodeScannerPage/Information1'), 10), 'Scan a QR Code that contains a url.', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/BurgerMenu/QRCodeScannerPage/Information2'), 10), 'It will be opened in the default browser.', false)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenu/QRCodeScannerPage/CameraScanner'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenu/QRCodeScannerPage/GridHelper'), 10)

		println 'Successfully open the QR CODE SCANNER page'
	}

	@Then("User verifies that the system has successfully opens the URL address in the QR code")
	def Verify_opening_the_URL_address_in_the_QR_code() {

		Mobile.delay(7)

		AppiumDriver driver = MobileDriverFactory.getDriver()

		WebElement addressBar = driver.findElement(By.id("com.android.chrome:id/url_bar"))

		String currentUrl = addressBar.getText()

		Mobile.verifyMatch(currentUrl, 'testsigma.com', false)

		Mobile.closeApplication()

		Mobile.startExistingApplication("com.swaglabsmobileapp")

		Mobile.delay(2)

		println 'The system has successfully opens the URL address in the QR code'
	}
}