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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement

// Stocker la temperature dans la variable temp
CustomKeywords.'myWeather.keywordsBase.Connexion'()
testObjet_temp = findTestObject('Object Repository/Page_Current Temperature/Temperature')
text_temp1= WebUI.getText(testObjet_temp)
temp = (text_temp1.split()[0]).toInteger()




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
}

xpath_product_1 = (('//p[contains(translate(., \'ABCDEFGHIJKLMNOPQRSTUVWXYZ\', \'abcdefghijklmnopqrstuvwxyz\'), \'' + (product[
0])) + '\')]/following-sibling::p')

println(xpath_product_1)

xpath_product_2 = (('//p[contains(translate(., \'ABCDEFGHIJKLMNOPQRSTUVWXYZ\', \'abcdefghijklmnopqrstuvwxyz\'), \'' + (product[
1])) + '\')]/following-sibling::p')

println(xpath_product_2)

// Créer un objet test dynamique qui pointe vers le produit le moins cher de product[0] et cliquer dessus
products_1 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/All_products_1'), 
    'xpath', 'equals', xpath_product_1, true)

list_products_1 = WebUI.findWebElements(products_1, 10)

prices_products_1 = []

// Créer un objet test dynamique qui pointe vers le produit le moins cher de product[1] et cliquer dessus
products_2 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/All_products_2'), 
    'xpath', 'equals', xpath_product_2, true)

list_products_2 = WebUI.findWebElements(products_2, 10)

prices_products_2 = []

for (int i = 0; i < list_products_1.size; i++) {
    x = ((list_products_1[i]).getText().split()[-1]).toInteger()

    prices_products_1.add(x)
}

for (int i = 0; i < list_products_2.size; i++) {
    x = ((list_products_2[i]).getText().split()[-1]).toInteger()

    prices_products_2.add(x)
}

// Trier les listes de prix
prices_products_1.sort()

prices_products_2.sort()

products_1_min = '//button[contains(@onclick, '+prices_products_1[0].toString()+')]'

println(products_1_min)

products_2_min = '//button[contains(@onclick, '+prices_products_1[1].toString()+')]'

println(products_2_min)

// Créer un objet test dynamique qui pointe vers le produit le moins cher et cliquer dessus
add_1 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/button_Add'), 
    'xpath', 'equals', products_1_min, true)

WebUI.click(add_1)

// Créer un objet test dynamique qui pointe vers le produit le plus cher et cliquer dessus
add_2 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_The Best Moisturizers in the World/button_Add'), 
    'xpath', 'equals', products_2_min, true)

WebUI.click(add_2)

WebUI.click(findTestObject('Object Repository/Page_The Best Moisturizers in the World/span_1 item(s)'))

prix_ligne1 = WebUI.getText(findTestObject('Object Repository/Page_Cart Items/Prix_ligne_1')).toInteger()

prix_ligne2 = WebUI.getText(findTestObject('Object Repository/Page_Cart Items/Prix_ligne_2')).toInteger()

total = (WebUI.getText(findTestObject('Object Repository/Page_Cart Items/p_Total Rupees 353')).split()[-1]).toInteger()

WebUI.verifyEqual(prix_ligne1 + prix_ligne2, total)

WebUI.click(findTestObject('Object Repository/Page_Cart Items/span_Pay with Card'))

WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_email'), 'teste@test.com')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_card_number'), '5555')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_card_number'), '5555')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_card_number'), '5555 ')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_card_number'), '4444')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_cc-exp'), '12')

WebUI.sendKeys(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_cc-exp'), '22')

WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_cc-csc'), '123')


def CodePosatPresent = WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_zip'),10)
print("le zip est present?")
println(CodePosatPresent)
def CodePosatnotVisible = WebUI.verifyElementNotVisible(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_zip'),FailureHandling.OPTIONAL)
print("le zip n'est pas visible?")
println(CodePosatnotVisible)


if (!CodePosatnotVisible) {
			WebUI.setText(findTestObject('Object Repository/Page_Cart Items/input_Payer 353,00INR_zip'), '65001')
			}
		
WebUI.click(findTestObject('Object Repository/Page_Cart Items/span_Payer 353,00INR'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Confirmation/h2_PAYMENT SUCCESS'), 'PAYMENT SUCCESS')

WebUI.closeBrowser()

