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
      "Complete the Preliminary Questions user journey as a Company",
      PreliminaryJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Before You Start page")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)
      And("clicks the Save and continue button")
      PreliminaryBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'A Company' radio button")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.company)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the Purchaser Name page")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitleCompany)
      When("the user inputs their company name")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.companyName),
        PreliminaryPurchaserName.companyNameInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserName.clickSubmitButton()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddress.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Transaction Type page")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      TransactionType.radioButton(TransactionType.conveyance)
      And("clicks the Save and continue button")
      TransactionType.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Purchaser type")
      PreliminaryCheckYourAnswers.clickPurchaserTypeChange()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Transaction type")
      PreliminaryCheckYourAnswers.clickTransactionTypeChange()
      Then("the user is navigated to the Transaction Type page")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)
      When("the user selects the 'L - Grant of lease' radio button")
      TransactionType.radioButton(TransactionType.grantOfLease)
      And("clicks the Save and continue button")
      TransactionType.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
    }

    Scenario(
      "Complete the Preliminary Questions user journey as an Individual",
      PreliminaryJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Before You Start page")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)
      And("clicks the Save and continue button")
      PreliminaryBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the Purchaser Name page")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitle)
      When("the user inputs the purchaser's surname")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.purchasersSurname),
        PreliminaryPurchaserName.purchasersSurnameInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserName.clickSubmitButton()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddress.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Transaction Type page")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      TransactionType.radioButton(TransactionType.conveyance)
      And("clicks the Save and continue button")
      TransactionType.saveAndContinue()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Purchaser name")
      PreliminaryCheckYourAnswers.clickPurchaserNameChange()
      Then("the user is navigated to the Purchasers Name page")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitle)
      When("the user inputs the purchaser's surname")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.purchasersSurname),
        PreliminaryPurchaserName.purchasersSurnameCYAInput
      )
      And("clicks the Save and continue button")
      PreliminaryPurchaserName.clickSubmitButton()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Property address")
      PreliminaryCheckYourAnswers.clickPropertyAddressChange()
      Then("the user is navigated to the Property Address page")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PreliminaryPropertyAddress.clickAddressManually()
      And("enters their address manually")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PreliminaryPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
    }
  }
}
