package myWeather

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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class keywordsBase {

	@Keyword
	def Connexion() {

		// Ouvrir le navigateur
		WebUI.openBrowser('')

		// Naviguer vers la page web
		WebUI.navigateToUrl('https://weathershopper.pythonanywhere.com/')

		WebUI.maximizeWindow()
	}

	@Keyword
	def Verifier_Temperature() {

		Integer temp
		List product
		temp = (WebUI.getText(findTestObject('Object Repository/Page_Current Temperature/Temperature')).split()[0]).toInteger()

		if (temp < 19) {
			println('La temperature est inférieur à 19')
			WebUI.click(findTestObject('Object Repository/Page_Current Temperature/button_Buy moisturizers'))
			product = ['almond', 'aloe']
		} else if (temp > 34) {
			println('La temperature est supérieur à 34')
			WebUI.click(findTestObject('Object Repository/Page_Current Temperature/button_Buy sunscreens'))
			product = ['spf-50', 'spf-30']
		} else {
			println('La température est entre 19 et  34')
			product=[]
		}

		return product
	}

	@Keyword
	Ajouter_Min_Produit(String product){

		def xpath_product = "//p[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + product + "')]/following-sibling::p"
		println(xpath_product)

		// Créer un objet test dynamique qui pointe vers le produit le moins cher de product[0] et cliquer dessus

		def products = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/All_products_1'),
				'xpath', 'equals', xpath_product, true)

		def list_products = WebUI.findWebElements(products, 10)

		def prices_products = []

		def x

		for (int i = 0; i < list_products.size; i++) {
			x = ((list_products[i]).getText().split()[-1]).toInteger()

			prices_products.add(x)
		}

		// Trier les listes de prix
		prices_products.sort()

		def products_min = "//button[contains(@onclick, ${prices_products[0].toString()})]"

		println(products_min)

		// Créer un objet test dynamique qui pointe vers le produit le moins cher et cliquer dessus
		def add = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/button_Add'),
				'xpath', 'equals', products_min, true)

		WebUI.click(add)

	}

	@Keyword
	def Choisir_deux_Produits(List product) {

		Ajouter_Min_Produit(product[0])
		Ajouter_Min_Produit(product[1])

		WebUI.click(findTestObject('Object Repository/Page_The Best Moisturizers in the World/span_1 item(s)'))

	}

	@Keyword
	def Assert_les_bonnes_Produits() {

		def prix_ligne1 = WebUI.getText(findTestObject('Object Repository/Page_Cart Items/Prix_ligne_1')).toInteger()

		def prix_ligne2 = WebUI.getText(findTestObject('Object Repository/Page_Cart Items/Prix_ligne_2')).toInteger()

		def total = (WebUI.getText(findTestObject('Object Repository/Page_Cart Items/p_Total Rupees 353')).split()[-1]).toInteger()

		WebUI.verifyEqual(prix_ligne1 + prix_ligne2, total)
	}

	@Keyword
	def Saisir_les_données_Paiement() {

		WebUI.click(findTestObject('Object Repository/Page_Cart Items/span_Pay with Card'))

		WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_email'), 'teste@test.com')



		My_sendKeys_by_Key('Object Repository/Page_Cart Items/input_Payer 353,00INR_card_number','5555555555554444')

		My_sendKeys_by_Key("Object Repository/Page_Cart Items/input_Payer 353,00INR_cc-exp","1225")



		WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_cc-csc'), '123')

		def CodePosatVisible = WebUI.verifyElementNotVisible(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_zip'),FailureHandling.OPTIONAL)
		print("le zip n'est pas visible?")
		println(CodePosatVisible)


		if (!CodePosatVisible) {
			WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_zip'), '65001')
		}


		WebUI.click(findTestObject('Object Repository/Page_Cart Items/span_Payer 353,00INR'))

	}

	/**
	 * Construct a Katalon-compatible TestObject in memory.
	 * @param css (String) The CSS selector used to find the target element.
	 * @return (TestObject) The constructed TestObject.
	 */
	@Keyword
	static TestObject makeTO(String css) {
		TestObject to = new TestObject()
		to.addProperty("css", ConditionType.EQUALS, css)
		return to
	}

	@Keyword
	def My_sendKeys_by_Key(String testObjId, String myText) {
		for(int i=0; i < myText.size();i++)
			WebUI.sendKeys(findTestObject(testObjId), myText[i])
	}
}
