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

import scroll.*


class Cart {
	@When("User taps the add to cart button of {int} products from the product list or details")
	def Add_to_Cart_Products(int counts) {

		GlobalVariable.counts = counts

		int addedCount = 0

		while (addedCount < counts) {

			// Get currently visible product elements
			List<WebElement> productsnames = MobileDriverFactory.getDriver().findElements(By.xpath("//android.widget.TextView[@content-desc='test-Item title']"))
			List<WebElement> productsprices = MobileDriverFactory.getDriver().findElements(By.xpath("//android.widget.TextView[@content-desc='test-Price']"))

			for (int i = 0; i < productsnames.size(); i++) {
				if (addedCount >= counts) {
					break
				}

				String productName = productsnames.get(i).getText()
				GlobalVariable.productNames[addedCount] = productName

				String productPrice = productsprices.get(i).getText()
				GlobalVariable.productPrices[addedCount] = productPrice

				// Tap Add to Cart from list
				if (addedCount < counts - 1) {
					Mobile.tap(findTestObject('Object Repository/ProductsPage/ADDTOCARTButton'), 10)
				}

				// Open product details
				productsnames.get(i).click()

				Mobile.verifyMatch(Mobile.getText(findTestObject("Object Repository/ProductDetailPage/ProductName"), 10), GlobalVariable.productNames[addedCount], false)

				String productDesc = Mobile.getText(findTestObject('Object Repository/ProductDetailPage/ProductDesc'), 10)
				GlobalVariable.productDesc[addedCount] = productDesc

				if (addedCount == counts - 1) {
					// Last product: scroll to ADD TO CART and add from detail
					Mobile.scrollToText('ADD TO CART')

					Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/ProductDetailPage/ProductPrice'), 10), GlobalVariable.productPrices[addedCount], false)

					Mobile.tap(findTestObject('Object Repository/ProductDetailPage/ADDTOCARTButton'), 10)
				}else{
					// Earlier products: scroll to REMOVE and just verify
					Mobile.scrollToText('REMOVE')

					Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/ProductDetailPage/ProductPrice'), 10), GlobalVariable.productPrices[addedCount], false)
				}

				// Back to product list
				Mobile.tap(findTestObject('Object Repository/ProductDetailPage/BACKTOPRODUCTSButton'), 10)

				Mobile.delay(1)

				addedCount++
			}

			if (addedCount < counts) {
				// Scroll to load more items
				ScrollTo scrollto = new ScrollTo()
				scrollto.swipeaction(400, 1350, 400, 500)
				Mobile.delay(2)
			}
		}


		println('Successfully added ' + counts + ' products to the cart.');
		println('Product Names: ' + GlobalVariable.productNames);
		println('Product Prices: ' + GlobalVariable.productPrices);

		println 'Successfully added product to cart'
	}


	@And("The number of products in the cart will increase")
	def Verify_Product_Number_in_The_Cart() {

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/NumberofItemsintheCart'), 10), GlobalVariable.counts.toString(), false)

		println 'The number of digits on the basket icon has successfully increased'
	}

