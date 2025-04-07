package checkout
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
import scroll.*

class Checkout {

	@When("User taps Checkout button on the cart page")
	def Tap_Checkout() {

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CartPage/CHECKOUTButton'), 3)) {
			Mobile.scrollToText('CHECKOUT')
		}

		Mobile.tap(findTestObject('Object Repository/CartPage/CHECKOUTButton'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/InformationPage/CHECKOUTINFORMATIONPageTitle'), 10), 'CHECKOUT: INFORMATION', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/InformationPage/CANCELButton'), 10), 'CANCEL', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/InformationPage/CONTINUEButton'), 10), 'CONTINUE', false)

		println 'Successfully open Checkout: Your Information page'
	}

	@And("User enters the required data on the Checkout: Information")
	def Input_Your_Information_Data() {

		RandomData randomdata = new RandomData()

		Mobile.sendKeys(findTestObject('Object Repository/CheckoutPage/InformationPage/FirstNameInputField'), randomdata.generateRandomFirstName())

		Mobile.sendKeys(findTestObject('Object Repository/CheckoutPage/InformationPage/LastNameInputField'), randomdata.generateRandomLastName())

		Mobile.sendKeys(findTestObject('Object Repository/CheckoutPage/InformationPage/ZipPostalCodeInputField'), randomdata.generateRandomPostalCode())

		println 'Successfully input the required data on the Checkout: Your Information'
	}

	@And("User taps continue button")
	def Tap_Continue() {

		Mobile.tap(findTestObject('Object Repository/CheckoutPage/InformationPage/CONTINUEButton'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/CHECKOUTOVERVIEWPageTitle'), 10), 'CHECKOUT: OVERVIEW', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/QTYTableTitle'), 10), 'QTY', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/DESCRIPTIONTableTitle'), 10), 'DESCRIPTION', false)

		println 'Successfully open Checkout: Overview page'
	}

	@And("User Verifies Checkout Page: Overview")
	def Verify_Checkout_Overview() {

		List<WebElement> cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))

		int totalVerified = 0
		int totalToVerify = GlobalVariable.counts
		double calculatedSum = 0.0

		while (totalVerified < totalToVerify) {
			// Fetch current visible cart items
			cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))

			// Validate 2 items max per scroll
			int itemsToValidate = Math.min(2, totalToVerify - totalVerified)

			for (int i = 0; i < itemsToValidate && i < cartItems.size(); i++) {
				WebElement item = cartItems[i]

				// Get product info from inside this item block
				String name = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[1]")).getText()
				String desc = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[2]")).getText()
				String price = item.findElement(By.xpath(".//android.view.ViewGroup[4]/android.widget.TextView[1]")).getText()

				// Verifying with stored values
				Mobile.verifyMatch(name, GlobalVariable.productNames[totalVerified], false)
				Mobile.verifyMatch(price, GlobalVariable.productPrices[totalVerified], false)
				Mobile.verifyMatch(desc, GlobalVariable.productDesc[totalVerified], false)

				// Sum of all productPrice[i] value
				String cleanedPrice = price.replaceAll("[^0-9.]", "")
				double pricenumber = Double.parseDouble(cleanedPrice)
				calculatedSum += pricenumber

				totalVerified++
			}

			if (totalVerified < totalToVerify) {
				// Scroll down to reveal more items
				ScrollTo scrollto = new ScrollTo()
				scrollto.swipeaction(400, 1700, 400, 500)
				Mobile.delay(2)
			}
		}

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/PaymentInformation'), 10)) {

			Mobile.scrollToText('SauceCard #31337')
		}

		// Validate Payment Information
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/PaymentInformation'), 10), 'SauceCard #31337', false)

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/ShoppingInformation'), 10)) {

			Mobile.scrollToText('FREE PONY EXPRESS DELIVERY!')
		}

		// Validate Shipping Information
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/ShoppingInformation'), 10), 'FREE PONY EXPRESS DELIVERY!', false)


		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/ItemTotal'), 10)) {

			Mobile.scrollToText('Item Total')
		}

		// Validate Item Total
		String itemTotalText = Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/ItemTotal'), 10)

		String totalPriceValue = itemTotalText.replaceAll("[^0-9.]", "")

		double totalPrice = Double.parseDouble(totalPriceValue)

		Mobile.verifyMatch(totalPrice.toString(), calculatedSum.toString(), false)

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/Tax'), 10)) {

			Mobile.scrollToText('Tax')
		}

		// Validate Tax
		double taxAmount = totalPrice * 0.08

		double roundedTaxAmount = Math.ceil(taxAmount * 100.0) / 100.0

		String formattedTax = String.format("%.2f", roundedTaxAmount)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/Tax'), 10), 'Tax: $' + formattedTax, false)

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/Total'), 10)) {

			Mobile.scrollToText('CANCEL')
		}

		// Validate Total
		String totalText = Mobile.getText(findTestObject('Object Repository/CheckoutPage/OverviewPage/Total'), 10).replaceAll("[^0-9.]", "").trim()

		double total = Double.parseDouble(totalText)

		double expectedTotal = totalPrice + roundedTaxAmount

		if (Math.abs(expectedTotal - total) < 0.01) {

			System.out.println("Validation Passed: Total matches the sum of Item Total and Tax.")
		} else {

			System.out.println("Validation Failed: Total does not match the sum of Item Total and Tax.")
		}

		println 'The data in the checkout overview is the same as the previous data'
	}

	@And("User taps Finish button")
	def Tap_Finish() {

		if(Mobile.waitForElementNotPresent(findTestObject('Object Repository/CheckoutPage/OverviewPage/FINISHButton'), 10)) {

			Mobile.scrollToText('FINISH')
		}

		Mobile.tap(findTestObject('Object Repository/CheckoutPage/OverviewPage/FINISHButton'), 10)

		println 'Successfully tap the Finish button'
	}

	@Then("User verifies the Finish page after completing the checkout process")
	def Verify_Finish_Page() {

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/CompletePage/CHECKOUTCOMPLETEPageTitle'), 10), 'CHECKOUT: COMPLETE!', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/CompletePage/SuccessMessageTitle'), 10), 'THANK YOU FOR YOU ORDER', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/CompletePage/SuccessMessage'), 10), 'Your order has been dispatched, and will arrive just as fast as the pony can get there!', false)

		Mobile.verifyElementExist(findTestObject('Object Repository/CheckoutPage/CompletePage/SuccessIllustration'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CheckoutPage/CompletePage/BACKHOMEButton'), 10), 'BACK HOME', false)

		println 'Successfully finish the checkout process'
	}
}