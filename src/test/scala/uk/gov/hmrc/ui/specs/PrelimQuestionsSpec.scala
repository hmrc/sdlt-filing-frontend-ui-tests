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
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.PrelimQuestions.{AboutTheTransactionPage, BeforeYouStartPage, CheckYourAnswersPage, IndividualOrCompanyPage, PropertyAddressPage, PurchasersNamePage}
import uk.gov.hmrc.ui.pages.AuthWizard
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class PrelimQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Prelim Questions") {
    Scenario("Complete the Prelim Questions user journey as a Company with no return id stub data") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Before You Start page")
      BeforeYouStartPage.verifyPageTitle(BeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      BeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the Individual or Company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.company)
      And("clicks the Save and continue button")
      IndividualOrCompanyPage.saveAndContinue()
      Then("the user should be navigated to the Purchasers Name page")
      PurchasersNamePage.verifyPageTitle(PurchasersNamePage.pageTitleCompany)
      When("the user inputs their company name")
      PurchasersNamePage.input(
        By.id(PurchasersNamePage.companyName),
        PurchasersNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      PurchasersNamePage.clickSubmitButton()
      Then("the user should be navigated to the Property Address page")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.editPageTitle)
      PropertyAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the About the Transaction page")
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      AboutTheTransactionPage.radioButton(AboutTheTransactionPage.conveyance)
      And("clicks the Save and continue button")
      AboutTheTransactionPage.saveAndContinue()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser type")
      CheckYourAnswersPage.clickPurchaserTypeChange()
      Then("the user should be navigated to the Individual or Company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.individual)
      And("clicks the Save and continue button")
      IndividualOrCompanyPage.saveAndContinue()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Transaction type")
      CheckYourAnswersPage.clickTransactionTypeChange()
      Then("the user should be navigated to the About the Transaction page")
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      When("the user selects the 'L - Grant of lease' radio button")
      AboutTheTransactionPage.radioButton(AboutTheTransactionPage.grantOfLease)
      And("clicks the Save and continue button")
      AboutTheTransactionPage.saveAndContinue()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
    }

    Scenario("Complete the Prelim Questions user journey as an Individual with no return id stub data") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Before You Start page")
      BeforeYouStartPage.verifyPageTitle(BeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      BeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the Individual or Company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.individual)
      And("clicks the Save and continue button")
      IndividualOrCompanyPage.saveAndContinue()
      Then("the user should be navigated to the Purchasers Name page")
      PurchasersNamePage.verifyPageTitle(PurchasersNamePage.pageTitle)
      When("the user inputs the purchaser's surname")
      PurchasersNamePage.input(
        By.id(PurchasersNamePage.purchasersSurname),
        PurchasersNamePage.purchasersSurnameInput
      )
      And("clicks the Save and continue button")
      PurchasersNamePage.clickSubmitButton()
      Then("the user should be navigated to the Property Address page")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.editPageTitle)
      PropertyAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the About the Transaction page")
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      AboutTheTransactionPage.radioButton(AboutTheTransactionPage.conveyance)
      And("clicks the Save and continue button")
      AboutTheTransactionPage.saveAndContinue()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchasers name")
      CheckYourAnswersPage.clickPurchaserNameChange()
      Then("the user should be navigated to the Purchasers Name page")
      PurchasersNamePage.verifyPageTitle(PurchasersNamePage.pageTitle)
      When("the user inputs the purchaser's surname")
      PurchasersNamePage.input(
        By.id(PurchasersNamePage.purchasersSurname),
        PurchasersNamePage.purchasersSurnameCYAInput
      )
      And("clicks the Save and continue button")
      PurchasersNamePage.clickSubmitButton()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Property address")
      CheckYourAnswersPage.clickPropertyAddressChange()
      Then("the user should be navigated to the Property Address page")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.editPageTitle)
      PropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
    }
  }
}
