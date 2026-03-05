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
      PreliminaryCheckYourAnswers.saveAndContinue()

      Then("the user is navigated to the Return Task List page")
      ReturnTaskListPage.verifyPageTitle(ReturnTaskListPage.pageTitle)
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Overview page")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverview.radioButton(VendorOverview.yes)
      And("clicks the Continue button")
      VendorOverview.saveAndContinue()
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStart.verifyPageTitle(VendorBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)
      When("the user selects the 'A Company' radio button")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.company)
      And("clicks the Save and continue button")
      WhoIsTheVendor.saveAndContinue()
      Then("the user is navigated to the Vendor Name page")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitleCompany)
      When("the user inputs their company name")
      VendorName.input(
        By.id(VendorName.companyName),
        VendorName.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorName.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitle)
      When("the user selects the 'No' radio button")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.no)
      And("clicks the Save and continue button")
      ConfirmVendorsAddress.saveAndContinue()
      Then("the user is navigated to the Vendor Property Address page")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddress.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleBusiness)
      VendorPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Vendor Property Address page to 'Review and confirm' the address")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
    }
  }
}
