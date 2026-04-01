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
import uk.gov.hmrc.ui.pages.{AuthWizard, ReturnTaskList}
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
      "Complete the Preliminary Questions journey as a Company",
      PreliminaryJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)

      Then("the Preliminary Before You Start page is displayed")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)

      When("the user starts the preliminary questions journey")
      PreliminaryBeforeYouStart.saveAndContinue()

      Then("the Who Is Making The Purchase page is displayed")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)

      When("the user selects Company as the type of purchaser")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.company)
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()

      Then("the Purchaser Name page is displayed")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitleCompany)

      When("the user enters the company name")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.companyName),
        PreliminaryPurchaserName.companyNameInput
      )
      PreliminaryPurchaserName.clickSubmitButton()

      Then("the Find Property Address page is displayed")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)

      When("the user chooses to enter the property address manually")
      PreliminaryPropertyAddress.clickAddressManually()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")

      Then("the Confirm Property Address page is displayed")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)

      When("the user confirms the property address")
      PreliminaryPropertyAddress.clickContinueButton()

      Then("the Transaction Type page is displayed")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)

      When("the user selects Conveyance/transfer with lease involvement as the transaction type")
      TransactionType.radioButton(TransactionType.conveyance)
      TransactionType.saveAndContinue()

      Then("the Check Your Answers page is displayed")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user updates the Purchaser type to Individual")
      PreliminaryCheckYourAnswers.clickPurchaserTypeChange()
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.individual)
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()

      Then("the Purchaser type is updated on the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user updates the Transaction type to Grant of lease")
      PreliminaryCheckYourAnswers.clickTransactionTypeChange()
      TransactionType.verifyPageTitle(TransactionType.pageTitle)
      TransactionType.radioButton(TransactionType.grantOfLease)
      TransactionType.saveAndContinue()

      Then("the Transaction Type is updated on the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user submits the preliminary questions")
      PreliminaryCheckYourAnswers.saveAndContinue()

      Then("the Return Task List page is displayed")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)
    }

    Scenario(
      "Complete the Preliminary Questions journey as an Individual",
      PreliminaryJourney
    ) {

      Given("the user logs in through the Authority Wizard page and the Who Is Making The Purchase page is displayed")
      AuthWizard.login(HASDIRECT, Organisation)

      Then("the Preliminary Before You Start page is displayed")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)

      When("the user starts the preliminary questions journey")
      PreliminaryBeforeYouStart.saveAndContinue()

      Then("the Who Is Making The Purchase page is displayed")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)

      When("the user selects Individual as the type of purchaser")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.individual)
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()

      Then("the Purchaser Name page is displayed")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitle)

      When("the user enters the purchaser's surname")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.purchasersSurname),
        PreliminaryPurchaserName.purchasersSurnameInput
      )
      PreliminaryPurchaserName.clickSubmitButton()

      Then("the Find Property Address page is displayed")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)

      When("the user chooses to enter the property address manually")
      PreliminaryPropertyAddress.clickAddressManually()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")

      Then("the Confirm Property Address page is displayed")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)

      When("the user confirms the property address")
      PreliminaryPropertyAddress.clickContinueButton()

      Then("the Transaction Type page is displayed")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)

      When("the user selects Conveyance/transfer with lease involvement as the transaction type")
      TransactionType.radioButton(TransactionType.conveyance)
      TransactionType.saveAndContinue()

      Then("the Check Your Answers page is displayed")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user updates the Purchaser name")
      PreliminaryCheckYourAnswers.clickPurchaserNameChange()
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitle)
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.purchasersSurname),
        PreliminaryPurchaserName.purchasersSurnameCYAInput
      )
      PreliminaryPurchaserName.clickSubmitButton()

      Then("the Purchaser name is updated on the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user updates the Property Address")
      PreliminaryCheckYourAnswers.clickPropertyAddressChange()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)
      PreliminaryPropertyAddress.clickAddressManually()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)
      PreliminaryPropertyAddress.clickContinueButton()

      Then("the Property Address is updated on the Check Your Answers page")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)
    }
  }
}
