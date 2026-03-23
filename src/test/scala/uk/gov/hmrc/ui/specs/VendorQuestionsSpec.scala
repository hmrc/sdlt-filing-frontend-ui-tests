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
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class VendorQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Vendor Questions") {

    Scenario(
      "Complete the Vendor Questions journey as a Company",
      VendorJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))

      When("the user opens the vendor questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")

      Then("the Vendor Overview page is displayed")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user removes an existing vendor")
      VendorOverview.clickRemoveVendor()

      Then("the Remove Vendor page is displayed")
      RemoveVendor.verifyPageTitle(RemoveVendor.pageTitle)

      When("the user confirms the vendor removal")
      RemoveVendor.radioButton(RemoveVendor.yes)
      RemoveVendor.saveAndContinue()

      Then("the Vendor Overview page is displayed")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user adds a vendor")
      VendorOverview.radioButton(VendorOverview.yes)
      VendorOverview.saveAndContinue()

      Then("the Vendor Before You Start page is displayed")
      VendorBeforeYouStart.verifyPageTitle(VendorBeforeYouStart.pageTitle)

      When("the user starts the vendor questions journey")
      VendorBeforeYouStart.saveAndContinue()

      Then("the Who Is The Vendor page is displayed")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)

      When("the user selects Company as the vendor type")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.company)
      WhoIsTheVendor.saveAndContinue()

      Then("the Vendor Name page is displayed")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitleCompany)

      When("the user enters the company name")
      VendorName.input(
        By.id(VendorName.companyName),
        VendorName.companyNameInput
      )
      VendorName.saveAndContinue()

      Then("the Confirm Vendors Address page is displayed")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitle)

      When("the user chooses to enter the address manually")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.no)
      ConfirmVendorsAddress.saveAndContinue()

      Then("the Vendor Property Address page is displayed")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.pageTitle)

      When("the user enters the vendor address manually")
      VendorPropertyAddress.clickAddressManually()
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleBusiness)
      VendorPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")

      Then("the Confirm Vendor Property Address page is displayed")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleBusiness)

      When("the user confirms the address")
      VendorPropertyAddress.clickContinueButton()

      Then("the Vendor Check Your Answers page is displayed")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)

      When("the user updates the vendor type to Individual")
      VendorCheckYourAnswers.clickVendorTypeChange()
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)
      WhoIsTheVendor.radioButton(WhoIsTheVendor.individual)
      WhoIsTheVendor.saveAndContinue()

      Then("the Vendor type is updated on the Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)

      When("the user updates the vendor name")
      VendorCheckYourAnswers.clickVendorNameChange()

      Then("the Vendor Name page is displayed")
      VendorName.verifyPageTitle(VendorName.pageTitle)

      When("the user enters the vendor name")
      VendorName.vendorFullNameInput()
      VendorName.saveAndContinue()

      Then("the Vendor name is updated on the Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)

      When("the user updates the vendor address")
      VendorCheckYourAnswers.clickVendorAddressChange()
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.PageTitleIndividual)
      VendorPropertyAddress.clickAddressManually()
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleIndividual)
      VendorPropertyAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleIndividual)
      VendorPropertyAddress.clickContinueButton()

      Then("the Vendor address is updated on the Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)

      When("the user submits the vendor questions")
      VendorCheckYourAnswers.saveAndContinue()

      Then("the Vendor Overview page is displayed")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions journey as an Individual",
      VendorJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))

      When("the user opens the vendor questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")

      Then("the Vendor Overview page is displayed")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user adds a vendor")
      VendorOverview.radioButton(VendorOverview.yes)
      VendorOverview.saveAndContinue()

      Then("the Before You Start page is displayed")
      VendorBeforeYouStart.verifyPageTitle(VendorBeforeYouStart.pageTitle)

      When("the user starts the vendor questions journey")
      VendorBeforeYouStart.saveAndContinue()

      Then("the Who Is The Vendor page is displayed")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)

      When("the user selects Individual as the vendor type")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.individual)
      WhoIsTheVendor.saveAndContinue()

      Then("the Vendor Name page is displayed")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitle)

      When("the user enters the vendor name")
      VendorName.input(
        By.id(VendorName.forename),
        VendorName.forenameInput
      )
      VendorName.input(
        By.id(VendorName.middlename),
        VendorName.middlenameInput
      )
      VendorName.input(
        By.id(VendorName.surname),
        VendorName.surnameInput
      )
      VendorName.saveAndContinue()

      Then("the Confirm Vendors Address page is displayed")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitleIndividual)

      When("the user confirms the address")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.yes)
      ConfirmVendorsAddress.saveAndContinue()

      Then("the Check Your Answers page is displayed")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
    }
  }
}
