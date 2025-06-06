package randomdata

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.util.Random

public class RandomData {

	@Keyword
	def generateRandomUsername() {
		Random rand = new Random()
		int randomNum = rand.nextInt(1000)

		return "user_" + randomNum
	}

	@Keyword
	def generateRandomPassword() {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		String lower = "abcdefghijklmnopqrstuvwxyz"
		String digits = "0123456789"
		String symbols = "!@#\$%^&*()-_+=<>?"
		Random random = new Random()

		String allChars = upper + lower + digits + symbols

		int passwordLength = 12
		StringBuilder password = new StringBuilder()

		for (int i = 0; i < passwordLength; i++) {
			int index = random.nextInt(allChars.length())
			password.append(allChars.charAt(index))
		}

		return password.toString()
	}
	
	@Keyword
	def String generateRandomFirstName() {
		String[] firstNames = [
			"Aiden", "Bella", "Caleb", "Diana", "Ethan",
			"Fiona", "Gavin", "Hannah", "Ian", "Jasmine",
			"Kevin", "Luna", "Mason", "Nina", "Owen",
			"Paula", "Quinn", "Riley", "Sophia", "Tyler"
		]
		int index = new Random().nextInt(firstNames.size())
		return firstNames[index]
	}
	
	@Keyword
	def String generateRandomLastName() {
		String[] lastNames = [
			"Anderson", "Brown", "Carter", "Davis", "Evans",
			"Foster", "Garcia", "Hughes", "Irwin", "Johnson",
			"Keller", "Lewis", "Morris", "Nelson", "Owens",
			"Parker", "Quinn", "Roberts", "Smith", "Turner"
		]
		int index = new Random().nextInt(lastNames.size())
		return lastNames[index]
	}
	
	@Keyword
	def String generateRandomPostalCode() {
		int postalCode = 10000 + new Random().nextInt(90000)
		return postalCode.toString()
	}
}