	@And("User taps on the cart button in the header")
	def Tap_Cart_Icon_in_The_Header() {

		Mobile.tap(findTestObject('Object Repository/Header/CartButton'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenuButton'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/ApplicationIcon'), 10)

		Mobile.verifyElementExist(findTestObject('Object Repository/Header/BurgerMenuButton'), 10)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CartPage/YOURCARTPageTitle'), 10), 'YOUR CART', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CartPage/QTYTableTitle'), 10), 'QTY', false)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/CartPage/DESCRIPTIONTableTitle'), 10), 'DESCRIPTION', false)

		println 'Successfully opened the cart page'
	}

	@And("User verifies the product in the cart")
	def Verify_Cart_Page() {

		int totalVerified = 0
		int totalToVerify = GlobalVariable.counts
		int scrollAttempt = 0
		int maxScroll = 10 // prevent infinite scroll just in case

		while (totalVerified < totalToVerify && scrollAttempt < maxScroll) {
			List<WebElement> cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))

			for (int i = 0; i < cartItems.size(); i++) {
				if (totalVerified >= totalToVerify) {
					break
				}

				WebElement item = cartItems[i]

				// Get product info from inside this item block
				String name = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[1]")).getText()
				String price = item.findElement(By.xpath(".//android.view.ViewGroup[4]/android.widget.TextView[1]")).getText()
				String desc = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[2]")).getText()

				// Verifying with stored values
				Mobile.verifyMatch(name, GlobalVariable.productNames[totalVerified], false)
				Mobile.verifyMatch(price, GlobalVariable.productPrices[totalVerified], false)
				Mobile.verifyMatch(desc, GlobalVariable.productDesc[totalVerified], false)

				totalVerified++
			}

			if (totalVerified < totalToVerify) {
				Mobile.delay(1)
				ScrollTo scrollto = new ScrollTo()
				scrollto.swipeaction(400, 1820, 400, 500)
				Mobile.delay(3)
				scrollAttempt++
			}
		}

		if (totalVerified == totalToVerify) {
			println "Verified ${totalVerified} items in the cart successfully."
		} else {
			KeywordUtil.markFailed("Expected ${totalToVerify}, but only verified ${totalVerified}.")
		}
	}

	@When("User removes product from cart")
	def Update_Cart() {

		Mobile.scrollToText(GlobalVariable.productNames[0])

		Mobile.delay(1)

		Mobile.tap(findTestObject('Object Repository/CartPage/REMOVEButton'), 10)

		Mobile.delay(1)

		Mobile.swipe(193, 736, 28, 736)

		Mobile.delay(1)

		Mobile.tap(findTestObject('Object Repository/CartPage/TrashIconButton'), 10)

		Mobile.delay(1)

		println 'successfully removed the product from the cart'
	}

	@Then("User verifies cart after update")
	def Verify_Updated_Cart() {

		// Verify the number of products in the cart decreased by 2
		int counts = Integer.parseInt(GlobalVariable.counts.toString()) - 2

		Mobile.verifyMatch(Mobile.getText(findTestObject('Object Repository/Header/NumberofItemsintheCart'), 10), counts.toString(), false)

		int totalVerified = 0
		int totalToVerify = counts
		int scrollAttempt = 0
		int maxScroll = 10

		while (totalVerified < totalToVerify && scrollAttempt < maxScroll) {
			List<WebElement> cartItems = MobileDriverFactory.getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc='test-Item']"))

			for (int i = 0; i < cartItems.size(); i++) {
				if (totalVerified >= totalToVerify) {
					break
				}

				WebElement item = cartItems[i]

				String name = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[1]")).getText()
				String price = item.findElement(By.xpath(".//android.view.ViewGroup[4]/android.widget.TextView[1]")).getText()
				String desc = item.findElement(By.xpath(".//android.view.ViewGroup[2]/android.widget.TextView[2]")).getText()

				// Compare with index +2 since first product was removed
				int dataIndex = totalVerified + 2

				Mobile.verifyMatch(name, GlobalVariable.productNames[dataIndex], false)
				Mobile.verifyMatch(price, GlobalVariable.productPrices[dataIndex], false)
				Mobile.verifyMatch(desc, GlobalVariable.productDesc[dataIndex], false)

				totalVerified++
			}

			if (totalVerified < totalToVerify) {
				Mobile.delay(1)
				ScrollTo scrollto = new ScrollTo()
				scrollto.swipeaction(400, 1800, 400, 500)
				Mobile.delay(3)
				scrollAttempt++
			}
		}

		if (totalVerified == totalToVerify) {
			println "Verified ${totalVerified} items. Cart successfully updated."
		} else {
			KeywordUtil.markFailed("Expected ${totalToVerify} items, but only verified ${totalVerified}.")
		}
	}
}