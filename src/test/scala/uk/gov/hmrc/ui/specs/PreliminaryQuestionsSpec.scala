/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.Preliminary.*
import uk.gov.hmrc.ui.pages.AuthWizard
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class PreliminaryQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Preliminary Questions") {
    Scenario(
      "Complete the Preliminary Questions user journey as a Company with no return id stub data",
      PreliminaryJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Before You Start page")
      PreliminaryBeforeYouStartPage.verifyPageTitle(PreliminaryBeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      PreliminaryBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchasePage.verifyPageTitle(PreliminaryWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'A Company' radio button")
      PreliminaryWhoIsMakingThePurchasePage.radioButton(PreliminaryWhoIsMakingThePurchasePage.company)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Name page")
      PreliminaryPurchaserNamePage.verifyPageTitle(PreliminaryPurchaserNamePage.pageTitleCompany)
      When("the user inputs their company name")
      PreliminaryPurchaserNamePage.input(
        By.id(PreliminaryPurchaserNamePage.companyName),
        PreliminaryPurchaserNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserNamePage.clickSubmitButton()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.editPageTitle)
      PreliminaryPropertyAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Transaction Type page")
      TransactionTypePage.verifyPageTitle(TransactionTypePage.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      TransactionTypePage.radioButton(TransactionTypePage.conveyance)
      And("clicks the Save and continue button")
      TransactionTypePage.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser type")
      PreliminaryCheckYourAnswersPage.clickPurchaserTypeChange()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchasePage.verifyPageTitle(PreliminaryWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PreliminaryWhoIsMakingThePurchasePage.radioButton(PreliminaryWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Transaction type")
      PreliminaryCheckYourAnswersPage.clickTransactionTypeChange()
      Then("the user is navigated to the Transaction Type page")
      TransactionTypePage.verifyPageTitle(TransactionTypePage.pageTitle)
      When("the user selects the 'L - Grant of lease' radio button")
      TransactionTypePage.radioButton(TransactionTypePage.grantOfLease)
      And("clicks the Save and continue button")
      TransactionTypePage.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
    }

    Scenario(
      "Complete the Preliminary Questions user journey as an Individual with no return id stub data",
      PreliminaryJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Before You Start page")
      PreliminaryBeforeYouStartPage.verifyPageTitle(PreliminaryBeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      PreliminaryBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchasePage.verifyPageTitle(PreliminaryWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PreliminaryWhoIsMakingThePurchasePage.radioButton(PreliminaryWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Name page")
      PreliminaryPurchaserNamePage.verifyPageTitle(PreliminaryPurchaserNamePage.pageTitle)
      When("the user inputs the purchaser's surname")
      PreliminaryPurchaserNamePage.input(
        By.id(PreliminaryPurchaserNamePage.purchasersSurname),
        PreliminaryPurchaserNamePage.purchasersSurnameInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserNamePage.clickSubmitButton()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.editPageTitle)
      PreliminaryPropertyAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Transaction Type page")
      TransactionTypePage.verifyPageTitle(TransactionTypePage.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      TransactionTypePage.radioButton(TransactionTypePage.conveyance)
      And("clicks the Save and continue button")
      TransactionTypePage.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser name")
      PreliminaryCheckYourAnswersPage.clickPurchaserNameChange()
      Then("the user is navigated to the Purchasers Name page")
      PreliminaryPurchaserNamePage.verifyPageTitle(PreliminaryPurchaserNamePage.pageTitle)
      When("the user inputs the purchaser's surname")
      PreliminaryPurchaserNamePage.input(
        By.id(PreliminaryPurchaserNamePage.purchasersSurname),
        PreliminaryPurchaserNamePage.purchasersSurnameCYAInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserNamePage.clickSubmitButton()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Property address")
      PreliminaryCheckYourAnswersPage.clickPropertyAddressChange()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.editPageTitle)
      PreliminaryPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddressPage.verifyPageTitle(PreliminaryPropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswersPage.verifyPageTitle(PreliminaryCheckYourAnswersPage.pageTitle)
    }
  }
}
