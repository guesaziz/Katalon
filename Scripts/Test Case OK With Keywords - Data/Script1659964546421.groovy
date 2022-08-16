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

product = CustomKeywords.'myWeather.keywordsBase.Verifier_Temperature'()

CustomKeywords.'myWeather.keywordsBase.Choisir_deux_Produits'(product)

CustomKeywords.'myWeather.keywordsBase.Assert_les_bonnes_Produits'()

CustomKeywords.'myWeather.KeywordsBase_data.Saisir_les_données_Paiement'(number_Card, expire_Card)

WebUI.verifyElementText(findTestObject('Object Repository/Page_Confirmation/h2_PAYMENT SUCCESS'), 'PAYMENT SUCCESS')

WebUI.closeBrowser()

if (true) {
}

