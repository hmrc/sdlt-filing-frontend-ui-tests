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

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the PreliminaryBeforeYouStart page is shown")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)

      When("the user starts the preliminary questions")
      PreliminaryBeforeYouStart.saveAndContinue()
      Then("the PreliminaryWhoIsMakingThePurchase page is shown")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)

      When("the user selects Company as the type of purchaser")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.company)
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()
      Then("the PreliminaryPurchaserName page is shown")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitleCompany)

      When("the user provides the company name")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.companyName),
        PreliminaryPurchaserName.companyNameInput
      )
      PreliminaryPurchaserName.clickSubmitButton()
      Then("the PreliminaryPropertyAddress page is shown")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)

      When("the user enters the property address manually")
      PreliminaryPropertyAddress.clickAddressManually()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the ConfirmPropertyAddress page is shown")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)

      When("the user confirms the property address")
      PreliminaryPropertyAddress.clickContinueButton()
      Then("the TransactionType page is shown")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)

      When("the user selects Conveyance/transfer with lease involvement as the transaction type")
      TransactionType.radioButton(TransactionType.conveyance)
      TransactionType.saveAndContinue()
      Then("the PreliminaryCheckYourAnswers page is shown")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user submits the preliminary questions")
      PreliminaryCheckYourAnswers.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user opens the vendor questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")
      Then("the VendorOverview page is shown")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user adds a new vendor")
      VendorOverview.radioButton(VendorOverview.yes)
      VendorOverview.saveAndContinue()
      Then("the VendorBeforeYouStart page is shown")
      VendorBeforeYouStart.verifyPageTitle(VendorBeforeYouStart.pageTitle)

      When("the user starts the vendor questions")
      VendorBeforeYouStart.saveAndContinue()
      Then("the WhoIsTheVendor page is shown")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)

      When("the user selects Company as the vendor type")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.company)
      WhoIsTheVendor.saveAndContinue()
      Then("the VendorName page is shown")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitleCompany)

      When("the user provides the company name")
      VendorName.input(
        By.id(VendorName.companyName),
        VendorName.companyNameInput
      )
      VendorName.saveAndContinue()
      Then("the ConfirmVendorsAddress page is shown")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitle)

      When("the user confirms to enter the vendor address manually")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.no)
      ConfirmVendorsAddress.saveAndContinue()
      Then("the VendorPropertyAddress page is shown")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.pageTitle)

      When("the user enters the vendor address manually")
      VendorPropertyAddress.clickAddressManually()
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleBusiness)
      VendorPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the ConfirmVendorPropertyAddress page is shown")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleBusiness)

      When("the user confirms the vendor address")
      VendorPropertyAddress.clickContinueButton()
      Then("the VendorCheckYourAnswers page is shown")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
    }
  }
}
