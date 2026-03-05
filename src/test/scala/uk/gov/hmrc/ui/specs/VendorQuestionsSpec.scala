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
      "Complete the Vendor Questions user journey as a Company",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Overview page")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor")
      VendorOverview.clickRemoveVendor()
      Then("the user is navigated to the Remove Vendor page")
      RemoveVendor.verifyPageTitle(RemoveVendor.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemoveVendor.radioButton(RemoveVendor.yes)
      And("clicks the Save and continue button")
      RemoveVendor.saveAndContinue()
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
      When("the user clicks the Confirm address button")
      VendorPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Vendor type")
      VendorCheckYourAnswers.clickVendorTypeChange()
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)
      When("the user selects the 'An Individual' radio button")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.individual)
      And("clicks the Save and continue button")
      WhoIsTheVendor.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Vendor Name")
      VendorCheckYourAnswers.clickVendorNameChange()
      Then("the user is navigated to Vendor Name page")
      VendorName.verifyPageTitle(VendorName.pageTitle)
      When("the user updates their surname")
      VendorName.vendorFullNameInput()
      And("clicks the Save and continue button")
      VendorName.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Vendor address")
      VendorCheckYourAnswers.clickVendorAddressChange()
      Then("the user is navigated to the Vendor Address page")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.PageTitleIndividual)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddress.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleIndividual)
      VendorPropertyAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Property Address page to 'Review and confirm' the address")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleIndividual)
      And("clicks the Confirm address button")
      VendorPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
      When("the user clicks the Save and continue button")
      VendorCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Vendor Overview page")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions user journey as an Individual",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
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
      When("the user selects the 'An Individual' radio button")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.individual)
      And("clicks the Save and continue button")
      WhoIsTheVendor.saveAndContinue()
      Then("the user is navigated to the Vendor Name page")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitle)
      When("the user inputs their first name, middle name, and surname")
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
      And("clicks the Save and continue button")
      VendorName.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitleIndividual)
      When("the user selects the 'Yes' radio button")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.yes)
      And("clicks the Save and continue button")
      ConfirmVendorsAddress.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)
    }
  }
}
