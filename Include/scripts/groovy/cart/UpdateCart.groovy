package cart
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



class UpdateCart {
	@When("User removes product from cart")
	def Update_Cart() {
		
		Mobile.scrollToText(GlobalVariable.productNames[0])
		
		Mobile.delay(1)
		
		Mobile.tap(findTestObject('Object Repository/CartPage/REMOVEButton'), 10)
		
		println 'successfully removed the product from the cart'
	}
	
	@Then("User verifies cart after update")
	def Verify_Updated_Cart() {
		
		int counts = Integer.parseInt(GlobalVariable.counts.toString()) - 1
		
		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/NumberofItemsintheCart'), 10), counts.toString(), false)
		
		int verifiedIndex = 1 // because productNames[0] was removed

		// Get initial cart items
		List<WebElement> cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))

		while (verifiedIndex <= counts) {
			for (int i = 0; i < cartItems.size() && verifiedIndex <= counts; i++) {
				WebElement item = cartItems[i]

				// Read data from UI
				String name = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[1]")).getText()
				String desc = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[2]")).getText()
				String price = item.findElement(By.xpath(".//android.view.ViewGroup[4]/android.widget.TextView[1]")).getText()

				// Match with GlobalVariable (starting from index 1)
				Mobile.verifyMatch(name, GlobalVariable.productNames[verifiedIndex], false)
				Mobile.verifyMatch(desc, GlobalVariable.productDesc[verifiedIndex], false)
				Mobile.verifyMatch(price, GlobalVariable.productPrices[verifiedIndex], false)

				verifiedIndex++
			}

			// Scroll only if there are still unverified items left
			if (verifiedIndex <= counts) {
				Mobile.swipe(400, 1450, 400, 500)
				Mobile.delay(1)

				// Refresh the list after scroll
				cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))
			}
		}
		
		println 'Successfully updated cart'
	}
}