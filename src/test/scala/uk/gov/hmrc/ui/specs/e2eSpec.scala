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
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Preliminary.*
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class e2eSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend end to end") {
    Scenario("Complete the end to end flow of the Filing Journey", e2eJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Before You Start page")
      PreliminaryBeforeYouStartPage.verifyPageTitle(PreliminaryBeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      PreliminaryBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      WhoIsMakingThePurchasePage.verifyPageTitle(WhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'A Company' radio button")
      WhoIsMakingThePurchasePage.radioButton(WhoIsMakingThePurchasePage.company)
      And("clicks the Save and continue button")
      WhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Purchasers Name page")
      PurchasersNamePage.verifyPageTitle(PurchasersNamePage.pageTitleCompany)
      When("the user inputs their company name")
      PurchasersNamePage.input(
        By.id(PurchasersNamePage.companyName),
        PurchasersNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      PurchasersNamePage.clickSubmitButton()
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
      PreliminaryCheckYourAnswersPage.saveAndContinue()

      Then("the user is navigated to the Return Task List page")
      ReturnTaskListPage.verifyPageTitle(ReturnTaskListPage.pageTitle)
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverviewPage.radioButton(VendorOverviewPage.yes)
      And("clicks the Continue button")
      VendorOverviewPage.saveAndContinue()
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendorPage.verifyPageTitle(WhoIsTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      WhoIsTheVendorPage.radioButton(WhoIsTheVendorPage.company)
      And("clicks the Save and continue button")
      WhoIsTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor Name page")
      WhoIsTheVendorPage.verifyPageTitle(VendorNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorNamePage.input(
        By.id(VendorNamePage.companyName),
        VendorNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorNamePage.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitle)
      When("the user selects the 'No' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.no)
      And("clicks the Save and continue button")
      ConfirmVendorsAddressPage.saveAndContinue()
      Then("the user is navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Vendor Property Address page to 'Review and confirm' the address")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
    }
  }
}
